package edu.kmust.stack;

import java.awt.event.ItemEvent;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author BunnyAndOak0
 * TODO 逆波兰表达式的计算器
 * Dec 3, 2020
 */
public class PolandNotation {
	public static void main(String[] args) {
		//先定义一个逆波兰表达式
		//(3+4)*5-6 => 34+5*6-
		//为了说明方便，逆波兰表达式的数字和符号使用空格隔开
		String suffixExpression = "3 4 + 5 * 6 -";
		//思路
		//1.先将3 4 + 5 * 6 -放入一个arrayList中
		//2.将arrayList传递给一个方法，遍历配合栈完成计算
		List<String> rpnList = getListString(suffixExpression);
		System.out.println("rpnList=" + rpnList);

		int res = calculate(rpnList);
		System.out.println("计算的结果为：" + res);
	}
	
	//将逆波兰表达式依次将数据和运算符放入到ArrayList中
	public static List<String> getListString(String suffixExpression) {
		//将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<>();
		for (String ele : split) {
			list.add(ele);
		}
		return list;
	}
	
	//完成逆波兰表达式的运算
	public static int calculate(List<String> ls) {
		//创建栈  只需要一个
		Stack<String> stack = new Stack<String>();
		//遍历ls
		for (String item : ls) {
			//使用正则表达式
			if (item.matches("\\d+")) {	//匹配多位数 
				//直接入栈
				stack.push(item);
			}else {
				//pop出两个数并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				}else if (item.equals("-")) {
					res = num1 - num2;
				}else if (item.equals("*")) {
					res = num1 * num2;
				}else if (item.equals("/")) {
					res = num1 /  num2;
				}else {
					throw new RuntimeException("运算符有误...");
				}
				//把res入栈
				stack.push("" + res);
			}
		}
		//最后留在stack中的数据就是运算结果
		return Integer.parseInt(stack.pop());
	}
}
