
import java.util.Scanner;

public class Fibonacci {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		long[] sequence;

		// Take in user input: how many iterations of fibonacci sequence. 
		System.out.print("\nCount:  ");
		int count = kb.nextInt();
		System.out.println();

		// Ensure a count of at least 2. 
		if(count<2) count = 2;

		sequence = new long[count];
		sequence[0] = 0;
		sequence[1] = 1;
		for (int i=2; i<count ; i++) {
			sequence[i] = sequence[i-2] + sequence[i-1];
		}

		// Print the sequence of numbers. 
		printSequence(sequence);
		System.out.println();
	}

	public static void printSequence(long[] seq) {
		for (int i=0; i<seq.length ; i++) {
			if(i<=1) System.out.println( i + ": " + seq[i] );
			else System.out.println( i + ": " + seq[i] + "\t" + (1.0*seq[i]/seq[i-1]) );
		}
	}
}
