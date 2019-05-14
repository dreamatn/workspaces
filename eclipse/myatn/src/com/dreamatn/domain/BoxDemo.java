package com.dreamatn.domain;

public class BoxDemo {

	public static void main(String args[]) {
		Box mybox1 = new Box();
		Box mybox2 = new Box();
		
		// assign value to ...
		mybox1.width  = 10;
		mybox1.height = 20;
		mybox1.depth  = 15;
		
		mybox2.width  = 5;
		mybox2.height = 10;
		mybox2.depth  = 8;
		
		mybox1.volume();
		mybox2.volume();
	}
}
