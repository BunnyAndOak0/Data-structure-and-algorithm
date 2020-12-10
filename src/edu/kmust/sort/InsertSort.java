package edu.kmust.sort;

/**
 * @author BunnyAndOak0
 * TODO 插入排序的算法
 * Dec 11, 2020
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] array = {101, 34, 119, 1};
	}
	
	//插入排序
	public static void insertSort(int[] array) {
		//使用逐步推导的方式
		//第一轮 {101, 34, 119, 1} => {34, 101, 119, 1}
		
		
		//定义待插入的数
		int insertVal = array[1];
		int insertIndex = 0;		//也就是array[1]的前面这个数的下标
		
		//给insertVal找到插入的位置
		/**
		 * 说明：
		 * 1.insertIndex >= 0保证给insertVal < array[insertIndex]
		 * 2.insertVal < array[insertIndex] 待插入的数，还没有找到插入的位置 
		 * 3.就需要将array[insertIndex]后移
		 */
		while (insertIndex >= 0 && insertVal < array[insertIndex]) {
			array[insertIndex + 1] = array[insertIndex];
			insertIndex --; 
		}
		//当退出while循环的时候，说明插入的位置找到，insertIndex + 1
		 
	}
}
