package xpu.lhl.dockerweb.service.impl;

import com.google.common.collect.ImmutableList;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xpu.lhl.dockerweb.config.RepositoryConfig;
import xpu.lhl.dockerweb.enums.CPUSharesEnum;
import xpu.lhl.dockerweb.form.ContainerCommitForm;
import xpu.lhl.dockerweb.form.CreateContainerForm;
import xpu.lhl.dockerweb.repository.ContainerConfigRepository;
import xpu.lhl.dockerweb.service.ContainerService;
import xpu.lhl.dockerweb.service.DockerOperation;
import xpu.lhl.dockerweb.utils.MyDateFormat;
import xpu.lhl.dockerweb.vo.ContainerVO;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContainerServiceImpl implements ContainerService {
    private final ContainerConfigRepository configRepository;
    private final DockerOperation dockerOperation;
    private final RepositoryConfig repositoryConfig;

    public ContainerServiceImpl(DockerOperation dockerOperation,
                                ContainerConfigRepository configRepository,
                                RepositoryConfig repositoryConfig) {
        this.dockerOperation = dockerOperation;
        this.configRepository = configRepository;
        this.repositoryConfig = repositoryConfig;
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
            configRepository.deleteById(containerId);
        } catch (DockerException | InterruptedException e) {
            
        }
        return true;
    }

    @Override
    public String createContainer(CreateContainerForm createContainerForm) {
        DockerClient client = dockerOperation.getClient();
        ContainerConfig containerConfig = generate(createContainerForm);
        try {
            ContainerCreation containerCreation = client.createContainer(containerConfig, createContainerForm.getContainerName());
            xpu.lhl.dockerweb.entity.ContainerConfig config = new xpu.lhl.dockerweb.entity.ContainerConfig();
            BeanUtils.copyProperties(createContainerForm, config);
            config.setContainerId(containerCreation.id().substring(0, 12));
            xpu.lhl.dockerweb.entity.ContainerConfig saveResult = configRepository.save(config);
            log.info("【ContainerServiceImpl】 createContainer saveResult={}", saveResult);
            return containerCreation.id();
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String commitContainer(ContainerCommitForm commitForm) {
        DockerClient dockerClient = dockerOperation.getClient();
        Optional<xpu.lhl.dockerweb.entity.ContainerConfig> containerConfigOpt = configRepository.findById(commitForm.getContainerId());
        if(containerConfigOpt.isPresent()){
            xpu.lhl.dockerweb.entity.ContainerConfig myContainerConfig = containerConfigOpt.get();
            CreateContainerForm createContainerForm = new CreateContainerForm();
            BeanUtils.copyProperties(myContainerConfig, createContainerForm);
            ContainerConfig containerConfig = generate(createContainerForm);
            try {
                ContainerCreation commitContainer = dockerClient.commitContainer(commitForm.getContainerId(),
                        commitForm.getRepo(),
                        commitForm.getTag(),
                        containerConfig,
                        commitForm.getComment(),
                        commitForm.getAuthor());
                return commitContainer.id();
            } catch (DockerException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private ContainerConfig generate(CreateContainerForm createContainerForm) {
        Integer containerPort = createContainerForm.getContainerPort();
        Integer hostPort = createContainerForm.getHostPort();

        Map<String, List<PortBinding>> portBindings = null;
        if(!(containerPort == null || hostPort == null)){
            portBindings = new HashMap<>();
            portBindings.put(String.valueOf(containerPort),
                    Collections.singletonList(PortBinding.of("0.0.0.0", String.valueOf(hostPort))));
        }

        HostConfig hostConfig = HostConfig.builder()
                .cpuShares(CPUSharesEnum.A.getValue())
                //.memory((long)(createContainerForm.getMemorySize() * 1024 * 1024 * 1024))
                .privileged(true)
                .build();

        // 判断是否存在端口映射
        if(portBindings != null) {
            hostConfig = hostConfig.toBuilder().portBindings(portBindings).build();
        }

        // 判断是否存在容器数据卷
        String containerPath = createContainerForm.getContainerPath();
        String hostPath = createContainerForm.getHostPath();
        if(!StringUtils.isEmpty(containerPath) && !StringUtils.isEmpty(hostPath)){
            hostConfig = hostConfig.toBuilder().appendBinds(hostPath + ":" + containerPath).build();
        }

        // 这是个是容器的配置参数
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

        return containerConfig;
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

    @Override
    public void killContainer(String containerId) {
        DockerClient dockerClient = dockerOperation.getClient();
        try {
            dockerClient.stopContainer(containerId, 0);
            dockerClient.removeContainer(containerId);
            configRepository.deleteById(containerId);
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getRepositoryInfo(String containerId) {
        HashMap<String, String> retHashMap = new HashMap<>();
        Optional<xpu.lhl.dockerweb.entity.ContainerConfig> configOptional = configRepository.findById(containerId);
        String containerName = null;
        if(configOptional.isPresent()){
            xpu.lhl.dockerweb.entity.ContainerConfig containerConfig = configOptional.get();
            containerName = containerConfig.getContainerName();
        }else{
            DockerClient dockerClient = dockerOperation.getClient();
            try {
                ContainerInfo containerInfo = dockerClient.inspectContainer(containerId);
                containerName = containerInfo.name();
            } catch (DockerException | InterruptedException e) {
                log.error("【Use docker client find container by Id error!】");
            }
        }

        String serverAddress = repositoryConfig.getServerAddress();
        String namespace = repositoryConfig.getNamespace();
        retHashMap.put("containerId", containerId);
        retHashMap.put("containerName", containerName);
        retHashMap.put("serverAddress", serverAddress);
        retHashMap.put("namespace", namespace);
        retHashMap.put("author", repositoryConfig.getUserName() + ":" + repositoryConfig.getEmail());
        return retHashMap;
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