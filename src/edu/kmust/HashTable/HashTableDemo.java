package edu.kmust.HashTable;

import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

/**
 * 哈希表
 * @author BunnyAndOak0
 *
 */
public class HashTableDemo {
	public static void main(String[] args) {
		//创建一个哈希表
		HashTable hashTable = new HashTable(7);
		
		//写一个简单的菜单
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("add：添加雇员");
			System.out.println("list：显示雇员");
			System.out.println("exit：退出系统");
			System.out.println("find：查找雇员");
			
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("输入id");
				int id = scanner.nextInt();
				System.out.println("输入名字");
				String name = scanner.next();
				//创建雇员
				Emp emp = new Emp(id, name);
				hashTable.add(emp);
				break;
			case "list":
				hashTable.list();
				break;
			case "find":
				System.out.println("请输入要查找的id");
				id = scanner.nextInt();
				hashTable.findEmpById(id);
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
	}
}

//创建哈希表HashTable，用来管理多条链表
class HashTable {
	private EmpLinkedList[] empLinkedListArray;
	private int size;
	
	//构造器
	public HashTable (int size) {
		this.size = size;		//表示有多少条链表
		//初始化empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		//???这时不要忘记初始化每个元素
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
		
	}
	
	//添加雇员
	public void add(Emp emp) {
		//根据员工id，得到该员工应当添加到哪条链表
		int empLinkedListNo = hasFun(emp.id);
		//将emp添加到对应的链表中
		empLinkedListArray[empLinkedListNo].add(emp);
	}
	
	//遍历所有的链表,也就是遍历哈希表hasTab
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);;
		}
	}
	
	//根据输入的id，查好雇员
	public void findEmpById(int id) {
		//使用散列函数确定到哪条链表查找
		int empLinkedListNo = hasFun(id);
		Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
		if (emp != null) {	//找到
			System.out.printf("在第%d条链表中，找到雇员id = %d\n", (empLinkedListNo + 1), id);
		}else {
			System.out.println("在哈希表中，没有找到该雇员~");
		}
	}
	
	//编写一个散列函数，使用简单的取模法
	public int hasFun(int id) {
		return id % size;
	}
}


//表示一个雇员
class Emp{
	public int id;
	public String name;
	public Emp next;	//next默认为null
	
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

//创建一个E名片LinkedList，表示链表
class EmpLinkedList{
	//头指针，指向第一个雇员Emp，因此这个链表的head是直接指向第一个Emp
	private Emp head;		//默认为null
	//添加雇员到链表
	//说明：
	//1.假定当添加雇员的时候，id是自增长的，即id的分配总是从小到大
	//因为，将该雇员直接加入到本链表的最后即可
	public void add(Emp emp) {
		//如果是添加链表的第一个雇员
		if (head == null) {
			head = emp;
			return;
		}
		//如果不是第一个雇员，则使用辅助的指针帮助定位到最后
		Emp curEmp = head;
		while (true) {
			if (curEmp.next == null) {	//说明到了链表的最后
				break;
			}
			curEmp = curEmp.next;	//后移
		}
		//退出时直接将emp加入链表
		curEmp.next = emp;
	}

	//遍历链表的雇员信息
	public void list(int no) {
		if (head == null) {		//说明链表为空，则不会继续往下走
			System.out.println("第" + (no + 1) + "条链表为空");
			return;
		}
		System.out.print("第" + (no + 1) + "条链表的信息为：");
		Emp curEmp = head;		//辅助指针
		while (true) {
			System.out.printf("=> id=%d  name=%s\t", curEmp.id, curEmp.name);
			if (curEmp.next == null) {		//说明curEmp已经是最后的节点
				break;
			}
			curEmp = curEmp.next;	//后移、遍历
		}
		System.out.println();
	}
	
	
	//根据id查找雇员
	//如果查找到，就返回Emp，如果没有找到，就返回null
	public Emp findEmpById(int id) {
		//先判断链表是否为空
		if (head == null) {
			System.out.println("链表为空");
			return null;
		}
		//辅助指针
		Emp curEmp = head;
		while (true) {
			if (curEmp.id == id) {	//找到
				break;		//这是curEmp就指向要查找的雇员 
			}
			//退出
			if (curEmp.next == null) {	//说明遍历当前链表没有找到该雇员
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;	//移后
		}
		return curEmp;
	}
}

