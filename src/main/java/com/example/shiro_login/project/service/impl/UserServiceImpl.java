package com.example.shiro_login.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shiro_login.common.req.LoginReqVo;
import com.example.shiro_login.common.resp.ErrorStatus;
import com.example.shiro_login.common.resp.JsonResult;
import com.example.shiro_login.common.resp.SuccessStatus;
import com.example.shiro_login.common.utils.PwdHelper;
import com.example.shiro_login.common.utils.StringUtil;
import com.example.shiro_login.framework.jwt.JwtUtil;
import com.example.shiro_login.framework.shiro.PhoneToken;
import com.example.shiro_login.project.entity.User;
import com.example.shiro_login.project.mapper.UserMapper;
import com.example.shiro_login.project.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenPengfei
 * @since 2020-05-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getPhone,phone);
        User user = getOne(queryWrapper);
        return user;
    }

    @Override
    public JsonResult loginCode(LoginReqVo loginReqVo) {
        String phone = loginReqVo.getPhone();
        String code = loginReqVo.getCode();

        String id = "";

        User existUser = getByPhone(phone);
        if(null == existUser){
            User newUser = new User();
            newUser.setPhone(phone);
            boolean saveUser = save(newUser);
            if(!saveUser){
                return JsonResult.fail(ErrorStatus.SYSTEM_ERROR);
            }
            id = newUser.getId();
        }else{
            id = existUser.getId();
        }

        Subject subject = SecurityUtils.getSubject();
        //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(phone,code);
        PhoneToken phoneToken = new PhoneToken(phone,code);
        try{
            subject.login(phoneToken);
            String token = JwtUtil.sign(id,phone);
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            return JsonResult.ok(data);
        }catch(UnknownAccountException e){
            return JsonResult.fail(ErrorStatus.UNKNOWN_ACCOUNT);
        }catch(ExpiredCredentialsException e){
            return JsonResult.fail(ErrorStatus.EXPIRED_CODE);
        }catch(IncorrectCredentialsException e){
            return JsonResult.fail(ErrorStatus.ERROR_CODE);
        }
    }

    @Override
    public JsonResult userInfo() {
        JsonResult jsonResult = new JsonResult();
        String id = JwtUtil.getId();
        User user = getById(id);
        if(null != user){
            return JsonResult.ok(user);
        }
        return jsonResult;
    }

    @Override
    public JsonResult editInfo(User user) {
        JsonResult jsonResult = new JsonResult();
        boolean updSuccess = updateById(user);
        if(updSuccess){
            return JsonResult.ok(SuccessStatus.SUCCESS);
        }
        return jsonResult;
    }

    @Override
    public JsonResult registPwd(LoginReqVo loginReqVo) {
        JsonResult jsonResult = new JsonResult();
        String phone = loginReqVo.getPhone();
        String password = loginReqVo.getPassword();

        User existUser = getByPhone(phone);
        if(null == existUser){
            User newUser = new User();
            newUser.setPhone(phone);
            PwdHelper.PwdInfo pwdInfo = PwdHelper.encryptPassword(password);
            String encryptPwd = pwdInfo.getEncryptPwd();
            String salt = pwdInfo.getSalt();
            newUser.setPassword(encryptPwd);
            newUser.setSalt(salt);
            boolean saveSuccess = save(newUser);
            if(saveSuccess){
                return JsonResult.ok(SuccessStatus.SUCCESS);
            }
        }else{
            String sqlPwd = existUser.getPassword();
            if(StringUtil.isEmpty(sqlPwd)){
                PwdHelper.PwdInfo pwdInfo = PwdHelper.encryptPassword(password);
                String encryptPwd = pwdInfo.getEncryptPwd();
                String salt = pwdInfo.getSalt();
                existUser.setPassword(encryptPwd);
                existUser.setSalt(salt);
                boolean updSuccess = updateById(existUser);
                if(updSuccess){
                    return JsonResult.ok(SuccessStatus.SUCCESS);
                }
            }else{
                return JsonResult.fail(ErrorStatus.PHONE_HAVE_SET_PASSWORD);
            }
        }

        return jsonResult;
    }

    @Override
    public JsonResult loginPwd(LoginReqVo loginReqVo) {
        String phone = loginReqVo.getPhone();
        String password = loginReqVo.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(phone,password);
        try{
            subject.login(usernamePasswordToken);
            User user = getByPhone(phone);
            String id = user.getId();
            String token = JwtUtil.sign(id,phone);
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            return JsonResult.ok(data);
        }catch(AuthenticationException e){
            return JsonResult.fail(ErrorStatus.PHONE_PWD_ERROR);
        }
    }
}
