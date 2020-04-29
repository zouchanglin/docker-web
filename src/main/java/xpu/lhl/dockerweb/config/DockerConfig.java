package xpu.lhl.dockerweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "docker")
public class DockerConfig {
    private String dockerIp;
    private Integer dockerPort;
}