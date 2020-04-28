package xpu.lhl.dockerweb.vo;

import lombok.Data;

@Data
public class ImageVO {
    private String repository;
    private String tag;
    private String imageId;
    private String created;
    private String size;
}
