package xpu.lhl.dockerweb.service;

import xpu.lhl.dockerweb.vo.DockerVersionVO;

public interface DockerVersionService {
    /**
     * 获取Docker引擎相关信息 {@link DockerVersionVO}, {@link com.spotify.docker.client.messages.Version}
     * @return Docker引擎相关信息
     */
    DockerVersionVO getDockerVersion();
}
