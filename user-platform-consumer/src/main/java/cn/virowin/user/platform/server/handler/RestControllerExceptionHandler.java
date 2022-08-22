package cn.virowin.user.platform.server.handler;

import cn.virowin.user.platform.server.common.exception.ParamValidationException;
import cn.virowin.user.platform.server.common.response.CommonResponse;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author virowin
 * @date 2022/8/22 17:11
 */
@RestController
public class RestControllerExceptionHandler implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);
    private final static String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(path = ERROR_PATH )
    public CommonResponse routerExceptionHandle(Exception e) {
        CommonResponse commonResponse = CommonResponse.createCommonResponse();
        logger.error("[RouterException]Exception:", e);
        return commonResponse.fail("router not found!");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}