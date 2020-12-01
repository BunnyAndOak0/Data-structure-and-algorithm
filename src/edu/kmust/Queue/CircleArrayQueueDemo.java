package edu.kmust.Queue;

import java.util.Scanner;

/**
 * @author BunnyAndOak0
 * TODO
 * Sep 9, 2020
 */
public class CircleArrayQueueDemo {
	public static void main(String[] args) {
		//测试
		System.out.println("测试数组模拟环形队列的案例！");
		// 创建一个队列
		CircleArray circleArray = new CircleArray(4);	//设置为4，其队列的有效数据最大是3
		char key = ' '; // 接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		// 输出一个菜单
		while (loop) {
			System.out.println("s(show)：显示队列");
			System.out.println("e(exit)：退出程序");
			System.out.println("a(add)：添加数据队列");
			System.out.println("g(get)：取出队列");
			System.out.println("h(head)：查看队列头的数据");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				circleArray.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数：");
				int value = scanner.nextInt();
				circleArray.addQueue(value);
				break;
			case 'g':
				// 取出数据
				try {
					int res = circleArray.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
				break;
			case 'h':
				// 查看队列头
				try {
					int res = circleArray.headQueue();
					System.out.printf("队列头的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			case 'e':
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("程序退出");
	}
}

class CircleArray{
	private int maxSize;	//表示数组的最大容量
	//front变量的含义：font就指向队列的第一个元素，也就是说arr[front]
	//front的初始值为0
	private int front;
	//rear变量的含义：rear指向队列的最后一个元素的后一个位置，空出一个空间作为约定
	//rear初始值为0
	private int rear;	//队列尾
	
	private int[] arr;	//该数组用于存放数据，模拟队列
	
	public CircleArray(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	//判断是否满
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}
	
	//添加数据到队列中
	public void addQueue(int n) {
		if (isFull()) {
			System.out.println("队列满，不能加入数据！");
			return;
		}
		//直接将数据加入
		arr[rear] = n;
		//将rear后移，这里必须考虑取模
		rear = (rear + 1) % maxSize;
	}
	
	//从队列中取出数据
	public int getQueue() {
		//判断队列是否为空
		if (isEmpty()) {
			throw new RuntimeException("队列为空，不能取出数据！");
		}
		//需要分析出front是指向队列的第一个元素
		//1.先将front对应的值保存到一个临时变量
		//2.将front后移，考虑取余
		//3.将临时保存的变量返回
		int value = arr[front];
		//因为是环形数组，防止越界
		front = (front + 1) % maxSize;
		return value;
	}
	
	//显示队列中的所有数据
	public void showQueue() {
		//遍历
		if (isEmpty()) {
			System.out.println("队列为空，没有数据~~~");
			return;
		}
		//从front开始遍历
		for (int i = front; i < front + size(); i++) {
			//因为是一个环形队列 所以需要进行取模
			System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	//求出当前队列的有效数据个数
	public int size() {
		//rear = 1;
		//front = 0;
		//maxSize = 3;
		return (rear + maxSize - front) % maxSize;
	}
	
	//显示队列的头数据，注意不是取出数据
	public int headQueue() {
		//判断
		if (isEmpty()) {
			throw new RuntimeException("队列为空，没有数据！");
		}
		return arr[front];
	}
	
}
