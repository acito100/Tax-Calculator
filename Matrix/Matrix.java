
/****************************
 * Matrix
 * @author Alex Titus-Glover
 * @since 02-2020
 * @version 1.0
 ****************************/

import java.util.Random;
import java.util.Scanner;

public class Matrix {

	protected int rowCount;
	protected int colCount;
	protected double[][] elements;

	// Construct a new matrix object. //
	public Matrix() {
		this(1,1);
	}
	public Matrix(int m, int n) {
		this.rowCount = m;
		this.colCount = n;
		this.elements = new double[m][n];
	}
	public Matrix(int m, int n, boolean askValues) {
		this(m,n);
		if (askValues)  this.inputElements();
		else 			this.setRandomElements();
	}

	// Provide user-input values for each element in the matrix. //
	public void inputElements() {
		Scanner userInput = new Scanner(System.in);

		for(int i=0 ; i<rowCount ; i++) {
			for(int j=0 ; j<colCount ; j++) {
				System.out.print("\t\tValue for M["+i+"]["+j+"]:  ");
				if( userInput.hasNextDouble() ) this.setElement(i,j, userInput.nextDouble() );
			}
		}
	}

	// Set a random value (0-9) for each element in the matrix. //
	public void setRandomElements() {
		Random rGen = new Random();

		for(int i=0 ; i<rowCount ; i++) {
			for(int j=0 ; j<colCount ; j++) {
				this.setElement(i,j, rGen.nextInt(10) );
			}
		}
	}

	// Return how many rows are in this matrix. //
	public int getRowCount() {
		return rowCount;
	}
	// Return how many columns are in this matrix. //
	public int getColCount() {
		return colCount;
	}

	// Check to see if matrix has equal number of rows and columns. //
	public boolean isSquare() {
		return (rowCount==colCount);
	}

	// Show the number of rows and columns in the matrix. //
	public String getSize() {
		return ("["+rowCount+"x"+colCount+"]");
	}

	// Return the value for a given element in the matrix. //
	public double getElement(int m, int n) {
		double result = elements[m][n];
		return result;
	}
	// Set the value for a given element in the matrix. //
	public void setElement(int m, int n, double val) {
		elements[m][n] = val;
	}
	// Increase the value of a given element by a given amount. //
	public void incrElement(int m, int n, double dval) {
		double temp = this.getElement(m,n);
		this.setElement(m,n, temp+dval);
	}

