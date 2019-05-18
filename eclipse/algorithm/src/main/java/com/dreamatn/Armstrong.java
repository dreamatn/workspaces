package main.java.com.dreamatn;

/**
 * 
 * @Title Armstrong.java
 * @Description TODO(打印三位数中所有的armstrong数)
 * @author ning_qb
 * @date 创建时间:2019年5月14日下午3:22:53
 * @version 1.0
 */
public class Armstrong {

	public static void main(String[] args) {
		for (int i = 100; i < 1000; i++) {
			int a, b, c;
			a = i / 100;          //取百位
			b = (i % 100) / 10;   //取十位
			c = (i % 100) % 10;   //取个位
			if (a * a * a + b * b * b + c * c * c == i) {
				System.out.print(i + " ");
			}
		}
		for (int i = 0; i < 80; i++) {
			System.out.print((int)(Math.random()*6) + " ");
		}

	}
}
