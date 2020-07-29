package com.qiqi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiqi.common.lang.Result;
import com.qiqi.entity.User;
import com.qiqi.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
//    @RequiresAuthentication
    @RequestMapping("list")
    public Result selectUser(){
        User user = userService.getById(1l);

        return  Result.suscc(user);
    }

}
