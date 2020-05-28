package com.example.shiro_login.project.service;

import com.example.shiro_login.common.req.LoginReqVo;
import com.example.shiro_login.common.resp.JsonResult;
import com.example.shiro_login.project.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenPengfei
 * @since 2020-05-28
 */
public interface IUserService extends IService<User> {

    User getByPhone(String phone);

    JsonResult loginCode(LoginReqVo loginReqVo);

    JsonResult userInfo();

    JsonResult editInfo(User user);

    JsonResult registPwd(LoginReqVo loginReqVo);

    JsonResult loginPwd(LoginReqVo loginReqVo);
}
