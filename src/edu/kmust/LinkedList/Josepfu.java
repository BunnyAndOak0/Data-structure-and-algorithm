package edu.kmust.LinkedList;

import javax.naming.NoPermissionException;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author BunnyAndOak0
 * TODO 单向环形链表
 * Dec 2, 2020
 */
public class Josepfu {

	public static void main(String[] args) {
		//测试  构建环形链表和遍历是否正确
	 	CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);   //加入5个小孩节点
		circleSingleLinkedList.showBoy();
		
		//测试小孩出圈
		circleSingleLinkedList.countBoy(1, 2, 5);
	}

}

//创建一个环形的单向链表
class CircleSingleLinkedList{
	//创建一个first节点，当前没有编号
	private Boy first = new Boy(-1);
	
	//添加小孩节点，构建成一个环形链表
	public void addBoy(int nums) {
		//nums做一个数据校验
		if (nums < 1) {
			System.out.println("nums的值不正确");
			return;
		}
		
		Boy curBoy = null;   //辅助指针，帮助构建环形链表
		//使用for来创建环形链表
		for (int i = 1; i <= nums; i++) {
			//根据编号 创建小孩节点
			Boy boy = new Boy(i);
			//如果使第一个小孩
			if (i == 1) {
				first = boy;
				first.setNext(first);  //构成环
				curBoy = first;		//让curBoy指向一个小孩
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}
	
	//遍历当前环形链表
	public void showBoy() {
		//判断是否为空
		if (first == null) {
			System.out.println("链表为空！");
			return;
		}
		
		//因为first不能动 因为仍然使用辅助指针 完成遍历
		Boy curBoy = first;
		while (true) {
			System.out.printf("小孩的编号%d\n", curBoy.getNo());
			if (curBoy.getNext() == first) {
				//说明已经遍历完毕
				break;
			}else {
				curBoy = curBoy.getNext();	//curBoy后移
			}
		}
	}
	
	//根据用户的输入，计算出小孩出圈的顺序
	/**
	 * @param startNo 表示从第几个小孩开始数数
	 * @param countNum 表示数几下
	 * @param nums 表示最初有多少小孩在圈中
	 */
	public void countBoy(int startNo, int countNum, int nums) {
		//先对数据进行校验
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数输入有误，请重新输入");
			return;
		}
		
		//创建辅助指针，帮助完成小孩出圈
		Boy helper = first;
		//需要创建一个辅助指针（变量）helper，事先应该指向环形链表的最后这个节点
		while (true) {
			if (helper.getNext() == first) {
				break;
			}
			
			helper = helper.getNext();
		}
		//报数前，先让first和helper移动k-1次
		for (int j = 0; j < startNo - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		
		//当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
		//循环操作，直到圈中只有一个节点
		while (true) {
			if (helper == first) {
				//说明圈中只有一个节点
				break;
			}
			
			//让first、helper移动countNum - 1次，然后出圈
			for (int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			
			//此时，first指向的节点就是小孩要出圈节点
			System.out.printf("小孩%d出圈\n", first.getNo(), first);
		
			//这时将first指向的小孩节点出圈
			first = first.getNext();
			helper.setNext(first);
		}
		
		System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());
	}
}


//创建一个Boy类，表示一个节点
class Boy{
	private  int no;    //编号
	private Boy next;   //指向下一个节点  默认为空
	
	public Boy (int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}
	
}
