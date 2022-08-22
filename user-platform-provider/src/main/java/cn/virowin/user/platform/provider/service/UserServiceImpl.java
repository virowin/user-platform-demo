package cn.virowin.user.platform.provider.service;

import cn.virowin.user.platform.facade.UserService;
import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.provider.common.constant.UserPlatformConstant;
import cn.virowin.user.platform.provider.dao.dataobject.UserDO;
import cn.virowin.user.platform.provider.dao.mapper.UserMapper;
import cn.virowin.user.platform.provider.otm.UserOtm;
import cn.virowin.user.platform.provider.utils.SendMailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author virowin
 * @date 2022/8/20 15:14
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOtm userOtm;

    @Autowired
    private SendMailUtil sendMailUtil;

    @Override
    public long insertUser(UserInfoRequestVO requestVO) {
        UserDO userDO = userOtm.userRequestVO2UserDO(requestVO);
        userDO.setDateline(new Timestamp(System.currentTimeMillis()));
        userDO.setStatus((byte) 0);
        userDO.setPassword(md5Password(userDO.getPassword()));

        userMapper.insert(userDO);
        if (userDO.getId() > 0) {
            // @TODO: 2022/8/22 do not really send, because the smtp is fake
            //sendMailUtil.sendSimpleMail(userDO.getEmail(),
            // "Register Success",
            // String.format("hello %s, welcome to my user-platform!", userDO.getNickname()));
        }
        return userDO.getId();
    }

    @Override
    public boolean updateUser(UserInfoRequestVO requestVO, Boolean ignoreStatus) {
        UserDO userDO = userOtm.userRequestVO2UserDO(requestVO);
        userDO.setDateline(new Timestamp(System.currentTimeMillis()));

        if (userDO.getPassword() != null) {
            userDO.setPassword(md5Password(userDO.getPassword()));
        }
        QueryWrapper wrapper = (new QueryWrapper<UserDO>()).eq("email", userDO.getEmail());

        if (!ignoreStatus) {
            wrapper.eq("status", 0);
        }

        int res = userMapper.update(userDO, wrapper);

        return res > 0;
    }

    @Override
    public boolean updateUser(UserInfoRequestVO requestVO) {
        return updateUser(requestVO, false);
    }

    @Override
    public boolean deleteUser(long id) {
        UserDO userDO = new UserDO();
        userDO.setStatus((byte) 1);
        userDO.setDateline(new Timestamp(System.currentTimeMillis()));
        int res = userMapper.update(userDO, (new QueryWrapper<UserDO>()).eq("id", id).eq("status", 0));
        return res > 0;
    }

    @Override
    public boolean deleteUser(List<Long> ids) {
        UserDO userDO = new UserDO();
        userDO.setStatus((byte) 1);
        userDO.setDateline(new Timestamp(System.currentTimeMillis()));
        int res = userMapper.update(userDO, (new QueryWrapper<UserDO>()).in("id", ids).eq("status", 0));
        return res > 0;
    }

    @Override
    public boolean hardDeleteUser(List<Long> ids) {
        int res = userMapper.delete((new QueryWrapper<UserDO>()).in("id", ids));
        return res > 0;
    }

    @Override
    public UserInfoResponseVO getUserById(long id) {
        UserDO userDO = userMapper.selectOne((new QueryWrapper<UserDO>()).eq("id", id).eq("status", 0));
        return userOtm.userDO2UserInfoResponseVO(userDO);
    }

    @Override
    public UserInfoResponseVO getUserByEmail(String email) {
        return getUserByEmail(email, false);
    }

    @Override
    public UserInfoResponseVO getUserByEmail(String email, boolean ignoreStatus) {
        QueryWrapper queryWrapper = (new QueryWrapper<UserDO>()).eq("email", email);
        if (!ignoreStatus) {
            queryWrapper.eq("status", 0);
        }
        UserDO userDO = userMapper.selectOne(queryWrapper);
        return userOtm.userDO2UserInfoResponseVO(userDO);
    }

    @Override
    public List<UserInfoResponseVO> selectUser(int limit, int offset) {
        List<UserDO> userDO = userMapper.selectList((new QueryWrapper<UserDO>()).eq("status", 0));
        return userOtm.userDO2UserInfoResponseVO(userDO);
    }

    @Override
    public long selectUserCount() {
        long num = userMapper.selectCount((new QueryWrapper<UserDO>()).eq("status", 0));
        return num;
    }

    @Override
    public boolean checkPassword(String pw, String md5) {
        return md5Password(pw).equals(md5);
    }

    private String md5Password(String password) {
        return DigestUtils.md5DigestAsHex(password.concat(UserPlatformConstant.PASSWORD_SALT).getBytes());
    }
}
