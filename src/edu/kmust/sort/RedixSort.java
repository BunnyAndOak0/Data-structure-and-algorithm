package edu.kmust.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * @author BunnyAndOak0
 * TODO	基数排序
 * Dec 13, 2020
 */
public class RedixSort {
	public static void main(String[] args) {
//		int[] array = {53, 3, 542, 748, 14, 214};
//		
//		redixSort(array);
		
		//速度测试
		int[] array = new int[80000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 80000);
		}
		System.out.println("基数排序：");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sdf.format(date1);
		System.out.println("排序前的时间为：" + date1Str);
		
		redixSort(array);
		
		Date date2 = new Date();
		String date2Str = sdf.format(date2);
		System.out.println("排序后的时间为：" + date2Str);
		
	}

	//基数排序方法
	public static void redixSort(int[] array) {
		//根据前面的推导过程，可以得到最终的基数排序代码
		//1.先得到数组中最大的数的位数
		int max = array[0];		//假设第一个数就是最大数
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		//得到最大数是几位数
		int maxLength = (max + "").length();
		
		//定义一个二维数组 表示10个桶 每个桶就是一个一维数组
		//说明：
		//1.二维数组包含10个一维数组
		//2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为array.length
		//3.很明确基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][array.length];
		
		//为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
		//例如：bucketElementCounts[0]记录的就是bucket[0]桶的放入的数据个数
		int[] bucketElementCounts = new int[10];
		
		//使用循环将代码进行处理
		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
			//针对每个元素对应的位进行排序，第一次是个位，第二次是十位，第三次是百位
			for(int j = 0; j < array.length; j++) {
				//取出每个元素的对应位的值
				int digitOfElement = array[j] / n % 10;
				//放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
				bucketElementCounts[digitOfElement] ++;
			}
			//按照桶的顺序（一维数组下标依次取出的数据，放入原来的数组）
			int index = 0;
			//遍历每一个桶，并将桶中的数据放入原数组
			for (int k = 0; k < bucketElementCounts.length; k++) {
				//如果桶中有数据，才放入到原数组
				if (bucketElementCounts[k] != 0) {
					//循环该桶，即第k个桶(即第k个一维数组)，放入
					for (int l = 0; l < bucketElementCounts[k]; l++) {
						//取出元素放入到array中
						array[index] = bucket[k][l];
						index ++;
					}
				}
				//第i轮处理后，需要将每个bucketElementCounts[k] = 0 也就是进行清零处理
				bucketElementCounts[k] = 0;
			}
//			System.out.println("第" + (i + 1) + "轮，排序处理后：" + Arrays.toString(array));
		}
		
		
		/**
		//第一轮排序(针对每个元素的个位数进行排序)
		
		//定义一个二维数组 表示10个桶 每个桶就是一个一维数组
		//说明：
		//1.二维数组包含10个一维数组
		//2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为array.length
		//3.很明确基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][array.length];
		
		//为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
		//例如：bucketElementCounts[0]记录的就是bucket[0]桶的放入的数据个数
		int[] bucketElementCounts = new int[10];
		
		//第一轮(针对每个元素的个位进行排序的处理)
		for(int j = 0; j < array.length; j++) {
			//取出每个元素的个位的值
			int digitOfElement = array[j] / 1 % 10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
			bucketElementCounts[digitOfElement] ++;
		}
		//按照桶的顺序（一维数组下标依次取出的数据，放入原来的数组）
		int index = 0;
		//遍历每一个桶，并将桶中的数据放入原数组
		for (int k = 0; k < bucketElementCounts.length; k++) {
			//如果桶中有数据，才放入到原数组
			if (bucketElementCounts[k] != 0) {
				//循环该桶，即第k个桶(即第k个一维数组)，放入
				for (int l = 0; l < bucketElementCounts[k]; l++) {
					//取出元素放入到array中
					array[index] = bucket[k][l];
					index ++;
				}
			}
			//第一轮处理后，需要将每个bucketElementCounts[k] = 0 也就是进行清零处理
			bucketElementCounts[k] = 0;
		}
		System.out.println("第一轮（对个位的排序处理）：" + Arrays.toString(array));
		
		//==============================================================================
		
		//第二轮(针对每个元素十位进行排序的处理)
		for(int j = 0; j < array.length; j++) {
			//取出每个元素的十位的值
			int digitOfElement = array[j] / 10 % 10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
			bucketElementCounts[digitOfElement] ++;
		}
		//按照桶的顺序（一维数组下标依次取出的数据，放入原来的数组）
		index = 0;
		//遍历每一个桶，并将桶中的数据放入原数组
		for (int k = 0; k < bucketElementCounts.length; k++) {
			//如果桶中有数据，才放入到原数组
			if (bucketElementCounts[k] != 0) {
				//循环该桶，即第k个桶(即第k个一维数组)，放入
				for (int l = 0; l < bucketElementCounts[k]; l++) {
					//取出元素放入到array中
					array[index] = bucket[k][l];
					index ++;
				}
			}
			bucketElementCounts[k] = 0;
		}
		System.out.println("第二轮（对个位的排序处理）：" + Arrays.toString(array));
	
		//==============================================================================
		//第三轮(针对每个元素百位进行排序的处理)
		for(int j = 0; j < array.length; j++) {
			//取出每个元素的百位的值
			int digitOfElement = array[j] / 100 % 10;
			//放入到对应的桶中
			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
			bucketElementCounts[digitOfElement] ++;
		}
		//按照桶的顺序（一维数组下标依次取出的数据，放入原来的数组）
		index = 0;
		//遍历每一个桶，并将桶中的数据放入原数组
		for (int k = 0; k < bucketElementCounts.length; k++) {
			//如果桶中有数据，才放入到原数组
			if (bucketElementCounts[k] != 0) {
				//循环该桶，即第k个桶(即第k个一维数组)，放入
				for (int l = 0; l < bucketElementCounts[k]; l++) {
					//取出元素放入到array中
					array[index] = bucket[k][l];
					index ++;
				}
			}
			bucketElementCounts[k] = 0;
		}
		System.out.println("第三轮（对个位的排序处理）：" + Arrays.toString(array));
	**/
		
	
	}
}
