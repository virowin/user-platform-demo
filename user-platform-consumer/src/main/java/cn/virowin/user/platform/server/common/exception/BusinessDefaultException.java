package cn.virowin.user.platform.server.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author virowin
 * @date 2022/8/22 16:12
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessDefaultException extends PlatformException {
    public BusinessDefaultException(String message) { super(message); }
}
