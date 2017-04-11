package com.frank.mail.dto;

/**
 * Created by FrankTang on 2017/4/10.
 * Version 1.0
 */
public class Request {

    private String subject; //主题

    private String content;//内容

    private String receiveUser;//接收者

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }
}
