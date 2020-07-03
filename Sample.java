
import java.util.*;
import java.text.*;

public class Sample {

	private int size;
	private double[] sampleData;
	private double[] sortedData;
	private double mean;
	private double geometricMean;
	private double variance;

	// Format numbers to 2 or 3 decimal places. 
	DecimalFormat d2 = new DecimalFormat("##.##");
	DecimalFormat d3 = new DecimalFormat("###.###");


	public Sample(int n) {
		// Set the size of the sample. 
		this.size = n;

		// Initialize the sample data array to the given size. 
		this.sampleData = new double[n];
	}

	// Get a data item by index. 
	public double get(int index) {
		return sampleData[index];
	}

	// Provide sample size. 
	public int getSize() {
		return this.size;
	}

	// Provide sample arithmetic mean. 
	public double getMean() {
		return this.mean;
	}

	// Provide sample geometric mean. 
	public double getGeometricMean() {
		return this.geometricMean;
	}

	// Provide sample variance. 
	public double getVariance() {
		return this.variance;
	}

	// Provide sample variance. 
	public double getStDev() {
		return Math.sqrt( this.variance );
		// return Math.pow(getVariance(),.5)
	}

	// Provide a string representation of the sample data. 
	public String toString() {
		String result = "data: {";

		// Add each data item in original order. 
		for (int i=0 ; i<size ; i++) {
			// Add delimiter. 
			if(i!=0) result += (", ");
			else	 result += (" ");

			// Add data point. 
			result += ( d2.format(sampleData[i]) );
		}
		result += (" }");

		return result;
	}


	// Update sample data and re-calculate basic sample metrics. 
	public void updateData() {
		// Allow user input thru the keyboard. 
		Scanner kb = new Scanner(System.in);

		// Ask the user if the sample data should be generated randomly. 
		boolean useRandomData = true;
		// System.out.print("Random data ?  ");
		// char resp = kb.nextLine().charAt(0);
		// if(resp=='y'||resp=='Y') 
		// 	useRandomData = true;
		// else 
		// 	useRandomData = false;

		// Use randomly-generated data set for calculating statistics (debugging). 
		if(useRandomData) {
			// Randomly choose each data item. 
			Random rng = new Random();
			for (int i=0 ; i<size ; i++) {
				sampleData[i] = 10*rng.nextDouble();
			}
		}
		// Obtain user-generated data set for calculating statistics. 
		else {
			// Ask the user for each data item. 
			for (int i=0 ; i<size ; i++) {
				System.out.print("x"+i+": ");
				sampleData[i] = kb.nextDouble();
			}
		}


		// Sort data items in separate array. 
		sortedData = new double[size];
		for (int i=0 ; i<size ; i++) {
			sortedData[i] = sampleData[i];
		}
		Arrays.sort(sortedData);


		// Print space. 
		System.out.println();

		// Print out sample data. 
		// System.out.println(this);


		// Update sample arithmetic mean. 
		mean = 0;
		for (int i=0 ; i<size ; i++) {
			mean += (1.0/size) * sampleData[i];
		}

		// Update sample variance. 
		variance = 0;
		for (int i=0 ; i<size ; i++) {
			double deviation = sampleData[i] - mean;
			variance += deviation*deviation;
		}
		if(size>1)  variance /= (size);
		else 		variance = 0;

		// Update sample geometric mean. 
		geometricMean = 1;
		for (int i=0 ; i<size ; i++) {
			geometricMean *= Math.pow(sampleData[i],(1.0/size));
		}
	}


