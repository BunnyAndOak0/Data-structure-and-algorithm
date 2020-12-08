package edu.kmust.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author BunnyAndOak0
 * TODO	冒泡排序
 * Dec 8, 2020
 */
public class BubbleSort {
	public static void main(String[] args) {
//		int array[] = {3, 9, -1, 10, -2};
//		
//		//测试冒泡排序
//		System.out.println("排序前的数组为：");
//		System.out.println(Arrays.toString(array));
//		bubbleSort(array);
//		System.out.println("排序后的数组为：");
//		System.out.println(Arrays.toString(array));
		
		//测试冒泡排序的速度O(n^2) 给80000个数据，进行测试
		//创建一个80000个的随机数组
		int[] array = new int[80000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 80000);    //会产生[0， 80000)之间的数
		}
		
	 	Date date1 = new Date();
	 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sdf.format(date1);
	 	System.out.println("排序前的时间：" + date1Str);
		
	 	bubbleSort(array);
	 	
	 	Date date2 = new Date();
		String date2Str = sdf.format(date2);
	 	System.out.println("排序后的时间：" + date2Str);
		
		
		//为了容易理解 将冒泡排序的过程进行展示
		//1.第一趟排序，就是将最大的数排在最后
		/*  int temp = 0;		//临时变量 用于做交换
		 	for (int j = 0; j < array.length - 1; j++) {
			//如果前面的数比后面的数大，则交换
			if (array[j] > array[j + 1]) {
				temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
			}
		}
		System.out.println("第一趟排序后的数组：");
		System.out.println(Arrays.toString(array));
	
		//第二趟排序，就是将第二大的数排在倒数第二位
		for (int j = 0; j < array.length - 1 - 1; j++) {
			//如果前面的数比后面的数大，则交换
			if (array[j] > array[j + 1]) {
				temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
			}
		}
		System.out.println("第二趟排序后的数组：");
		System.out.println(Arrays.toString(array));
	
		//后面的以此类推
		*/
		
		/*
		//O(n^2)
		System.out.println("一开始的数组顺序为：");
		System.out.println(Arrays.toString(array));
		int temp = 0;		//临时变量 用于做交换
		//进行优化 当某一趟排序没有交换位置的时候 说明已经排好了 不需要再进行交换 故直接输出
		boolean flag = false;	//标识变量，表示是否进行过交换
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					flag = true;
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
			System.out.println("第" + (i + 1) + "趟后的结果为：");
			System.out.println(Arrays.toString(array));
			if (!flag) {
				//说明在排序中，一次交换都没有发生过
				break;
			}else {
				flag = false;		//重置flag进行下次的判断
			}
		}
		*/
	}
	
	
	//将冒泡排序算法，封装为一个方法
	public static void bubbleSort(int[] array) {
//		System.out.println("一开始的数组顺序为：");
//		System.out.println(Arrays.toString(array));
		int temp = 0;		//临时变量 用于做交换
		/**
		 * 进行优化 当某一趟排序没有交换位置的时候 说明已经排好了 不需要再进行交换 故直接输出
		 */
		boolean flag = false;	//标识变量，表示是否进行过交换
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					flag = true;
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
//			System.out.println("第" + (i + 1) + "趟后的结果为：");
//			System.out.println(Arrays.toString(array));
			if (!flag) {
				//说明在排序中，一次交换都没有发生过
				break;
			}else {
				flag = false;		//重置flag进行下次的判断
			}
		}
	}
}
