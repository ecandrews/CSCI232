/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 1
 * Tuesday, Feb. 9, 2016
 */

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class HuffmanTree {
	String input;
	Hashtable<Character, Integer> freqTable = new Hashtable<Character, Integer>();
	Hashtable<Character, String> codeTable = new Hashtable<Character, String>();
	int maxSize;
	PriorityQ pq;
	Tree huffTree;
	String encoded;
	String decoded;

	public HuffmanTree(String str) {

		//replace spaces and line breaks with [ and \, and convert string to lowercase
		str = str.replace(' ', '[');
		str = str.replace('\n', '\\');
		input = str.toLowerCase();
		//make frequency table using input from user
		makeFreqTable(input);
		//make priority queue based on the number of characters from user input
		pq = new PriorityQ(freqTable.size());
		queueTree(freqTable); 
		makeHuffmanTree(); 
		encoded = makeCodeTable(huffTree.getRoot(), "");
	}
	
	//method to create a frequency table for the characters in the input from user
	public void makeFreqTable(String str) {
		//initialize hashtable with frequencies of each character
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Integer val = freqTable.get(new Character(c));
			//for hashtable: key = character, value = frequency of character
			if(val != null) {
				freqTable.put(c, new Integer(val + 1));
			} else {
				freqTable.put(c, 1);
			}
		}
	}
	
	//method to put the entries from the frequency table into a priority queue
	public void queueTree(Hashtable<Character, Integer> freqTable) {
		Set<Character> keys = freqTable.keySet();
		for(Character key: keys) {
			Tree n = new Tree();
			//create a tree with one node, where key = character and value = frequency
			n.insert(key, freqTable.get(key));
			//insert tree into priority queue
			pq.insert(n);
		}
	}

	//method to create a huffman tree from the priority queue
	public void makeHuffmanTree() {
		
		while(pq.getSize() != 1) { 
			Tree TreeA = pq.remove(); //remove min
			Tree TreeB = pq.remove(); //remove next min
			Tree n = new Tree();
			//create new subtree with the two minimum trees from priority queue
			n.insert('+', TreeA.getRoot().freq + TreeB.getRoot().freq);
			n.getRoot().leftChild = TreeA.getRoot();
			n.getRoot().rightChild = TreeB.getRoot();
			//insert newly created tree back into priority queue
			pq.insert(n);
		}
		//when only one tree is left in queue, that tree becomes huffTree
		huffTree = pq.remove();	
	}

	//method to print out the code table with characters and frequencies
	public void displayCodeTable() { 
		Set<Character> keys = freqTable.keySet();
		for(Character key: keys) {
			System.out.println(key + ": " + codeTable.get(key));
		}
	}

	//method to create a code table from the values in the huffman tree
	public String makeCodeTable(Node root, String c) {
		//if node is not a leaf node:
		if(root.key == '+') {
			makeCodeTable(root.leftChild, c + "0");
			makeCodeTable(root.rightChild, c + "1");
		} else { 
			//insert the character and it's path back into the hashtable
			codeTable.put(root.key, c);
		}
		return c;
	}

	//method to encode input into condensed binary using the code table
	public void encode() { 
		encoded = "";
		for(int i = 0; i < input.length(); i++) {
			//encoded message = the value of each character based on the code table
			encoded = encoded + codeTable.get(input.charAt(i));
		}
		System.out.println(encoded);
	}

	//method to decode an encoded string using the huffman tree
	public void decode(Node root, String c) { 
		Node originalRoot = root; //very top root of the tree
		for(int i = 0; i < encoded.length(); i++) {
			if(encoded.charAt(i) == '0') { //if the character is a 0, go left
				root = root.leftChild;
			}
			if(encoded.charAt(i) == '1') { //if the character is a 1, go right
				root = root.rightChild;
			}
			if(root.key != '+') { //if a leaf node is reached
				c = c + root.key; //save character as part of the string
				root = originalRoot; //go back to the top of the tree
			}
		}
		decoded = c;
		System.out.println(decoded);
	}
}
