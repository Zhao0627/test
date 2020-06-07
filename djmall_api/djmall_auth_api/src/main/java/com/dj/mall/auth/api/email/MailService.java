package com.dj.mall.auth.api.email;

/**
 * 邮件发送接口
 */
public interface MailService {
    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content);
    /**
     * 发送HTML邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to, String subject, String content);
    /**
     * 发送带有附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);
}
