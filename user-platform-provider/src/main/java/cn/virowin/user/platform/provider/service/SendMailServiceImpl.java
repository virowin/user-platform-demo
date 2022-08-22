package cn.virowin.user.platform.provider.service;

import cn.virowin.user.platform.facade.MailService;
import cn.virowin.user.platform.facade.vo.request.MailServiceRequestVO;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Properties;
import javax.activation.*;

/**
 * @author virowin
 * @date 2022/8/21 21:54
 */
@DubboService
public class SendMailServiceImpl implements MailService {
    @Override
    public void sendMail(MailServiceRequestVO mailServiceRequestVO) {

    }
}