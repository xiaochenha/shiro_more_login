package com.example.shiro_login.common.resp;

import lombok.Data;

@Data
public class JsonResult {
    private boolean success = false;
    private String msg = "";
    private Object data = null;
    private int code;
    public JsonResult(){
        super();
    }
    public JsonResult(boolean success, String msg, Object data, int code) {
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }
    public static JsonResult ok(Object data){
        return new JsonResult(true,"成功",data,200);
    }
    public static JsonResult fail(){
        return new JsonResult(false,"失败",null,200);
    }
    public static JsonResult ok(SuccessStatus successStatus){
        return new JsonResult(true,successStatus.getMsg(),null,successStatus.getCode());
    }
    public static JsonResult fail(ErrorStatus errorStatus){
        return new JsonResult(false,errorStatus.getMsg(),null,errorStatus.getCode());
    }
}


