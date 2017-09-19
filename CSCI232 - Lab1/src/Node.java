/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 1
 * Tuesday, Feb. 9, 2016
 */

public class Node {

	public char key; // data item (key) character
	public int freq; // data item (frequency)
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	public void displayNode() {// display ourself

		System.out.print('{');
		System.out.print(key);
		System.out.print(", ");
		System.out.print(freq);
		System.out.print("} ");
	}
	
	public Node(char key, int freq) {
		this.key = key;
		this.freq = freq;
	}

}
