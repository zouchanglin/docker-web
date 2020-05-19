package xpu.lhl.dockerweb.enums;

import lombok.Getter;

@Getter
public enum ApplicationNameEnum {
    GITLAB("gitlab", "twang2218/gitlab-ce-zh:10.5"),
    MAVEN("nexus", "sonatype/nexus3"),
    REPOSITORY("repository", "registry"),
    REPOSITORY_WEB_UI("repository_web_ui", "konradkleine/docker-registry-frontend:v2"),
    SPRING_INITIALIZR("spring_initializr", "pollyduan/start_spring_io"),
    NGINX_SERVER("nginx", "nginx"),
    RABBITMQ("rabbitmq", "rabbitmq:management")
    ;

    ApplicationNameEnum(String appName, String appImageName) {
        this.appName = appName;
        this.appImageName = appImageName;
    }

    private final String appName;
    private final String appImageName;
    // private final Integer appPort;
}
