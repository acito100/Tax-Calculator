
public class Vector extends Matrix {
	public Vector() {
		super();
	}
	public Vector(int size) {
		super(size,1);
	}
	public Vector(int size, boolean askValues) {
		super(size,1, askValues);
	}

	public String getSize() {
		return (this.rowCount + "x1");
	}

	public boolean orthogonalTo(Vector other) {
		return ( dotProduct(this,other) == 0 );
	}

	public boolean sameDimension(Vector other) {
		return ( this.rowCount == other.rowCount );
	}

	// Scalar Multiplication: Multiply this vector by a number and return the result. //
	public Vector multiplyBy(double c) {
		Vector result = new Vector( this.rowCount );
		for (int i=0 ; i<rowCount ; i++) {
			result.setElement( i,1, c*this.getElement(i,1) );
		}
		return result;
	}

	public Vector projectOnto(Vector other) {
		double c = dotProduct(this,other) / dotProduct(other,other);
		return ( other.multiplyBy(c) );
	}

	public static double dotProduct(Vector v1, Vector v2) {
		var resultMatrix = v1.getTranspose().multiplyBy(v2);
		return resultMatrix.getElement(0,0);
	}
}