	// Show sample statistics of central tendency and variation. 
	public void showSampleStatistics() {

		// Show sample arithmetic mean. 
		System.out.println("mean = " + d3.format( getMean() ) );

		// // Show sample geometric mean. 
		// System.out.println("geometric mean = " + d3.format( getGeometricMean() ) );


		// Calculate sample median... 
		double median;
		int i2 = 0, iA = 0, iB = 0;
		// ...for even number sample size
		if(size%2==0) {
			iA = (size/2) - 1;
			iB = size/2;
			median = (sampleData[iA] + sampleData[iB]) / 2;
		}
		// ...for odd number sample size
		else {
			i2 = (size-1) / 2;
			median = sortedData[i2];
		}

		// Find sample quartiles. 
		int i1 = (int) Math.floor( 0.25*(size+1)-1 );		// index of q1
		int i3 = (int) Math.ceil( 0.75*(size+1)-1 );		// index of q3
		double q1 = sortedData[i1];
		double q3 = sortedData[i3];

		// Show sample quartiles (including sample median). 
		System.out.println( "Q1 = data["+i1+"] = " + d3.format(q1) );
		// System.out.println("median = " + d3.format(median) );
		System.out.print( "Q2 = data[" );
		if(size%2==0) System.out.print( iA+"~"+iB );
		else 		  System.out.print( i2 );
		System.out.println( "] = " + d3.format(median) + " = median" );
		System.out.println( "Q3 = data["+i3+"] = " + d3.format(q3) );



		// Show sample range. 
		System.out.println("range = (max-min) = " + d3.format( sortedData[size-1] - sortedData[0] ) );

		// Show sample variance. 
		System.out.println("variance = Σ[(x-x̄)^2]/n = " + d3.format( getVariance() ) );

		// Show sample standard deviation. 
		System.out.println("stdev = √(variance) = " + d3.format( getStDev() ) );

		// Show sample midrange. 
		System.out.println("midrange = avg(min,max) = " + d3.format(  (sortedData[0] + sortedData[size-1]) / 2 )  );

		// Show sample midhinge. 
		System.out.println("midhinge = avg(q1,q3) = " + d3.format( (q1+q3)/2 ) );
	}


	public static double covariance (Sample X, Sample Y) {
		double result = 0;
		int n = 0;

		if( X.getSize()==Y.getSize() ) 
			n = Math.min( X.getSize(), Y.getSize() );

		for (int i=0 ; i<n ; i++) {
			double x = X.get(i);
			double y = Y.get(i);
			result += ( x - X.getMean() ) * ( y - Y.getMean() );
		}
		result /= n;

		return result;
	}




	public static void main(String[] args) {

		// Print out the title with space at the top. 
		System.out.println("\n*** Statistics ***\n");


		// Ask the user for the sample size of the statistics. 
		System.out.print("Sample size: ");
		int n = ( new Scanner(System.in) ).nextInt();

		// Initialize the sample data array to the given size. 
		Sample sampleX = new Sample(n);
		Sample sampleY = new Sample(n);

		// Collect sample data, and show sample statistics. 
		sampleX.updateData();
		sampleX.showSampleStatistics();

		// Collect sample data, and show sample statistics. 
		sampleY.updateData();
		sampleY.showSampleStatistics();


		// Show covariance between the two samples. 
		double covXY = covariance(sampleX,sampleY);
		System.out.println("\nCov(X,Y) = " + covXY );
		System.out.println("ρ = " + (covXY / ( sampleX.getStDev() * sampleY.getStDev() ) ) );

		// Print out space at the bottom. 
		System.out.println();

	}

}





		// TODO: Find sample mode. 
		// boolean modeExists = false;
		// double mode = 0;
		// int dataFreq[][] = new int[size][2];
		// int hiFreq = 0;

		// TODO: Populate the frequency array.
		// for (int i=0 ; i<size ; i++) {
		// 	if() contained(dataFreq[i][0],);
		// 	else ;
		// 	if(datafreq) dataFreq[i][1] = 1;
		// 	else dataFreq[i][1] ++;
		// }
		// TODO: Check for a valid mode by searching for a frequency higher than 1;
		// for (int i=0 ; i<size ; i++) {
		// 	// If a data item has a frequency higher than 1, then mode is valid. 
		// 	if (dataFreq[i][1]>1) {
		// 		hiFreq = dataFreq[i][1];
		// 		modeExists = true;
		// 	}
		// }
		// if(modeExists) System.out.println("mode = " + d3.format(mode) );

		// Check if a given data item is contained within a given data set. 
		// boolean contained(double[] dataSet, double dataItem) {
		// 	return false;
		// }
		// 