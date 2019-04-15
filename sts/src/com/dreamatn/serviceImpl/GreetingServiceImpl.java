package com.dreamatn.serviceImpl;

import com.dreamatn.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {
	private String greeting;

	public GreetingServiceImpl() {
	}

	public GreetingServiceImpl(String greeting) {
		this.greeting = greeting;
	}

	@Override
	public void sayGreeting() {
		// TODO Auto-generated method stub
		System.out.println(greeting);
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

}
