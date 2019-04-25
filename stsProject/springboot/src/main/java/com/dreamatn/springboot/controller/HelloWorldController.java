package com.dreamatn.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController Controller里的所有方法都是用json格式输出
public class HelloWorldController {
	@RequestMapping("/hello")
	public String index() {
		return "Hello world";
	}
}
