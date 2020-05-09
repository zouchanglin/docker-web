package xpu.lhl.dockerweb.form;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
public class DockerIPFrom {
    private String dockerIp;
    private Integer dockerPort;
}