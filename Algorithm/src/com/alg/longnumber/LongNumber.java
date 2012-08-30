package com.alg.longnumber;

import java.util.Scanner;

public class LongNumber {
	
	public static int compare(String str1, String str2){
		if(str1.length() > str2.length())
			return 1;
		else if (str1.length() < str2.length())
			return -1;
		else return str1.compareTo(str2);
	}
	public static String ADD_INT(String str1, String str2){
		System.out.println(str1+" + "+str2);
		int sign = 1;
		String str = "";
		if(str1.charAt(0) == '-'){
			if(str2.charAt(0) == '-'){
				sign = -1;
				str = ADD_INT(str1.substring(1, str1.length()), str2.substring(1, str2.length()));
			}
			else{
				str = MINUS_INT(str2, str1.substring(0, str1.length()));
			}
		}
		else{
			if(str2.charAt(0) == '-'){
				str = MINUS_INT(str1, str2.substring(1, str2.length()));
			}
			else{
				int length1, length2;
				int i;
				length1 = str1.length();
				length2 = str2.length();
				if(length1 < length2){
					for(i = 1; i <= length2 - length1; i++){
						str1 = "0" + str1;
					}
				}
				else{
					for(i = 1; i <= length1 - length2; i++){
						str2 = "0" + str2;
					}
				}
				
				int int1 = 0, int2 = 0;
				for(i = str1.length() -1; i >= 0; i--){
					int1 = (str1.charAt(i)-'0'+str2.charAt(i)-'0'+int2) % 10;
					int2 = (str1.charAt(i)-'0'+str2.charAt(i)-'0'+int2) / 10;
					str = int1 + str;
				}
				if(int2 != 0) str = int2 + str;
			}
		}
		if((sign == -1) && (str.charAt(0) != '0'))
			str = "-" + str;
		return str;
	}
	
	public static String MINUS_INT(String str1, String str2){
		int sign = 1;
		String str = "";
		System.out.println(str1 + " - " +str2);
		if(str2.charAt(0) == '-'){
			str = ADD_INT(str1, str2.substring(1, str2.length()));
		}
		else if(str1.charAt(0) == '-'){
			str = ADD_INT(str2, str1.substring(1,str1.length()));
			sign = -1;
		}
		else{
			int res = compare(str1, str2);
			if(res == 0) return "0";
			if(res < 0){
				sign = -1;
				String temp = str1;
				str1 = str2;
				str2 = temp;
			}
			int tempint = str1.length() - str2.length();
			StringBuffer sb1 = new StringBuffer(str1);
			for(int i = str2.length()-1; i >= 0; i--){
				if(str1.charAt(i+tempint) < str2.charAt(i)){
					sb1.setCharAt(i+tempint-1, str1.charAt(i+tempint-1));
					str1 = sb1.toString();
					str = (str1.charAt(i+tempint) - str2.charAt(i) + 10) + str;
				}
				else{
					str = str1.charAt(i+tempint) - str2.charAt(i) +str;
				}
			}
			for(int i = tempint - 1; i >= 0; i--){
				str = str1.charAt(i) + str;
			}
		}
		while(str1.charAt(0) == '0'){
			str1 = str1.substring(1, str1.length());
		}
		if(str.equals("")) return "0";
		if(sign == -1) str = "-" + str;
		return str;
	}
	public static String MULTIPLY_INT(String str1, String str2){
		int sign = 1;
		String str = "0";
		if(str1.charAt(0) == '-'){
			sign *= -1;
			str1 = str1.substring(1, str1.length());
		}
		if(str2.charAt(0) == '-'){
			sign *= -1;
			str2 = str2.substring(1, str2.length());
		}
		int i, j;
		int length1, length2;
		length1 = str1.length();
		length2 = str2.length();
		for(i = length2-1; i >=0; i--){
			String tempString = "";
			int int1 = 0, int2 = 0, int3 = str2.charAt(i) - '0';
			if(int3 != 0){
				for(j = 1; j <= length2 - i -1; j++){
					tempString = "0" + tempString;
				}
				for(j = length1-1; j >= 0; j--){
					int temp = int3*(str1.charAt(j) - '0') + int2;
					int1 = temp % 10;
					int2 = temp / 10;
					tempString = int1 + tempString;
				}
				if(int2 != 0) tempString = int2 + tempString;
			}
			str = ADD_INT(str, tempString);
		}
		while(str.charAt(0) == '0'){
			str = str.substring(1, str.length());
		}
		if(str.equals("")) return "0";
		if(sign == -1) str = "-" + str;
		return str;
	}
	
