package cn.virowin.user.platform.facade;

import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;

import java.util.List;

/**
 * @author virowin
 * @date 2022/8/22 11:56
 */
public interface UserService {

    public long insertUser(UserInfoRequestVO userDO);

    public boolean updateUser(UserInfoRequestVO userDO);

    /**
     * use ignore to update soft deleted account
     * */
    public boolean updateUser(UserInfoRequestVO requestVO, Boolean ignoreStatus);

    public boolean deleteUser(long id);

    public boolean deleteUser(List<Long> ids);

    /**
     * sometimes we need to hard delete
     * */
    public boolean hardDeleteUser(List<Long> ids);

    public UserInfoResponseVO getUserById(long id);

    public UserInfoResponseVO getUserByEmail(String email);

    /**
     * use ignore to get soft deleted account
     * */
    public UserInfoResponseVO getUserByEmail(String email, boolean ignoreStatus);

    public List<UserInfoResponseVO> selectUser(int limit, int offset);

    public long selectUserCount();

    public boolean checkPassword(String pw, String md5);
}
