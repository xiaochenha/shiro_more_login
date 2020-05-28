package com.example.shiro_login.common.resp;

public enum ErrorStatus {
    SYSTEM_ERROR(500,"服务器繁忙"),
    NEED_LOGIN(-1,"未登录"),
    NO_PERMISSION(-2,"没有权限"),
    PHONE_HAVE_SET_PASSWORD(200,"该手机已经注册"),
    UNKNOWN_ACCOUNT(200,"用户不存在"),
    PHONE_PWD_ERROR(200,"账号或密码错误"),
    EXPIRED_CODE(200,"验证码失效"),
    ERROR_CODE(200,"验证码错误");
    private int code;
    private String msg;

    ErrorStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


