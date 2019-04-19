package com.dreamatn.autowired.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamatn.autowired.dao.UserRepostory;

@Service
public class UserService {
	@Autowired
	private UserRepostory userRepostory;
	public void diao(){
		System.out.println("UserService diao");
		userRepostory.save();
	}
}
