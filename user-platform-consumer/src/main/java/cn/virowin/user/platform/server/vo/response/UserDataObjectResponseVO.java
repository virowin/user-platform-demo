package cn.virowin.user.platform.server.vo.response;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author virowin
 * @date 2022/8/22 16:02
 */
@Data
public class UserDataObjectResponseVO {
    private long id;
    private String nickname;
    private String email;
    private Timestamp dateline;
}
