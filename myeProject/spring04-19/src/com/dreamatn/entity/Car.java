package com.dreamatn.entity;

public class Car {

	public Car() {
		System.out.println("构造器。。。");
	}

	private String brand;

	public void setBrand(String brand) {
		System.out.println("设置属性。。。");
		this.brand = brand;
	}

	public void init() {
		System.out.println("初始化。。。");
	}

	public void destroy() {
		System.out.println("销毁。。。");
	}

	public String getBrand() {
		return brand;
	}

}
