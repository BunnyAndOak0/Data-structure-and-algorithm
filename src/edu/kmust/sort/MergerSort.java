package edu.kmust.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.xml.transform.Templates;

/**
 * @author BunnyAndOak0
 * TODO	归并排序
 * Dec 13, 2020
 */
public class MergerSort {
	public static void main(String[] args) {
//		int[] array = {8, 4, 5, 7, 1, 3, 6 ,2};
//		int[] temp = new int[array.length];
//		mergerSort(array, 0, array.length - 1, temp);
//		
//		System.out.println("归并排序后：" + Arrays.toString(array));
		
		//速度测试
		int[] array = new int[80000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 80000);
		}
		int[] temp = new int[array.length];
		//测试快速排序的执行速度
		System.out.println("归并排序");
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sdf.format(date1);
		System.out.println("排序前的时间为：" + date1Str);
		
		mergerSort(array, 0, array.length - 1, temp);
		
		Date date2 = new Date();
		String date2Str = sdf.format(date2);
		System.out.println("排序后的时间为：" + date2Str);
		
	
	}
	
	//分 + 和 的方法
	public static void mergerSort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;		//递归索引
			//向左递归进行分解
			mergerSort(array, left, mid, temp);
			
			//向右递归进行分解
			mergerSort(array, mid + 1, right, temp);
			
			//到合并的时候
			merger(array, left, mid, right, temp);
		}
		
	}
	
	
	//合并的方法
	/**
	 * @param array	待排序的数组
	 * @param left 左边有序序列的初始索引
	 * @param mid 中间的索引
	 * @param right 右边索引
	 * @param temp 做中转的数组
	 */
	public static void merger(int[] array, int left, int mid, int right, int[] temp) {
//		System.out.println("进行合并！！！");
		int i = left;	//初始化i，左边有序序列的初始索引
		int j = mid + 1;	//初始化j，右边有序序列的初始索引
		int t = 0;		//指向temp数组的当前索引
		
		//1.
		//先将左右两边的数据（有序）按照规则填充到temp数组
		//直到左右两边的有序序列，有一边处理完毕为止
		while (i <= mid && j <= right) {//继续
			//如果左边的有序序列的当前元素，小于等于	右边有序序列的当前元素
			//即将左边的当前元素拷贝到temp数据
			//然后t后移，i后移
			if (array[i] <= array[j]) {
				temp[t] = array[i];
				t += 1;
				i += 1;
			}else {		//反之将右边的有序序列的当前元素填充到temp数组
				temp[t] = array[j];
				t += 1;
				j += 1;
			}
		}
		
		//2.
		//把有剩余数据的一边的数据依次全部填充到temp
		while (i <= mid) {		//说明左边的剩余的元素，就全部填充到temp
			temp[t] = array[i];
			t += 1;
			i += 1;
		}
		
		while (j <= right) {	//说明右边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = array[j];
			t += 1;
			j += 1;
		}
		
		//3.
		//将temp数组的元素拷贝到array
		//注意，并不是每次都拷贝
		t = 0;
		int tempLeft = left;
		//第一次合并tempLeft = 0, right = 1
		//tempLeft = 1, right = 2
		//tempLeft = 2, right = 3
		//tempLeft = 0, right = 3
//		System.out.println("tempLeft=" + tempLeft + "  right=" + right);
		while (tempLeft <= right) {
			array[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
	}

}
