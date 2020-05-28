package com.example.shiro_login.common.req;

import lombok.Data;

@Data
public class LoginReqVo {
    private String phone;
    private String password;
    private String code;
}
