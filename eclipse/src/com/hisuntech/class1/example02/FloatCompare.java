package com.hisuntech.class1.example02;

public class FloatCompare {
	public static void main (String args[]) {
		float f1 = 0.05f+0.01f;
		float f2 = 0.06f;
		float f3 = (float) (0.05+0.01);
		
		float f4 = (float) 0.05;
		float f5 = (float) 0.01;
		float f6 = f4 + f5;
		boolean b1 = f1==f2;
		
		System.out.println("f1=" + f1 + ",f2=" + f2 + ",f3=" + f3);
		System.out.println("f4=" + f4 + ",f5=" + f5 + ",f6=" + f6);
		System.out.println("f1==f2? " + b1);
	}
}
