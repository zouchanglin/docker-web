package xpu.lhl.dockerweb.form;

import lombok.Data;

@Data
public class BuildImageForm {
    private String repository;
    private String tag;
}
