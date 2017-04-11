package com.frank.mail.dto;

/**
 * Created by FrankTang on 2017/4/10.
 * Version 1.0
 */
public class Result {

    private String code;

    private String msg;


    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
