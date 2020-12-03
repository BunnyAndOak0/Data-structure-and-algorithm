package edu.kmust.stack;

/**
 * @author BunnyAndOak0
 * TODO 计算器
 * Dec 3, 2020
 */
public class Caculator {
	public static void main(String[] args) {
		//完成表达式计算
		String expression = "70+20*6-4";
		//创建两个栈，数栈、符号栈
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		//定义需要的相关变量
		int index = 0;		//用于扫描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';		//将每次扫描得到的char保存到ch
		String keepNum = "";	//用于拼接多位数
		
		//开始循环的扫描expression
		while (true) {
			//依次得到expression的每一个字符
			ch = expression.substring(index, index + 1).charAt(0);
			//判断ch，并进行响应的处理
			if (operStack.isOper(ch)) {
				//判断当前符号栈是否为空
				if (!operStack.isEmpty()) {
					//处理
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						//将运算结果如数栈
						numStack.push(res);
						operStack.push(ch);
					}else {
						operStack.push(ch);
					}
				}else {
					//如果为空直接入栈
					operStack.push(ch);
				}
			}else {
				//如果是数，就直接入栈，转为数字 
				//numStack.push(ch - 48);
				//当处理多位数时，可能是多位数
				//处理多位数
				keepNum += ch;
				
				//如果ch已经是expression的最后一位，就直接入栈
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				}else {
					//判断下一个字符是不是数字，如果使数字，就继续扫描，如果是运算符则入栈
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {	//如果后一位是运算符就入栈
						numStack.push(Integer.parseInt(keepNum));
						//重要！！！！keepNum清空
						keepNum = "";
					}
				}
	 			
			}
			//让index加一，并判断是否扫描到expression的最后
			index ++;
			if (index >= expression.length()) {
				break;
			}
		}
		
		//表达式扫描完毕后，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
		while (true) {
			//如果符号栈为空，则计算到最后的结果，数栈中只有一个数字也就是结果
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		System.out.printf("表达式%s = %d", expression, numStack.pop());
	}
}


//先创建一个栈，使用前面，需要扩展功能
class ArrayStack2{
	private int maxSize;	//栈的大小
	private int[] stack;	//数组，数组模拟栈，数据就放在该数组
	private int top = -1;	//top表示栈顶 初始化为-1
	
	//构造器
	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	
	//增加一个方法，可以返回当前栈顶，但是不pop
	public int peek() {
		return stack[top];
	}
	
	//栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	
	//栈空
	public boolean isEmpty() {
		return top == -1;
	}
	
	//入栈
	public void push(int value) {
		//先判断是否栈满
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		
		top ++;
		stack[top] = value;
	}
	
	//出栈 将栈顶的数据返回
	public int pop() {
		//先判断是否栈空  抛出异常
		if (isEmpty()) {
			new RuntimeException("栈空，没有数据");
		}
		int value = stack[top];
		top --;
		return value;
	}
	
	//遍历栈  遍历时需要从栈顶开始显示数据
	public void list() {
		if (isEmpty()) {
			System.out.println("栈空，没有数据！");
		}
		//从栈顶开始显示
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d] = %d\n", stack[i]);
		}
	}
	
	/**
	 * 返回运算符的优先级，由程序员定，
	 * 优先级使用数字表示
	 * 数字越大，优先级越高
	 */
	public int priority(int oper) {
		//假定目前表达式只有+，-，*，/
		if (oper == '*' || oper == '/') {
			return 1;
		}else if (oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	
	/**
	 * 判断是否为一个运算符
	 */
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*'
				|| val == '/';
	}
	
	/**
	 * 计算方法
	 */
	public int cal(int num1, int num2, int oper) {
		int res = 0;		//用于存放计算结果
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;  
		default:
			break;
		}
		return res;
	}
	
}