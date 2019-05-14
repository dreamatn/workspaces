package com.hisuntech.class1.example09;

import com.hisuntech.class1.example01.HelloWorld;

/**
 * hello world
 * @author thinkpad
 * <p>该注释可以输出到java doc中</p>
 */
public class HelloWorld2 {//类的开始
	public void callHelloWorld() {
		HelloWorld hw = new HelloWorld();
		System.out.println(" call HelloWorld1...");
	}
	//程序执行的起始点
	public static void main (String args[]) {//方法的开始
		System.out.println("HelloWorld2...");//声明如何使用类名、对象名和方法调用
		
	}//方法的结束
}//类的结束
