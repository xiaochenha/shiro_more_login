package com.example.shiro_login.framework.shiro;

import com.example.shiro_login.common.constant.RedisConstant;
import com.example.shiro_login.framework.redis.RedisUtil;
import com.example.shiro_login.project.entity.User;
import com.example.shiro_login.project.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class CodeRealm extends AuthenticatingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        PhoneToken token = null;

        if(authenticationToken instanceof PhoneToken){
            token = (PhoneToken) authenticationToken;
        }else{
            return null;
        }

        String phone = (String) token.getPrincipal();
        User user = userService.getByPhone(phone);
        if(null == user){
            throw new UnknownAccountException();
        }
        Object credentials = redisUtil.get(RedisConstant.getMobileCodeKey(phone));
        if(null == credentials){
            throw new ExpiredCredentialsException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,credentials,null,this.getName());
        return simpleAuthenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken){
        return authenticationToken instanceof PhoneToken;
    }

}

