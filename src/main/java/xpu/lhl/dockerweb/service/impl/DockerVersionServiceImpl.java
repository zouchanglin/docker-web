package xpu.lhl.dockerweb.service.impl;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpu.lhl.dockerweb.service.DockerOperation;
import xpu.lhl.dockerweb.service.DockerVersionService;
import xpu.lhl.dockerweb.vo.DockerVersionVO;


@Slf4j
@Service
public class DockerVersionServiceImpl implements DockerVersionService {
    private final DockerOperation dockerOperation;

    @Autowired
    public DockerVersionServiceImpl(DockerOperation dockerOperation) {
        this.dockerOperation = dockerOperation;
    }

    @Override
    public DockerVersionVO getDockerVersion() {
        DockerClient client = dockerOperation.getClient();
        try {
            Version version = client.version();
            DockerVersionVO versionVO = new DockerVersionVO();

            // BeanUtils.copyProperties(version, versionVO);
            versionVO.setOs(version.os());
            versionVO.setArch(version.arch());
            versionVO.setApiVersion(version.apiVersion());
            versionVO.setBuildTime(version.buildTime());
            versionVO.setGitCommit(version.gitCommit());
            versionVO.setKernelVersion(version.kernelVersion());
            versionVO.setVersion(version.version());
            versionVO.setGoVersion(version.goVersion());
            log.info("【DockerVersionService】{}", versionVO);
            return versionVO;
        } catch (DockerException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}