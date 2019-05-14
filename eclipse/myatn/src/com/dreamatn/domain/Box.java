package com.dreamatn.domain;

public class Box {
	
	double height;
	double width;
	double depth;
	
	// display volume of box
	void volume() {
		System.out.print("Volume is ");
		System.out.println(height * width * depth);
	}
	
	
}
