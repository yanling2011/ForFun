package com.ly.alg.shortestpath;

public class Node implements Comparable<Node>{
	final int name;
	boolean visited = false;	
	public Node(final int argName){
		name = argName;
	}

	public int compareTo(final Node o) {
		return o == this ? 0 : -1;
	}
	
}
