package com.hisun.controller;

import com.hisun.model.MyResult;
import com.hisun.model.User;
import com.hisun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MoHuaiyi
 * 2019/4/17 11:01
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MyResult login(@RequestBody User user){
        System.out.println("Controller开始");
        return userService.login(user);
    }



    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public MyResult getList(@RequestBody User user) {
        List<User> users = userService.getUserList(user);
        return new MyResult(200, "请求成功", users, user);
    }

}
