package edu.kmust.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author BunnyAndOak0
 * TODO 快速排序
 * Dec 12, 2020
 */
public class QuickSort {
	public static void main(String[] args) {
//		int[] array = {-9, 78, 0, 23, -567, 70};
//		int[] array = {1, 2, 3, 4, 5, 15, 18, 27, 31, 14, 62};
//		quickSort(array, 0, array.length - 1);
//		System.out.println("快速排序完之后：" + Arrays.toString(array));
	
		
		int[] array = new int[80000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 80000);
		}
		
		//测试快速排序的执行速度
		System.out.println("快速排序");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sdf.format(date1);
		System.out.println("排序前的时间为：" + date1Str);
		
		quickSort(array, 0, array.length - 1);
		
		Date date2 = new Date();
		String date2Str = sdf.format(date2);
		System.out.println("排序后的时间为：" + date2Str);
		
	}
	
	public static void quickSort(int[] array, int left, int right) {
		int l = left;	//左下标
		int r = right;	//右下标
		int pivot = array[(left + right) / 2];	//中 轴值
		int temp = 0;	//临时变量用于交换
		//while循环的目的是为了让比pivot值小的放在左边
		//让比pivot值大的放在右边
		while (l < r) {
			//在pivot的左边一直找，找到大于等于pivot值才退出
			while (array[l] < pivot) {
				l += 1;
			}
			//在pivot的右边一直找，找到小于等于pivot值才退出
			while (array[r] > pivot) {
				r -= 1;
			}
			//如果l >= r说明pivot的左右两边的值已经按照左边全部是
			//小于等于pivot的值，右边全部是大于等于pivot的值
			if (l >= r) {
				break;
			}
			
			//进行交换
			temp = array[l];
			array[l] = array[r];
			array[r] = temp;
			
			//如果交换完后发现这个array[l] == pivot的值
			//此时r--，相当于前移一下
			if (array[l] == pivot) {
				r -= 1;
			}
			//如果交换完后发现这个array[r] == pivot的值
			//此时r--，相当于前移一下
			if (array[r] == pivot) {
				l += 1;
			}
		}
		
		//如果l == r，必须l++，r--，否则会出现栈溢出(是为了下一步的递归写的)
		if (l == r) {
			l += 1;
			r -= 1;
		}
		
		//向左递归
		if (left < r) {
			quickSort(array, left, r);
		}
		
		//向右递归
		if (right > l) {
			quickSort(array, l, right);
		}
	}
}
