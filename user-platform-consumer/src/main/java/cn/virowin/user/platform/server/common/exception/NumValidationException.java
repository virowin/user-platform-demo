package cn.virowin.user.platform.server.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author virowin
 * @date 2022/8/21 20:27
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumValidationException extends PlatformException {
    public NumValidationException(String message) { super(message); }
}
