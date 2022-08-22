package cn.virowin.user.platform.server.controller;

import cn.virowin.user.platform.facade.UserService;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.server.common.exception.BusinessDefaultException;
import cn.virowin.user.platform.server.common.exception.NumValidationException;
import cn.virowin.user.platform.server.common.exception.ParamValidationException;
import cn.virowin.user.platform.server.otm.UserServerOTM;
import cn.virowin.user.platform.server.vo.request.UserHttpRequestVO;
import cn.virowin.user.platform.server.vo.request.UserUpdateHttpRequestVO;
import cn.virowin.user.platform.server.vo.response.UserDefaultHttpResponseVO;
import cn.virowin.user.platform.server.vo.response.UserGetHttpResponseVO;
import cn.virowin.user.platform.server.vo.response.UserRegisterHttpResponseVO;
import cn.virowin.user.platform.server.vo.response.UserSelectHttpResponseVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author virowin
 * @date 2022/8/20 15:37
 */
@Controller("UserServerController")
public class UserController {
    @DubboReference
    private UserService userDubboService;

    @Autowired
    private UserServerOTM userServerOTM;

    @ResponseBody
    @RequestMapping(value = "/user/register")
    public UserRegisterHttpResponseVO register(@Validated UserHttpRequestVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParamValidationException(bindingResult);
        }
        long id = 0;
        UserInfoResponseVO userInfoResponseVO = userDubboService.getUserByEmail(userVO.getEmail(), true);
        //reopen this account instead of insert a row, if it was a deleted email.
        boolean registered = false;
        if (userInfoResponseVO != null && userInfoResponseVO.getId() > 0) {
            if (userInfoResponseVO.getStatus() == 0) {
                throw new BusinessDefaultException("duplicated email");
            }
            registered = true;
        }
        //@todo: Actually,we should use a validate code by using email verification.But we cant use smtp in this demo.
        if (registered) {
            userDubboService.updateUser(userServerOTM.userHttpRequestVO2UserRequestVO(userVO));
            id = userInfoResponseVO.getId();
        } else {
            id = userDubboService.insertUser(userServerOTM.userHttpRequestVO2UserRequestVO(userVO));
        }

        UserRegisterHttpResponseVO userRegisterHttpResponseVO = new UserRegisterHttpResponseVO();
        userRegisterHttpResponseVO.setId(id);

        return userRegisterHttpResponseVO;
    }
    @ResponseBody
    @RequestMapping(value = "/user/update")
    public UserDefaultHttpResponseVO update(@Validated UserUpdateHttpRequestVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParamValidationException(bindingResult);
        }

        UserInfoResponseVO userInfoResponseVO = userDubboService.getUserByEmail(userVO.getEmail());
        if (userInfoResponseVO == null || userInfoResponseVO.getId() <= 0) {
            throw new BusinessDefaultException("this email had not registered");
        }
        if (userDubboService.checkPassword(userVO.getOldPassword(), userInfoResponseVO.getPassword())) {
            throw new BusinessDefaultException("old password not right");
        }

        boolean ret = userDubboService.updateUser(userServerOTM.userUpdateHttpRequestVO2UserRequestVO(userVO));

        UserDefaultHttpResponseVO retVO = new UserDefaultHttpResponseVO();
        retVO.setStatus(0);

        return retVO;
    }

    @ResponseBody
    @RequestMapping(value = "/user/select")
    public UserSelectHttpResponseVO select(@RequestParam(value = "size", required = false, defaultValue = "10") int limit,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        if (page <= 0 || limit <= 0) {
            throw new NumValidationException("`page` and `limit` need null or <= 0");
        }
        int offset = limit * (page - 1);
        List<UserInfoResponseVO> list = userDubboService.selectUser(limit, offset);
        long num = userDubboService.selectUserCount();

        if (list == null) {
            list = new ArrayList<>();
        }

        UserSelectHttpResponseVO userVO = new UserSelectHttpResponseVO();
        userVO.setCount(num);
        userVO.setList(userServerOTM.userDO2UserResponse(list));

        return userVO;
    }

    @ResponseBody
    @RequestMapping(value = "/user/get")
    public UserGetHttpResponseVO get(@RequestParam(value = "id") int id) {
        if (id <= 0) {
            throw new NumValidationException("invalid id");
        }

        UserInfoResponseVO userInfoResponseVO = userDubboService.getUserById(id);

        if (userInfoResponseVO == null) {
            throw new BusinessDefaultException("cant find this user");
        }
        UserGetHttpResponseVO userVO = new UserGetHttpResponseVO();
        userVO.setUserInfo(userServerOTM.userDO2UserResponse(userInfoResponseVO));

        return userVO;
    }

    @ResponseBody
    @RequestMapping(value = "/user/delete")
    public UserDefaultHttpResponseVO delete(@RequestParam(value = "id") long id) {
        if (id <= 0) {
            throw new NumValidationException("invalid id");
        }
        UserInfoResponseVO userInfoResponseVO = userDubboService.getUserById(id);

        if (userInfoResponseVO == null || userInfoResponseVO.getId() <= 0) {
            throw new BusinessDefaultException("id not found");
        }

        boolean ret = userDubboService.deleteUser(id);

        UserDefaultHttpResponseVO userVO = new UserDefaultHttpResponseVO();
        userVO.setStatus(ret ? 0 : 1);

        return userVO;
    }

    @ResponseBody
    @RequestMapping(value = "/user/batchDelete")
    public UserDefaultHttpResponseVO delete(@RequestParam(value = "ids") String ids) {
        if (ids.length() <= 0) {
            throw new NumValidationException("empty ids");
        }
        List<Long> idListInt = new ArrayList<>();
        String[] idList = ids.split(",");
        for (String id : idList) {
            long idInt = Long.parseLong(id);
            if (idInt <= 0) {
                throw new NumValidationException("invalid ids");
            }

            idListInt.add(idInt);
        }

        boolean ret = userDubboService.deleteUser(idListInt);

        UserDefaultHttpResponseVO userVO = new UserDefaultHttpResponseVO();
        userVO.setStatus(ret ? 0 : 1);

        return userVO;
    }
}
