package xpu.lhl.dockerweb.service;

import xpu.lhl.dockerweb.form.CreateContainerForm;
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

    /**
     * 暂停容器
     * @param containerId 容器Id
     * @return 操作是否成功
     */
    boolean pauseContainer(String containerId);

    /**
     * 停止容器
     * @param containerId 容器Id
     * @return 操作是否成功
     */
    boolean stopContainer(String containerId);

    /**
     * 解除暂停容器
     * @param containerId 容器Id
     * @return 操作是否成功
     */
    boolean unpauseContainer(String containerId);

    /**
     * 移除容器
     * @param containerId 容器Id
     * @return 操作是否成功
     */
    boolean removeContainer(String containerId);

    /**
     * 创建容器
     */
    String createContainer(CreateContainerForm createContainerForm);

    /**
     * 重启容器
     * @param containerId 容器Id
     * @return 是否重启成功
     */
    boolean restartContainer(String containerId);


    void packageContainer(String containerId, String name);
}
