package com.dreamatn.action;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object o, String s)
			throws BeansException {
		System.out.println("postProcessAfterInitialization……");
		return o;
	}

	@Override
	public Object postProcessBeforeInitialization(Object o, String s)
			throws BeansException {
		System.out.println("postProcessBeforeInitialization……");
		return o;
	}

}
