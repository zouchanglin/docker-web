package xpu.lhl.dockerweb.form;

import lombok.Data;

@Data
public class ContainerCommitForm {
    private String containerId;
    private String repo;
    private String tag;
    private String comment;
    private String author;
}
