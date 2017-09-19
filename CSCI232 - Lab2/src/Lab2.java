/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 2
 * Tuesday, March 1, 2016
 */

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//program randomly generates numbers to insert into the tree

public class Lab2 {

	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		//get array size and number of items from user
		int arraySize = getArraySize();
		int numItems = getNumItems();
		//creates a hashtable with an integer as the key, and a tree as the value;
		Hashtable<Integer, Tree> treeHashTable = new Hashtable<Integer, Tree>();
		//method to populate the hashtable
		fillHashtable(treeHashTable, numItems, arraySize);

		while(true) { //exits internally
			int choice = printMenu(); //returns users choice from the menu
			switch(choice) {
			case 0: //user chose show
				//call method to display hashtable
				displayHashtable(treeHashTable, arraySize);
				break;
			case 1: //user chose insert
				//call method to insert a new item into the hashtable
				insertItem(treeHashTable, arraySize);
				break;
			case 2: //user chose find
				//call method to find a certain value in the hashtable
				findItem(treeHashTable, arraySize);
				break;
			case 3: //user chose quit
				System.exit(0); //exit program
			} //end switch statements
		} //end while loop

	} //end main method

	
	//method to get array size from user
	public static int getArraySize() {
		Scanner input = new Scanner(System.in);
		int arraySize = -1;
		do {
			System.out.print("Enter size of hash table: ");
			arraySize = input.nextInt();
			if(arraySize < 0) { //array size from user must be greater than 0
				System.out.println("Please enter an integer that is greater than zero.");
			}
		} while(arraySize < 0);
		
		return arraySize;
	} //end getArraySize

	
	//method to get number of items from user
	public static int getNumItems() {
		Scanner input = new Scanner(System.in);
		int numItems = -1;
		do {
			System.out.print("Enter initial number of items: ");
			numItems = input.nextInt();
			if(numItems < 0) { //num of items from user must be greater than 0
				System.out.println("Please enter an integer that is greater than zero.");
			}
		} while(numItems < 0);
		
		return numItems;
	} //end getNumItems

	
	//method that prints out the menu and returns the user's choice to the main method
	public static int printMenu() {
		Scanner input = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Enter number for choice from menu: ");
			System.out.println("[0] Show");
			System.out.println("[1] Insert");
			System.out.println("[2] Find");
			System.out.println("[3] Quit");
			choice = input.nextInt();
			
			if(choice < 0 || choice > 3) { //if user enters an num outside the range of choices
				System.out.println("Please enter a valid choice from the menu.");
			}
			
		} while(choice < 0 || choice > 3);

		return choice;
	} //end printMenu

	
	//method to fill hashtable with initial number of values obtained from user
	public static Hashtable<Integer, Tree> fillHashtable(Hashtable<Integer, Tree> treeHashtable, int numItems, int arraySize) {		
		//generate random integers for numItems
		Random randGenerator = new Random();
		int[] randArray = new int[numItems]; //array to hold randomly generated integers, as many as specified by user
		
		for(int i = 0; i < numItems; i++) { //for loop to make enough random integers
			randArray[i] = randGenerator.nextInt(1000); //generate a random, positive integer that is less than 100
		} //numItems number of random integers have been created
		
		//check for duplicates somehow?
		checkForDuplicates(randArray);
		
		//for loop to create trees, generate indices, insert, and deal with collisions for each number
		for(int i = 0; i < randArray.length; i++) {
			Tree newTree = new Tree();
			newTree.insert(randArray[i]);
			int index = i % arraySize; //calculate index using hash function
			
			if(treeHashtable.get(index) == null) { //index in hashtable is empty
				treeHashtable.put(index, newTree);
			} else { //collision happens
				treeHashtable.get(index).insert(newTree.getRoot().value);
			} //end of if-else statements for inserting into hashtable

		} //end of for loop
		
		return treeHashtable;
	} //end of fillHashtable

	
	//method to show/display hashtable to user
	public static void displayHashtable(Hashtable<Integer, Tree> treeHashtable, int arraySize) {
		
		//for loop to go through all the indices in the hashtable
		for(int i = 0; i < arraySize; i++) {
			Tree toTraverse = treeHashtable.get(i);
			System.out.print(i + ". ");
			
			if(toTraverse != null) { //if the slot in the hashtable is occupied
				toTraverse.traverse(2); //traverse tree using inOrder traversal
			} else { //else if the slot in the hashtable is empty, continue to next slot
				System.out.println();
			} //end of if-else statements
		} //end of for loop
	} //end displayHashtable method

	
	//method to insert another number into the tree
	public static void insertItem(Hashtable<Integer, Tree> treeHashtable, int arraySize) {
		Random randGenerator = new Random();
		Tree newTree = new Tree();
		newTree.insert(randGenerator.nextInt(100)); //generate a random, positive integer that is less than 100
		int index = (newTree.getRoot().value) % arraySize;
		
		if(treeHashtable.get(index) == null) { //if index in hashtable is empty
			treeHashtable.put(index, newTree); //insert new tree
		} else { //collision happens
			treeHashtable.get(index).insert(newTree.getRoot().value);
		} //end of if-else statements
		
	} //end of insertItem method
	
	
	//method to find an item in a tree
	public static void findItem(Hashtable<Integer, Tree> treeHashtable, int arraySize) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter key value to find: "); //get value to search for from user
		int num = input.nextInt();
		
		//for loop to go through each index in the hashtable
		for(int i = 0; i < arraySize; i++) {
			if (treeHashtable.get(i) != null) { //if that index of the hashtable is occupied
				if (treeHashtable.get(i).find(num) != null) { //if the tree in that index contains that node
					System.out.println("Found " + num); //tell user that value is found, exit method
					return;
				} //end of inside if statement
			} //end of outside if statement 
		} //end of for loop
		
		System.out.println("Could not find " + num); //tell user that value was not found
	} //end of findItem method
	
	//method to check for duplicates when the array is initialized
	public static int[] checkForDuplicates(int[] randArray) {
		Random randGenerator = new Random();
		for(int i = 0; i < randArray.length - 1; i++) { 
			for(int j = i + 1; j < randArray.length; j++) {
				if(randArray[i] == randArray[j]) { //if the two values match
					System.out.println(randArray[i] + " matches " + randArray[j]);
					randArray[i] = randGenerator.nextInt(1000); //generate a new random number to replace in array
				} //end if statement
			} //end inside for loop
		} //end outside for loop
		
		return randArray;
	} //end checkForDuplicates method

} //end of class Lab2
