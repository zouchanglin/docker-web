package xpu.lhl.dockerweb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ContainerInfo {
    @Id
    private String containerId;
    private String containerName;
}
