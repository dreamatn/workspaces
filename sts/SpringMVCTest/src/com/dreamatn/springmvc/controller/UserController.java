package com.dreamatn.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dreamatn.springmvc.entity.User;

/**
 * UserController 是一个基于注解的控制器
  *  可以同时处理多个请求动作
 *
 */
@Controller
public class UserController {
	/**
	 * RequestMapping 用来映射一个请求和请求的方法
	 * value="/register"表示请求有register方法进行处理
	 * 
	 */
	@RequestMapping(value="/register")
	/**
	 * user:视图层传给控制层的表单对象
	 * model:控制层返回给视图层的对象
	 * 
	 */
	public String Register(@ModelAttribute("form") User user, Model model) {
		model.addAttribute("user",user);
		return "success";
	}
}
