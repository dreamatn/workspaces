package com.hisuntech.class1.example03;

public class StringCompare {
	public static void main (String args[]) {
		String s1 = "I am Rock";
		String s2 = "I am Rock";
		String s3 = new String("I am Rock");
		s3 = new String("I am Rock");
		String s4 = s3.toLowerCase().substring(0,4);
		StringBuilder sb = new StringBuilder();
		
		boolean b1 = s1==s2;
		boolean b2 = s1.equals(s2);
		
		boolean b3 = s1==s3;
		boolean b4 = s1.equals(s3);
		
		System.out.println("s1=" + s1 + ",s2=" + s2 + ",s3=" + s3);
		System.out.println("s1==s2? " + b1);
		System.out.println("s1.equals(s2)? " + b2);
		
		System.out.println("s1==s3? " + b3);
		System.out.println("s1.equals(s3)? " + b4);
	}
}
