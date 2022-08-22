package cn.virowin.user.platform.server.common.response;

import cn.virowin.user.platform.server.handler.ResponseBodyWrapHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author virowin
 * @date 2022/8/21 19:40
 */
@Component
public class ResponseBodyWrapFactoryBean implements InitializingBean {
    private final RequestMappingHandlerAdapter adapter;

    @Autowired
    public ResponseBodyWrapFactoryBean(RequestMappingHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        if (returnValueHandlers.size() > 0) {
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
            decorateHandlers(handlers);
            adapter.setReturnValueHandlers(handlers);
        }
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
    }
}