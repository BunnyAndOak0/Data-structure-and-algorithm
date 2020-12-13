package edu.kmust.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.swing.text.GapContent;

/**
 * @author BunnyAndOak0
 * TODO 希尔排序
 * Dec 12, 2020
 */
public class ShellSort {
	public static void main(String[] args) {
//		int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		
//		shellSort(array);
//		shellSort2(array);
		
		//速度测试
		int[] array = new int[80000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 80000);
		}
		
		System.out.println("希尔排序");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sdf.format(date1);
		System.out.println("排序前的时间为：" + date1Str);
		
//		shellSort(array);		//交换式
		shellSort2(array);		//移位式
		
		Date date2 = new Date();
		String date2Str = sdf.format(date2);
		System.out.println("排序后的时间为：" + date2Str);
		
	}
	
	//使用逐步推导的方式进行shell排序
	//希尔排序，采用的是交换法
	public static void shellSort(int[] array) {
		int temp = 0;
		int count = 0;
		
		//根据前面的分析使用循环处理
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < array.length; i++) {
				//遍历各组中所有的元素，共有五组，每组有两个元素，步长为5 
				for (int j = i - gap; j >= 0; j -= gap) {
					//如果当前元素大于加上步长的元素，说明需要交换
					if (array[j] > array[j + gap]) {
						temp = array[j];
						array[j] = array[j + gap];
						array[j + gap] = temp;
					}
				}
			}
//			count += 1;
//			System.out.println("希尔排序第" + count + "轮之后：" + Arrays.toString(array));
		}
		
		
		/**
		//希尔排序的第一轮
		//因为第一轮排序，是将10个数据分为了5组
		for (int i = 5; i < array.length; i++) {
			//遍历各组中所有的元素，共有五组，每组有两个元素，步长为5 
			for (int j = i - 5; j >= 0; j -= 5) {
				//如果当前元素大于加上步长的元素，说明需要交换
				if (array[j] > array[j + 5]) {
					temp = array[j];
					array[j] = array[j + 5];
					array[j + 5] = temp;
				}
			}
		}
		System.out.println("希尔排序第一轮之后：" + Arrays.toString(array));
		
		//希尔排序的第二轮
		//因为第二轮排序，是将5 / 2个数据分为了2组
		for (int i = 2; i < array.length; i++) {
			//遍历各组中所有的元素，共有五组，每组有两个元素，步长为2 
			for (int j = i - 2; j >= 0; j -= 2) {
				//如果当前元素大于加上步长的元素，说明需要交换
				if (array[j] > array[j + 2]) {
					temp = array[j];
					array[j] = array[j + 2];
					array[j + 2] = temp;
				}
			}
		}
		System.out.println("希尔排序第二 轮之后：" + Arrays.toString(array));
		
		
		//希尔排序的第三轮
		//因为第三轮排序，是将10个数据分为了1组
		for (int i = 1; i < array.length; i++) {
			//遍历各组中所有的元素，共有五组，每组有两个元素，步长为5 
			for (int j = i - 1; j >= 0; j -= 1) {
				//如果当前元素大于加上步长的元素，说明需要交换
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		System.out.println("希尔排序第三轮之后：" + Arrays.toString(array));
		**/
	}
	
	//对交换式的希尔排序进行优化 -> 移位法
	public static void shellSort2(int[] array) {
		//增量gap，逐步缩小增量
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			//从第gap个元素开始，对其所在的组进行直接插入排序
			for (int i = gap; i < array.length; i++) {
				int j = i;
				int temp = array[i];
				if (array[j] < array[j - gap]) {
					while (j - gap >= 0 && temp < array[j - gap]) {
						//移动
						array[j] = array[j - gap];
						j -= gap;
 					}
					//当退出while循环后，就给temp找到了插入的位置
					array[j] = temp;
				}
			}
		}
//		System.out.println("移位法希尔的结果为：" + Arrays.toString(array));
	}
	
	
	
	
	
	
	
	
	
}
