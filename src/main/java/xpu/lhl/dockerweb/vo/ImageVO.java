package xpu.lhl.dockerweb.vo;

import lombok.Data;

/**
 * 镜像基本信息视图对象，详情请见{@link ImageDetailVO}
 */
@Data
public class ImageVO {
    private String repository;
    private String tag;
    private String imageId;
    private String created;
    private String size;
}
