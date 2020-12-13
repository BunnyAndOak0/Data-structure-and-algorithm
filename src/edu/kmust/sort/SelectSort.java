package edu.kmust.sort;

import java.util.Arrays;

/**
 * @author BunnyAndOak0
 * TODO 选择排序
 * Dec 9, 2020
 */
public class SelectSort {
	public static void main(String[] args) {
		int[] array = {101, 34, 119, 1};
		
		System.out.println("排序前：");
		System.out.println(Arrays.toString(array));
		selectSort(array);
	}

	//选择排序
	public static void selectSort(int[] array) {
		//使用嵌套循环解决
		//选择排序的时间复杂度O(n^2)
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			int min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (min > array[j]) {
					//说明假定的最小值并不是最小的
					min = array[j];		//重置min
					minIndex = j;		//重置minIndex
				}
			}
			//将最小值放在array[0]，即交换
			if (minIndex != i) {
				array[minIndex] = array[i];
				array[i] = min; 
			}
			
			System.out.println("第" + (i + 1) + "轮后：");
			System.out.println(Arrays.toString(array));
		}

		
		
		/*
		//使用逐步推导
		//原始的数组：101，34，119，1
		//第一轮：1，34，119，101
		int minIndex = 0;
		int min = array[0];
		for (int j = 0 + 1; j < array.length; j++) {
			if (min > array[j]) {
				//说明假定的最小值并不是最小的
				min = array[j];		//重置min
				minIndex = j;		//重置minIndex
			}
		}
		//将最小值放在array[0]，即交换
		if (minIndex != 0) {
			array[minIndex] = array[0];
			array[0] = min;
		}
		
		System.out.println("第一轮后：");
		System.out.println(Arrays.toString(array));
		
		//第二轮
		minIndex = 1;
		min = array[1];
		for (int j = 0 + 2; j < array.length; j++) {
			if (min > array[j]) {
				//说明假定的最小值并不是最小的
				min = array[j];		//重置min
				minIndex = j;		//重置minIndex
			}
		}
		//将最小值放在array[0]，即交换
		if (minIndex != 1) {
			array[minIndex] = array[1];
			array[1] = min; 
		}
		
		System.out.println("第二轮后：");
		System.out.println(Arrays.toString(array));
	
		//第三轮
		minIndex = 2;
		min = array[2];
		for (int j = 0 + 3; j < array.length; j++) {
			if (min > array[j]) {
				//说明假定的最小值并不是最小的
				min = array[j];		//重置min
				minIndex = j;		//重置minIndex
			}
		}
		//将最小值放在array[0]，即交换
		if (minIndex != 2) {
			array[minIndex] = array[2];
			array[2] = min; 
		}
		
		System.out.println("第三轮后：");
		System.out.println(Arrays.toString(array));
		*/
	}
}
