package com.ly.acm.poj;

import java.util.Scanner;

public class POJ_1017_Packets {
	//
	
	public static int[] array = new int[7];
	public static int[] threeForTwo = {0, 5, 3, 1};
	public static int solve(){
		int num = 0;
		num += array[6];
		num += array[5];
		num += array[4];
		int forTwo = array[4]*5;
		int forOne = 0;
		num += (array[3] + 3)/4;
		forTwo += threeForTwo[array[3] % 4];
		if(forTwo < array[2]) num = num + (array[2] - forTwo + 8) / 9;
		forOne = num*6*6 - array[6]*6*6 - array[5]*5*5- array[4]*4*4- array[3]*3*3 - array[2]*2*2;	
		if(forOne >= array[1]) return num;
		num = num + (array[1] - forOne + 35) / 36;
		return num;
	}
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()){
			int sum = 0;
			for(int i = 1; i < 7; i++){
				array[i] = scanner.nextInt();
				sum += array[i];
			}
			if(sum == 0) return;
			System.out.println(solve());
		}
		return;
	}
	/*
	public static int[] a = new int[7];
	public static int[] threeForTwo = {0, 5, 3, 1};
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int n;
		while (scanner.hasNext()){
			int sum = 0;
			n = 0;
			for(int i = 1; i < 7; i++){
				a[i] = scanner.nextInt();
				sum += a[i];
			}
			if(sum == 0) return;
			n += a[6] +a[5] + a[4] + (a[3]+3)/4;
			int i = a[4]*5 + threeForTwo[a[3]%4];
			if(a[2] > i)  n += (a[2] - i + 8) / 9;
			int j = n*36 - a[6]*36-a[5]*25-a[4]*16-a[3]*9-a[2]*4;
			if(a[1] > j) n += (a[1] - j + 35)/36;
			System.out.println(n);
		}	
	}
	*/
}
