package com.frank.mail.controller;

import com.alibaba.fastjson.JSONObject;
import com.frank.mail.core.JavaMail;
import com.frank.mail.dto.Request;
import com.frank.mail.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FrankTang on 2017/4/10.
 * Version 1.0
 */
@RestController
public class MailController {
    // 日志记录
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private JavaMail javaMail;

    @RequestMapping(value = "/sendEmail", produces = "application/json,charset=utf-8")
    public Object sendEmail(@RequestBody Request request) {
        LOG.info(format.format(new Date()) + "调用发送邮件,request = " + JSONObject.toJSONString(request));

        javaMail.doSendHtmlEmail(request.getSubject(), request.getContent(), request.getReceiveUser());

        return new Result("0", "发送成功");
    }
}
