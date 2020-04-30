package xpu.lhl.dockerweb.service;

import xpu.lhl.dockerweb.vo.ContainerVO;

import java.util.List;

public interface ContainerService {
    /**
     * 获取所有状态的容器
     * @return 容器信息列表
     */
    List<ContainerVO> getAllContainer();

    /**
     * 根据状态获取容器列表
     * @param state 容器状态
     * @return 容器列表
     */
    List<ContainerVO> getAllContainerByState(String state);

    /**
     * 启动对应Id的容器
     * @param containerId 容器Id
     * @return 是否启动成功
     */
    boolean startContainer(String containerId);

    boolean pauseContainer(String containerId);

    boolean stopContainer(String containerId);

    boolean unpauseContainer(String containerId);
}
