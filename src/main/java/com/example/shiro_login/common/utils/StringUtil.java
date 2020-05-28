package com.example.shiro_login.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class StringUtil extends StringUtils {
    public static String customUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
