package com.qiqi.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiqi.common.lang.Result;
import com.qiqi.common.lang.dto.LogIDto;
import com.qiqi.entity.User;
import com.qiqi.service.UserService;
import com.qiqi.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public Result login(@Validated @RequestBody LogIDto logIDto, HttpServletResponse response){

        User user = userService.getOne(new QueryWrapper<User>().eq("username", logIDto.getUsername()));
        Assert.notNull(user,"用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(logIDto.getPassword()))){
            return Result.fail("密码错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());
//        System.out.println(jwt);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        return  Result.suscc(MapUtil.builder().put("username",user.getUsername())
                .put("avater",user.getAvatar())
                .put("email",user.getEmail())
                .put("id",user.getId()).map());
    }


    @RequiresAuthentication
    @GetMapping("/logout")
    public  Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.suscc(null);
    }
}
