package com.ly.alg.bsearch;

public class BinarySearch {
	public static int BinarySearch(int[] array, int target, int m, int n){
		if(m > n) return -1;
		int mid = (m + n) / 2;
		if(array[mid] == target) return mid;
		else if(array[mid] < target) return BinarySearch(array, target, mid+1, n);
		else return BinarySearch(array, target, m, mid-1);
	}
	
	public static void main(String[] args){
		int[] array = new int[10];
		array[0] = (int)(Math.random()*10);
		System.out.println(array[0]+" ");
		for(int i = 1; i < 10; i++){
			array[i] = (int)(Math.random()*10 + 1) + array[i-1];
			System.out.println(array[i]+" ");

		}
		int target = array[(int)(Math.random()*10)];
		System.out.println(target);
		int x = BinarySearch(array, target, 0, 10-1);
		System.out.println(x);
	}
}
