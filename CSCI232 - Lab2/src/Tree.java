/*
 * CSCI232 - Data Structures and Algorithms
 * Elizabeth Andrews
 * Lab 2
 * Tuesday, March 1, 2016
 */

public class Tree {

	private Node root;

	public Tree() {
		root = null; //no nodes in tree yet, empty
	}

	public Node getRoot() { //return root node
		return root;
	}

	public Node find(int key) {// find node with given key (assumes non-empty tree)

		Node current = root; // start at root

		while(current.value != key) { // while no match,
			if(key < current.value) // go left?
				current = current.leftChild;
			else //or go right?
				current = current.rightChild;
			if(current == null) // if no child,
				return null; // didn't find it
		}

		return current; // found it
	} // end find()


	public void insert(int key) { //insert new node into the tree

		Node newNode = new Node(key); // make new node
		newNode.value = key; // insert data
		if(root==null) // no node in root
			root = newNode;
		else { // root occupied
			Node current = root; // start at root
			Node parent;

			while(true) { // (exits internally)
				parent = current;

				if(key < current.value) {// go left?
					current = current.leftChild;
					if(current == null) { // if end of the line,
						//insert on left
						parent.leftChild = newNode;
						return;
					}
					//end if go left
				} else { // or go right?
					current = current.rightChild;

					if(current == null) { // if end of the line
						// insert on right
						parent.rightChild = newNode;
						return;
					}
				} // end else go right
			} // end while
		} // end else not root
	} // end insert()



	public void traverse(int traverseType) {

		switch(traverseType) {

		case 1: 
			preOrder(root);
			break;
		case 2:
			inOrder(root);
			break;
		case 3:
			postOrder(root);
			break;
		}

		System.out.println();
	}


	private void preOrder(Node localRoot) {

		if(localRoot != null) {

			System.out.print(localRoot.value + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}


	private void inOrder(Node localRoot) {

		if(localRoot != null) {

			inOrder(localRoot.leftChild);
			System.out.print(localRoot.value + " ");
			inOrder(localRoot.rightChild);
		}
	}


	private void postOrder(Node localRoot) {

		if(localRoot != null) {

			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.value + " ");
		}
	}


}
