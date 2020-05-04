package xpu.lhl.dockerweb.service.impl;

import com.google.common.collect.ImmutableList;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xpu.lhl.dockerweb.enums.CPUSharesEnum;
import xpu.lhl.dockerweb.form.CreateContainerForm;
import xpu.lhl.dockerweb.service.ContainerService;
import xpu.lhl.dockerweb.service.DockerOperation;
import xpu.lhl.dockerweb.utils.MyDateFormat;
import xpu.lhl.dockerweb.vo.ContainerVO;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContainerServiceImpl implements ContainerService {
    private final DockerOperation dockerOperation;

    public ContainerServiceImpl(DockerOperation dockerOperation) {
        this.dockerOperation = dockerOperation;
    }

    @Override
    public List<ContainerVO> getAllContainer() {
        DockerClient dockerClient = dockerOperation.getClient();
        try {
            List<Container> containerList = dockerClient.listContainers(DockerClient.ListContainersParam.allContainers());
            return convertContainerVOList(containerList);
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<ContainerVO> getAllContainerByState(String state) {
        DockerClient dockerClient = dockerOperation.getClient();
        List<Container> containerList;
        try {
            switch (state){
                case "create":
                    containerList = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusCreated());
                    break;
                case "running":
                    containerList = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusRunning());
                    break;
                case "paused":
                    containerList = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusPaused());
                    break;
                case "restarting":
                    containerList = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusRestarting());
                    break;
                case "exited":
                    containerList = dockerClient.listContainers(DockerClient.ListContainersParam.withStatusExited());
                    break;
                default:
                    return new ArrayList<>();
            }

            return convertContainerVOList(containerList);
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean startContainer(String containerId) {
        DockerClient dockerClient = dockerOperation.getClient();
        try {
            dockerClient.startContainer(containerId);
            return true;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean pauseContainer(String containerId) {
        DockerClient dockerClient = dockerOperation.getClient();
        try {
            dockerClient.pauseContainer(containerId);
            return true;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean stopContainer(String containerId) {
        DockerClient dockerClient = dockerOperation.getClient();
        try {
            dockerClient.stopContainer(containerId, 0); // 0s后停止(立马停止)
            return true;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unpauseContainer(String containerId) {
        DockerClient dockerClient = dockerOperation.getClient();
        try {
            dockerClient.unpauseContainer(containerId);
            return true;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeContainer(String containerId){
        DockerClient client = dockerOperation.getClient();
        try {
            client.removeContainer(containerId);
            return true;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String createContainer(CreateContainerForm createContainerForm) {
        DockerClient client = dockerOperation.getClient();

        Integer containerPort = createContainerForm.getContainerPort();
        Integer hostPort = createContainerForm.getHostPort();
        Map<String, List<PortBinding>> portBindings = new HashMap<>();
        portBindings.put(String.valueOf(containerPort), Collections.singletonList(PortBinding.of("0.0.0.0", String.valueOf(hostPort))));

        HostConfig hostConfig = HostConfig.builder()
                .portBindings(portBindings)
                .cpuShares(CPUSharesEnum.A.getValue())
                .memory((long)(createContainerForm.getMemorySize() * 1024 * 1024 * 1024))
                .privileged(true)
                .build();
        // 判断是否存在容器数据卷
        String containerPath = createContainerForm.getContainerPath();
        String hostPath = createContainerForm.getHostPath();
        if(!StringUtils.isEmpty(containerPath) && !StringUtils.isEmpty(hostPath)){
            hostConfig = hostConfig.toBuilder().appendBinds(hostPath + ":" + containerPath).build();
        }

        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image(createContainerForm.getImage())
                .exposedPorts(String.valueOf(containerPort))
                .build();
        // 判断是否存在环境变量
        String envK = createContainerForm.getEnvK();
        String envV = createContainerForm.getEnvV();
        if(!StringUtils.isEmpty(envK) && !StringUtils.isEmpty(envV)){
            containerConfig = containerConfig.toBuilder().env(envK + "=" + envV).build();
        }



        try {
            ContainerCreation containerCreation = client.createContainer(containerConfig, createContainerForm.getContainerName());
            return containerCreation.id();
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void packageContainer(String containerId, String name) {
        DockerClient dockerClient = dockerOperation.getClient();
        //dockerClient.commitContainer();
    }

    @Override
    public boolean restartContainer(String containerId) {
        try {
            dockerOperation.getClient().restartContainer(containerId);
            return true;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<ContainerVO> convertContainerVOList(List<Container> containerList){
        return containerList.stream().map(this::convertContainerVO).collect(Collectors.toList());
    }

    private ContainerVO convertContainerVO(Container container){
        ContainerVO containerVO = new ContainerVO();
        containerVO.setContainerId(container.id().substring(0, 12));
        containerVO.setImage(container.image());
        containerVO.setCommand(container.command());
        containerVO.setCreated(MyDateFormat.dateFormatForImage.format(new Date(container.created() * 1000)));
        containerVO.setPorts(convertPortMapping(Objects.requireNonNull(container.ports())));
        containerVO.setNames(Objects.requireNonNull(container.names()).get(0));
        containerVO.setStatus(container.status());
        containerVO.setState(container.state());
        return containerVO;
    }

    private String convertPortMapping(ImmutableList<Container.PortMapping> portMappings){
        StringBuilder portDescription = new StringBuilder();
        for(Container.PortMapping mapping: portMappings){
            portDescription.append(String.format("%s:%s->%s/%s", mapping.ip(), mapping.publicPort(),
                    mapping.privatePort(), mapping.type())).append(" ");
        }
        return portDescription.toString();
    }
}