package edu.kmust.sparsearray;

/**
 * @author BunnyAndOak0
 * TODO 稀疏数组 实现数据的压缩
 * Aug 13, 2020
 */
public class SparseArray {

	public static void main(String[] args) {
		//先创建一个原始二维数组  11 * 11
		//0表示没有棋子  1表示黑子  2表示蓝子
		int chessArr1[][] = new int[11][12];
		
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
		
		//输出原始二维数组
		for (int[] row : chessArr1) {  //取到每一行
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		
		//将二维数组转为稀疏数组
		//1.先遍历二维数组得到非0的数据的个数
		int sum = 0;
		for (int i = 0; i < chessArr1.length; i++) {
			for (int j = 0; j < chessArr1[i].length; j++) {
				if (chessArr1[i][j] != 0) {
					sum ++;
				}
			}
		}
		
		//创建对应的稀疏数组
		int sparseArray[][] = new int[sum + 1][3];
		//给稀疏数组赋值
		sparseArray[0][0] = 11;
		sparseArray[0][1] = 11;
		sparseArray[0][2] = sum;
		
		//遍历二维数组将非0的值存放带稀疏数组中
		int count = 0;
		for (int i = 0; i < chessArr1.length; i++) {
			for (int j = 0; j < chessArr1[i].length; j++) {
				if (chessArr1[i][j] != 0) {
					count ++;
					sparseArray[count][0] = i;
					sparseArray[count][1] = j;
					sparseArray[count][2] = chessArr1[i][j];
				}
			}
		}
		
		//输出稀疏数组的形式
		System.out.println();
		System.out.println("得到的稀疏数组为~~~：");
		for (int i = 0; i < sparseArray.length; i++) {
			for (int j = 0; j < sparseArray[i].length; j++) {
				System.out.printf("%d\t", sparseArray[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
		
		//将稀疏数组恢复为二维数组
		//1.先读取行列
		int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
		
		//2.读取稀疏数组的第二行，根据第一行的数据，创建原始的二维数组
		for (int i = 1; i <sparseArray.length; i++) {
			chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
		
		//恢复后的二维数组
		System.out.println("恢复后的二维数组：");
		for (int[] row : chessArray2) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		
	}
}
