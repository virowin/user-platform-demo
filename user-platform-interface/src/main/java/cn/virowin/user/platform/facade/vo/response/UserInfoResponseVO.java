package cn.virowin.user.platform.facade.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author virowin
 * @date 2022/8/22 12:01
 */
@Data
public class UserInfoResponseVO implements Serializable {
    private long id;
    private String email;
    private String password;
    private String nickname;
    private Timestamp dateline;
    private byte status;
}
