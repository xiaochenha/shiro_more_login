package com.example.shiro_login.common.utils;

import lombok.Data;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.io.Serializable;

public final class PwdHelper {
    public static final String ALGORITHM_NAME = "md5";
    public static final int HASHLTERATIONS = 2;
    public static PwdInfo encryptPassword(String password){
        PwdInfo pwdInfo = new PwdInfo();
        pwdInfo.setSalt(StringUtil.customUUID());
        Object encryptPwd = new SimpleHash(ALGORITHM_NAME,password,pwdInfo.getSalt(),HASHLTERATIONS);
        pwdInfo.setEncryptPwd(encryptPwd.toString());
        return pwdInfo;
    }
    @Data
    public static class PwdInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        private String encryptPwd;
        private String salt;
    }
}

