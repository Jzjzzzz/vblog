package com.jzj.vblog.web.service;

/**
 * <p>
 * 邮箱 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-11
 */
public interface EmailService {

    /**
     * 发送纯文本邮件.
     *
     * @param to      目标email 地址
     * @param subject 邮件主题
     * @param text    纯文本内容
     */
    void sendMail(String to, String subject, String text);

    /**
     * 发送邮件并携带附件.
     * 请注意 from 、 to 邮件服务器是否限制邮件大小
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param filePath 附件的路径 当然你可以改写传入文件
     */
    void sendMailWithAttachment(String to, String subject, String text, String filePath);

    /**
     * 发送富文本邮件.
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param filePath 附件的路径 当然你可以改写传入文件
     */
    void sendRichMail(String to, String subject, String text, String filePath);
}