	public static String DIVIDE_INT(String str1, String str2, int flag){
		//flag == 1 return quotient;  flag == 0 return remainder
		String quotient = "", residue="";
		int sign1 = 1, sign2 = 1;
		if(str2.equals("0")){
			quotient = "ERROR";
			residue = "ERROR";
			if(flag == 1) return quotient;
			else return residue;
		}
		if(str1.equals("0")){
			quotient = "0";
			residue = "0";
		}
		if(str1.charAt(0) == '-'){
			str1 = str1.substring(1, str1.length());
			sign1 *= -1;
			sign2 = -1;
		}
		if(str2.charAt(0) == '-'){
			str2 = str2.substring(1, str2.length());
			sign1 *= -1;
		}
		int res = compare(str1, str2);
		if(res < 0){
			quotient = "0";
			residue = str1;
		}
		else if(res == 0){
			quotient = "1";
			residue = "0";
		}
		else{
			int length1 = str1.length();
			int length2 = str2.length();
			String buffer = "";
			buffer += (str1.substring(0, length2-1));
			for(int i = length2 -1; i < length1; i++){
				buffer += ((str1.charAt(i)));
				for(char ch = '9'; ch >= '0'; ch--){
					String str = "";
					str = str + ch;
					if(compare(MULTIPLY_INT(str2, str), buffer.toString()) <= 0){
						quotient = quotient + ch;
						buffer = MINUS_INT(buffer.toString(), MULTIPLY_INT(str2, str));
						break;
					}
				}
			}
			residue = buffer;
		}
		
		while(!quotient.equals("") && quotient.charAt(0) == '0'){
			quotient = quotient.substring(1, quotient.length());
		}
		if(quotient.equals("")) quotient = "0";
		if(residue.equals("")) residue = "0";
		if(sign1 == -1 && quotient.charAt(0) != '0')
			quotient = "-"+quotient;
		if(sign2 == -1 && residue.charAt(0) != '0')
			residue = "-" + residue;
		if(flag == 1) return quotient;
		return residue;
	}
	
	public static String DIV_INT(String str1, String str2){
		return DIVIDE_INT(str1, str2, 1);
	}
	
	public static String MOD_INT(String str1, String str2){
		return DIVIDE_INT(str1, str2, 0);
	}
	public static void main(String[] args){		
//		String num1 = "-111111";
//		String num2 = "-222222";
//		System.out.println(ADD_INT(num1, num2));
//		System.out.println(MINUS_INT(num2, num1));
//		System.out.println(MULTIPLY_INT(num1, num2));
//		System.out.println(DIV_INT(num2, num1));
//		System.out.println(MOD_INT(num2, num1));
		char ch;
		String s1, s2, res="";
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			ch = scanner.next().charAt(0);
			s1 = scanner.next();
			s2 = scanner.next();
			switch(ch){
			case '+': res = ADD_INT(s1, s2); break;
			case '-': res = MINUS_INT(s1, s2); break;
			case '*': res = MULTIPLY_INT(s1, s2); break;
			case '/': res = DIV_INT(s1, s2); break;
			case '%': res = MOD_INT(s1, s2);break;
			default: break;
			}
			System.out.println(res);
		}
	}
}
