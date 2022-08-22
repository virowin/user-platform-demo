package cn.virowin.user.platform.server.handler;


import cn.virowin.user.platform.server.common.exception.BusinessDefaultException;
import cn.virowin.user.platform.server.common.exception.NumValidationException;
import cn.virowin.user.platform.server.common.exception.ParamValidationException;
import cn.virowin.user.platform.server.common.exception.RouterException;
import cn.virowin.user.platform.server.common.response.CommonResponse;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author virowin
 * @date 2022/8/21 18:09
 */
@ControllerAdvice(basePackages = {"cn.virowin.user.platform.server.controller"})
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ParamValidationException.class})
    public CommonResponse paramValidationExceptionHandle(Exception e) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        logger.error("[ParamValidationException]Exception:", e);
        return commonResponse.fail("Parameter validation failure! Message:" + e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {NumValidationException.class})
    public CommonResponse numValidationExceptionHandle(Exception e) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        logger.error("[NumValidationException]Exception:", e);
        return commonResponse.fail("Number validation failure! Message:" + e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BusinessDefaultException.class})
    public CommonResponse bsValidationExceptionHandle(Exception e) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        logger.error("[BusinessDefaultException]Exception:", e);
        return commonResponse.fail("default failure! Message:" + e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public CommonResponse normalExceptionHandle(Exception e) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        logger.error("[NotDefinedException]Exception:", e);
        return commonResponse.fail("services failure!");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RouterException.class)
    public CommonResponse routerExceptionHandle(Exception e) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        logger.error("[RouterException]Exception:", e);
        return commonResponse.fail("router not found!");
    }
}
