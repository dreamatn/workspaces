package com.dreamatn.autowired.dao.impl;

import org.springframework.stereotype.Repository;

import com.dreamatn.autowired.dao.UserRepostory;

@Repository("userRepostory")
public class UserRepostoryImpl implements UserRepostory {

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("UserRepostory save");
	}

}
