package com.puppy.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RegisterDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String ip;
}
