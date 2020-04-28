package xpu.lhl.dockerweb.vo;

import lombok.Data;

@Data
public class SearchImageVO {
    private String name;
    private String description;
    private Integer stars;
    private String official;
    private String automated;
}
