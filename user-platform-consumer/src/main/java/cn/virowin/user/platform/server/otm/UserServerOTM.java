package cn.virowin.user.platform.server.otm;

import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.server.vo.request.UserHttpRequestVO;
import cn.virowin.user.platform.server.vo.request.UserUpdateHttpRequestVO;
import cn.virowin.user.platform.server.vo.response.UserDataObjectResponseVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author virowin
 * @date 2022/8/22 13:00
 */
@Mapper(componentModel = "spring")
public interface UserServerOTM {
    UserInfoRequestVO userHttpRequestVO2UserRequestVO(UserHttpRequestVO userHttpRequestVO);

    UserInfoRequestVO userUpdateHttpRequestVO2UserRequestVO(UserUpdateHttpRequestVO userHttpRequestVO);

    UserDataObjectResponseVO userDO2UserResponse(UserInfoResponseVO vo);

    List<UserDataObjectResponseVO> userDO2UserResponse(List<UserInfoResponseVO> vo);
}
