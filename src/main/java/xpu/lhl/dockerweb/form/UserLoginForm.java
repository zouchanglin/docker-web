package xpu.lhl.dockerweb.form;

import lombok.Data;

@Data
public class UserLoginForm {
    private String username;

    private String password;

    private String keep;
}
