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
    /**
     * Docker引擎IP
     */
    private String dockerIp;

    /**
     * Docker引擎Port
     */
    private Integer dockerPort;
}