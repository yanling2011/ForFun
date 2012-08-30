package com.ly.usaco.training;
/*
 * ID: jsycyl21
 * LANG: JAVA
 * TASK: test
 */
/*
 * Your Ride is Here
 * Greedy Gift Givers
 * Friday The Thirteenth
 * Broken Necklace
 */
public class Section1 {
/*public static void main(String[] args) throws IOException{
	BufferedReader f = new BufferedReader(new FileReader("test.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int i1 = Integer.parseInt(st.nextToken());
	int i2 = Integer.parseInt(st.nextToken());
	out.println(i1+i2);
	out.close();
	System.exit(0);
}
*/
/*
//ride
public static void main(String[] args) throws IOException{
	BufferedReader f = new BufferedReader(new FileReader("ride.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
	String s1 = f.readLine();
	String s2 = f.readLine();
	int temp1 = 1;
	int temp2 = 1;
	for(int i = 0; i < s1.length(); i++){
		temp1 *= (s1.charAt(i) - 'A' + 1);
	}
	for(int i = 0; i < s2.length(); i++){
		temp2 *= (s2.charAt(i) - 'A' + 1);
	}
	if(temp1 % 47 == temp2 % 47){
		out.println("GO");
	}
	else out.println("STAY");
	out.close();
}
*/
/*
//gift1
public static void main(String[] args) throws IOException{
	BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
	int NP = Integer.parseInt(f.readLine());
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	String[] array = new String[NP];
	for(int i = 0; i < NP; i++){
		String nameString = f.readLine();
		hm.put(nameString, 0);
		array[i] = nameString; 
	}
	for(int i = 0; i < NP; i++){
		String name = f.readLine();
		StringTokenizer st = new StringTokenizer(f.readLine());
		int money = Integer.parseInt(st.nextToken());
		int number = Integer.parseInt(st.nextToken());
		if(number == 0) continue;
		int temp = money / number;
		hm.put(name, hm.get(name) - temp*number);
		for(int j = 0; j < number; j++){
			name = f.readLine();
			hm.put(name, hm.get(name)+temp);
		}
	}
	for(int i = 0; i < NP; i++){
		out.println(array[i] + " " + hm.get(array[i]));
	}
	out.close();
}
*/
/*
//friday
public static void main(String[] args) throws IOException{
	BufferedReader f = new BufferedReader(new FileReader("friday.in"));
	PrintWriter pw = new PrintWriter(new FileWriter("friday.out"));
	int N = Integer.parseInt(f.readLine());
	int[] array = new int[7];
	for(int i = 0; i < 7; i++) array[i] = 0;
	int weekday = 2;
	for(int i = 0; i < N; i++){
		int year = i + 1900;
		boolean isLeap = ((year % 400 == 0) || (year % 100 != 0 && year %4 == 0)) ? true : false; 
		for(int j = 1; j <= 12; j++){
			if(i == 0 && j == 1){
				weekday = (12 + weekday) % 7;
			}
			else{
				int temp = j -1;
				if(temp == 4 || temp == 6 || temp == 9 || temp == 11){
					weekday = (30 + weekday) % 7;
				}
				else if(temp == 2 && isLeap){
					weekday = (29 + weekday) % 7;
				}
				else if(temp == 2 && !isLeap){
					weekday = (28 + weekday) % 7;
				}
				else{
					weekday = (31+weekday) % 7;
				}
			}
			array[weekday]++;
		}
	}
	pw.print(array[0]);
	for(int k = 1; k < 7; k++){
		pw.print(" "+array[k]);
	}
	pw.println();
}
*/
/*
//beads
public static void main(String[] args) throws IOException{
	BufferedReader f = new BufferedReader(new FileReader("beads.in"));
	PrintWriter pw = new PrintWriter(new FileWriter("beads.out"));
	int N = Integer.parseInt(f.readLine());
	String str = f.readLine();
	int ans = 0;
	for(int i = 0; i < N; i++){
		//count from 0
		int count1 = 0;
		char color1 = 'w';
		int j;
		for(j = i; j < N + i; j++){
			if(str.charAt(j % N) == 'w'){
				count1++;
			}
			else if(color1 =='w'){
				color1 = str.charAt(j % N);
				count1++;
			}
			else if(color1 == str.charAt(j % N)) count1++;
			else break;
		}
		System.out.println(count1);
		//count from 0 - 1
		
		int count2 = 0;
		char color2 = 'w';
			for(int k = j; k <= i -1 + N; k++){
			int temp = k % N;
			if(str.charAt(temp) == 'w'){
				count2++;
			}
			else if(color2 =='w'){
				color2 = str.charAt(temp);
				count2++;
			}
			else if(color2 == str.charAt(temp)) count2++;
			else 
				break;
		}
		if(count1+count2 > ans) ans = count1+ count2;
	} 
	pw.println(ans);
	pw.close();
}
*/
/*
//beads DP
public static void main(String[] args){
	int N = 29;
	String str = "wwwbbrwrbrbrrbrbrwrwwrbwrwrrb";
	//we can count the same beads twice if they can be taken off either side of the break
	//this will only happen if all beads can be taken off the nacklace
	//so we can check that in the end
	str = str + str;
	int[][] left = new int[N*2+1][2];
	int[][] right = new int[N*2+1][2];
	left[0][0] = left[0][1] = 0;
	for(int i = 1; i<=2*N; i++){
		if(str.charAt(i-1) == 'b'){
			left[i][0] = left[i-1][0] + 1;
			left[i][1] = 0;
		}
		else if(str.charAt(i-1) == 'r'){
			left[i][0] = 0;
			left[i][1] = left[i-1][1] + 1;
		}
		else{
			left[i][0] = left[i-1][0]+1;
			left[i][1] = left[i-1][1] + 1;
		}
	}
	right[2*N][0] = right[2*N][1] = 0;
	for(int i = 2*N -1; i >=0; i--){
		if(str.charAt(i) == 'b'){
			right[i][0] = right[i+1][0] +1;
			right[i][1] = 0;
		}
		else if(str.charAt(i) == 'r'){
			right[i][0] = 0;
			right[i][1] = right[i+1][1] + 1;
		}
		else{
			right[i][0] = right[i+1][0] + 1;
			right[i][1] = right[i+1][1] + 1;
		}
	}
	int sum = 0;
	for(int i = 0; i <= 2*N; i++){
		int a, b;
		if(left[i][0] > left[i][1]) a = left[i][0];
		else a = left[i][1];
		if(right[i][0] > right[i][1]) b = right[i][0];
		else b = right[i][1];
		if(sum < a+b) sum = a+b;
	}
	if(sum > N) sum = N;
	System.out.println(sum);
}
*/
}
