package com.ly.usaco.training;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ID: jsycyl21
 * LANG: JAVA
 * TASK: test
 */

public class test {
	
	public class state{
		int[] clocks;
	}
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new FileReader("clocks.in"));
//		BufferedWriter out = new BufferedWriter(new FileWriter("clocks.out"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] initial = new int[9];
		int index = 0;
		for(int i = 0; i < 3; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			initial[index++] = Integer.parseInt(st.nextToken());
			initial[index++] = Integer.parseInt(st.nextToken());
			initial[index++] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i < 9; i++){
			System.out.println(initial[i]) ;
		}
		
	}
}
