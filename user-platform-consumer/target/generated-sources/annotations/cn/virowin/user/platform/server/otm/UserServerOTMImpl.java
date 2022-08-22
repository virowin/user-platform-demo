package cn.virowin.user.platform.server.otm;

import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.server.vo.request.UserHttpRequestVO;
import cn.virowin.user.platform.server.vo.request.UserUpdateHttpRequestVO;
import cn.virowin.user.platform.server.vo.response.UserDataObjectResponseVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T01:41:42+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
@Component
public class UserServerOTMImpl implements UserServerOTM {

    @Override
    public UserInfoRequestVO userHttpRequestVO2UserRequestVO(UserHttpRequestVO userHttpRequestVO) {
        if ( userHttpRequestVO == null ) {
            return null;
        }

        UserInfoRequestVO userInfoRequestVO = new UserInfoRequestVO();

        userInfoRequestVO.setEmail( userHttpRequestVO.getEmail() );
        userInfoRequestVO.setPassword( userHttpRequestVO.getPassword() );
        userInfoRequestVO.setNickname( userHttpRequestVO.getNickname() );

        return userInfoRequestVO;
    }

    @Override
    public UserInfoRequestVO userUpdateHttpRequestVO2UserRequestVO(UserUpdateHttpRequestVO userHttpRequestVO) {
        if ( userHttpRequestVO == null ) {
            return null;
        }

        UserInfoRequestVO userInfoRequestVO = new UserInfoRequestVO();

        userInfoRequestVO.setEmail( userHttpRequestVO.getEmail() );
        userInfoRequestVO.setPassword( userHttpRequestVO.getPassword() );
        userInfoRequestVO.setNickname( userHttpRequestVO.getNickname() );

        return userInfoRequestVO;
    }

    @Override
    public UserDataObjectResponseVO userDO2UserResponse(UserInfoResponseVO vo) {
        if ( vo == null ) {
            return null;
        }

        UserDataObjectResponseVO userDataObjectResponseVO = new UserDataObjectResponseVO();

        userDataObjectResponseVO.setId( vo.getId() );
        userDataObjectResponseVO.setNickname( vo.getNickname() );
        userDataObjectResponseVO.setEmail( vo.getEmail() );
        userDataObjectResponseVO.setDateline( vo.getDateline() );

        return userDataObjectResponseVO;
    }

    @Override
    public List<UserDataObjectResponseVO> userDO2UserResponse(List<UserInfoResponseVO> vo) {
        if ( vo == null ) {
            return null;
        }

        List<UserDataObjectResponseVO> list = new ArrayList<UserDataObjectResponseVO>( vo.size() );
        for ( UserInfoResponseVO userInfoResponseVO : vo ) {
            list.add( userDO2UserResponse( userInfoResponseVO ) );
        }

        return list;
    }
}
