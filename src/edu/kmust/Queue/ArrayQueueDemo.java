package edu.kmust.Queue;

import java.util.Scanner;

/**
 * @author BunnyAndOak0 TODO Aug 14, 2020
 */
public class ArrayQueueDemo {
	public static void main(String[] args) {
		// 测试
		// 创建一个队列
		ArrayQueue arrayQueue = new ArrayQueue(3);
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
				arrayQueue.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数：");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;
			case 'g':
				// 取出数据
				try {
					int res = arrayQueue.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
				break;
			case 'h':
				// 查看队列头
				try {
					int res = arrayQueue.headQueue();
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

//使用数组模拟队列 - 编写一个交ArrayQueue的类
class ArrayQueue {
	private int maxSize; // 表示数组的最大容量
	private int front; // 队列头
	private int rear;  // 队列尾
	private int[] arr; // 该数组用于存放数据，模拟队列

	// 创建队列的构造器
	public ArrayQueue(int arrMaxSize) {
		maxSize = arrMaxSize;
		arr = new int[maxSize];
		front = -1; // front 指向队列头部的前一个位置，并不包含
		rear = -1; // reaer 指向队列尾 指向队列尾的数据（即包含队列最后一个数据）
	}

	// 判断队列是否满
	public boolean isFull() {
		return rear == maxSize - 1;
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return rear == front;
	}

	// 添加数据到队列
	public void addQueue(int n) {
		// 判断队列是否满
		if (isFull()) {
			System.out.println("队列满，不能加入数据...");
		}
		rear++; // 让rear后移
		arr[rear] = n;
	}

	// 出队列
	public int getQueue() {
		// 判断队列是否空
		if (isEmpty()) {
			// 通过抛出异常来处理
			throw new RuntimeException("队列空 不可取数据");
		}
		front++; // front后移
		return arr[front];
	}

	// 显示队列的所有数据
	public void showQueue() {
		// 遍历
		if (isEmpty()) {
			System.out.println("队列是空的，没有数据...");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d] = %d\n", i, arr[i]);
		}
	}

	// 显示队列的头数据，注意不是取出数据
	public int headQueue() {
		// 判断
		if (isEmpty()) {
			throw new RuntimeException("队列空，没有数据~");
		}
		return arr[front + 1];
	}

}