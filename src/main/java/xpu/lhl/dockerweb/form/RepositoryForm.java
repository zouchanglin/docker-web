package xpu.lhl.dockerweb.form;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
public class RepositoryForm {
    private String serverAddress;
    private String userName;
    private String password;
    private String email;
    private String namespace;
}
