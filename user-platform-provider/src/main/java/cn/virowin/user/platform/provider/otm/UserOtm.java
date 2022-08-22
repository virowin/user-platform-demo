package cn.virowin.user.platform.provider.otm;

import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.provider.dao.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author virowin
 * @date 2022/8/22 12:16
 */
@Mapper(componentModel = "spring")
public interface UserOtm {
    UserOtm INSTANCE = Mappers.getMapper(UserOtm.class);

    UserDO userRequestVO2UserDO(UserInfoRequestVO userInfoRequestVO);

    UserInfoResponseVO userDO2UserInfoResponseVO(UserDO userDO);

    List<UserInfoResponseVO> userDO2UserInfoResponseVO(List<UserDO> userDO);
}
