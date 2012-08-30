package com.ly.acm.poj;

import java.util.LinkedList;
import java.util.Scanner;


/*
 * Suppose that there are k good guys and k bad guys. 
 * In the circle the first k are good guys and the last k bad guys. 
 * You have to determine such minimal m that 
 * all the bad guys will be executed before the first good guy. 
 */
public class POJ_1012_Joseph {
	public static int[] array = new int[14];
	public static boolean satisfied(int m, int k){
		LinkedList<Integer> ls = new LinkedList<Integer>();
		for(int i = 1; i <= k*2; i++){
			ls.addLast(i);
		}
		int index = 0;
		for(int i = 0; i < k; i++){
			index = index + m - 1;
			index = index % ls.size();
			if(ls.get(index) <= k) return false;
			ls.remove(index);
			index = index % ls.size();
		}
		return true;
	}
	public static int solve(int k){
		//2nk+2k >= m >= 2nk+k+1 ( n >= 0)
		if(array[k] != 0) return array[k];
		for(int n = 0; ; n++){
			int start = 2*n*k + k + 1;
			int end = 2*n*k + 2*k;
			for(int m = start; m <= end; m++){
				boolean result = satisfied(m, k);
				if(result){
					array[k] = m;
					return m;
				}
			}
		}
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int k;
		for(int i = 0 ; i < 14; i++) array[i] = 0; 
		while(scanner.hasNextInt()){
			k = scanner.nextInt();
			if(k == 0) return;
			System.out.println(solve(k));
		}
	}
}
 