package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.utils.sign.EmailUtil;
import com.jzj.vblog.web.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送纯文本邮件.
     *
     * @param to      目标email 地址
     * @param subject 邮件主题
     * @param text    纯文本内容
     */
    @Retryable(value = MailException.class,maxAttempts = 2,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    @Override
    public void sendMail(String to, String subject, String text) {
        try {
            if(EmailUtil.isEmail(to)){
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);
                mailSender.send(message);
            }
        } catch (MailException e) {
            log.error("邮箱发送失败,发送用户为:"+to+"---错误信息为:"+e);
        }
    }

    /**
     * 发送邮件并携带附件.
     * 请注意 from 、 to 邮件服务器是否限制邮件大小
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param filePath 附件的路径 当然你可以改写传入文件
     */
    @Retryable(value = RuntimeException.class,maxAttempts = 2,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    @Override
    public void sendMailWithAttachment(String to, String subject, String text, String filePath) {
        try {
            File attachment = new File(filePath);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(attachment.getName(),attachment);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("邮箱发送失败,发送用户为:"+to+"---错误信息为:"+e);
        }
    }

    /**
     * 发送富文本邮件.
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param filePath 附件的路径 当然你可以改写传入文件
     */
    @Retryable(value = RuntimeException.class,maxAttempts = 2,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    @Override
    public void sendRichMail(String to, String subject, String text, String filePath) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);

            helper.setText(text,true);
            // 图片占位写法  如果图片链接写入模板 注释下面这一行
            helper.addInline("qr",new FileSystemResource(filePath));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("邮箱发送失败,发送用户为:"+to+"---错误信息为:"+e);
        }
    }
}
