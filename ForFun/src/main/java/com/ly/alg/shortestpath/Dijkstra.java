package com.ly.alg.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {
	ArrayList<Node> findShortestPath(Node[] nodes, Edge[] edges, Node target){
		int[][] weight = initializeWeight(nodes, edges);
		int[] D = new int[nodes.length];
		Node[] p = new Node[nodes.length];
		ArrayList<Node> C = new ArrayList<Node>();
		
		for(int i = 0; i < nodes.length; i++){
			C.add(nodes[i]);
			D[i] = weight[0][i];
			if(D[i] != Integer.MAX_VALUE){
				p[i] = nodes[0];
			}
		}
		
		for(int i = 0; i < nodes.length; i++){
			int l = Integer.MAX_VALUE;
			Node n = nodes[0];
			for(Node j: C){
				if(D[j.name] < l){
					n = j;
					l = D[j.name];
				}
			}
			C.remove(n);
			
			for(int j = 0; j < nodes.length; j++){
				if(D[n.name] != Integer.MAX_VALUE && weight[n.name][j] != Integer.MAX_VALUE && D[n.name] + weight[n.name][j]< D[j] ){
					D[j] = D[n.name] + weight[n.name][j];
					p[j] = n;
				}
			}
		}
		C.clear();
		int loc = target.name;
		C.add(target);
		while(p[loc] != nodes[0]){
			if(p[loc] == null){
				return null;
			}
			C.add(0,p[loc]);
			loc = p[loc].name;
		}
		C.add(0, nodes[0]);
		return C;
	}
	private int[][] initializeWeight(Node[] nodes, Edge[] edges){
		int[][] Weight = new int[nodes.length][nodes.length];
		for(int i = 0; i < nodes.length; i++){
			Arrays.fill(Weight[i], Integer.MAX_VALUE);
		}
		for(Edge e:edges){
			Weight[e.from.name][e.to.name] = e.weight;
		}
		return Weight;
	}
	
}
