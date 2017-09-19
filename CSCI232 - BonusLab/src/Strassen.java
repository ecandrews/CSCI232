import java.util.*;
import java.math.*;

/*
 * Elizabeth Andrews -02224876
 * CSCI232 Bonus Lab
 * Wed. Apr 27, 2016
 * Implement Strassen's algorithm, randomly generate numbers for matrices
 */

public class Strassen {

	public static int m1Rows, m1Cols, m2Rows, m2Cols;
	
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		//get number of rows and columns for each matrix from user
		System.out.print("Enter the number of rows for Matrix 1: ");
		m1Rows = input.nextInt();
		System.out.print("Enter the number of columns for Matrix 1: ");
		m1Cols = input.nextInt();
		System.out.print("Enter the number of rows for Matrix 2: ");
		m2Rows = input.nextInt();
		System.out.print("Enter the number of columns for Matrix 2: ");
		m2Cols = input.nextInt();
		//declare two arrays, matrix1 and matrix2
		int [][] m1 = new int[m1Rows][m1Cols];
		int [][] m2 = new int[m2Rows][m2Cols];
		
		//initialize matrix1 and matrix2 to random integers from 0 to 5, inclusive
		for(int i = 0; i < m1Rows; i++) {
			for(int j = 0; j < m1Cols; j++) {
				m1[i][j] = random.nextInt(6);
			}
		}
		for(int i = 0; i < m2Rows; i++) {
			for(int j = 0; j < m2Cols; j++) {
				m2[i][j] = random.nextInt(6);
			}
		}
		
		//declare a third array created by multiplying the first two matrices
		int [][] m3 = multiply(m1, m2);
		//print results of multiplication
		printMatrices(m1, m2, m3);
	} //end main method
	
	//method to multiply two matrices, called recursively
	public static int[][] multiply(int [][] a, int [][] b) {
		int n = a.length;
		int [][] m3 = new int [n][n];
		if(n == 1) { //base case, multiply the only only entries
			m3[0][0] = a[0][0] * b[0][0];
		} else { //create new arrays for each quarter of each array
			int [][] a00 = new int[n/2][n/2];
			int [][] a01 = new int[n/2][n/2];
			int [][] a10 = new int[n/2][n/2]; 
			int [][] a11 = new int[n/2][n/2];
			int [][] b00 = new int[n/2][n/2];
			int [][] b01 = new int[n/2][n/2];
			int [][] b10 = new int[n/2][n/2];
			int [][] b11 = new int[n/2][n/2];
			
			//split matrix a and matrix b into 4 parts
			splitMatrices(a, a00, 0, 0);
			splitMatrices(a, a01, 0, n/2);
			splitMatrices(a, a10, n/2, 0);
			splitMatrices(a, a11, n/2, n/2);
			splitMatrices(b, b00, 0, 0);
			splitMatrices(b, b01, 0, n/2);
			splitMatrices(b, b10, n/2, 0);
			splitMatrices(b, b11, n/2, n/2);

			
			/* STRASSEN'S ALGORITHM
			 * M1 = (a00+a11 * b00+b11)
			 * M2 = (a10+a11 * b00)
			 * M3 = (a00 * b01-b11)
			 * M4 = (a11 * b10-b00)
			 * M5 = (a00+a01 * b11)
			 * M6 = (A10-A00 * B00+B01)
			 * M7 = (A01-A11 * B10+B11)
			 */
			
			//multiply recursively according to strassen's algorithm
			int [][] M1 = multiply(addMatrices(a00, a11), addMatrices(b00, b11));
			int [][] M2 = multiply(addMatrices(a10, a11), b00);
			int [][] M3 = multiply(a00, subtractMatrices(b01, b11));
			int [][] M4 = multiply(a11, subtractMatrices(b10, b00));
			int [][] M5 = multiply(addMatrices(a00, a01), b11);
			int [][] M6 = multiply(subtractMatrices(a10, a00), addMatrices(b00, b01));
			int [][] M7 = multiply(subtractMatrices(a01, a11), addMatrices(b10, b11));
			
			/* STRASSEN'S ALGORITHM
			 * c00 = m1 + m4 - m5 + m7
			 * c01 = m3 + m5
			 * c10 = m2 + m4
			 * c11 = m1 + m3 - m2 + m6
			 */
			
			//join results of matrix multiplication into one final multiplied array according to strassen's algorithm
			int [][] c00 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
			int [][] c01 = addMatrices(M3, M5);
			int [][] c10 = addMatrices(M2, M4);
			int [][] c11 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);
			joinMatrices(c00, m3, 0, 0);
			joinMatrices(c01, m3, 0, n/2);
			joinMatrices(c10, m3, n/2, 0);
			joinMatrices(c11, m3, n/2, n/2);
		} //end if-else statement
		
		//return final multiplied matrix
		return m3;
	} //end multiply method
	
	//method to split the matrices into smaller ones
	public static void splitMatrices(int [][] a, int [][] b, int i, int j) {
		for(int i1 = 0, i2 = i; i1 < b.length; i1++, i2++) {
			for(int j1 = 0, j2 = j; j1 < b.length; j1++, j2++) {
				b[i1][j1] = a[i2][j2];
			} //end inside for loop
		} //end outside for loop
	} //end splitMatrices method

	//method to add two matrices together
	public static int [][] addMatrices(int [][] a, int [][] b) {
		int n = a.length;
		int [][] c = new int [n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				c[i][j] = a[i][j] + b[i][j];
			} //end inside for loop
		} //end outside for loop
		return c; //return new matrix
	} //end addMatrices method
	
	//method to subtract two matrices from each other
	public static int[][] subtractMatrices(int [][] a, int [][] b) {
		int n = a.length;
		int [][] c = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				c[i][j] = a[i][j] - b[i][j];
			} //end inside for loop
		} //end ouside for loop
		return c; //return new matrix
	} //end subtractMatrices method
	
	//method to join two matrices together
	public static void joinMatrices(int [][] a, int [][] b, int i, int j) {
		for(int i1 = 0, i2 = i; i1 < a.length; i1++, i2++) {
			for(int j1 = 0, j2 = j; j1 < a.length; j1++, j2++) {
				b[i2][j2] = a[i1][j1];
			} //end inside for loop
		} //end outside for loop
	} //end joinMatrices method
	
	//method to print the results of the matrix multiplication
	public static void printMatrices(int [][] m1, int [][] m2, int [][] m3) {
		System.out.println();
		//print matrix 1
		System.out.println("Matrix 1:");
		for(int i = 0; i < m1Rows; i++) {
			for(int j = 0; j < m1Cols; j++) {
				System.out.print(m1[i][j] + "  ");
			} //end col for loop
			System.out.println();
		} //end row for loop
		System.out.println();
		
		//print matrix 2
		System.out.println("Matrix 2:");
		for(int i = 0; i < m2Rows; i++) {
			for(int j = 0; j < m2Cols; j++) {
				System.out.print(m2[i][j] + "   ");
			} //end col for loop
			System.out.println();
		} //end row for loop
		System.out.println();
		
		//print multiplied matrix
		System.out.println("Multiplied Matrix:");
		for(int i = 0; i < m3.length; i++) {
			for(int j = 0; j < m3.length; j++) {
				System.out.print(m3[i][j] + "  ");
			} //end col for loop
			System.out.println();
		} //end row for loop
	} //end printMatrices method
	
} //end Strassen class
