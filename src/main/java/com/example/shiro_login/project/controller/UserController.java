package com.example.shiro_login.project.controller;


import com.example.shiro_login.common.resp.JsonResult;
import com.example.shiro_login.project.entity.User;
import com.example.shiro_login.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenPengfei
 * @since 2020-05-28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("user_info")
    public JsonResult userInfo(){
        return userService.userInfo();
    }

    @PostMapping("edit_info")
    public JsonResult editInfo(@RequestBody User user){
        return userService.editInfo(user);
    }

}
