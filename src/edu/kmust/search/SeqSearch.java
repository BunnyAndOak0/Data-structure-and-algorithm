package edu.kmust.search;

import java.util.Iterator;

/**
 * @author BunnyAndOak0
 * TODO 线性查找
 * Dec 14, 2020
 */
public class SeqSearch {
	public static void main(String[] args) {
		int[] array = {1, 9, 11, -1, 34, 89};		//没有顺序的数组
		
		int index = seqSearch(array, 11);
		if (index == -1) {
			System.out.println("没有找到相匹配的值");
		}else {
			System.out.println("找到了相应的值，下标为：" + index);
		}
		
	}
	
	//实现的线性查找是找到一个满足条件的值就返回
	public static int seqSearch(int[] array, int value) {
		//线性查找是逐一比对，发现有相同的值，就返回下标
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
