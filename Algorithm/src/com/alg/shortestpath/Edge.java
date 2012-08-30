package com.alg.shortestpath;

public class Edge implements Comparable<Edge>{
	final Node from, to;
	final int weight;
	public Edge(final Node argFrom, final Node argTo, final int argWeight){
		from = argFrom;
		to = argTo;
		weight = argWeight;
	}
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return weight - o.weight;
	}
	
	
}
