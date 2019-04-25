package com.dreamatn.springboot.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController Controller里的所有方法都是用json格式输出
public class HelloWorldController {
/*	@RequestMapping("/hello")
	public String index() {
		return "Hello world";
	}*/
	@RequestMapping("/getUser")
	public User getUser() {
		User user = new User();
		user.setName("小明");
		user.setPassword("123456");
		return user;
	}
}
