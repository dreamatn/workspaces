package com.hisuntech.class1.example02;

import java.math.BigDecimal;

public class BigDecimalCompare {
	public static void main (String args[]) {
		BigDecimal f1 = BigDecimal.valueOf(0.05).add(BigDecimal.valueOf(0.01));
		BigDecimal f2 = BigDecimal.valueOf(0.06);
		BigDecimal f3 = BigDecimal.valueOf(0.05+0.01);
		
		BigDecimal f4 = BigDecimal.valueOf(0.05);
		BigDecimal f5 = BigDecimal.valueOf(0.01);
		BigDecimal f6 = f4.add(f5);
		boolean b1 = f1==f2;
		boolean b2 = f1.equals(f2);
		
		System.out.println("f1=" + f1 + ",f2=" + f2 + ",f3=" + f3);
		System.out.println("f4=" + f4 + ",f5=" + f5 + ",f6=" + f6);
		System.out.println("f1==f2? " + b1);
		System.out.println("f1.equals(f2)? " + b2);
	}
}
