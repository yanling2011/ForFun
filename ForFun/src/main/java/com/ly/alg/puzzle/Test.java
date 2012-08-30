package com.ly.alg.puzzle;

import java.util.HashMap;





/*
public class Test{
    public static void printAllInterleavings(String str, String str1, String str2, int i1, int i2){
        if(i1 == str1.length()){
            System.out.println(str+str2.substring(i2));
            return;
        }
        if(i2 == str2.length()){
            System.out.println(str+str1.substring(i1));
            return;
        }
		printAllInterleavings(str+str1.charAt(i1), str1, str2, i1+1, i2);
		printAllInterleavings(str+str2.charAt(i2), str1, str2, i1, i2 + 1);
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();
        String str = "";
        printAllInterleavings(str, str1, str2, 0, 0);
    }
}
*/
/*
public class Test{
    public static boolean isInterleaving(String str, String str1, String str2){
        int i = 0;
        int j = 0;
        int k = 0;
        while(k != str.length()){
            if(i == str1.length() && j == str2.length()) return false;
            if(i != str1.length() && str1.charAt(i) == str.charAt(k)){
                i++; k++;
            }
            else if(j != str2.length() && str2.charAt(j) == str.charAt(k)){
                j++; k++;
            }
            else return false;
        }
        return true;
    }
    public static void main(String[] args){
        String str, str1, str2;
        Scanner scanner = new Scanner(System.in);
        str1 = scanner.next();
        str2 = scanner.next();
        str = scanner.next();
        if(isInterleaving(str, str1, str2)){
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }
    }
}
*/
/*
public class Test{
    public static void solve(String str){
        Stack<Character> s = new Stack<Character>();
        int num = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '*' && s.size() < 2){
                num++;
            }
            else if(str.charAt(i) == '*'){
				s.pop();
				s.pop();
				s.push('x');
            }
            else{
                s.push('x');
            }
        }
        if(s.size() %2 != 0 && s.size() > 1) num += 1;
        num += s.size()/2;
        System.out.println(num);
    }
    public static void main(String[] args){
        Scanner scanner  = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int i = 0; i < T; i++){
            solve(scanner.next());
        }
    }
}
*/
/*
public class Test{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
		int[] array = new int[size];
		int j = -1;
		for(int i = 0; i < size; i++) array[i] = scanner.nextInt();
		for(int i = 0; i < size; i++){
			if(j == -1 && array[i] == 0){
				j = i;
			}
			else if(j != -1 && array[i]!= 0){
				array[j]= array[i];
				array[i] = 0;
				j = i;
			}
		}
		for(int i = 0; i < size; i++)
			System.out.print(array[i] +",");
	}
}
*/

/*
public class Test{
    public static void solve(String str){
        Stack<Character> s = new Stack<Character>();
        for(int i = 0; i < str.length(); i++){
            char ch2 = str.charAt(i);
            while(!s.empty() && s.peek() != ch2){
            	char ch1 = s.pop();
                int x = ch1 - 'a' + ch2 -'a';
                if(x == 1) ch2 = 'c';
                else if(x == 2) ch2 = 'b';
                else if(x == 3) ch2 = 'a';
            }
            s.push(ch2);
        }
        System.out.println(s.size());
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int i = 0; i < T; i++) solve(scanner.next());
    }
}
*/

