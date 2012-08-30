package com.ly.alg.puzzle;

import java.util.Arrays;

public class Puzzle{
    public static void subfunc(String str, int index, char[] newStr, int length){
        if(length == 0) {
        	System.out.println(str);
        	return;
        }
        for(int i = index; i< newStr.length; i++){
            subfunc(str+newStr[i], i, newStr, length-1);
        }

    }
    public static void func(char[] newStr, int length){
        for(int i = 0; i <newStr.length; i++){
            String str = "";
            str += newStr[i];
            subfunc(str, 0, newStr, length-1);
        }

    }
    public static void printAllPermutation(String str){
        int length = str.length();
        char[] array = str.toCharArray();
        Arrays.sort(array);
        String newStr = "";
        char temp = array[0];
        newStr += temp;

        for(int i = 1;  i < array.length; i++){
        	if(array[i] == temp) continue;
            temp = array[i];
            newStr += temp;
        }

        func(newStr.toCharArray(), length);

    }
    public static void main(String[] args){
        String str = "aaabc";
        printAllPermutation(str);

    }
}