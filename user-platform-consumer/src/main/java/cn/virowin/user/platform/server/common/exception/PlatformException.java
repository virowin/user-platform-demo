package cn.virowin.user.platform.server.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author virowin
 * @date 2022/8/21 17:42
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PlatformException extends RuntimeException {
    public PlatformException() {super(); }

    public PlatformException(String message) {
        super(message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }
}
