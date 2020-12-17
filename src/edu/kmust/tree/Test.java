package edu.kmust.tree;

import java.util.ArrayList;

/**
 * @author BunnyAndOak0
 * TODO 以ArrayList为例，查看底层是如何进行数组扩容的
 * Dec 18, 2020
 */
public class Test {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		//ArrayList维护了数组transient Object[] elementData	
		/**
		 * ArrayList底层仍然是进行了数组扩容
		 */
		ArrayList arrayList = new ArrayList();
	}

}
