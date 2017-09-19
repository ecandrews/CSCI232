/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 1
 * Tuesday, Feb. 9, 2016
 */

public class PriorityQ {

	// array in sorted order, from max at 0 to min at size-1
	private int maxSize;
	private Tree[] queArray;
	private int nItems;
	
	public PriorityQ(int s) { // constructor
		maxSize = s;
		queArray = new Tree[maxSize];
		nItems = 0;
	}
	
	
	public void insert(Tree n) { // insert item

		int j;
		if(nItems==0) // if no items,
			queArray[nItems++] = n; // insert at 0
		else {// if items,

			for(j=nItems-1; j>=0; j--) { // start at end,

				if(n.getRoot().freq > queArray[j].getRoot().freq) // if new item larger,
					queArray[j+1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j+1] = n; // insert it
			nItems++;
		} // end else (nItems > 0)
	} // end insert()

	public Tree remove() { // remove minimum item
		//System.out.println("in remove");		
		return queArray[--nItems];
	}

	public Tree peekMin() {// peek at minimum item
		return queArray[nItems-1]; 
	}

	public boolean isEmpty() { // true if queue is empty
		return (nItems==0);
	}

	public boolean isFull() {// true if queue is full
		return (nItems == maxSize);
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public int getSize() {
		return nItems;
	}

} // end class PriorityQ

