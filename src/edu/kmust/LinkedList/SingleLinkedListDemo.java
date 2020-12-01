package edu.kmust.LinkedList;
import java.awt.HeadlessException;
import java.util.Stack;

/**
 * @author BunnyAndOak0
 * TODO
 * Sep 10, 2020
 */
public class SingleLinkedListDemo {
	public static void main(String[] args) {
		//进行测试
		//先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		//创建要给链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		
//		//加入
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero4);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);
//		
//		//显示
//		singleLinkedList.list();
		
		//加入
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		
		//显示
		singleLinkedList.list();
		
//		System.out.println("反转过后：");
//		
////		reverseList(singleLinkedList.getHead());
//		Test.reverseList(singleLinkedList.getHead());
//		
//		singleLinkedList.list();
		
		System.out.println("逆序打印之后：");
		reversePrint(singleLinkedList.getHead());
		
//		//测试修改节点的代码
//		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~");
//		singleLinkedList.update(newHeroNode);
//		
//		System.out.println("修改后的链表情况！");
//		singleLinkedList.list();
//		
//		//删除一个节点
//		singleLinkedList.del(1);
//		singleLinkedList.del(4);
////		singleLinkedList.del(2);
//		System.out.println("删除后的链表情况");
//		singleLinkedList.list();
//		
//		//求单链表的有效结点的个数
//		System.out.println("获取到的有效节点的个数为：" + getLength(singleLinkedList.getHead()));
//	
//		//测试一下，看是否得到了倒数第k个节点
//		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
//		System.out.println("获取到的倒数第k个节点为：" + res);
	}
	
	//方法：获取到单链表的节点个数（如果是带头结点的链表 需求不统计头节点）
	//head是链表的头节点
	//return 返回的是有效节点的个数
	public static int getLength(HeroNode head) {
		if (head.next == null) {
			return 0;
		}
		int length = 0;
		//定义一个辅助的变量
		//这里没有统计头节点
		HeroNode cur = head.next;
		while (cur != null) {
			length ++;
			cur = cur.next;		//遍历
		}
		return length;
	}
	
	//查找单链表中的倒数第k个结点（新浪的面试题）
	/*
	 * 思路：
	 * 1.编写一个方法，接收head节点，同时接受一个index
	 * 2.index表示的是倒数第index个节点
	 * 3.先把链表从头到尾辩能力，得到链表的总的长度getLength
	 * 4.得到size后，从链表的第一个开始遍历（size-index）个，就可以得到
	 * 5.如果找到了就返回该节点，否则返回null
	 */
	public static HeroNode findLastIndexNode(HeroNode head, int index) {
		//如果链表为空，返回null
		if (head.next == null) {
			return null;
		}
		//第一次遍历得到链表的长度（个数）
		int size = getLength(head);
		//第二次遍历size-index位置，就是倒数的第K个节点
		//先做一个index的校验
		if (index <= 0 || index > size) {
			return null;
		}
		//定义一个辅助变量
		HeroNode cur = head.next;
		//for循环定位到倒数的index
		for (int i = 0; i < size - index; i++) {
			cur = cur.next;
		}
		return cur;
	}
	
	//将单链表进行翻转
	public static void reverseList(HeroNode head) {
		//如果当前链表为空 或者 只有一个节点 无需反转 直接返回
		if (head.next == null || head.next.next == null) {
			return ;
		}
		
		//定义一个辅助的指针（帮助遍历原来的链表）
		HeroNode cur = head.next;
		//指向当前节点的下一个节点
		HeroNode next = null;
		HeroNode reverseHead = new HeroNode(0, "", "");
		//遍历原来的链表 每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
		while (cur != null) {
			//先暂时保存当前节点的下一个节点 因为后面需要使用
			next = cur.next;
			//将cur的下一个节点指向新的链表的最前端
			cur.next = reverseHead.next; 
			System.out.println("1111" + reverseHead.next);
			reverseHead.next = cur;
			System.out.println("222" + reverseHead.next);
			cur = next;
		}
		//将head.next指向reverseHead.next 实现反转
		head.next = reverseHead.next;
	}
	
	//使用方式2：利用栈的数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，实现逆序打印的效果
	public static void reversePrint(HeroNode head) {
		if (head.next == null) {
			//说明是一个空链表 无法打印
			return;
		}
		//先创建一个栈，将各个节点压入栈中
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		//将链表的所有节点压入栈中
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		//将栈中的节点进行打印 pop出栈
		while (stack.size() > 0) {
			//先进后出
			System.out.println(stack.pop());
		}
	}
}

