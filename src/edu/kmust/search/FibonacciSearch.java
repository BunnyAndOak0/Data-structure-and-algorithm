package edu.kmust.search;

import java.util.Arrays;
import java.util.Iterator;

import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

/**
 * @desc 斐波那契查找
 * @author BunnyAndOak0
 *
 */
public class FibonacciSearch {

	public static int maxSize = 20;
	public static void main(String[] args) {
		int[] array = {1, 8, 10, 89, 1000, 1234};

		System.out.println("index = " + fibSearch(array, 1234));
	}

	//因为后面需要用到mid=low+F(k-1)-1斐波那契数列，因此我们需要先获取一个斐波那契数列
	//使用非递归的方法得到一个斐波那契数列(大小为20)
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}
	
	//编写斐波那契查找算法
	/**
	 * @param array 数组
	 * @param key 我们需要的查找的关键码
	 * @return 返回对应的下标，如果没有就返回-1
	 * 1. 说明使用非递归的方法
	 */
	public static int fibSearch(int[] array, int key) {
		int low = 0;
		int high = array.length - 1;
		int k = 0;	//表示斐波那契分隔数值的下标
		int mid = 0;	//存放mid值
		int f[] = fib();	//获取到斐波那契数列
		//获取到斐波那契分隔数值的下标
		while (high > f[k] - 1) {
			k ++;
		}
		//因为f[k]可能大于数组a的长度，因此需要使用Arrays类，构造一个新的数组，并指向a[]
		//不足的部分会使用0进行填充
		int[] temp = Arrays.copyOf(array, f[k]);
		//实际上需要使用array数组的最后的数进行填充temp
		//举例：
		//temp = {1, 8, 10, 89, 1000, 1234, 0, 0, 0} => {1, 8, 10, 89, 1000, 1234, 1234, 1234}
		for(int i = high + 1; i < temp.length; i++) {
			temp[i] = array[high];
		}
		
		//使用while来循环处理，找到我们的书key
		while (low <= high) {	//只要条件满足就可以一直找
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) {		//应该继续向数组的前面查找（左边查找）
				high = mid - 1;
				//k--的原因
				//说明:
				//1.全部元素 = 前面的元素 + 后面的元素
				//2.f[k] = f[k - 1] + f[k - 2]
				//因为前面有f[k - 1]个元素，所以可以继续拆分f[k - 1] = f[k - 2] + f[k - 3]
				//即在f[k - 1]的前面继续查找，k--
				//即下次循环的时候 mid = f[k - 1 - 1] - 1
				k --;
			}else if (key > temp[mid]) {	//说明应该继续向数组的右边进行查找（右边）
				low = mid + 1;
				//k -= 2的原因
				//说明:
				//1.全部元素  = 前面的元素 + 后面的元素
				//2.f[k] = f[k - 1] + f[k - 2]
				//3.因为后面又f[k - 2]所以可以继续拆分f[k - 1] = f[k - 3] + f[k - 4]
				//4.即在f[k - 2]的前面可以继续进行查找k -= 2
				//5.即下次循环mid = f[k - 1 - 2] - 1
				k -= 2;
			} else {		//找到了
				//需要确定，返回的是哪个下标
				if (mid <= high) {
					return mid;
				}else {
					//是因为之前填充过了
					return high;
				}
			}
		}
		return -1;
	}

}
