package com.hisuntech.class1.example06;

public class SwitchFlow001 {
	public void switchFlow(int value) {
		switch (value) {
		case 100:
		case 200: System.out.println("in case 1:" + value);
		case 300:
		case 400: System.out.println("in case 2:" + value); break;
		case 500: System.out.println("in case 3:" + value);
		default : System.out.println("in case 4:" + value);
		}
		System.out.println("-----------------------------------");
	}
	public static void main (String args[]) {
		SwitchFlow001 sf = new SwitchFlow001();
		sf.switchFlow(100);
		sf.switchFlow(200);
		sf.switchFlow(300);
		sf.switchFlow(400);
		sf.switchFlow(500);
		
	}
}
