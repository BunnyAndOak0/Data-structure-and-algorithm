package edu.kmust.search;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 插值查找算法
 * @author BunnyAndOak0
 *
 */
public class InsertValueSearch {
	public static void main(String[] args) {
//		int[] array = new int[100];
//		
//		for (int i = 0; i < array.length; i++) {
//			array[i] = i + 1;
//		}
//		System.out.println(Arrays.toString(array));
		
		int[] array = {1, 8, 10, 89, 1000, 2000, 1234};
		
		int index = insertValueSearch(array, 0, array.length - 1, 1234);
//		int index = insertValueSearch(array, 0, array.length - 1, 123);       //会有bug，会产生复数
		System.out.println("index = " + index);
	}
	
	//编写插值查找算法
	/**
	 * @param array 数组  
	 * @param left 左边的索引
	 * @param right 右边的索引
	 * @param findVal 查找值
	 * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
	 * 1. 说明：插值查找算法也要求数组是有序的
	 * emmmm 感觉有bug
	 */
	public static int insertValueSearch(int[] array, int left, int right, int findVal) {
		System.out.println("嘿~~~这是插值查找");
		//注意：findVal < array[0]和findVal > array[array.length - 1]是必须需要的
		//否则得到的mid可能会越界（比如传入的一个数超级大的时候）
		if (left > right || findVal < array[0] || findVal > array[array.length - 1]) {
			return -1;
		}
		
		//求出mid
		int mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left]); 
		int midVal = array[mid];
		if (findVal > midVal) {		//说明应该向右边进行递归查找(此时的数组是从小到大的)
			System.out.println("mid = " + mid + ", right = " + right);
			return insertValueSearch(array, mid + 1, right, findVal);
		}else if (findVal < midVal) {	//说明向左边递归查找
			return insertValueSearch(array, left, mid - 1, findVal);
		}else {
			return mid;
		}
		
	}
}
