package edu.kmust.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BunnyAndOak0
 * TODO	哈夫曼树
 * Dec 28, 2020
 */
public class HuffmanTree {
	public static void main(String[] args) {
		int[] array = {13, 7, 8, 3, 29, 6, 1};
		Node root = createHuffmanTree(array);
		
		//测试
		preOrder(root); 	//67, 29, 38, 15, 7, 8, 23, 10, 4, 1, 3, 6, 13
		
	}
	
	//编写一个前序遍历的方法
	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		}else {
			System.out.println("是空树不能遍历~");
		}
	}
	
	/**
	 * @param array需要创建成赫夫曼树的数组
	 * @return 创建好后的赫夫曼树的root
	 */
	//创建赫夫曼树的方法
	public static Node createHuffmanTree(int[] array) {
		//第一步：为了操作方便
		//1.遍历array数组
		//2.将array的每个元素构建成一个Node
		//3.将Node放入到ArrayList中
		List<Node> nodes = new ArrayList<Node>();
		for(int value : array) {
			nodes.add(new Node(value));
		}
		
		
		//处理的过程是一个循环的过程
		while (nodes.size() > 1) {
			//排序 从小到大
			Collections.sort(nodes);
			System.out.println("nodes = " + nodes);
			
			//取出根节点权值最小的两颗二叉树
			//(1)取出权值最小的节点(二叉树)
			Node leftNode = nodes.get(0);
			//(2)取出第二小的节点(二叉树)
			Node rightNode = nodes.get(1);
			//(3)构建一颗新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			//(4)从ArrayList中删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//(5)将parent加入到nodes
			nodes.add(parent);
			
			System.out.println("处理后：" + nodes);
		}
		//返回赫夫曼树的root节点
		return nodes.get(0);
	}
}

//创建节点类
//为了让Node对象支持排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{
	int value;		//节点权值
	Node left;		//指向左子节点
	Node right;		//指向右子节点
	
	public Node(int value) {
		this.value = value;
	}
	
	//前序遍历，用于结果的检验
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	@Override
	public String toString() {  
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		//从小到大进行排序
		return this.value - o.value;
	}
	
	
}