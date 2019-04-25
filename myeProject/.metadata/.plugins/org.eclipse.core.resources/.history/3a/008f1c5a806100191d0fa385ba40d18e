package com.dreamatn.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.dreamatn.dao.Car;

@Service
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// 创建IOC容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 从容器中取bean实例
//		Person person = (Person) ac.getBean("person");
		Car car = (Car) ac.getBean("car");
		// 使用bean
//		System.out.println(person);
//		System.out.println(person.getBook());
		System.out.println(car);
	}

}
