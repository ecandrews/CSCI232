/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 1
 * Tuesday, Feb. 9, 2016
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HuffmanApp {

	public static void main(String[] args) throws IOException
	{
		HuffmanTree huff = null;
		String str;
		
		//program gets input from user
		System.out.println("Enter text lines, terminate with $");
		str = getText();
		huff = new HuffmanTree(str);

		while(true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("code, decode, show tree, frequency table, quit: ");
			int choice = getChar();
			switch(choice)
			{
			case 'c': //prints out the encoded message
				huff.encode();
				break;
			case 'd': //prints out the decoded message
				huff.decode(huff.huffTree.getRoot(), "");
				break;
			case 's': //prints out a text representation of the huffman tree
				huff.huffTree.displayTree();
				break;
			case 'f': //prints out the code table
				huff.displayCodeTable();
				break;
			case 'q': //exits the program
				System.exit(0);
				break;
			default: //any other entries are invalid
				System.out.print("Invalid entry\n");
			}  
		}  
	}  

	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static String getText() throws IOException
	{
		String outStr="", str = "";
		while(true)
		{
			str = getString();
			if( str.equals("$") )
				return outStr;
			outStr = outStr + str + "\n";
		}
	} 

	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}

} 
