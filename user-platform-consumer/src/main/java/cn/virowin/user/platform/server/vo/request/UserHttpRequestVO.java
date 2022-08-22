package cn.virowin.user.platform.server.vo.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author virowin
 * @date 2022/8/22 15:53
 */
@Data
public class UserHttpRequestVO {
    @NotBlank(message = "email cant be empty")
    @Email(message = "email invalid")
    private String email;
    @NotBlank(message = "password cant be empty")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z.!@#$%^&*].*)(?=.*[0-9.!@#$%^&*].*).{6,32}$",
            message = "password must contain number,letter and special characters (6-32 characters)")
    private String password;
    @NotBlank(message = "nickname cant be empty")
    private String nickname;
}
