package com.ly.usaco.training;

import java.io.IOException;

/*
 * ID: jsycyl21
 * LANG: JAVA
 * TASK: test
 */
/*
 * Milking Cows
 * Transformations
 * Name that number
 * Palindromic Squares
 * Dual Palindromes
 */
public class Section2 {
	//
	/*
	public static class Period implements Comparable<Period>{
		int begin;
		int end;
		public Period(int b, int e){
			begin = b;
			end = e;
		}
		public int compareTo(Period o) {
			if(begin > o.begin) return 1;
			else if(begin < o.begin) return -1;
			return 0;
		}

	}
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("milk2.out"));
		
		int N = Integer.parseInt(f.readLine());
		Set<Period> s = new HashSet<Period>();
		for(int i = 0; i< N; i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Period p = new Period(a, b);
			if(s.isEmpty()){
				s.add(p);
				continue;
			}
			
			Period[] array =  new Period[s.size()];
			s.toArray(array);
			if(array == null || array.length <= 0) s.add(p);
			int j = 0;
			for(j = 0; j < array.length; j++){
				if(a < array[j].begin && b < array[j].begin){
				}
				else if(a <= array[j].begin && b <=array[j].end){
					p.end = array[j].end;
					s.remove(array[j]);
				}
				else if(a <= array[j].begin && b >= array[j].end){
					s.remove(array[j]);
				}
				else if(a <= array[j].end && b <= array[j].end){
					break;
				}
				else if(a <= array[j].end && b >= array[j].end){
					p.begin = array[j].begin;
					s.remove(array[j]);
				}
				else{
				}
			}
			if(j == array.length)
				s.add(p);
		}
		Period[] array =  new Period[s.size()];
		s.toArray(array);
		Arrays.sort(array);
		int ans1 = array[0].end - array[0].begin;
		int ans2 = 0;
		System.out.println(array[0].begin + " " + array[0].end);
		for(int i = 1; i < array.length; i++){
			System.out.println(array[i].begin + " " + array[i].end);

			if(array[i].end - array[i].begin > ans1){
				ans1 = array[i].end - array[i].begin; 
			}
			if(array[i].begin - array[i-1].end > ans2){
				ans2 = array[i].begin - array[i-1].end;
			}
		}
		pw.println(ans1 + " " + ans2);
		pw.close();
		
	}
	*/
	/*
	//transform
	public static class Node{
		int N;
		int steps;
		int[][] matrix;
		public Node(int N, int[][] matrix, int steps){
			this.steps = steps;
			this.N = N;
			this.matrix = matrix;
		}
		public Node rotate90(){
			int[][] newMatrix = new int[N][N];
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					newMatrix[i][j] = matrix[N - j - 1][i];
				}
			}
			return new Node(N, newMatrix, steps+1);
		}
		public Node rotate180(){
			int[][] newMatrix = new int[N][N];
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					newMatrix[i][j] = matrix[N - i - 1][N - j - 1];
				}
			}
			return new Node(N, newMatrix, steps+1);	
		}
		public Node rotate270(){
			int[][] newMatrix = new int[N][N];
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					newMatrix[i][j] = matrix[j][N - i - 1];
				}
			}
			return new Node(N, newMatrix, steps+1);
		}
		public Node reflect(){
			int[][] newMatrix = new int[N][N];
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					newMatrix[i][j] = matrix[i][N-1-j];
				}
			}
			return new Node(N, newMatrix, steps+1);
		}
		public boolean equals(Node n){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if(matrix[i][j] != n.matrix[i][j])
						return false;
				}
			}
			return true;
		}
		
		public int hashCode(){
			return matrix[0][0]*31+matrix[N-1][N-1]*13;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("transform.out"));
		int N = Integer.parseInt(f.readLine());
		//init
		int[][] src = new int[N][N];
		int[][] des = new int[N][N];
		for(int i = 0; i < N; i++){
			String str = f.readLine();
			for(int j = 0; j < N; j++){
				if(str.charAt(j) == '-'){
					src[i][j] = 0;
				}
				else src[i][j] = 1;
			}
		}
		Node beginNode = new Node(N, src, 0);
		for(int i = 0; i < N; i++){
			String str = f.readLine();
			for(int j = 0; j < N; j++){
				if(str.charAt(j) == '-'){
					des[i][j] = 0;
				}
				else des[i][j] = 1;
			}
		}
		Node endNode = new Node(N, des, 0);
		
		int ans = 0;
		
		if(beginNode.rotate90().equals(endNode)) ans = 1;
		else if(beginNode.rotate180().equals(endNode)) ans = 2;
		else if(beginNode.rotate270().equals(endNode)) ans = 3;
		else if(beginNode.reflect().equals(endNode)) ans = 4;
		else if(beginNode.reflect().rotate90().equals(endNode) || 
				beginNode.reflect().rotate180().equals(endNode) ||
				beginNode.reflect().rotate270().equals(endNode))
			ans = 5;
		else if(beginNode.equals(endNode))  ans = 6; //No change
		else ans = 7;
		System.out.println(ans);
		pw.println(ans);
		pw.close();
		//For BFS
		HashSet<Node> closed = new HashSet<Node>();
		Queue<Node> open = new LinkedList<Node>();
		open.offer(beginNode);
		Node temp = null;
		while(!open.isEmpty()){
			Node n = open.poll();
			temp = n.rotate90();
			if(closed.contains(temp)){
			}
			else if(temp.equals(endNode)){
				break;
			}
			else{
				open.add(temp);
			}
			temp = n.rotate180();
			if(closed.contains(temp)){
			}
			else if(temp.equals(des)){
				break;
			}
			else{
				open.add(temp);
			}
			temp = n.rotate270();
			if(closed.contains(temp)){
			}
			else if(temp.equals(des)){
				break;
			}
			else{
				open.add(temp);
			}
			temp = n.reflect();
			if(closed.contains(temp)){
			}
			else if(temp.equals(des)){
				break;
			}
			else{
				open.add(temp);
			}
			closed.add(n);
		}
		if(temp!=null) pw.write(temp.steps);
		pw.close();
	}
	*/
	
