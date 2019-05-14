package com.hisuntech.class1.example06;

public class ForFlow002 {
	public void forFlow1() {
		for (int i=0; i<10; i++ ) {
			if (i%2 == 0) {
				System.out.println("pass i=" + i);
				continue;
			}
			
			System.out.println("i=" + i);
			//i++;
		}
		System.out.println("Finished.---------------------");
	}
	public void forFlow2() {
		for (int i=0; i<10; i++) {
			if (i%2 == 0) {
				System.out.println("pass i=" + i);
				continue;
			}
			
			System.out.println("i=" + i);
			i++;
		}
		System.out.println("Finished.---------------------");
	}
	public void forFlow3() {
		for (int i=0; i<10; i++ ) {
			if (i%2 == 0) {
				System.out.println("break i=" + i);
				break;
			}
			
			System.out.println("i=" + i);
			i++;
		}
		System.out.println("Finished.---------------------");
	}
	public static void main (String args[]) {
		ForFlow002 ff = new ForFlow002();
		//ff.forFlow1();
		//ff.forFlow2();
		ff.forFlow3();
	}
}
