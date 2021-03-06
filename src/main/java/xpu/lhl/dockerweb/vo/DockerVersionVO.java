package xpu.lhl.dockerweb.vo;

import lombok.Data;

/**
 * Docker引擎信息视图对象，由{@link com.spotify.docker.client.messages.Version} 得出
 */
@Data
public class DockerVersionVO {
    private String apiVersion;
    private String arch;
    private String buildTime;
    private String gitCommit;
    private String goVersion;
    private String kernelVersion;
    private String os;
    private String version;
}
