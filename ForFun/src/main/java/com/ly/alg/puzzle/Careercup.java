package com.ly.alg.puzzle;


/*
 * Created 15:41 on 2012/5/8
 */
public class Careercup {
	/*
	//Reverse words in a sentence
	public static void main(String[] args){
		char[] str = "Hello World".toCharArray();
		for(int i = 0; i <= str.length/2; i++){
			char temp = (char) (str[i] ^ str[str.length - 1 - i]);
			str[i] ^= temp;
			str[str.length - 1 - i] ^= temp;
		}
		System.out.println(str);
		int i = 0;
		while(i < str.length){
			int j = i;
			while(j != str.length &&str[j] != ' ') j++;
			for(int k = i; k <= (i + j - 1) /2; k++){
				char temp = (char) (str[k] ^ str[j - 1 - k + i]);
				str[k] ^= temp;
				str[j - 1 - k + i] ^= temp;
			}
			i = j + 1;
		}
		System.out.println(str);
	}
	*/
	
	public static class Node{
		char name;
		Node(char ch){
			name = ch;
		}
	}
	public static void solve(Node[] nodes, int[][] matrix, int src, int des){
		
	}
	//Tree related
	public static void main(String[] args){
		Node[] nodes = {new Node('a'), new Node('b'), new Node('c'), new Node('d')};
		int[][] matrix = {
				{0, 1, 1, 0},
				{1, 0, 0, 1},
				{1, 0, 0, 1},
				{0, 1, 1, 0}
		};
		int[] flag = {0, 0, 0, 0};
		int src = 0;
		int des = 3;
		solve(nodes, matrix, src, des);
	}
}
