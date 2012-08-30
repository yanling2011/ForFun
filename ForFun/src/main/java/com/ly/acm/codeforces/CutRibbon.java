package com.ly.acm.codeforces;

import java.util.Arrays;
import java.util.Scanner;

public class CutRibbon {
	public static int num = 0;
	public static void main(String[] args){
		int n;
		int []size = new int[3];
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		size[0] = scanner.nextInt();
		size[1] = scanner.nextInt();
		size[2] = scanner.nextInt();
		Arrays.sort(size);
		solve(n, size);
	}
	
	public static void solve(int n, int[] size){
		int a = n / size[2];
		int b = n / size[1];
		int c = n / size[0];
		int ans = 0;
		int count = 0;
		for(int i = 0; i <= a; i++){
			for(int j = 0; j <= b; j++){
				for(int k = 0; k <= c; k++){
					 count = i*size[2] + j*size[1] + k*size[0];
					if(count == n){
						if(ans < i+j+k) ans = i+j+k;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
