import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;
/**
 * The program WaterTransfer takes four integers c1, c2, c3, d that represent three
 * jugs that can hold c1, c2, and c3 liters of water, and a goal d liters, respectively
 *
 * Initially, jug 1 is full and the other two jugs are empty
 *
 * The program can repeat the following procedure any number of times:
 *
 * Choose two of the jugs and pour the contents of one into the other until either the first 
 * is empty or the second is full
 * 
 * The goal is to end up with exactly d liters in one of the jugs
 *
 * WaterTransfer determines the transfers required to reach the goal
 * 
 * The input is a single line containing four integers between 2 and
 * 100 (inclusive) representing c1, c2, c3, and d
 *
 * The output is a minimal sequence of jug contents, starting with the initial contents
 * and ending with one of the jugs containing d liters
 *
 * @author Jose Mojica Perez
 * @version 2.0
 * @since 2020-10-09
 **/ 
public class WaterTransfer{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		int fountain = Integer.parseInt(scan.next());
		int a = Integer.parseInt(scan.next());
		int b = Integer.parseInt(scan.next());
		int goal = Integer.parseInt(scan.next());
		scan.close();
		//If the only jug with water does not have enough water to reach the goal or if the goal
		//is not a multiple of the greatest common divisor of the volume of the three jugs
		//then the goal is impossible to achieve
		if(fountain < goal || (goal % gcd(fountain, a, b) != 0)){
			return;
		}

		ArrayDeque<JugState> states = new ArrayDeque<JugState>();
		ArrayDeque<JugState> sequence = new ArrayDeque<JugState>();
		ArrayList<JugState> visitedStates = new ArrayList<JugState>();

		JugState start = new JugState(fountain, a, b, true);
		states.addFirst(start);
		visitedStates.add(start);
		
		while(!states.isEmpty()){
			JugState currState = states.removeFirst();
			if(currState.hasAmount(goal)){
				for(JugState x = currState; x != null; x = x.prev){
					sequence.offerLast(x);
				}
				JugState y = sequence.pollLast();
				while(y != null){
					System.out.println(y);
					y = sequence.pollLast();
				}
				break;
			}
			//Generate all possible moves, ignoring illegal moves, impossible moves, and repeated moves	
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					for(int k = 0; k < 3; k++){
						if((i+j+k != 3) || (i == 1 && j == 1 && k == 1)){
							continue; //illegal moves
						}
						JugState nextState;
						if(currState.canMove(i, j, k)){
							nextState = currState.move(i, j, k);
						}
						else{
							continue; //cannot perform that move
						}
					
						boolean beenHere = false;
						for(JugState visited : visitedStates){
							if(nextState.isEquivalentTo(visited)){
								beenHere = true;
								break;
							}
						}
						if(beenHere){
							continue;//Already did that move
						}
						nextState.prev = currState;
						states.addLast(nextState);
						visitedStates.add(nextState);
					}
				}
			}
		}
	}
	/*
	 * gcd calculates the greatest common denominator of three given integers 
	 */
	public static int gcd(int x, int y, int z){
		int max = x > y ? Math.max(x,z) : Math.max(y,z);
		int gcd = -1;
		for(int i = 1; i <= max; i++){
			if(x % i == 0 && y % i == 0 && z % i == 0){
				gcd = i;
			}
		}
		return gcd;
	}
}

class JugState{
	private int[] quantities;
	public static int[] maxQuantities;
	public JugState prev; //JugState previous to this

