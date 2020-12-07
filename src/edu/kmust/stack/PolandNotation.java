package edu.kmust.stack;

import java.awt.event.ItemEvent;
import java.awt.font.TextHitInfo;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.sun.javafx.perf.PerformanceTracker.SceneAccessor;

/**
 * @author BunnyAndOak0
 * TODO 逆波兰表达式的计算器
 * Dec 3, 2020
 */
public class PolandNotation {
	public static void main(String[] args) {
		//功能：完成将一个中缀表达式转成后缀表达式的功能
		/**
		 * 说明：
		 * 1、1+((2+3)*4)-5  => 123+4*5-1
		 * 2、因为直接对字符串操作不方便，故先将字符串"1+((2+3)*4)-5" => 中缀表达式对应的List
		 * 	 也就是1+((2+3)*4)-5 => ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
		 * 3、将得到的中缀表达式对应的List => 后缀表达式对应的List
		 * 	  也就是ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => ArrayList [1, 2, 3, +, 4, *, +, 5, -]
		 */
		String expressioin = "1+((2+3)*4)-5";
		List<String> infixExpresionList = toInFixExpressionList(expressioin);
		System.out.println("中缀表达式对应的List：" + infixExpresionList);
		List<String> parseList = parseSuffixExpressionList(infixExpresionList);
		System.out.println("后缀表达式对应的list：" + parseList);
		
		
		
		
		
//		//先定义一个逆波兰表达式
//		//(3+4)*5-6 => 34+5*6-
//		//为了说明方便，逆波兰表达式的数字和符号使用空格隔开
//		String suffixExpression = "3 4 + 5 * 6 -";
//		//思路
//		//1.先将3 4 + 5 * 6 -放入一个arrayList中
//		//2.将arrayList传递给一个方法，遍历配合栈完成计算
//		List<String> rpnList = getListString(suffixExpression);
//		System.out.println("rpnList=" + rpnList);
//
//		int res = calculate(rpnList);
//		System.out.println("计算的结果为：" + res);
	}
	
	/**
	 * 也就是ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => 
	 * ArrayList [1, 2, 3, +, 4, *, +, 5, -]
	 * 将得到的中缀表达式对应的List => 后缀表达式对应的List
	 */
	public static List<String> parseSuffixExpressionList(List<String> ls){
		//定义两个栈
		Stack<String> s1 = new Stack<String>();		//符号栈
		/**
		 * 但是s2在整个转过过程中，没有pop操作，而且还需要逆序输出，
		*  因此比较麻烦，就不用Stack<String>，而使用List<String>来替代
	    */
		//Stack<String> s2 = new Stack<String>();		//存储中间结果的栈s2  弃用用 改为用List<String>
		List<String> s2 = new ArrayList<String>();
		
		//遍历ls
		for (String item : ls) {
			//如果是一个数，就加入s2
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if(item.equals("(")) {
				s1.push(item);
			}else if (item.equals(")")) {
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();		//将（弹出s1栈，消除小括号
			}else {
				/**
				 * 当item的优先级小于等于栈顶的优先级
				 * 将s1栈顶的运算符弹出并压入到s2中，再次转到s1中进行新的栈顶运算符的比较
				 * 问题：缺少一个比较优先级高低的方法
				 */
				while (s1.size() != 0 && (Operation.getValue(s1.peek()) >= Operation.getValue(item))) {
					s2.add(s1.pop());
				}
				//还需要将item压入栈中
				s1.push(item); 
			}
		}
		//将s1中剩余的运算符依次弹出加入到s2中
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}
		
		//因为是存放在list中，因此按顺序输出就是对应的后缀表达式对应的List
		return s2;
	}
	
	
	
	/**
	 * 将中缀表达式转为对应的List
	 */
	public static List<String> toInFixExpressionList(String s){
		//定义一个List，存放中缀表达式对应的内容
		List<String> ls = new ArrayList<String>();
		//这是一个指针 ，用于遍历中缀表达式字符串
		int i = 0;
		//用来进行多位数的拼接工作
		String str;
		//每遍历到一个字符，就放入到c中
		char c;
		do {
			//如果c是一个非数字，就需要加入到ls中
			if (((c = s.charAt(i)) < 48) || ((c = s.charAt(i)) > 57)) {
				ls.add("" + c);
				i++;		//i进行后移
			}else {
				//如果是一个数，需要考虑多位数的问题
				str = "";	//先将str置成""
				while (i < s.length() && (((c = s.charAt(i)) >= 48) && ((c = s.charAt(i)) <= 57))) {
					str += c;		//拼接
					i ++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;
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


//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	
	//写一个方法 返回对应的优先级数字
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("不存在该运算符");
			break;
		}
		return result;
	}
}