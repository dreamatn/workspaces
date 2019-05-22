package main.java.com.dreamatn;

import java.util.Arrays;

/**
 * 
 * @Title BinarySearch.java
 * @Description TODO(二分搜索法)
 * @author ning_qb
 * @date 创建时间:2019年5月14日下午3:22:05
 * @version 1.0
 */
public class BinarySearch {
	private static Object QuickSort;

	public static int search(int[] number, int des) {
		int low = 0;
		int upper = number.length - 1;
		while(low <= upper) {
			int mid = (low + upper) / 2;
			if(number[mid] < des) {
				low = mid + 1;
			}else if(number[mid] > des) {
				upper = mid - 1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		int[] number = {1, 4, 2, 6, 7, 3, 5, 8};
		Arrays.sort(number);
		for (int i : number) {
			System.out.print(i + " ");
		}
		int find = search(number, 3);
		if(find == -1) {
			System.out.println('\n'+"找不到该数值");
		}else {
			System.out.println('\n'+"找到数值，索引为" + find);
		}
	}
}