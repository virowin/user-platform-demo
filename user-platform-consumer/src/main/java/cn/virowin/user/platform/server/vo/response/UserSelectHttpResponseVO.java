package cn.virowin.user.platform.server.vo.response;

import lombok.Data;

import java.util.List;

/**
 * @author virowin
 * @date 2022/8/22 16:06
 */
@Data
public class UserSelectHttpResponseVO {
    private List<UserDataObjectResponseVO> list;
    private long count;
}
