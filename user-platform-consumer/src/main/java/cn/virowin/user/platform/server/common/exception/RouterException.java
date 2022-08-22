package cn.virowin.user.platform.server.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author virowin
 * @date 2022/8/22 17:21
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RouterException extends PlatformException{
    public RouterException(String message) { super(message); }
}
