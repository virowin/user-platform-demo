package cn.virowin.user.platform.facade.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author virowin
 * @date 2022/8/22 23:02
 */
@Data
public class UserUpdateRequestVO implements Serializable {
    private String email;
    private String password;
    private String nickname;
    private String oldPassword;
}

