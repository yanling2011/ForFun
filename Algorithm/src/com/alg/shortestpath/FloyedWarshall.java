package com.alg.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FloyedWarshall {
	private final int[][] d;
	private final Node[][] next;
	
	public FloyedWarshall(final Node[] nodes, final Edge[] edges){
		final int maxNodes = 23000;
		d = initializeWeight(nodes, edges);
		next = new Node[nodes.length][nodes.length];
		
		for(int k = 0; k < nodes.length; k++){
			for(int i = 0; i < nodes.length; i++){
				for(int j = 0; j < nodes.length; j++){
					if(d[i][k] != Integer.MAX_VALUE
					 && d[k][j] != Integer.MAX_VALUE
					 && d[i][k] + d[k][j] < d[i][j]){
						d[i][j] = d[i][k]+ d[k][j];
						next[i][j] = nodes[k];
					}
				}
			}
		}
	}
	
	public int getShortestDistance(final Node source, final Node target){
		return d[source.name][target.name];
	}
	
	public List<Node> getShortestPath(final Node source, final Node target){
		if(d[source.name][target.name] == Integer.MAX_VALUE){
			return new ArrayList<Node>();
		}
		final List<Node> path = getIntermediatePath(source, target);
		path.add(0, source);
		path.add(target);
		return path;
	}
	private List<Node> getIntermediatePath(final Node source, final Node target){
		if(next[source.name][target.name] == null){
			return new ArrayList<Node>();
		}
		final List<Node> path = new ArrayList<Node>();
		path.addAll(getIntermediatePath(source, next[source.name][target.name]));
		path.add(next[source.name][target.name]);
		path.addAll(getIntermediatePath(next[source.name][target.name], target));
		return path;
	}
	private int[][] initializeWeight(final Node[] nodes, final Edge[] edges){
		int[][] weight = new int[nodes.length][nodes.length];
		for(int i = 0; i < nodes.length; i++){
			Arrays.fill(weight[i], Integer.MAX_VALUE);
			
		}
		for(Edge e: edges){
			weight[e.from.name][e.to.name] = e.weight;
		}
		return weight;
	}

}
