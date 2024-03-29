package com.dreamatn.autowired.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamatn.autowired.controller.UserController;

public class MainTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserController uc = (UserController) ac.getBean("userController");
		uc.excute();
	}
}
