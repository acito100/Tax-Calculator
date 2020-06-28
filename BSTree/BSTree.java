
public class BSTree {

	Node root;


	// Create a new BSTree. 
	public BSTree() {
		root = null;
	}

	// Check to see if this BSTree is empty. 
	public boolean isEmpty() {
		return (root==null);
	}

	// Add a new element to the BSTree. 
	public void add(int d) {
		if( isEmpty() ) {
			root = new Node(d);
		}
		else {
			root.add(d);
		}
	}

	// Delete an element from BSTree and return it. 
	public int remove(int d) {
		return root.remove(d);
	}

	// Check to see if a given item is in this BSTree. 
	public boolean contains(int query) {
		return root.contains(query);
	}

	// Find the index of a given item in this BSTree. 
	public Node search(int query) {
		return root.search(query);
	}

	// Create a string representation of this BSTree (pre order). 
	public String toStringPre() {
		String result = "";

		result += this.root.data;
		if(this.root.left!=null) {
			result += this.root.left.toString();
			result += ", ";
		}
		if(this.root.right!=null) {
			result += this.root.right.toString();
			result += ", ";
		}

		return result;
	}
	// Create a string representation of this BSTree (in order). 
	public String toString() {
		String result = "";

		if(this.root.left!=null) {
			result += this.root.left.toString();
			result += ", ";
		}
		result += this.root.data;
		if(this.root.right!=null) {
			result += ", ";
			result += this.root.right.toString();
		}

		return result;
	}
	// Create a string representation of this BSTree (post order). 
	public String toStringPost() {
		String result = "";

		if(this.root.left!=null) {
			result += this.root.left.toString();
			result += ", ";
		}
		if(this.root.right!=null) {
			result += this.root.right.toString();
			result += ", ";
		}
		result += this.root.data;

		return result;
	}


	class Node {
		int data;
		Node left;
		Node right;

		// Create a new Node. 
		public Node(int d) {
			this.data = d;
			this.left = null;
			this.right = null;
		}

		// Add a new descendant element to the BSTree. 
		public void add(int d) {
			if(d<root.data) {
				if(root.left!=null) root.left.add(d);
				else root.left = new Node(d);
			}
			else /*(d>=root.data)*/ {
				if(root.right!=null) root.right.add(d);
				else root.right = new Node(d);
			}
		}

		// Remove a descendant element from Node and return it. 
		public int remove(int d) {
			int result = -1;

			// Assumes all valid data entries are non-negtive numbers
			if( isEmpty() ) {
				result = -1;
			}
			else if(d==root.data){
				root = null;	// *TODO*
			}
			else if(d<root.data) {
				root.left.remove(d);
			}
			else if(d>=root.data) {
				root.right.remove(d);
			}

			return result;
		}

		// Check to see if a given item is a descendant element of this Node. 
		public boolean contains(int query) {
			if( isEmpty() ) {
				return false;
			}
			else if(query==root.data){
				return true;
			}
			else if(query<root.data) {
				return root.left.contains(query);
			}
			else if(query>=root.data) {
				return root.right.contains(query);
			}
			else return false;
		}

		// Find the index of a given item in this BSTree. 
		public Node search(int query) {
			if( isEmpty() ) {
				return null;
			}
			else if(query==root.data){
				return root;
			}
			else if(query<root.data) {
				return root.left.search(query);
			}
			else if(query>=root.data) {
				return root.right.search(query);
			}
			else return null;
		}
	}


	public static void main(String[] args) {
		BSTree bst = new BSTree();
		bst.add(1);
		bst.add(5);
		bst.add(4);
		bst.add(2);
		bst.add(3);

		System.out.println( bst.toStringPre() );
		System.out.println( bst );
		System.out.println( bst.toStringPost() );
	}

}
