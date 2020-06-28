
public class Hanoi {
	private int visibleLayers;
	private HStack fromPeg;
	private HStack auxPeg;
	private HStack toPeg;

	public Hanoi() {
		// Create base case object. 
		this(2,2);
	}
	public Hanoi(int n, int l) {
		this.visibleLayers = l;
		this.fromPeg = new HStack();
		this.auxPeg = new HStack();
		this.toPeg = new HStack();

		// Populate "from peg" with disks. 
		for (int i=n ; i>=1 ; i--) {
			this.fromPeg.push(i);
		}
	}

	public String toString() {
		String result = new String("");

		result += ( "\nA: "+this.fromPeg );
		result += ( "\nB: "+this.auxPeg );
		result += ( "\nC: "+this.toPeg );

		return result;
	}

	public void movePlates() {
		this.movePlates(fromPeg.size(), fromPeg,auxPeg,toPeg,0);
	}
	public void movePlates(int n, HStack a, HStack b, HStack c, int layer) {
		if(n<=0) return;
		if(n==1){
			c.push( a.pop() );
		}
		else {
			// for (int i=0;i<layer;i++) System.out.print("\t");
			// System.out.println("Begin: ");
			movePlates(n-1, a,c,b, layer+1);
			// for (int i=0;i<layer;i++) System.out.print("\t");
			// System.out.println("Middle: ");
			movePlates(1, a,b,c, layer+1);
			// for (int i=0;i<layer;i++) System.out.print("\t");
			// System.out.println("End: ");
			movePlates(n-1, b,a,c, layer+1);
		}
		// for (int i=0;i<layer;i++) System.out.print("\t");
		if(layer<visibleLayers) System.out.println(this + "\n");
	}
	

	private class HStack {
		private AList stack;

		// Create a new stack. 
		public HStack() {
			stack = new AList();
		}

		// Add a value to the top of the stack. 
		public void push(int x) {
			if(stack.isEmpty() || x<this.peek()) stack.add(x);
			else {
				System.out.print( "Error: Improper stack operation. Big on top of small. " );
				System.out.println( x + " on top of "+peek()+". " );
			}
		}
		// Remove the value at the top of the stack and return it. 
		public int pop() {
			return stack.removeLast();
		}

		// Return the value at the top of the stack. 
		public int peek() {
			return stack.get( stack.size()-1 );
		}

		// Return the size of the stack. 
		public int size() {
			return stack.size();
		}
		// Check if the stack is empty. 
		public boolean isEmpty() {
			return stack.isEmpty();
		}

		// Provide a string reresentation of the stack. 
		public String toString() {
			return stack.toString();
		}
	}


	public static void main(String[] args) {
		int n = 10;
		int l = 1;

		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("Plates: ");
		if( input.hasNextInt() ) n = input.nextInt();
		System.out.print("Visible Layers: ");
		if( input.hasNextInt() ) l = input.nextInt();

		if( n<0 || l<0 ) {
			System.out.println("Error: Invalid parameter. Must be postive numbers.");
			return;
		}

		Hanoi h = new Hanoi(n,l);
		System.out.println(h);
		System.out.println();
		h.movePlates();
	}
}