/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 2
 * Tuesday, March 1, 2016
 */

public class Node {

	public Node leftChild;
	public Node rightChild;
	public int value;
	
	public Node(int value) {
		this.value = value;
	}
	
	public void displayNode() { //display this Node
		System.out.print("value: " + value);
	}
	
}
