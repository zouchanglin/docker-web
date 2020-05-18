package xpu.lhl.dockerweb.vo;

import lombok.Data;

/**
 * 容器信息视图对象
 */
@Data
public class ContainerVO {
    private String containerId;
    private String image;
    private String command;
    private String created;
    private String status;
    private String state;
    private String ports;
    private String names;
}
