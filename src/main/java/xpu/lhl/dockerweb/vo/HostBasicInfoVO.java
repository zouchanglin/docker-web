package xpu.lhl.dockerweb.vo;

import lombok.Data;

/**
 * 宿主机信息视图对象、以及管理系统所处的Java环境
 */
@Data
public class HostBasicInfoVO {
    private String osName;
    private String osArch;
    private String osVersion;
    private Integer cpuNumber;
    private String userName;
    private String homePath;

    private String javaVersion;
    private String javaVendor;
    private String javaVendorUrl;
    private String vmSpecificationVersion;
    private String vmSpecificationVendor;
    private String vmSpecificationName;
    private String vmVersion;
    private String specificationVersion;
    private String vmName;
}
