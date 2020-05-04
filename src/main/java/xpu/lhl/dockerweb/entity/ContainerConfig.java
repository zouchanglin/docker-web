package xpu.lhl.dockerweb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ContainerConfig {
    @Id
    private String containerId;
    private Integer hostPort;
    private Integer containerPort;
    private String hostPath;
    private String containerPath;
    private Integer diskSize; // G
    private float memorySize = 1; // G
    private Integer cpuShare;// 0 1 2 3 4
    private String envK;
    private String envV;
    private String image;
    private String containerName;
}