/*
public class Test{
    public static void solve(int C, int B, int P){
        int result;
        if(P==100 && B==0) result = C;
        else if((C-1)*100 >= C*P){
		    result = (int) Math.ceil((double)(C*P)/100);
		    if(result + B < C) result = C - B;
		}
	    else{
	
	        result = (int) Math.ceil(B / (100 / (double)(C*P - 100*(C-1)) - 1)) + (C - 1);
	
	    }
		System.out.println(result);
	}
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int i = 0; i < T; i++){
            solve(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
    }
}
*/
/*
public class Test{
    public static class Node{
        int content;
        Node left;
        Node right;
        public Node(int content){
            this.content = content;
        }
        public int getContent(){
            return content;
        }
        public void setContent(int content){
            this.content = content;
        }
    }
    public static void visit(Node root){
        Queue<Node> q = new LinkedList<Node>();
        Node n = root;
        q.offer(n);
        int count = q.size();
        int level = 0;
        while(count != 0){
	        System.out.print(level+":");
	        for(int i = 0; i < count; i++){
	        	n = q.poll();
	        	System.out.print(n.getContent()+" " );
	        	if(n.left != null) q.offer(n.left);
	        	if(n.right != null) q.offer(n.right);
	        }
	        System.out.println();
	        count = q.size();
	        level++;
        }
    }
    
    public static Node init(){
    	Node root = new Node(1);
    	root.left = new Node(2);
    	root.right = new Node(3);
    	root.left.right = new Node(4);
    	return root;
    }
    public static void main(String[] args){
        Node root = init();
        visit(root);
    }
}
*/
/*
import java.util.Arrays;
public class Test{
    public static void solve(int[] array){
        Arrays.sort(array);
        for(int i = 0; i < array.length; i++){
        	int j = i+1;
        	int k = array.length - 1;
        	while(j < k){
        		int sum = array[i] + array[j] + array[k];
        		if(sum == 0){
        			System.out.println(array[i] +" "+ array[j] +" "+ array[k]);
        			return;
        		}
        		else if(sum < 0) j++;
        		else k--;
        	}
        }
        System.out.println("No answer");
       
    }
    public static void main(String[] args){
        int[] array = {-1, 2, 3, 4, -2};
        solve(array);
    }
}
*/
/*
public class Test{
	public static void main(String[] args){
		String[] array = {"fooo", "barr", "wing", "ding"};
		String srcString = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
		Hashtable<String, Integer> hs = new Hashtable<String, Integer>();
		int add = 0;
		for(int i = 0; i < array.length; i++){
			hs.put(array[i], i+1);
			add += i+1;
		}
		int subLength = array[0].length();
		int arraySize = array.length;
		int strLength = srcString.length();
		int[] match = new int[strLength - subLength + 1];
		for(int i = 0; i < match.length; i++){
			String part = srcString.substring(i, i+subLength);
			if(hs.containsKey(part)){
				match[i] = hs.get(part);
			}
			else{
				match[i] = 0; 
			}
		}
		for(int i = subLength * (arraySize - 1); i < match.length; i++){
			int sum = match[i];
			for(int j = 1; j < arraySize; j++){
				sum += match[i-j*subLength];
			}
			if(sum == add){
				System.out.println(srcString.substring(i-(arraySize-1)*subLength, i+subLength));
			}
		}
	}
}
*/
/*
//Balance
public class Test{
	public static Balance[] balances;
	public static class Balance{
		int No;
		boolean balanced;
		int leftPounds;
		int rightPounds;
		Balance[] left;
		Balance[] right;
	}
	public static void solve(){
		for(int i = 0; i < balances.length; i++){
			Balance b = balances[i];
			banlanceIt(b);
		}
	}
	public static int[][] result;
	public static int banlanceIt(Balance b){
		if(b.balanced){
			return b.leftPounds * 2 + 10;
		}
		int leftPounds = 0;
		int rightPounds = 0;
		for(int j = 0; j < b.left.length; j++){
			leftPounds += banlanceIt(b.left[j]);
		}
		for(int j = 0; j < b.right.length; j++){
			rightPounds += banlanceIt(b.right[j]);
		}
		leftPounds += b.leftPounds;
		rightPounds += b.rightPounds;
		
		if(leftPounds > rightPounds){
			result[b.No][0] = 0;
			result[b.No][1] = leftPounds - rightPounds;
			b.rightPounds = leftPounds;
			b.leftPounds = leftPounds;
			
		}
		else{
			result[b.No][1] = 0;
			result[b.No][0] = rightPounds - leftPounds;
			b.leftPounds = rightPounds;
			b.rightPounds = rightPounds;
		}
		b.balanced = true;
		return b.leftPounds*2 + 10;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(scanner.readLine());
		balances = new Balance[N];
		for(int i = 0; i < N; i++) balances[i]= new Balance(); 
		result = new int[N][2];
		String[] temp;
		Balance b;
		for(int i = 0; i < N; i++){
			String line;
			b = balances[i];
			line = scanner.readLine();
			temp = line.split(" ");
			
			b.No = i;
			b.leftPounds = Integer.parseInt(temp[0]);
			b.left = new Balance[temp.length - 1];
			for(int j = 0; j < temp.length - 1; j++){
				b.left[j] = balances[Integer.parseInt(temp[j+1])];
			}
			line = scanner.readLine();
			temp = line.split(" ");
			b.rightPounds = Integer.parseInt(temp[0]);
			b.right = new Balance[temp.length - 1];
			for(int j = 0; j < temp.length - 1; j++){
				b.right[j] = balances[Integer.parseInt(temp[j+1])];
			}
			b.balanced = false;
		}
		solve();
		for(int i = 0; i < N; i++){
			System.out.println(i+":"+result[i][0]+" "+result[i][1]);
		}
	}
}
*/

