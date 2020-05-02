package xpu.lhl.dockerweb.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.enums.CPUSharesEnum;
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
    public void createContainer() {
        DockerClient client = dockerOperation.getClient();

        java.util.Map<String, List<PortBinding>> portBindings = new HashMap<>();
        portBindings.put("3306", Collections.singletonList(PortBinding.of("0.0.0.0", "3307")));
        HashMap<String, String> storageMForm = new HashMap<>();
        storageMForm.put("size", "120G");
        HostConfig hostConfig = HostConfig.builder()
                .portBindings(portBindings)
                .privileged(true)
                .appendBinds("/root/tim:/root/lhl")
                .cpuShares(CPUSharesEnum.A.getValue())
                .memory(1024 * 1024 * 1024L)
                .storageOpt(storageMForm)
                .build();
        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image("mysql:5.7")
                .env("MYSQL_ROOT_PASSWORD=123456")
                .exposedPorts("3306")
                .build();
        try {
            client.createContainer(containerConfig, "mysql5.7.v1");
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
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