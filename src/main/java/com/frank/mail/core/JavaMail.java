package com.frank.mail.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@Component
public class JavaMail {

    //注入属性
    @Value("${mail.smtp.host}")
    private String mailHost;

    //注入属性
    @Value("${mail.sender.username}")
    private String username;

    //注入属性
    @Value("${mail.sender.password}")
    private String password;

    /**
     * Message对象将存储我们实际发送的电子邮件信息，
     * Message对象被作为一个MimeMessage对象来创建并且需要知道应当选择哪一个JavaMail session。
     */
    private MimeMessage message;

    /**
     * Session类代表JavaMail中的一个邮件会话。
     * 每一个基于JavaMail的应用程序至少有一个Session（可以有任意多的Session）。
     * <p/>
     * JavaMail需要Properties来创建一个session对象。
     * 寻找"mail.smtp.host"    属性值就是发送邮件的主机
     * 寻找"mail.smtp.auth"    身份验证，目前免费邮件服务器都需要这一项
     */
    private Session session;

    /***
     * 邮件是既可以被发送也可以被受到。JavaMail使用了两个不同的类来完成这两个功能：Transport 和 Store。
     * Transport 是用来发送信息的，而Store用来收信。对于这的教程我们只需要用到Transport对象。
     */
    private Transport transport;

    private Properties properties = new Properties();


    /*
     * 初始化message
     */
    private Message getMessage() {
        properties.setProperty("mail.smtp.host",mailHost);
        properties.setProperty("mail.sender.username",username);
        properties.setProperty("mail.sender.password",password);
        session = Session.getInstance(properties);
        session.setDebug(true);//开启后有调试信息
        message = new MimeMessage(session);
        return message;
    }

    /**
     * 发送邮件
     *
     * @param subject     邮件主题
     * @param content    邮件内容
     * @param receiveUser 收件人地址
     */
    public void doSendHtmlEmail(String subject, String content,
                                String receiveUser) {
        //获取message对象
        Message message = getMessage();

        try {
            // 发件人
            //InternetAddress from = new InternetAddress(username);
            // 下面这个是设置发送人的Nick name
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("FrankTang") + " <" + username + ">");
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);//还可以有CC、BCC

            // 邮件主题
            message.setSubject(subject);

            // 邮件内容,纯文本"text/plain",也可以使用html
            message.setContent(content, "text/plain;charset=UTF-8");

            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, username, password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            //System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}