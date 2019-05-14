package com.hisuntech.class1.example04;

public class Boxing {
	public static void main (String args[]) {
		Integer i1 = 1;//auto boxing
		Integer i2 = Integer.valueOf(1);//manual boxing
		Integer i5 = new Integer(1);//new
		
		Integer i3 = 10000;//auto boxing
		Integer i4 = 10000;//auto boxing
		
		boolean b1 = i1==i2;
		boolean b2 = i1.equals(i2);
		boolean b5 = i1==i5;
		boolean b6 = i1.equals(i5);
		boolean b3 = i3==i4;
		boolean b4 = i3.equals(i4);
		
		System.out.println("i1=" + i1 + ",i2=" + i2 + ",i5=" + i5);
		System.out.println("i1==i2? " + b1);
		System.out.println("i1.equals(i2)? " + b2);
		System.out.println("i1==i5? " + b5);
		System.out.println("i1.equals(i5)? " + b6);
		System.out.println("----------------------------------");
		System.out.println("i3=" + i3 + ",i4=" + i4);
		System.out.println("i3==i4? " + b3);
		System.out.println("i3.equals(i4)? " + b4);
	}
}
