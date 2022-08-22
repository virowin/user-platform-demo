package cn.virowin.user.platform.provider.otm;

import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.provider.dao.dataobject.UserDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T01:41:41+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
@Component
public class UserOtmImpl implements UserOtm {

    @Override
    public UserDO userRequestVO2UserDO(UserInfoRequestVO userInfoRequestVO) {
        if ( userInfoRequestVO == null ) {
            return null;
        }

        UserDO userDO = new UserDO();

        userDO.setEmail( userInfoRequestVO.getEmail() );
        userDO.setPassword( userInfoRequestVO.getPassword() );
        userDO.setNickname( userInfoRequestVO.getNickname() );

        return userDO;
    }

    @Override
    public UserInfoResponseVO userDO2UserInfoResponseVO(UserDO userDO) {
        if ( userDO == null ) {
            return null;
        }

        UserInfoResponseVO userInfoResponseVO = new UserInfoResponseVO();

        userInfoResponseVO.setId( userDO.getId() );
        userInfoResponseVO.setEmail( userDO.getEmail() );
        userInfoResponseVO.setPassword( userDO.getPassword() );
        userInfoResponseVO.setNickname( userDO.getNickname() );
        userInfoResponseVO.setDateline( userDO.getDateline() );
        userInfoResponseVO.setStatus( userDO.getStatus() );

        return userInfoResponseVO;
    }

    @Override
    public List<UserInfoResponseVO> userDO2UserInfoResponseVO(List<UserDO> userDO) {
        if ( userDO == null ) {
            return null;
        }

        List<UserInfoResponseVO> list = new ArrayList<UserInfoResponseVO>( userDO.size() );
        for ( UserDO userDO1 : userDO ) {
            list.add( userDO2UserInfoResponseVO( userDO1 ) );
        }

        return list;
    }
}
