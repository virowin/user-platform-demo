package cn.virowin.user.platform.provider.dao.mapper;
/*
* CREATE TABLE IF NOT EXISTS `user`(
`id` INT UNSIGNED AUTO_INCREMENT,
`email` CHAR(100) NOT NULL default "" COMMENT "email",
`password` CHAR(64) NOT NULL default "" COMMENT "md5 password",
`nickname` CHAR(64) NOT NULL default "" COMMENT "nickname",
`dateline` DATETIME,
PRIMARY KEY ( `id` ))ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
* */

import cn.virowin.user.platform.provider.dao.dataobject.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author virowin
 * @date 2022/8/20 19:46
 */
public interface UserMapper extends BaseMapper<UserDO> {
}
