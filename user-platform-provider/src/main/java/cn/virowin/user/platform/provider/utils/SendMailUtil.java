package cn.virowin.user.platform.provider.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author virowin
 * @date 2022/8/22 10:58
 */
@Component
public class SendMailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(String to, String subject, String content) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("ximayes@126.com");
            helper.setTo(to);
            helper.setSubject(subject);

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
        }
    }

}
