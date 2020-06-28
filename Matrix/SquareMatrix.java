
/****************************
 * Square Matrix
 * @author Alex Titus-Glover
 * @since 03-2020
 * @version 1.0
 ****************************/

public class SquareMatrix extends Matrix {

	// Construct a new default square matrix object. //
	public SquareMatrix() {
		this(1);
	}
	// Construct a new square matrix object. //
	public SquareMatrix(int n) {
		super(n,n);
	}
	// Construct a new square matrix object. //
	public SquareMatrix(int n, boolean askValues) {
		super(n,n,askValues);
	}


	// Determine if all the entries above the main diagonal are zero. //
	public boolean isLowerTriangular() {
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=i+1 ; j<colCount ; j++) {
				if( this.getElement(i,j)!=0 ) return false;
			}
		}
		// If the loops have traversed the upper triangle without finding a non-zero entry, the result is true.
		return true;
	}
	// Determine if all the entries below the main diagonal are zero. //
	public boolean isUpperTriangular() {
		for (int i=0 ; i<rowCount ; i++) {
			for (int j=0 ; j<i ; j++) {
				if( this.getElement(i,j)!=0 ) return false;
			}
		}
		// If the loops have traversed the lower triangle without finding a non-zero entry, the result is true.
		return true;
	}

	// Determine whether this matrix is diagonal (all entries outside the main diagonal are zero). //
	public boolean isDiagonalMatrix() {
		return ( this.isUpperTriangular() && this.isLowerTriangular() );
	}

	// Return an array representing the main diagonal of the square matrix. //
	public double[] getMainDiagonal() {
		double[] result = new double[rowCount];
		for (int i=0 ; i<rowCount ; i++) {
			result[i] = elements[i][i];
		}
		return result;
	}

	// Determine whether this matrix is a scalar matrix (diagonal and all entries on diagonal are equal). //
	public boolean isScalarMatrix() {
		// If the matrix ids not a diagonal matrix, then it cannot be a scalar matrix.
		if(!isDiagonalMatrix()) return false;

		double[] md = getMainDiagonal();
		double c = md[0];
		for (int i=0; i<md.length ; i++) {
			if(md[i]!=c) return false;
		}

		// If the matrix has passed the diagonal test, and the loop has traversed the main diagonal without finding a mismatched element, 
		// then all the elements on the diagonal are equal to each other and this is a scalar matrix. 
		return true;
	}

	// Multiply this matrix by itself n times and return the result. //
	public SquareMatrix getExponential(int exp) {
		SquareMatrix result;

		if( this.isSquare() ) {
			int size = this.getRowCount();
			result = SquareMatrix.identityMatrix( size );

			for(int i=1 ; i<=exp ; i++) {
				// Why does this need to be casted ? "(SquareMatrix) "
				result = (SquareMatrix)result.multiplyBy(this);
			}
		}
		else {
			result = null;
			System.out.println("Non-square matrix is incompatible with matrix exponential function.");
		}

		return result;
	}

	// TODO: Generate a matrix, if possible, which when multiplied by the original, results in the identity matrix In. //
	public SquareMatrix getInverse() {
		double determinant = this.determinant();
		SquareMatrix result = new SquareMatrix(rowCount);

		// Calculate inverse for a 1x1 matrix.
		if(rowCount==1 && colCount==1) {
			result.setElement(0,0, 1/elements[0][0]);
		}
		// Calculate inverse for a 2x2 matrix.
		else if(rowCount==2 && colCount==2) {
			result.setElement(0,0, elements[1][1]/determinant);
			result.setElement(0,1, -1*elements[0][1]/determinant);
			result.setElement(1,0, -1*elements[1][0]/determinant);
			result.setElement(1,1, elements[0][0]/determinant);
		}
		// Calculate inverse for a NxN matrix.
		else {
		}
		
		return result;
	}

	// Find the determinant of the matrix. //
	public double determinant() {
		// Dismiss requests for determinant of non-square matrices. 
		if( !this.isSquare() ) {
			System.out.println("Determinant is undefined for non-square "+this.getSize()+" matrix. ");
			return 0;
		}

		else if(rowCount==1) {
			return elements[0][0]; // det(M) = a
		}
		else if(rowCount==2) {
			return ( elements[0][0]*elements[1][1] - elements[0][1]*elements[1][0] ); // det(M) = ad-bc
		}
		else {
			double sum = 0;

			int i = 0;	// expansion along 1st row
			for (int j=0 ; j<colCount ; j++) {
				sum += elements[i][j] * Math.pow(-1, (i+j) ) * this.minorMatrix(i,j).determinant();
			}
			return sum;
		}
	}
	// Generate a new  matrix that has row i and column j removed. //
	public SquareMatrix minorMatrix(int m, int n) {
		SquareMatrix result = new SquareMatrix(rowCount-1);
		for (int i=0 ; i<m ; i++) {
			for (int j=0 ; j<n ; j++) {
				result.setElement(i,j, elements[i][j] );
			}
			for (int j=n+1 ; j<colCount ; j++) {
				result.setElement(i,j-1, elements[i][j] );
			}
		}
		for (int i=m+1 ; i<rowCount ; i++) {
			for (int j=0 ; j<n ; j++) {
				result.setElement(i-1,j, elements[i][j] );
			}
			for (int j=n+1 ; j<colCount ; j++) {
				result.setElement(i-1,j-1, elements[i][j] );
			}
		}
		return result;
	}

	// Return an identity matrix of the given size. //
	public static SquareMatrix identityMatrix(int size) {
		SquareMatrix result = new SquareMatrix(size);
		for (int i=0; i<size ; i++) {
			result.setElement(i,i,1);
		}
		return result;
	}
	
}
