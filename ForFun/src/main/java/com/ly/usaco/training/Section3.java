package com.ly.usaco.training;

public class Section3 {
	/*
	//mixed milk
	public static class Farmer implements Comparable<Farmer>{
		int price;
		int amount;
		public Farmer(int price, int amount){
			this.price = price;
			this.amount = amount;
		}
		public int compareTo(Farmer o) {
			if(price < o.price) return -1;
			else if(price > o.price) return 1;
			return 0;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new FileReader("milk.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("milk.out"));
		StringTokenizer st = new StringTokenizer(r.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Farmer[] farmers = new Farmer[M];
		for(int i = 0; i < M; i++){
			st = new StringTokenizer(r.readLine());
			farmers[i] = new Farmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(farmers);
		int money = 0;
		for(int i = 0; i < farmers.length; i++){
			if(N <= farmers[i].amount){
				money += N*farmers[i].price;
				break;
			}
			else{
				N -= farmers[i].amount;
				money += farmers[i].price * farmers[i].amount;
			}
		}
		pw.println(money);
		pw.close();
	}
	*/
	/*
	//Barn Repair
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("barn1.out"));
		StringTokenizer st = new StringTokenizer(r.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] occupied = new int[C];
		for(int i = 0; i < C; i++){
			occupied[i] = Integer.parseInt(r.readLine());
		}
		Arrays.sort(occupied);
		ArrayList<Integer> aList = new ArrayList<Integer>();
		for(int i = 1; i < C; i++){
			if(occupied[i-1]+1 == occupied[i]) continue;
			else aList.add(occupied[i]-occupied[i-1]-1);
		}
		int[] gaps = new int[aList.size()];
		for(int i = 0; i < aList.size(); i++){
			gaps[i] = aList.get(i);
		}
		Arrays.sort(gaps);
		int total = occupied[C-1] - occupied[0] + 1;
		int index = gaps.length - 1;
		for(int i = 2; i <= M && index >=0; i++){
			total -= gaps[index--];
		}
		pw.println(total);
		pw.close();
	}
	*/
	//CALF FLAC
	/*
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("calfflac.out"));
		String input = r.readLine();
		String lineString;
		while((lineString = r.readLine()) != null){
			input = input+"\n"+lineString;
		}
//		String input = "Confucius say: Madam, I'm Adam.";
		int max = 0;
		int posx = 0;
		int posy = 0;
		for(int i = 0; i < input.length(); i++){
			if(!Character.isLetter(input.charAt(i))){
				continue;
			}
			int j = i - 1;
			int k = i + 1;
			int count = 1;
			int posx_temp = i;
			int posy_temp = i;
			while(j >= 0 && k < input.length()){
				if(!Character.isLetter(input.charAt(j))){
					j--;
					continue;
				}
				if(!Character.isLetter(input.charAt(k))){
					k++;
					continue;
				}
				if(Character.toLowerCase(input.charAt(j)) == Character.toLowerCase(input.charAt(k))){
					count += 2;
					posx_temp = j;
					posy_temp = k;
					j--; k++;
				}
				else{
					break;
				}
			}
			if(count > max){
				max = count;
				posx = posx_temp;
				posy = posy_temp;
			}
		}
		
		
		for(int i = 0; i < input.length(); i++){
			if(!Character.isLetter(input.charAt(i))){
				continue;
			}
			int j = i;
			int k = i + 1;
			int count = 0;
			int posx_temp = 0;
			int posy_temp = 0;
			while(j >= 0 && k < input.length()){
				if(!Character.isLetter(input.charAt(j))){
					j--;
					continue;
				}
				if(!Character.isLetter(input.charAt(k))){
					k++;
					continue;
				}
				if(Character.toLowerCase(input.charAt(j)) == Character.toLowerCase(input.charAt(k))){
					count += 2;
					posx_temp = j;
					posy_temp = k;
					j--; k++;
				}
				else{
					break;
				}
			}
			if(count > max){
				max = count;
				posx = posx_temp;
				posy = posy_temp;
			}
		}
		System.out.println(max);
		pw.println(max);
		for(int i = posx; i <= posy; i++){
			pw.print(input.charAt(i));
			System.out.print(input.charAt(i));
		}
		pw.println();
		pw.close();
	}
	*/
	
	/*
	//Prime Cryptarithm
	
	public static boolean isLegalPart(int num, int[] array){
		if(num > 99 && num < 1000 && isLegal(num, array)) return true;
		return false;
	}
	
	public static boolean isLegalAns(int num, int[] array){
		if(num > 999 && num < 10000 && isLegal(num, array)) return true;
		return false;
	}
	public static boolean isLegal(int num, int[] array){
		while(num != 0){
			int a = num % 10;
			num = num / 10;
			int i = 0;
			for(i = 0; i<array.length; i++){
				if(array[i] == a){
					break;
				}
			}
			if(i == array.length) return false;
		}
		return true;
	}
	public static void solve(int level, int[] sample, int num, int[] array){
		if(level == 0){
			int a = sample[0]*100 + sample[1]*10 + sample[2];
			int b = sample[3]*10 + sample[4];
			int f1 = sample[4] * a;
			int f2 = sample[3] * a;
			int ans = a*b;
			if(isLegalPart(f1, array) && isLegalPart(f2, array) && isLegalAns(ans, array)){
				System.out.println(a + "*" + b +"=" + ans);
				result++;
			}
			return;
		}
		for(int i = 0; i < num; i++){
			sample[5-level] = array[i];
			solve(level-1, sample, num, array);
		}
	}
	public static int result = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("crypt1.out"));
		int N = Integer.parseInt(r.readLine());
		StringTokenizer tokenizer = new StringTokenizer(r.readLine());
		int[] array = new int[N];
		for(int i = 0; i < N; i++)
			array[i] = Integer.parseInt(tokenizer.nextToken());
		solve(5, new int[5], N, array);
		pw.println(result);
		pw.close();
		System.out.println(result);
	}
	*/
}
