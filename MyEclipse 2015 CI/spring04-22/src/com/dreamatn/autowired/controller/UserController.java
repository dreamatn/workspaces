package com.dreamatn.autowired.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dreamatn.autowired.service.UserService;

@Controller
public class UserController {
	@Autowired(required=false)
	private UserService userService;
	// 用AutoWired也可以放在setter方法上，那就去掉上面的Autowired注解
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public void excute(){
		System.out.println("UserController excute");
		userService.diao();
	}
}
