/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 3
 * Tuesday, Apr. 5, 2016
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class KnightsTour {

	private static Stack visited = new Stack(); //stack to keep track of spaces visited
	private static int numMoves; //number of moves total
	private static boolean success;
	private static final int [][] moves8 = {{-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}}; //array of possible moves for knight
	private static boolean occupied[]; //array to keep track of which squares are occupied

	public static void main(String [] args) {
		int boardSize = getBoardSize(); //get board size from user
		Board board = new Board(boardSize); //create new board with board size
		occupied = new boolean[boardSize * boardSize + 1]; 
		int begSquare = getBegSquare(boardSize); //get beginning square from user
		fillArrayBeg(); //fill the array for the beginning
		occupied[begSquare] = true; //occupy the beginning square
		visited.push(begSquare); //push square onto stack

		do {
			int nextPos = getNextPos(boardSize); //get the next possible move for piece
			if(nextPos == -1) { //if nextPos fails
				//pop top move off of stack
				visited.pop();
			} else { //nextPos returned a valid move
				visited.push(nextPos); //push move onto stack
				if(visited.size() == boardSize * boardSize) { //if the stakc is full
					printResults(true, numMoves, begSquare); //print the results, success
				} //end if statement
			} //end if else statement
		} while(!visited.empty()); //while the stack is not empty
		printResults(false, numMoves, begSquare); //print the results, fail
	} //end main method
	
	//getNextPos method
	public static int getNextPos(int boardSize) {
		int cycle = 0;

		while (cycle < 8) { //while all 8 moves have not been tried
			int dx = moves8[cycle][0];
			int dy = moves8[cycle][1];

			int x = ((int)visited.peek() - 1) % boardSize; //get x and y of top space
			int y = ((int)visited.peek() - 1) / boardSize;	
			x = x + dx; //next x
			y = y + dy; //next y

			cycle++; //used this move

			if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) { //make sure next move is on board
				int nextPos = x + (y * boardSize) + 1; //convert (x, y) to a single integer
				//make sure position is not occupied
				if(occupied[nextPos] == false) { //if cell is not occupied
					occupied[nextPos] = true;
					numMoves++;
					return nextPos; //found a valid move
				} //end if cell not occupied
			} //end if(x >= 0 && ...
		} //end while loop
		cycle = 0;
		numMoves++;
		return -1; //getNextPos failed
	} //end getNextPos

	//method to get board size from user
	public static int getBoardSize() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter board size (8 for 8x8 board): ");
		int size = input.nextInt();
		return size;
	} //end getBoardSize

	//method to get the beginning square from the user
	public static int getBegSquare(int boardSize) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the beginning square (1 to " + (boardSize * boardSize) + "): ");
		int begSquare = input.nextInt();
		return begSquare;
	} //end getBegSquare

	//method to print the results of the program
	public static void printResults(boolean success, int numMoves, int begSquare) {
		if(success) {
			System.out.println("SUCCESS:");
		} else {
			System.out.println("FAILURE:");
		}
		//print out the number of moves and the moving sequence
		System.out.println("Total number of moves: " + numMoves);
		System.out.print("Moving sequence: ");
		while(!visited.empty()) {
			System.out.print(visited.pop() + " ");
		}
		System.out.print("(" + begSquare + ")");
	} //end printResults
	
	//method to initialize the occupied array to all be empty
	public static void fillArrayBeg() {
		for(int i = 0; i < occupied.length; i++) {
			occupied[i] = false;
		} //end for loop
	} //end fillArrayBeg

} //end KnightsTour2
