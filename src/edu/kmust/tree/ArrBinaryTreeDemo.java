package edu.kmust.tree;

/**
 * @author BunnyAndOak0
 * TODO 以数组的方式来存储二叉树
 * Dec 22, 2020
 */
public class ArrBinaryTreeDemo {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		
		//创建ArrayBinaryTree
		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
		arrayBinaryTree.preOrder(); 	//1, 2, 4, 5, 3, 6, 7
	}
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树的遍历
class ArrayBinaryTree{
	private int[] array;	//存储数据结点的数组

	public ArrayBinaryTree(int[] array) {
		super();
		this.array = array;
	}
	
	//重载preOrder方法
	public void preOrder() {
		this.preOrder(0);
	}
	
	//编写一个方法，完成顺序存储二叉树的前序遍历
	//index表示数组的下标
	public void preOrder(int index) {
		//如果数组为空，或者array.length = 0
		if (array == null || array.length == 0) {
			System.out.println("数组为空，不能按照二叉树的前序遍历");
		}
		//输出当前这个元素
		System.out.println(array[index]);
		
		//向左递归遍历
		if (index * 2 + 1 < array.length) {
			preOrder(index * 2 + 1);
		}
		
		//向右递归遍历
		if (index * 2 + 2 < array.length) {
			preOrder(index * 2 + 2);
		}
	}
}
