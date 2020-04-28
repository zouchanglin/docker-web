package xpu.lhl.dockerweb.vo;

import lombok.Data;

@Data
public class ImageDetailVO{
    private String architecture;
    private String os;
    private String virtualSize;
}