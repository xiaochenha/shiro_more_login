package com.example.shiro_login.common.resp;

public enum SuccessStatus {
    SUCCESS(200,"成功"),
    FAIL(200,"失败"),
    CODE_SEND_SUCCESS(200,"验证码已发送");
    private int code;
    private String msg;

    SuccessStatus(int code, String msg) {
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


