package com.ly.acm.poj;

import java.util.Scanner;

public class POJ_1061_FrogsDate {
	public static long t, k;
	public static void main(String[] args){
		long x, y, m, n, L;
		Scanner scanner = new Scanner(System.in);
		x = scanner.nextInt();
		y = scanner.nextInt();
		m = scanner.nextInt();
		n = scanner.nextInt();
		L = scanner.nextInt();
		
		solve(x, y, m, n, L);
		return;
	}
	
	private static long gcd(long a, long b){
		if(b == 0) return a;
		else return gcd(b, a%b);
	}
	private static void extendgcd(long a, long b){
		if(b == 0) {
			t = 1;
			k = 0;
			return;
		}
		extendgcd(b, a % b);
		long temp = t;
		t = k;
		k = temp - a / b * k;
	}
	private static void solve(long x, long y, long m, long n, long l) {
		long a = n - m;
		long b = l;
		long c = x - y;
		long g = gcd(a, b);
		if(c % g != 0) {
			System.out.println("Impossible");
			return;
		}
		
		a /= g;
		b /= g;
		c /= g;
		extendgcd(a, b);
		
		long xx = c * t / b;
		t = c * t - xx * b;
		if(t < 0) t += b;
		System.out.println(t);
	}
}