//定义SingleLinkedList  管理英雄
class SingleLinkedList{
	//先初始化一个头节点 头节点不要动  不存放具体的数据
	private HeroNode head = new HeroNode(0, "", "");
	
	//返回头节点
	public HeroNode getHead() {
		return head;
	}
	
	//添加节点到单向链表
	//思路：不考虑编号的顺序时
	//1.找到当前链表的最后节点
	//2.将最后这个节点next指向新的节点
	public void add(HeroNode heroNode) {
		//因为head节点不能动，因此我们需要一个辅助指针 temp
		HeroNode temp = head;
		//遍历链表，找到最后
		while (true) {
			//找到链表的最后
			if (temp.next == null) {
				break;
			}
			//如果没有找到最后，就把temp后移
			temp = temp.next;
		}
		//当退出while循环时 temp就指向了链表最后
		//将最后这个节点的next，指向了新的节点
		temp.next = heroNode;
	}
	
	//第二种添加英雄的方式，根据排名将英雄插入到指定位置
	//（如果有这个排名，则添加失败，并给出提示）
	public void addByOrder(HeroNode heroNode) {
		//因为头节点不能动,因此仍然通过一个辅助指针（变量）来帮助找到添加的位置
		//因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
		HeroNode temp = head;
		boolean flag = false;	//标志添加的编号是否存在，默认为false
		while (true) {
			if (temp.next == null) {
				//说明temp已经在链表的最后
				break;
			}
			if (temp.next.no > heroNode.no) {	//位置找到，就在temp的后面插入
				break;
			}else if (temp.next.no == heroNode.no) {	//说明希望添加的heroNode的编号已然存在
				//说明编号存在
				flag = true;
				break;
			}
			temp = temp.next;	//后移 遍历当前的链表
		}
		//判断flag
		if (flag == true) {	//不能添加 编号存在
			System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.no);
		}else {
			//加入到链表中
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}
	
	//修改节点的信息 根据no编号来修改 即no编号不能改
	//1.根据newHeroNode的no来修改即可
	public void update(HeroNode newHeroNode) {
		//判断是否为空
		if (head.next == null) {
			System.out.println("链表为空！");
			return;
		}
		//找到需要修改的节点  根据no编号
		//先定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false;	//表示是否找到该节点
		while (true) {
			if (temp == null) {	//说明聊表已经遍历结束
				break;
			}
			
			if (temp.no == newHeroNode.no) {
				//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag判断是否找到要修改的节点
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		}else {	//没有找到
			System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
		}
	}
	
	//删除节点  被删除的节点不会有其他的引用指向  会被垃圾回收机制回收
	//head不能动 因为需要一个temp辅助节点找到待删除节点的前一个结点
	//在比较时temp.next.no 和 需要删除的节点的no比较
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;	//标志是否找到待删除节点
		while (true) {
			if (temp.next == null) {	//已经到链表的最后 需要退出
				break;
			}
			
			if (temp.next.no == no) {
				//知道了待删除结点的前一个结点temp
				flag = true;
				break;
			}
			temp = temp.next;  //实现遍历
		}
		//判断flag
		if (flag) {
			//找到  可以删除
			temp.next = temp.next.next;
		}else {
			System.out.printf("要删除的%d节点不存在\n", no);
		}
	}
	
	//显示链表[遍历]
		public void list() {
			//判断链表是否为空
			if(head.next == null) {
				System.out.println("链表为空");
				return;
			}
		 	//因为头节点，不能动，因此我们需要一个辅助变量来遍历
			HeroNode temp = head.next;
			while(true) {
				//判断是否到链表最后
				if(temp == null) {
					break;
				}
				//输出节点的信息
				System.out.println(temp);
				//将temp后移， 一定小心
				temp = temp.next;
			}
		}
}


//定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;		//指向下一个节点
	
	//构造器
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	//为了显示方法，重写toString方法
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + ", next=" + next + "]";
	}
	
	
}