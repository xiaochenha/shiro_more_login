package com.example.shiro_login.project.service.impl;

import com.example.shiro_login.common.constant.RedisConstant;
import com.example.shiro_login.common.resp.JsonResult;
import com.example.shiro_login.common.resp.SuccessStatus;
import com.example.shiro_login.common.utils.EmailUtil;
import com.example.shiro_login.framework.redis.RedisUtil;
import com.example.shiro_login.project.service.MsgService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JsonResult emailSendCode(String phone) {
        JsonResult jsonResult = new JsonResult();
        String code = RandomStringUtils.randomNumeric(6);

        EmailUtil.emailSendCode(phone,code);
        boolean flag = redisUtil.set(RedisConstant.getMobileCodeKey(phone),code,RedisConstant.MOBILE_CODE_EXPIRE_TIME);
        if(flag){
            return JsonResult.ok(SuccessStatus.SUCCESS);
        }
        return jsonResult;
    }
}
