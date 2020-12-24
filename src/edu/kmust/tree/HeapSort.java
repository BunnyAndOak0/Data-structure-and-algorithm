package edu.kmust.tree;

/**
 * @author BunnyAndOak0
 * TODO 堆排序
 * Dec 25, 2020
 */
public class HeapSort {
	public static void main(String[] args) {
		//要求将数组进行升序排列（大顶堆）
		int arr[] = {4, 6, 8, 5, 9};
	}
	
	//编写一个堆排序的方法
	public static void heapSort(int arr[]) {
		System.out.println("堆排序");
	}
	
	
	//将一个数组（对应的是二叉树），调整为一个大顶堆
	/**
	 * 功能：完成将以i对应的非叶子节点的数调整为大顶堆
	 * @param arr 待调整的数组
	 * @param i	表示非叶子节点在数组中的索引
	 * @param length 表示对多少个元素进行调整，length是在逐渐的减少
	 */
	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];		//先取出当前元素的值，保存在临时变量中 
		//开始调整
		//说明：
		//1.k = i * 2 + 1，k是i节点的左子节点
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) {		//说明左子节点的值小于右子节点的值
				k ++;		//k 就指向右子节点
			}
			if (arr[k] > temp) {		//如果子节点大于父节点
				arr[i] = arr[k];		//把较大的值赋给当前的节点
				i = k;		//!!!  让i指向k，然后循环比较
			}else {
				break;
			}
		}
		//当for循环结束后，已经将以i为父节点的树的最大值放在了最顶上(局部的)
		arr[i] = temp;		//将temp值放到调整后的位置
	}
	
}
