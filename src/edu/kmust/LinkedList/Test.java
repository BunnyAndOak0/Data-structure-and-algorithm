package edu.kmust.LinkedList;

/**
 * @author BunnyAndOak0
 * TODO
 * Sep 13, 2020
 */
public class Test {
	public static void main(String[] args) {
		String s1 = "a";
		String s2 = "b";
		String s3 = "ab";
		String s4 = s1 + s2;
		System.out.println(s3 == s4);
		//intern的作用是直接从常量池中取值
		System.out.println(s3 == s4.intern());
	}
	
	public static void reverseList(HeroNode head) {
		if (head.next == null || head.next.next == null) {
			return;
		}
		
		HeroNode cur = head.next;
		HeroNode next = null;
		HeroNode newList = new HeroNode(0, "", "");
		
		while (cur != null) {
			next = cur.next;
			cur.next = newList.next;
			newList.next = cur;
			cur = next;
		}
		head.next = newList.next;
	}
}
