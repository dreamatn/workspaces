package com.hisuntech.class1.example04;

public class Unboxing {
	public static void main (String args[]) {
		Integer i = new Integer(1);//new
		int i1 = i;//auto unboxing
		int i2 = (int) Integer.valueOf(1);//manual unboxing
		
		int i3 = new Integer(10000);//auto unboxing
		int i4 = Integer.valueOf(10000);//auto unboxing
		
		boolean b1 = i1==i2;
		boolean b3 = i3==i4;
		
		System.out.println("i1=" + i1 + ",i2=" + i2);
		System.out.println("i1==i2? " + b1);
		System.out.println("----------------------------------");
		System.out.println("i3=" + i3 + ",i4=" + i4);
		System.out.println("i3==i4? " + b3);
	}
}
