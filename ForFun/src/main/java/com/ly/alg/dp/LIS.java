package com.ly.alg.dp;

import java.util.Scanner;
import java.util.Stack;

public class LIS {
	/*
	 * O(n^2) algrithm
	 */
	public static void func1(){
		int n;
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		int[] num = new int[n];
		int[] list = new int[n];
		int[] trace = new int[n];
		for(int i = 0; i < n; i++){
			num[i] = scanner.nextInt();
		}
		
		for(int i = 0; i < n; i++){
			list[i] = 1;
			trace[i] = i;
			for(int j = 0; j < i; j++){
				if(num[i] > num[j] && list[j]+1 > list[i]){
					list[i] = list[j] + 1;
					trace[i] = j;
				}
			}
		}
		int maxn = 0;
		for(int i = 0 ; i < n; i++){
			if(list[i] > maxn){
				maxn = list[i];
			}
		}
		System.out.println(maxn);
		int i = maxn;
		Stack<Integer> s = new Stack<Integer>();
		while(trace[i] != i){
			s.push(num[i]);
			i = trace[i];
		}
		s.push(num[i]);
		
		while(!s.empty()){
			System.out.print(s.pop() + " ");
		}
	}
	
	/*
	 * func2  with a single Binary search 
	 * O(nlogn) algrithm
	 */
	public static void func2(){
		int n;
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		int[] num = new int[n+1];
		int[] last = new int[n+1];
		for(int i = 1; i < n+1; i++){
			num[i] = scanner.nextInt();
		}
		int k = 0;
		last[0] = 0;
		for(int i = 1; i <= n; i++){
			 
			 if(num[i] >= last[k]){
				 k++;
				 last[k] = num[i];
			 }
			 else{
				 for(int j = 1; j < i; j++){
					 if(last[j] > num[i]){
						 last[j] = num[i];
						 break;
					 }
				 }
			 }
		}
		System.out.println(k);
		
	}
	public static void main(String[] args){
		func2();
	}
}
