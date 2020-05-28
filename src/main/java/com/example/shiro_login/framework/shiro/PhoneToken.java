package com.example.shiro_login.framework.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.io.Serializable;

public class PhoneToken implements HostAuthenticationToken, RememberMeAuthenticationToken, Serializable {

    private String phone;
    private String code;

    /**
     * 重写getPrincipal方法
     */
    public Object getPrincipal() {
        return phone;
    }

    /**
     * 重写getCredentials方法
     */
    public Object getCredentials() {
        return code;
    }

    public PhoneToken(){
        super();
    }

    public PhoneToken(String phone,String code){
        this.phone = phone;
        this.code = code;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public boolean isRememberMe() {
        return false;
    }
}
