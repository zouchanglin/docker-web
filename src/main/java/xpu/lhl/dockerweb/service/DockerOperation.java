package xpu.lhl.dockerweb.service;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.RegistryAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import xpu.lhl.dockerweb.config.DockerConfig;
import xpu.lhl.dockerweb.config.RepositoryConfig;

import java.net.URI;

@Slf4j
@Component
public class DockerOperation {
    private final RepositoryConfig repositoryConfig;
    private final DockerConfig dockerConfig;

    public DockerOperation(RepositoryConfig repositoryConfig, DockerConfig dockerConfig) {
        this.repositoryConfig = repositoryConfig;
        this.dockerConfig = dockerConfig;
    }

    @Bean
    public DockerClient getClient(){
        String url = String.format("http://%s:%s", dockerConfig.getDockerIp(), dockerConfig.getDockerPort());
        return DefaultDockerClient.builder().uri(URI.create(url)).build();
    }

    @Bean
    public RegistryAuth getRegistryAuth(){
        return RegistryAuth.builder()
                .email(repositoryConfig.getEmail())
                .username(repositoryConfig.getUserName())
                .password(repositoryConfig.getPassword())
                .serverAddress(repositoryConfig.getServerAddress())
                .build();
    }
}