package cn.virowin.user.platform.server.handler;

import cn.virowin.user.platform.server.common.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author virowin
 * @date 2022/8/21 19:36
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if (returnValue instanceof CommonResponse) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            return;
        }
        CommonResponse response = CommonResponse.createCommonResponse(returnValue);

        delegate.handleReturnValue(response, returnType, mavContainer, webRequest);
    }
}
