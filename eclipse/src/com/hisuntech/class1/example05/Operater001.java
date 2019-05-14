package com.hisuntech.class1.example05;

public class Operater001 {
	public void flow1() {
		int l=1; 
		do{ 
			System.out.println("Doing it for l is:" + l); 
		} while(--l>0);
		System.out.println("Finish"); 
	}
	public void flow2() {
		int l=1; 
		do{ 
			System.out.println("Doing it for l is:" + l); 
		} while(l-->0);
		System.out.println("Finish"); 
	}
	public static void main(String args[] ){ 
		Operater001 oper = new Operater001();
		oper.flow1();
		oper.flow2();
	} 
}
