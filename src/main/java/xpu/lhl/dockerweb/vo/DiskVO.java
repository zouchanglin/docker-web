package xpu.lhl.dockerweb.vo;

import lombok.Data;
import xpu.lhl.dockerweb.utils.MyDateFormat;

import java.util.Date;

/**
 * 磁盘信息视图对象
 */
@Data
public class DiskVO {
    private String free;
    private String used;
}
