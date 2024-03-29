package com.dreamatn.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamatn.entity.MyDataSource;

public class Main {
	public static void main(String[] args){
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		MyDataSource mds = (MyDataSource) ac.getBean("dataSource");
		//关闭Ioc容器
		ac.close();
	}
}
