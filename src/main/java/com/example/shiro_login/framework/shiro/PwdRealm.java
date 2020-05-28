package com.example.shiro_login.framework.shiro;

import com.example.shiro_login.project.entity.User;
import com.example.shiro_login.project.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class PwdRealm extends AuthenticatingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String phone = (String) authenticationToken.getPrincipal();
        User user = userService.getByPhone(phone);
        if(null == user){
            return null;
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(phone,user.getPassword(),credentialsSalt,this.getName());
        return simpleAuthenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken){
        return authenticationToken instanceof UsernamePasswordToken;
    }

}