	// Return an array representing a row of the matrix. //
	public double[] getRow(int m) {
		double[] result = new double[colCount];
		for (int j=0 ; j<colCount ; j++) {
			result[j] = elements[m][j];
		}
		return result;
	}
	// Return an array representing a column of the matrix. //
	public double[] getColumn(int n) {
		double[] result = new double[rowCount];
		for (int i=0 ; i<rowCount ; i++) {
			result[i] = elements[i][n];
		}
		return result;
	}
	// Return a row matrix. //
	public Matrix getRowMatrix(int m) {
		Matrix result = new Matrix(1,colCount);
		for (int i=0 ; i<1 ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				result.setElement( i,j, elements[m][j] );
			}
		}
		return result;
	}
	// Return a column matrix. //
	public Matrix getColumnMatrix(int n) {
		Matrix result = new Matrix(rowCount,1);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<1 ; j++) {
				result.setElement( i,j, elements[i][n] );
			}
		}
		return result;
	}
	// Generate a new matrix that is the transposed version of this matrix. //
	public Matrix getTranspose() {
		Matrix result = new Matrix(colCount,rowCount);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				result.setElement( j,i, elements[i][j] );
			}
		}
		return result;
	}

	// TODO: Find the row echelon form matrix of this matrix thru Gaussian elimination. //
	public static Matrix gaussianElimination(Matrix m) {
		Matrix result = Matrix.copyOf(m);

		// Perform operations until in proper form. 
		while( !isRowEchelonForm(result) ) {
			// result.rowOp();
		}
		
		return result;
	}
	// TODO: Determine if this matrix is in reduced row echelon form. //
	public static boolean isReducedRowEchelonForm(Matrix m) {
		if( !isRowEchelonForm(m) ) return false;

		boolean condition = true;

		for (int i=0; i<m.rowCount ; i++) {
			double[] row_i = m.getRow(i);
		 	condition = condition && ( leadEntryIsOne(row_i) || isZeroRow(row_i) );
		}

		// Return true if in row echelon form and every row is either a zero-row or a row with leading entry of 1. 
		return condition;
	}
	// Check if every entry in the row is 0. //
	public static boolean isZeroRow(double[] row) {
		// Return false: It is not a zero row if any of the entries is not zero. 
		for (int i=0; i<row.length ; i++) {
			if(row[i]!=0) return false;
		}

		// Return true: It is a zero row if the loop ends without returning false.
		return true;
	}
	// Check if the leading entry is 1. //
	public static boolean leadEntryIsOne(double[] row) {
		for (int i=0; i<row.length ; i++) {
			if(row[i]==0) continue;
			else if(row[i]==1) return true;
		}
		return false;
	}
	// TODO: Determine if this matrix is in row echelon form. //
	public static boolean isRowEchelonForm(Matrix m) {
		boolean condition1 = false;
		boolean condition2 = false;
		
		// Check to see if every value has all zeroes below it. 
		for (int j=0 ; j<m.colCount ; j++) {
			for (int i=0 ; i<m.rowCount ; i++) {
				//
			}
		}
		
		// Check to see if every leading coefficient is a 1. 
		for (int j=0 ; j<m.colCount ; j++) {
			for (int i=0 ; i<m.rowCount ; i++) {
				//
			}
		}
		
		return (condition1 && condition2);
	}
	// Perform elementary row operation: row switch (Rx <-> Ry). //
	public Matrix switchRows(int x, int y) {
		Matrix result = new Matrix(rowCount,colCount);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
					 if(i==x) result.setElement( i,j, elements[y][j] );
				else if(i==y) result.setElement( i,j, elements[x][j] );
				else result.setElement( i,j, elements[i][j] );
			}
		}
		return result;
	}
	// Perform elementary row operation: row multiplication (c*Rx). //
	public Matrix multipyRow(int x, double c) {
		if(c==0) c = 1;
		Matrix result = new Matrix(rowCount,colCount);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				if(i==x) result.setElement( i,j, c*elements[i][j] );
				else result.setElement( i,j, elements[i][j] );
			}
		}
		return result;
	}
	// Perform elementary row operation: row multiple addition (Rx + c*Ry). //
	public Matrix addRowMultiple(int x, int y, double c) {
		Matrix result = new Matrix(rowCount,colCount);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				if(i==x) result.setElement( i,j, ( elements[i][j] + c*elements[y][j] ) );
				else result.setElement( i,j, elements[i][j] );
			}
		}
		return result;
	}

	// Matrix Addition: Add this matrix to another matrix and return the result. //
	public Matrix addTo(Matrix other) {
		if(this.getRowCount() == other.getRowCount() && this.getColCount() == other.getColCount() ) {
			Matrix result = new Matrix(rowCount,colCount);

			for(int i=0 ; i<rowCount ; i++) {
				for(int j=0 ; j<colCount ; j++) {
					double thing = this.getElement(i,j) + other.getElement(i,j);
					result.setElement(i,j, thing);
				}
			}
			return result;
		}
		else {
			System.out.println("The matrices are incompatible for addition.");
			return null;
		}
	}
	// Matrix Multiplication: Multiply this matrix by another matrix and return the result. //
	public Matrix multiplyBy(Matrix other) {
		if( this.getColCount() == other.getRowCount() ) {
			Matrix result = new Matrix(this.getRowCount(),other.getColCount());

			int r = result.getRowCount();
			int c = result.getColCount();

			for(int i=0 ; i<r ; i++) {
				// System.out.print("<i"+i);
				for(int j=0 ; j<c ; j++) {
					// System.out.print("  <j"+j);
					for(int x=0 ; x<this.getColCount() ; x++) {
						result.incrElement(i,j, this.getElement(i,x) * other.getElement(x,j) );
					}
					// System.out.print(" j"+j+">");
				}
				// System.out.println(" i"+i+">");
			}

			return result;
		}
		else {
			System.out.println("The matrices are incompatible for matrix multiplication.");
			return null;
		}
	}
	// Scalar Multiplication: Multiply this matrix by a number and return the result. //
	public Matrix multiplyBy(double c) {
		Matrix result = new Matrix(rowCount,colCount);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				result.setElement( i,j, c*this.getElement(i,j) );
			}
		}
		return result;
	}

	// Check to see if each column adds up to one. //
	public static boolean isStochastic(Matrix m) {
		for (int j=0; j<m.colCount ; j++) {
			int sum = 0;
			for (int i=0 ; i<m.rowCount ; i++) {
				sum += m.getElement(i,j);
			}
			if(sum!=1.0) return false;
		}

		// If the loops have traversed the entire matrix without finding a non-match, the result is true.
		return true;
	}

	// Determine whether a given element value is contained within the matrix. //
	public boolean contains(double val) {
		// Traverse the entire matrix to find a matching element. 
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				if( this.getElement(i,j) == val ) return true;
			}
		}

		// If the loops have traversed the entire matrix without finding a match, the result is false.
		return false;
	}
	// Determine whether this matrix is equal to another. //
	public boolean equals(Matrix other) {
		// Traverse the entire matrix to find a non-matching element.
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				if( this.getElement(i,j) != other.getElement(i,j) ) return false;
			}
		}

		// If the loops have traversed the entire matrix without finding a non-match, the result is true.
		return true;
	}

	// Create a string representation of the matrix. //
	public String toString() {
		StringBuffer result = new StringBuffer("");
		result.append( this.getSize()+"\n\n" );

		for(int i=0 ; i<rowCount ; i++) {
			result.append('|');
			result.append(' ');
			for(int j=0 ; j<colCount ; j++) {
				result.append( getElement(i,j) );
				result.append(' ');
			}
			result.append('|');
			result.append("\n\n");
		}
		
		return result.toString();
	}

	// Create a new copy of the given matrix. 
	public static Matrix copyOf(Matrix m) {
		int rowCount = m.getRowCount();
		int colCount = m.getColCount();

		Matrix result = new Matrix(rowCount,colCount);
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<colCount ; j++) {
				result.setElement( i,j, m.getElement(i,j) );
			}
		}
		return result;
	}

	// Create a new copy of the given matrix. 
	public static SquareMatrix toSquareMatrix(Matrix m) {
		if( !m.isSquare() ) return null;

		int size = m.getRowCount();

		SquareMatrix result = new SquareMatrix(size);
		for (int i=0 ; i<size ; i++) {
			for (int j=0 ; j<size ; j++) {
				result.setElement( i,j, m.getElement(i,j) );
			}
		}
		return result;
	}

}
