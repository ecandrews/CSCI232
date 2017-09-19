/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 3
 * Tuesday, Apr. 5, 2016
 */

import java.util.HashMap;
import java.util.Map;

public class Board {

	//x for occupied, o for vacant
	private int size;
	int [][] chessboard;
	
	public Board() { //default constructor initializes board size to 25
		this.size = 5;
		int k = 1;
		chessboard = new int [size][size];
		for(int i = 1; i < size; i++) { //x values, rows
			for(int j = 0; j < size; j++) { //y values, columns
				chessboard[i][j] = k;
				k++;
			} //end for j loop
		} //end for i loop
	} //end constructor
	
	public Board(int size) { //constructor
		this.size = size;
		int k = 1;
		chessboard = new int [size][size];
		for(int i = 0; i < size; i++) { //x values, rows
			for(int j = 0; j < size; j++) { //y values, columns
				chessboard[i][j] = k;
				k++;
			} //end for j loop
		} //end for i loop
	} //end constructor
	
} //end board class
