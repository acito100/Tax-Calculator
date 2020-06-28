
/****************************
 * Augmented Matrix
 * @author Alex Titus-Glover
 * @since 03-2020
 * @version 1.0
 ****************************/

public class AugmentedMatrix extends Matrix {


	private int delimiter;
	private Matrix leftMatrix;
	private Matrix rightMatrix;


	// Create a new matrix composed of two matrices placed side by side.
	public AugmentedMatrix(Matrix A, Matrix B){
		super( A.rowCount , A.colCount + B.colCount );

		delimiter = A.colCount;

		for (int i=0; i<A.rowCount ; i++) {
			int j,k;
			for (j=0 ; j<A.colCount ; j++) {
				this.elements[i][j] = A.elements[i][j];
			}
			for (k=0 ; k<B.colCount ; k++) {
				this.elements[i][j+k] = B.elements[i][k];
			}
		}
	}

	// Create a string representation of the matrix. //
	public String toString() {
		StringBuffer result = new StringBuffer("");
		result.append( this.getSize()+"\n\n" );

		for(int i=0 ; i<rowCount ; i++) {
			result.append('|');
			result.append(' ');
			for(int j=0 ; j<colCount ; j++) {
				if(j==delimiter) result.append("| ");
				result.append( getElement(i,j) );
				result.append(' ');
			}
			result.append('|');
			result.append("\n\n");
		}
		
		return result.toString();
	}


	public static void main(String[] args) {
		SquareMatrix A,B;

		A = new SquareMatrix(2);
		A.elements[0][0] = 1;
		A.elements[0][1] = 2;
		A.elements[1][0] = 3;
		A.elements[1][1] = 4;

		B = new SquareMatrix(2);
		B.elements[0][0] = 5;
		B.elements[0][1] = 6;
		B.elements[1][0] = 7;
		B.elements[1][1] = 8;
		
		AugmentedMatrix AB = new AugmentedMatrix(A,B);
		System.out.println(AB);
	}

}