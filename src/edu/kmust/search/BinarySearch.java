package edu.kmust.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BunnyAndOak0
 * TODO 二分查找
 * 注意：使用二分查找的前提是，该数组是有序的
 * Dec 14, 2020
 */
public class BinarySearch {
	public static void main(String[] args) {
//		int[] array = {1, 8, 10, 89, 1000, 1234};
//		
//		int resultIndex = binarySearch(array, 0, array.length - 1, 1000);
//		System.out.println("resultIndex = " + resultIndex);
		
		//升级版	可以实现多个相同的数的查找
		int[] array = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
		
		ArrayList<Integer> resIndexList = binarySearch2(array, 0, array.length - 1, 1000);
		System.out.println("resultIndex = " + resIndexList);
	}
	
	//二分查找算法
	/**
	 * @param array	数组
	 * @param left 左边的索引
	 * @param right 右边的索引
	 * @param findVal 要查找的值
	 * @return 如果找到就返回下标，如果没有找到，就返回-1
	 */
	public static int binarySearch(int[] array, int left, int right, int findVal) {
		//当left > right时，说明递归完整个数组，但是没有找到
		if (left > right) {
			return -1;
		}
		
		int mid = (left + right) / 2;
		int midVal = array[mid];
		
		if (findVal > midVal) {		//需要向右边递归
			return binarySearch(array, mid + 1, right, findVal);
		}else if (findVal < midVal) {	//需要向左递归
			return binarySearch(array, left, mid - 1, findVal);
		}else {
			return mid;
		}
	}
	
	/**
	 * 二分查找的拓展
	 * 有多个相同的数值的时候，将所有的数值都查找到
	 * 思路分析：
	 * 1.找到mid值的时候，不要马上返回
	 * 2.向mid索引值的左边扫描，将所有满足1000的元素的下标加入到集合中ArrayList
	 * 3.向mid索引值的右边扫描，将所有满足1000的元素的下标加入到集合中ArrayList
	 * 4.将ArrayList返回
	 */
	public static ArrayList<Integer> binarySearch2(int[] array, int left, int right, int findVal) {
		//当left > right时，说明递归完整个数组，但是没有找到
		if (left > right) {
			return new ArrayList<Integer>();
		}
		
		int mid = (left + right) / 2;
		int midVal = array[mid];
		
		if (findVal > midVal) {		//需要向右边递归
			return binarySearch2(array, mid + 1, right, findVal);
		}else if (findVal < midVal) {	//需要向左递归
			return binarySearch2(array, left, mid - 1, findVal);
		}else {
			 /** 思路分析：
			 * 1.找到mid值的时候，不要马上返回
			 * 2.向mid索引值的左边扫描，将所有满足1000的元素的下标加入到集合中ArrayList
			 * 3.向mid索引值的右边扫描，将所有满足1000的元素的下标加入到集合中ArrayList
			 * 4.将ArrayList返回
			 **/
			ArrayList<Integer> resIndexList = new ArrayList<Integer>();
			//向mid索引值的左边扫描，将满足1000，的元素的下标，加入到集合ArrayList中
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || array[temp] != findVal) {
					//退出
					break;
				}
				//否则，就将temp放入到resIndexList中
				resIndexList.add(temp);
				temp -= 1;	//temp左移
			}
			resIndexList.add(mid);
			
			//向mid索引的值的右边扫描，将所有满足1000的元素的下标，加入到集合ArrayList中
			temp = mid + 1;
			while (true) {		//退出
				if (temp > array.length - 1 || array[temp] != findVal) {
					break;
				}
				//否则，就temp放入到resIndexList中
				resIndexList.add(temp);
				temp += 1;
			}
			return resIndexList;
		}
	}
}
