package edu.kmust.LinkedList;

/**
 * @author BunnyAndOak0
 * TODO 对象赋值的测试
 * Dec 2, 2020
 */
public class Test1 {
	public static void main(String[] args) {
		Hero hero = new Hero("alice", 19);
		System.out.println(hero);
		Hero temp = hero;
		temp.name = "anne";
		temp.age = 20;
		System.out.println(temp);
		System.out.println("改变之后为：");
		System.out.println(hero);
	}
}

class Hero{
	String name;
	int age;
	
	public Hero() {
		
	}
	
	public Hero(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "name:" + name + "\t" + "age:" + age;
	}
	
}
