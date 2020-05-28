package com.example.shiro_login.project.service;

import com.example.shiro_login.common.resp.JsonResult;

public interface MsgService {
    JsonResult emailSendCode(String phone);
}
