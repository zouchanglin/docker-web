package xpu.lhl.dockerweb.vo;

import lombok.Data;

/**
 * 镜像搜索结果视图对象
 */
@Data
public class SearchImageVO {
    private String name;
    private String description;
    private Integer stars;
    private String official;
    private String automated;
}
