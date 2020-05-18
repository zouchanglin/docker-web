package xpu.lhl.dockerweb.vo;

import lombok.Data;

/**
 * 镜像详情视图对象，基本信息请见{@link ImageVO}
 */
@Data
public class ImageDetailVO{
    private String architecture;
    private String os;
    private String virtualSize;
}