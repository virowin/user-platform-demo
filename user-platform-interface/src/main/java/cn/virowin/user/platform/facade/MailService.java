package cn.virowin.user.platform.facade;

import cn.virowin.user.platform.facade.vo.request.MailServiceRequestVO;

/**
 * @author virowin
 * @date 2022/8/22 12:04
 */
public interface MailService {
    public void sendMail(MailServiceRequestVO mailServiceRequestVO);
}
