package com.ly.acm.poj;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Dynamic programming
 */
public class POJ_1088_Skating {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int rows, cols;
		rows = scanner.nextInt();
		cols = scanner.nextInt();
		Dot[] dots = new Dot[rows*cols];
		int h;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				h = scanner.nextInt();
				dots[i*cols + j] = new Dot(i, j, h);
			}
		}
		Arrays.sort(dots);
		solve(dots);
		
	}
	public static void solve(Dot[] dots){
		int length = dots.length;
		int[] count = new int[length];
//		int[] last = new int[length];
		for(int i = 0; i < length; i++){
			count[i] = 1;
//			last[i] = i;
			for(int j = 0; j < i; j++){
				int xj = dots[j].x;
				int xi = dots[i].x;
				int yj = dots[j].y;
				int yi = dots[i].y;
				if((((xj == xi + 1 || xj == xi - 1) && (yi == yj)) || ((xi == xj) &&(yj == yi +1 || yj == yi - 1))) && count[i] < count[j] + 1 && dots[i].h > dots[j].h) {
					count[i] = count[j] + 1;
//					last[i] = j;
				}
					
			}
		}
		int max = count[0];
//		int index = 0;
		for(int i = 0; i < length; i++){
			if(max < count[i]){
				max = count[i];
//				index = i;
			}
		}
//		Stack<Integer> stack = new Stack<Integer>();
//		while(last[index] != index){
//			stack.push(index);
//			index = last[index];
//		}
//		stack.push(index);
		System.out.println(max);
//		while(!stack.empty()){
//			System.out.print(dots[stack.pop()].h + " ");
//		}
	}
	
	public static class Dot implements Comparable<Dot>{
		int x;
		int y;
		int h;
		public Dot(){
			x = y = h = 0;
		}
		public Dot(int x, int y, int h){
			this.x = x;
			this.y = y;
			this.h = h;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public int getH(){
			return h;
		}
		public int compareTo(Dot o) {
			if(this.h < o.h){
				return -1;
			}
			return 1;
		}
	}
}
