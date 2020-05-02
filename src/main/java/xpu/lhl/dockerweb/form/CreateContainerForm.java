package xpu.lhl.dockerweb.form;

import lombok.Data;

@Data
public class CreateContainerForm {
    private Integer hostPort;
    private Integer containerPort;
    private String hostPath;
    private String containerPath;
    private Integer diskSize; // G
    private float memorySize; // G
    private Integer cpuShare;// 0 1 2 3 4
    private String envK;
    private String envV;
    private String image;
    private String containerName;
}
