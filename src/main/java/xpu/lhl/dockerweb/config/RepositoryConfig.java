package xpu.lhl.dockerweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "repository")
public class RepositoryConfig {
    private String serverAddress;
    private String userName;
    private String password;
    private String email;
    private String namespace;
}
