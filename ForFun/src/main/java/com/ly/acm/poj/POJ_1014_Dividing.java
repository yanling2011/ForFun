package com.ly.acm.poj;

import java.util.Scanner;

public class POJ_1014_Dividing {
	
	public static int[] nums =  new int[7];
	public static int[] array;
	public static int half;
	public static boolean succeed = false;
	public static void completePack(int value){
		for(int i = value; i <= half; i++){
			array[i] = (array[i] > array[i-value] + value) ? array[i] : (array[i-value]+value);
			if(array[i] == half){
				succeed = true;
				return;
			}
		}
	}
	public static void oneZeroPack(int value){
		for(int i = half; i >= value; i--){
			array[i] = (array[i] > array[i-value]+value ) ? array[i] : (array[i-value]+value);
			if(array[i] == half){
				succeed = true;
				return;
			}
		}
		
	}
	public static boolean canBeDevided(){
		int sum = 0;
		for(int i = 1; i < 7; i++){
			sum += i * nums[i];
		}
		if(sum % 2 == 1) return false;
		half = sum / 2;
		array = new int[half+1];
		for(int i = 0; i <= half; i++){
			array[i] = 0; 
		}
		
		int k = 1;
		int temp;
		for(int i = 1; i <= 6; i++){
			temp = nums[i];
			if(temp * i > half){
				completePack(i);
				if(succeed) return true;
			}
			else{
				while(temp > k){
					oneZeroPack(i*k);
					if(succeed) return true;
					temp -= k;
					k = k*2;
				}
				oneZeroPack(i*temp);
				if(succeed) return true;
			}
		}
		return false;
	}
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int temp = 0;
		boolean flag = false;
		int caseNo = 0;
		while(scanner.hasNext()){
			for(int i = 1; i < 7; i++){
				temp = scanner.nextInt();
				nums[i] = temp;
				if(temp != 0) flag = true;
			}
			succeed = false;
			if(flag == false) return;
			else{
				caseNo++;
				flag = false;
				if(canBeDevided()){
					System.out.println("Collection #"+caseNo+":");
					System.out.println("Can be divided.");
				}
				else{
					System.out.println("Collection #"+caseNo+":");
					System.out.println("Can't be divided.");
				}
				System.out.println();
			}
		}
		
	}
}
