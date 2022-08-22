package cn.virowin.user.platform.server.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author virowin
 * @date 2022/8/21 17:44
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParamValidationException extends PlatformException {

    public ParamValidationException(String message) { super(message); }

    public ParamValidationException(BindingResult bindingResult) {
        super(bindingResultToString(bindingResult));

    }

    private static String bindingResultToString (BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append(";");
        }
        return sb.toString();
    }
}
