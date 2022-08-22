package cn.virowin.user.platform.facade.vo.request;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author virowin
 * @date 2022/8/22 12:00
 */
@Data
public class UserInfoRequestVO implements Serializable {
    private String email;
    private String password;
    private String nickname;
}
