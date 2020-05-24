package xpu.lhl.dockerweb.form;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
public class DockerIPForm {
    private String dockerIp;
    private Integer dockerPort;

    private String systemIp;
    private String systemPort;
}