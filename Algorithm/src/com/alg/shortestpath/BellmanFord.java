package com.alg.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
	Node[] predecessor;
	int[] distance;
	ArrayList<Node> ShortestPath(Node[] nodes, Edge[] edges, Node source, Node target){
		initializeWeight(nodes, edges, source);
		for(int i = 0; i < nodes.length - 1; i++){
			for(int j = 0; j < edges.length; j++){
				Node u = edges[j].from;
				Node v = edges[j].to;
				if(distance[u.name] + edges[j].weight < distance[v.name]){
					distance[v.name] = distance[u.name] + edges[j].weight;
					predecessor[v.name] = u;
				}
			}
		}
		ArrayList<Node> al = new ArrayList<Node>();

		for(int i = 0; i < nodes.length; i++){
			Node u = edges[i].from;
			Node v = edges[i].to;
			if(distance[u.name] + edges[i].weight < distance[v.name]){
				System.out.println("Negative weight cycle exists.");
				return al;
			}
		}
		al.add(target);
		Node temp = target;
		while((temp=predecessor[temp.name]) != null){
			al.add(temp);
		}
		return al;
	}
	
	private int[][] initializeWeight(Node[] nodes, Edge[] edges, Node source){
		int[][] weight = new int[nodes.length][nodes.length];
		predecessor = new Node[nodes.length];
		distance = new int[nodes.length];
		for(int i = 0; i < nodes.length; i++){
			Arrays.fill(weight[i], Integer.MAX_VALUE);
			predecessor[i] = null;
			distance[i] = Integer.MAX_VALUE;
		}
		distance[source.name] = 0;
		for(int i = 0; i < edges.length; i++){
			Edge temp = edges[i];
			weight[temp.from.name][temp.to.name] = temp.weight;
		}
		return weight;
		
	}
}
