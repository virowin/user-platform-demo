package cn.virowin.user.platform.facade.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author virowin
 * @date 2022/8/22 12:05
 */
@Data
public class MailServiceRequestVO implements Serializable {
    private String to;
    private String subject;
    private String content;
}