/*
Turn a array to a sorted one, with two operations: decrement by 1, delete
public class Test{
	public static int solve(int[] array, int size, int max){
		int ans;
		if(size < 0) return 0;
		else{
			if(array[size] <= max){
				ans = solve(array, size -1, array[size]);
			}
			else{
				int temp1 = solve(array, size-1, max) + array[size];
				int temp2 = solve(array, size - 1, max) + array[size] - max;
				ans = temp1 > temp2 ? temp2: temp1;
			}
		}
		return ans;
	}
	public static void main(String[] args){
		int[] array = {5, 3, 5, 3};
		System.out.println(solve(array, array.length-1, Integer.MAX_VALUE));
	}
}
*/
/*
Rearrange an array with 0, 1, 2
public class Test{
	public static void solve(int[] array){
		int p1 = 0; int p2 = array.length-1;
		int p3 = 0;
		while(array[p1] == 0) p1++;
		while(array[p2] == 2) p2--;
		p3 = p1;
		while(p3 < p2){
			if(array[p3] == 1) p3++;
			else if(array[p3] == 0){
				int temp = array[p1];
				array[p1] = array[p3];
				array[p3] = temp;
				p1++;
			}
			else {
				int temp = array[p2];
				array[p2] = array[p3];
				array[p3] = temp;
				p2--;
			}
		}
	}
	public static void main(String[] args){
		int[] array = {0,0,0,0,2,1,2,0,2,1,2,1,2,0,1,0,1,0,2,0,1};
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
		solve(array);
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
}
*/
/*
//count words in a sentence
public class Test{
	public static void main(String[] args){
		String sentence = "c        d  ";
		int count = 0;
		for(int i = 0; i < sentence.length(); i++){
			if(i == sentence.length() - 1) break;
			else if(sentence.charAt(i) == ' ' && sentence.charAt(i+1) !=' ') count++;
			else{
				continue;
			}
		}
		System.out.println(count);
	}
}
*/
/*
//print a tree in level order
public class Test{
	public static class Node{
		int content;
		Node left;
		Node right;
		public Node(int content){
			this.content = content;
			this.left = null;
			this.right = null;
		}
	}
	public static Node init(){
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		return root;
	}
	public static void solve(Node root){
		Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
		queue.offer(root);
		while(queue.size() != 0){
			for(int i = 0; i < queue.size(); i++){
				Node n = queue.poll();
				if(n.left != null) queue.offer(n.left);
				if(n.right != null) queue.offer(n.right);
				System.out.print(n.content+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args){
		Node root = init();
		solve(root);
	}
}
*/
// String match
/*
public class Test{
	public static boolean match(String pattern, String s){
		if(s == null || pattern == null) 
			throw new IllegalArgumentException();
		if(pattern.isEmpty()) return s.isEmpty();
		if(s.isEmpty()) return pattern.charAt(0) == '*' ? match(pattern.substring(1), s) : false;
		char s_0 = s.charAt(0), p_0 = pattern.charAt(0);
		if(p_0 == '.' || p_0 == s_0) return match(pattern.substring(1), s.substring(1));
		if(p_0 == '*') return match(pattern, s.substring(1)) ? true : match(pattern.substring(1), s);
		return false;
	}
	public static void main(String[] args){
		String str1 = "bhoppali";
		String str2 = "*ali";
		System.out.println(match(str2, str1)?"true":"false");
		
	}
}
*/

public class Test{
	public static void main(String[] args){
		String str1 = "aabcd";
		String str2 = "dcbaa";
		HashMap<Character, Integer> h = new HashMap<Character, Integer>();
		for(int i = 0; i < str1.length(); i++){
			char ch = str1.charAt(i);
			if(h.containsKey(ch)){
				h.put(ch, h.get(ch) + 1);
			}
			else{
				h.put(ch, 1);
			}
		}
		for(int i = 0; i < str2.length(); i++){
			char ch = str2.charAt(i);
			if(h.containsKey(ch) && h.get(ch) >= 1){
				h.put(ch, h.get(ch) - 1);
			}
			else{
				System.out.println("False");
				return;
			}
		}
		System.out.println("True");
	}
}


