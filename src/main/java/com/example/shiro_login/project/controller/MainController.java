package com.example.shiro_login.project.controller;

import com.example.shiro_login.common.req.LoginReqVo;
import com.example.shiro_login.common.resp.ErrorStatus;
import com.example.shiro_login.common.resp.JsonResult;
import com.example.shiro_login.project.service.IUserService;
import com.example.shiro_login.project.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pub")
public class MainController {

    @Autowired
    private IUserService userService;

    @Autowired
    private MsgService msgService;

    @GetMapping("need_login")
    public JsonResult needLogin(){
        return JsonResult.fail(ErrorStatus.NEED_LOGIN);
    }
    @GetMapping("no_permission")
    public JsonResult noPermission(){
        return JsonResult.fail(ErrorStatus.NO_PERMISSION);
    }
    @PostMapping("login_code")
    public JsonResult loginCode(@RequestBody LoginReqVo loginReqVo){
        return userService.loginCode(loginReqVo);
    }
    @PostMapping("send_code")
    public JsonResult sendCode(@RequestParam String phone){
        return msgService.emailSendCode(phone);
    }
    @PostMapping("regist_pwd")
    public JsonResult registPwd(@RequestBody LoginReqVo loginReqVo){
        return userService.registPwd(loginReqVo);
    }
    @PostMapping("login_pwd")
    public JsonResult loginPwd(@RequestBody LoginReqVo loginReqVo){
        return userService.loginPwd(loginReqVo);
    }
}
