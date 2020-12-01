package edu.kmust.LinkedList;

/**
 * 双向链表
 * @author BunnyAndOak0
 */
public class DoubleLinkedListDemo {
	public static void main(String[] args) {

	}
}

//创建一个双向链表的类
class DoubleLinkedList {
	//初始化
	private HeroNode2 head = new HeroNode2(0, "", "");
	
	//返回头节点
	public HeroNode2 getHead() {
		return head;
	}
	
	//遍历双向链表的方法
	public void list() {
		//判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		
		HeroNode2 temp = head.next;
		while (true) {
			if (temp == null) {
				break;
			}
			//输出节点信息
			System.out.println(temp);
			//将temp后移，一定小心
			temp = temp.next;
		}
	}
	
	//添加一个节点到双向链表的最后
	public void add(HeroNode2 hersNode2) {
		//创建一个辅助节点
		HeroNode2 temp = head;
		//遍历链表，找到最后
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		//退出while的时候，temp就指向的是链表的最后
		//将最后这个节点的next指向新的节点
		//形成双向链表
		temp.next = hersNode2;
		hersNode2.pre = temp;
	}
	
	//修改一个节点的内容
	public void update(HeroNode2 heroNode2) {
		//判断是否为空
		if (head.next == null) {
			System.out.println("链表为空啦");
			return;
		}
		//找到需要修改的节点，根据no编号
		//定义一个辅助变量
		HeroNode2 temp = head.next;
		boolean flag = false;		//是否找到该节点
		while (true) {
			if (temp == null) {
				break;		//遍历为链表
			}
			if (temp.no == heroNode2.no) {
				//找到了
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.name = heroNode2.name;
			temp.nickname = heroNode2.nickname;
		}else {
			//没有找到
			System.out.printf("没有找到编号%d的节点，不能修改", heroNode2.no);
		}
	}
	
	//删除节点
}

//定义HeroNode2，每个HeroNode对象就是一个节点
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;		//指向下一个节点 默认为null
	public HeroNode2 pre;		//指向前一个节点 默认为null
	
	public HeroNode2(int no, String name, String nickname) {
		super();
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}