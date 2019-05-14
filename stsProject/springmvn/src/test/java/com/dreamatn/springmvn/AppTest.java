package com.dreamatn.springmvn;

import junit.framework.TestCase;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
	public void testSay() {
		App app = new App();
		String result = app.say();
		System.out.println(result);
	}
}
