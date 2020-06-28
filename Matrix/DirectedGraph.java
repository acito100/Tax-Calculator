
public class DirectedGraph extends Graph {
	private int size;
	private SquareMatrix adjacency;

	public DirectedGraph() {
		this(1);
	}
	public DirectedGraph(int n) {
		this.size = n;
		this.adjacency = new SquareMatrix(n);
	}

	public void addConnection(int x, int y) {
		if( inBounds(x,y) ) adjacency.incrElement(x,y, 1);
		else System.out.println("Error: parameter(s) out of bounds in addConnection(int x, int y)");
	}
	public void clearConnections(int x, int y) {
		if( inBounds(x,y) ) adjacency.setElement(x,y, 0);
		else System.out.println("Error: parameter(s) out of bounds in removeConnection(int x, int y)");
	}
	public void removeConnection(int x, int y) {
		if( inBounds(x,y) ) {
			// Decrerment the element only if it's greater than zero.
			if ( adjacency.getElement(x,y)>0 ) adjacency.incrElement(x,y, -1);
		}
		else System.out.println("Error: parameter(s) out of bounds in removeConnection(int x, int y)");
	}

	public boolean isConnected(int x, int y) {
		if( inBounds(x,y) ) return ( adjacency.getElement(x,y) > 0 );
		else {
			System.out.println("Error: parameter(s) out of bounds in isConnected(int x, int y)");
			return false;
		}
	}
	public int countConnections(int x, int y) {
		if( inBounds(x,y) ) return (int) adjacency.getElement(x,y);
		else {
			System.out.println("Error: parameter(s) out of bounds in countConnections(int x, int y)");
			return -1;
		}
	}
	public int countConnections(int x) {
		if( inBounds(x) ) {
			int sum = 0;
			for (int y=0 ; y<size ; y++) {
				sum += adjacency.getElement(x,y);
			}
			
			return  (int)sum;
		}
		else {
			System.out.println("Error: parameter(s) out of bounds in countConnections(int x)");
			return -1;
		}
	}

	// Provide a string representation of the DiGraph in the form of its adjacency matrix. 
	public String toString() {
		return "DiGraph " + adjacency.toString();
	}

	// Let the user know if the given parameter is valid. 
	public boolean inBounds(int x) {
		return (x>=0 && x<size);
	}
	// Let the user know if the given parameters are valid. 
	public boolean inBounds(int x, int y) {
		return (x>=0 && y>=0 && x<size && y<size);
	}


	public static void main(String[] args) {
		DirectedGraph dg = new DirectedGraph(4);
		System.out.println(dg);
	}
}
