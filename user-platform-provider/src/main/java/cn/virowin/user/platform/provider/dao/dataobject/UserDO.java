package cn.virowin.user.platform.provider.dao.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author virowin
 * @date 2022/8/20 19:52
 */
@TableName("user")
@Data
public class UserDO {
    @TableId(type = IdType.AUTO)
    private long id;
    private String email;
    private String password;
    private String nickname;
    private Timestamp dateline;
    private byte status;
}