	//Name That Number
	/*
	//Time Limit Exceeded
	public static char[][] touchTone = {
		{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'},
		{'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'R', 'S'},
		{'T', 'U', 'V'}, {'W', 'X', 'Y'}
	};
	public static String getName(int i, String namenum){
		
		int length = namenum.length();
		int div = (int)Math.pow(3, length-1);
		String ret = "";
		for(int j = 0; j < length; j++){
			int index = i / div;
			i %= div;
			div /= 3;
			ret += touchTone[namenum.charAt(j) - '0' - 2][index];
		}
		return ret;
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader rDic = new BufferedReader(new FileReader("dict.txt"));
		BufferedReader rInput = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("namenum.out"));
		HashSet<String> hs = new HashSet<String>();
		String temp;
		while((temp = rDic.readLine()) != null){
			hs.add(temp);
		}
		String namenum = rInput.readLine();
		int length = namenum.length();
		int max = (int)Math.pow(3, length);
		boolean flag = false;
		for(int i = 0; i < max; i++){
			String name = getName(i, namenum);
			if(hs.contains(name)){
				pw.println(name);
				flag = true;
			}
		}
		if(!flag)
			pw.println("NONE");
		pw.close();
	}
	*/
	/*
	//Name that number
	public static char[][] touchTone = {
		{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'},
		{'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'R', 'S'},
		{'T', 'U', 'V'}, {'W', 'X', 'Y'}
	};
	public static void main(String[] args) throws IOException{
		BufferedReader rDic = new BufferedReader(new FileReader("dict.txt"));
		BufferedReader rInput = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("namenum.out"));
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for(int i = 0; i < touchTone.length; i++){
			for(int j = 0; j < 3; j++)
				hm.put(touchTone[i][j], i+2);
		}
		String namenum = rInput.readLine();
		String dict;
		boolean flag = false;
		while((dict = rDic.readLine()) != null){
			String n = "";
			for(int i = 0; i < dict.length(); i++){
				n += hm.get(dict.charAt(i));
			}
			if(namenum.equals(n)){
				pw.println(dict);
				flag = true;
			}
		}
		if(!flag)
			pw.println("NONE");
		pw.close();
	}
	*/
	/*
	//palindromic squares
	public static String isPalsquare(int N, int B){
		String str = "";
		int temp = N *N;
		str = getNum(temp, B);
		for(int i = 0; i <= str.length()/2; i++){
			if(str.charAt(i) != str.charAt(str.length()-1-i)) return null;
		}
		return str;
	}
	public static String getNum(int N, int B){
		String str = "";
		int temp = N;
		while(temp != 0){
			int x = temp % B;
			char ch = (char)(x+'0');
			switch(x){
			case 10: ch = 'A'; break;
			case 11: ch = 'B'; break;
			case 12: ch = 'C'; break;
			case 13: ch = 'D'; break;
			case 14: ch = 'E'; break;
			case 15: ch = 'F'; break;
			case 16: ch = 'G'; break;
			case 17: ch = 'H'; break;
			case 18: ch = 'I'; break;
			case 19: ch = 'J'; break;
			case 20: ch = 'K'; break;
			}
			str += ch;
			temp /= B;
		}
		char[] chs = str.toCharArray();
		for(int i = 0; i < str.length(); i++){
			chs[i] = str.charAt(str.length()-1-i);
		}
		return new String(chs);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("palsquare.out"));
		int B = Integer.parseInt(r.readLine());
		for(int i = 1; i <= 300; i++){
			String ret = isPalsquare(i, B);
			if(ret != null){
				pw.println(getNum(i, B) + " " + ret);
			}
		}
		pw.close();
	}
	*/
	
