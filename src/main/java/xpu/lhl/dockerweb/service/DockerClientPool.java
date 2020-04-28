package xpu.lhl.dockerweb.service;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;

import java.net.URI;

public class DockerClientPool {
    final static DockerClient docker = DefaultDockerClient.builder()
            .uri(URI.create("http://192.168.0.106:2375"))
            .build();

    public static DockerClient getClient(){
        return docker;
    }
}