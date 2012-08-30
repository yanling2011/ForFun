package com.ly.alg.dp;

import java.util.Scanner;


public class LCS {
	
	public static void main(String[] args){
		int m,n;
		Scanner scanner = new Scanner(System.in);
		m = scanner.nextInt();
		n = scanner.nextInt();
		int []array1 = new int[m+1];
		int []array2 = new int[n+1];
		int i;
		for(i = 1; i < m+1; i++)
			array1[i] = scanner.nextInt();
		for(i = 1; i < n+1; i++)
			array2[i] = scanner.nextInt();
	//	int result = SolveLCS(array1, array2, m, n);
		int result = DP_SolveLCS(array1, array2, m, n);
		System.out.println(result);
	}
	/*
	 * Recursion
	 */
	public static int SolveLCS(int[] array1, int[] array2, int m, int n){
		if(m == 0 || n == 0) return 0;
		if(array1[m] == array2[n])
			return SolveLCS(array1, array2, m-1, n-1) + 1;
		int a = SolveLCS(array1, array2, m-1, n);
		int b = SolveLCS(array1, array2, m, n-1);
		return (a > b) ? a : b;
	}
	
	/*
	 * Dynamic Programming
	 */
	public static int DP_SolveLCS(int[] array1, int[] array2, int m, int n){
		int[][] temp = new int[m+1][n+1];
		int i = 0;
		int j = 0;
		for(i = 0; i < m + 1; i++)
			temp[i][0] = 0;
		for(j = 0; j < n + 1; j++)
			temp[0][j] = 0;
		for(i = 1; i < m + 1; i++){
			for(j = 1; j < n + 1; j++){
				if(array1[i] == array2[j])
					temp[i][j] = temp[i-1][j-1] + 1;
				else
					temp[i][j] = (temp[i][j-1] > temp[i-1][j]) ? temp[i][j-1]: temp[i-1][j];
			}
		}
		
		return temp[m][n];
	}
}
