package edu.kmust.LinkedList;

import java.util.Stack;

/**
 * @author BunnyAndOak0
 * TODO 演示栈stack的基本使用 
  *  栈的特点是先进后出
 * Sep 13, 2020
 */
public class TestStack {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		//入栈
		stack.add("jack");
		stack.add("tom");
		stack.add("smith");
		
		//出栈
		//smith，tom，jack
		while (stack.size() > 0) {
			//pop就是将栈顶的数据取出
			System.out.println(stack.pop());
		}
	}
}
