package xpu.lhl.dockerweb.service.impl;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.service.ContainerMonitorService;
import xpu.lhl.dockerweb.service.DockerOperation;

@Service
public class ContainerMonitorServiceImpl implements ContainerMonitorService {
    @Autowired
    private DockerOperation dockerOperation;

    public void fun(){
        DockerClient client = dockerOperation.getClient();
        try {
            ContainerInfo containerInfo = client.inspectContainer("");

        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
