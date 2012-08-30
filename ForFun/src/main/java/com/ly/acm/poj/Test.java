package com.ly.acm.poj;

import java.util.LinkedList;
import java.util.Scanner;


public class Test {
	public static int A, B, C;
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		A = scanner.nextInt();
		B = scanner.nextInt();
		C = scanner.nextInt();
		LinkedList<State> openQueue = new LinkedList<State>();
		LinkedList<State> closedQueue = new LinkedList<State>();
		int count = 0;
		State initialState = new State(null, 0, 0, 0);
		openQueue.addLast(initialState);
		while(!openQueue.isEmpty()){
			State state = openQueue.getFirst();
			if(state.canHold(C)){
				
			}
			else{
				//move this state to closedQueue
				closedQueue.addLast(state);
				openQueue.remove(state);
				//generate branches for further search
				//fill A
				++count;
				State newState = new State(state, A, state.B, count);
				if(!closedQueue.contains(newState)){
					openQueue.addLast(newState);
				}
				//fill B
				newState = new State(state, state.A, B, count);
				if(!closedQueue.contains(newState)){
					openQueue.addLast(newState);
				}
				//fill A with B
				int aOld = state.A;
				int bOld = state.B;
				int emptyAOld = A - aOld;
				int aNew = aOld;
				int bNew = bOld;
				if(emptyAOld != 0 && bOld >= emptyAOld){
					aNew = A;
					bNew = bOld - emptyAOld;
					
				}
				else if(emptyAOld != 0){
					aNew = bOld + aOld;
					bNew = 0;
				}
				else{
				}
				newState = new State(state, aNew, bNew, count);
				if(!closedQueue.contains(newState)){
					openQueue.addLast(newState);
				}
				//fill B with A
				int emptyBOld = B - bOld;
				if(emptyBOld != 0 && aOld >= emptyBOld){
					aNew = aOld - emptyBOld;
					bNew = B;	
				}
				else if(emptyBOld != 0){
					bNew = bOld + aOld;
					aNew = 0;
				}
				else{
				}
				newState = new State(state, aNew, bNew, count);
				if(!closedQueue.contains(newState)){
					openQueue.addLast(newState);
				}			
			}
		}
		System.out.println();
	}
	public static class State{
		public State(State pre, int A, int B, int count){
			this.pre = pre;
			this.A = A;
			this.B = B;
			this.count = count;
		}
		boolean canHold(int C){
			if(A == C || B == C || A + B == C)
				return true;
			return false;
		}
		public State pre;
		public int A;
		public int B;
		public int count;
		
		public boolean equals(State s){
			if(this.A == s.A && this.B == s.B){
				return true;
			} else{
				return false;
			}
		}
	}
}