	public JugState(int f, int a, int b, boolean beginning){
		if(beginning){
			JugState.maxQuantities = new int[] {f, a, b};
			this.quantities = new int[] {f, 0, 0};
			this.prev = null;
		}
		else{
			this.quantities = new int[] {f, a, b};
			this.prev = null;
		}
	}
	public JugState(JugState state){
		int[] temp = state.getQuantities();
		this.quantities = new int[] {temp[0], temp[1], temp[2]};

	}
	public int[] getQuantities(){
		return this.quantities;
	}
	public String toString(){
		return this.quantities[0] + " " + this.quantities[1] + " " + this.quantities[2];
	}
	public boolean hasAmount(int amt){
		if(this.quantities[0] == amt || this.quantities[1] == amt || this.quantities[2] == amt){
			return true;
		}
		return false;
	}
	public boolean isFullAt(int x){
		if(this.quantities[x] == JugState.maxQuantities[x]) {
			return true;
		}
		return false;
	}
	public boolean isEmptyAt(int x){
		if(this.quantities[x] == 0){
			return true;
		}
		return false;
	}
	public boolean isEquivalentTo(JugState state){
		return Arrays.equals(this.quantities, state.getQuantities());
	}
	/*
	 * canMove checks if a move is possible
	 *
	 * There are 6 legal moves and these are represented as a triple (f, a, b):
	 * a->b : (0,1,2)
	 * b->a : (0,2,1)
	 * f->b : (1,0,2)
	 * b->f : (2,0,1)
	 * f->a : (1,2,0)
	 * a->f : (2,1,0)
	 *
	 * if a move is legal but either the recipient is full or the giver is empty
	 * then the move cannot be performed
	 */
	public boolean canMove(int f, int a, int b){
		if(f == 0){
			if(a == 1 && b == 2){
				return !(this.isEmptyAt(1) || this.isFullAt(2));
			}
			else{
				return !(this.isEmptyAt(2) || this.isFullAt(1));
			}
		}
		else if(a == 0){
			if(f == 1 && b == 2){
				return !(this.isEmptyAt(0) || this.isFullAt(2));
			}
			else{
				return !(this.isEmptyAt(2) || this.isFullAt(0));
			}
		}
		else if(b == 0){
			if(f == 1 && a == 2){
				return !(this.isEmptyAt(0) || this.isFullAt(1));
			}
			else{
				return !(this.isEmptyAt(1) || this.isFullAt(0));
			}
		}
		return false;
	}
	/*
	 * move is called only if canMove is true
	 * 
	 * move transfers as much water as possible from the jug (1) to jug (2) ignoring
	 * jug (0) and then creates a new JugState with the new values for the jugs and returns it
	 */
	public JugState move(int f, int a, int b){
		int fount = this.quantities[0];
		int jugA = this.quantities[1];
		int jugB = this.quantities[2];
		
		int maxF = JugState.maxQuantities[0];
		int maxA = JugState.maxQuantities[1];
		int maxB = JugState.maxQuantities[2];
		
		int maxAmt;
		if(f == 0){
			if(a == 1 && b == 2){
				maxAmt = maxB - jugB;
				if(jugA >= maxAmt){
					jugA -= maxAmt;
					jugB += maxAmt;
				}
				else{
					jugB += jugA;
					jugA = 0;
				}
			}
			else{
				maxAmt = maxA - jugA;
				if(jugB >= maxAmt){
					jugB -= maxAmt;
					jugA += maxAmt;
				}
				else{
					jugA += jugB;
					jugB = 0;
				}
			}
		}
		else if(a == 0){
			if(f == 1 && b == 2){
				maxAmt = maxB - jugB;
				if(fount >= maxAmt){
					fount -= maxAmt;
					jugB += maxAmt;
				}
				else{
					jugB += fount;
					fount = 0;
				}
			}
			else{
				maxAmt = maxF - fount;
				if(jugB >= maxAmt){
					jugB -= maxAmt;
					fount += maxAmt;
				}
				else{
					fount += jugB;
					jugB = 0;
				}
			}
		}
		else if(b == 0){
			if(f == 1 && a == 2){
				maxAmt = maxA - jugA;
				if(fount >= maxAmt){
					fount -= maxAmt;
					jugA += maxAmt;
				}
				else{
					jugA += fount;
					fount = 0;
				}
			}
			else{
				maxAmt = maxF - fount;
				if(jugA >= maxAmt){
					jugA -= maxAmt;
					fount += maxAmt;
				}
				else{
					fount += jugA;
					jugA = 0;
				}
			}
		}
		JugState next = new JugState(fount, jugA, jugB, false);
		return next;
	}
}