	//Dual palindromic
	public static String isPalsquare(int N, int B){
		String str = "";
		int temp = N;
		str = getNum(temp, B);
		for(int i = 0; i <= str.length()/2; i++){
			if(str.charAt(i) != str.charAt(str.length()-1-i)) return null;
		}
		return str;
	}
	public static String getNum(int N, int B){
		String str = "";
		int temp = N;
		while(temp != 0){
			int x = temp % B;
			char ch = (char)(x+'0');
			switch(x){
			case 10: ch = 'A'; break;
			case 11: ch = 'B'; break;
			case 12: ch = 'C'; break;
			case 13: ch = 'D'; break;
			case 14: ch = 'E'; break;
			case 15: ch = 'F'; break;
			case 16: ch = 'G'; break;
			case 17: ch = 'H'; break;
			case 18: ch = 'I'; break;
			case 19: ch = 'J'; break;
			case 20: ch = 'K'; break;
			}
			str += ch;
			temp /= B;
		}
		char[] chs = str.toCharArray();
		for(int i = 0; i < str.length(); i++){
			chs[i] = str.charAt(str.length()-1-i);
		}
		return new String(chs);
	}
	public static void main(String[] args) throws IOException{
//		BufferedReader r = new BufferedReader(new FileReader("dualpal.in"));
//		PrintWriter pw = new PrintWriter(new FileWriter("dualpal.out"));
//		StringTokenizer st = new StringTokenizer(r.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int S = Integer.parseInt(st.nextToken());
		int N = 5;
		int S = 1;
		int j = S+1;
		for(int i = 0; i < N; i++){
			for(;;j++){
				int count = 0;
				int B = 2;
				for(; B <= 10; B++){
					String str = isPalsquare(j, B);
					if(str != null){
						count++;
						if(count >= 2){
//							pw.println(getNum(j, 10));
							System.out.println(getNum(j, 10));
							j++;
							break;
						}
					}
				}
				if(B <= 10) break;
			}
		}
//		pw.close();
	}
}
