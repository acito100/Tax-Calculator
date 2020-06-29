
import java.util.*;
import java.text.*;

public class Sample {
	public static void main(String[] args) {
		// Allow user input thru the keyboard. 
		Scanner kb = new Scanner(System.in);

		// Format numbers to 3 decimal places. 
		DecimalFormat d3 = new DecimalFormat("###.###");


		// Ask the user for the sample size of the statistics. 
		System.out.println("\n*** Statistics ***");
		System.out.print("Sample size: ");
		int n = kb.nextInt();
		double[] data = new double[n];

		// Ask the user for each data item. 
		for (int i=0 ; i<n ; i++) {
			System.out.print("data["+i+"]: ");
			data[i] = kb.nextDouble();
		}


		// Display each data item on screen. 
		System.out.print("\ndata: {");
		for (int i=0 ; i<n ; i++) {
			// Print delimiter. 
			if(i!=0) System.out.print(", ");
			else	 System.out.print(" ");

			// Print data point. 
			System.out.print(data[i]);
		}
		System.out.println(" }");


		// Calculate sample mean. 
		double mean = 0;
		for (int i=0 ; i<n ; i++) {
			mean += (1.0/n) * data[i];
		}

		// Calculate sample variance. 
		double variance = 0, stdev;
		for (int i=0 ; i<n ; i++) {
			double deviation = data[i] - mean;
			variance +=deviation*deviation;
		}
		if(n>1) variance /= (n-1);
		else 	variance = 0;
		stdev = Math.pow(variance,.5);


		// Show sample statistics. 
		System.out.println("mean = " + d3.format(mean) );
		System.out.println("variance = " + d3.format(variance) );
		System.out.println("stdev = " + d3.format(stdev) );
		System.out.println();

	}
}