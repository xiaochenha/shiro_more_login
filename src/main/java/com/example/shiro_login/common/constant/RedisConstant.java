package com.example.shiro_login.common.constant;

public class RedisConstant {
    public static final Long MOBILE_CODE_EXPIRE_TIME = 5 * 60L;
    public static final String MOBILE_CODE = "login:mobile:code:";
    public static String getMobileCodeKey(String phone){
        return MOBILE_CODE+phone;
    }
}
