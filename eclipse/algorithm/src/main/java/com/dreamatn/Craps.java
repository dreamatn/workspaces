/**
 * @Title Craps.java
 * @Package main.java.com.dreamatn
 * @Description TODO(...)
 * @author ning_qb
 * @date 创建时间:2019年5月14日下午4:44:42
 * @version 1.0
 */
package main.java.com.dreamatn;

import java.io.IOException;

/**
 * @Title Craps.java
 * @Description TODO(赌博游戏)
 * @author ning_qb
 * @date 创建时间:2019年5月14日下午4:44:42
 * @version 1.0
 */
public class Craps {
	public static void main(String[] args) throws IOException {
		final int WON = 0, LOST = 1, CONTINUE = 3;
		boolean firstboll = true;
		int gameStatus = CONTINUE;
		int sumOfDice = 0;
		int firstPoint = 0;
		System.out.println("****Carp赌博游戏，按Enter开始游戏****");
		while (true) {
			System.in.read();
			sumOfDice = rollDice();
			if (firstboll) {
				switch (sumOfDice) {
				case 7:
				case 11:
					gameStatus = WON;
					break;
				case 2:
				case 3:
				case 12:
					gameStatus = LOST;
					break;
				default:
					firstboll = false;
					gameStatus = CONTINUE;
					firstPoint = sumOfDice;
				}
			} else {
				sumOfDice = rollDice();
				if (sumOfDice == firstPoint) {
					gameStatus = WON;
				} else if (sumOfDice == 7) {
					gameStatus = LOST;
				}
			}
			if (gameStatus == CONTINUE) {
				System.out.println("****胜负未分，在掷一次****");
			} else {
				if (gameStatus == WON) {
					System.out.println("玩家胜");
				} else {
					System.out.println("玩家输");
				}
				System.out.println("再玩一次？ (y|n)");
				if (System.in.read() == 'n') {
					System.out.println("游戏结束");
					break;
				}
				firstboll = true;
			}
		}
	}

	/**
	 * @Title Craps.java @Description:TODO(掷筛子) @return @throws
	 */
	private static int rollDice() {
		int roll = (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
		System.out.println("玩家掷出点数和：" + roll);
		return roll;
	}
}
