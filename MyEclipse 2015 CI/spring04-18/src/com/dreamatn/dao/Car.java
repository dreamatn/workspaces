package com.dreamatn.dao;

public class Car {

	private String brand;
	private int speed;
	private String color;
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(String brand, int speed, String color) {
		super();
		this.brand = brand;
		this.speed = speed;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", speed=" + speed + ", color=" + color
				+ "]";
	}

}
