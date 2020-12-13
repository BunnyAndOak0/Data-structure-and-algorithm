package edu.kmust.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 直接插入排序
 * @author BunnyAndOak0
 *
 */
public class InsertSort {
	public static void main(String[] args) {
//		int[] array = {101, 34, 119, 1, -1, 89};
		
//		insertSort(array);
		
		//测试速度
		int[] array = new int[80000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 80000);
		}
		
		System.out.println("直接插入排序");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sdf.format(date1);
		System.out.println("排序前的时间为：" + date1Str);
		
		insertSort(array);
		
		Date date2 = new Date();
		String date2Str = sdf.format(date2);
		System.out.println("排序后的时间为：" + date2Str);
		
	}
	
	
	//插入排序
	public static void insertSort(int[] array) {
		//使用for循环来简化代码
		int insertVal = 0;
		int insertIndex = 0;
		for(int i = 1; i < array.length; i++) {
			insertVal = array[i];
			insertIndex =  i - 1;
			while (insertIndex >= 0 && insertVal < array[insertIndex]) {
				array[insertIndex + 1] = array[insertIndex];
				insertIndex --;
			}
			
			//进行优化  判断是否需要赋值
			if (insertVal + 1 != i) {
				array[insertIndex + 1] = insertVal;
			}
 //			System.out.println("第" + i + "轮插入之后：");
//			System.out.println(Arrays.toString(array));
		}
		
		
		
		
		/**
		//使用逐步推导
		//第一轮{101, 34, 119, 1} => {34, 101, 119, 1}
		//定义待插入的数
		int insertVal = array[1];
		int insertIndex = 1 - 1;	//即array[1]的前面这个数的下标
		
		//给insertVal 找到插入的位置
		
		 // 说明：
		 // 1.insertIndex >= 0保证在给insertVal找插入位置，不越界
		 // 2.insertVal < array[insertIndex]待插入的数，还没有找到插入位置
		 // 3.需要将array[insertIndex]后移
		while (insertIndex >= 0 && insertVal < array[insertIndex]) {
			array[insertIndex + 1] = array[insertIndex];
			insertIndex --;
		}
		//当退出while循环的时候，说明位置找到，insertIndex + 1
		array[insertIndex + 1] = insertVal;
		System.out.println("第一轮插入后：");
		System.out.println(Arrays.toString(array));
		
		//第二轮
		insertVal = array[2];
		insertIndex =  2 - 1;
		while (insertIndex >= 0 && insertVal < array[insertIndex]) {
			array[insertIndex + 1] = array[insertIndex];
			insertIndex --;
		}
		array[insertIndex + 1] = insertVal;
		System.out.println("第二轮插入之后：");
		System.out.println(Arrays.toString(array));
		
		//第三轮
		insertVal = array[3];
		insertIndex =  3 - 1;
		while (insertIndex >= 0 && insertVal < array[insertIndex]) {
			array[insertIndex + 1] = array[insertIndex];
			insertIndex --;
		}
		array[insertIndex + 1] = insertVal;
		System.out.println("第三轮插入之后：");
		System.out.println(Arrays.toString(array));
		**/
	}
}
