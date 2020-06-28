
/**
 * Matrix Game
 * @author Alex Titus-Glover
 * @since 02-2020
 * @version 1.0
 */

import java.util.Scanner;

public class MatrixGame {
	static Scanner input;

	/////////////////////
	// Main Procedures //
	/////////////////////
	public static void main(String[] args) {

		// System.out.println( "det(I1) = " + SquareMatrix.identityMatrix(1).getDeterminant() );
		// System.out.println( "det(I2) = " + SquareMatrix.identityMatrix(2).getDeterminant() );
		// System.out.println( "det(I3) = " + SquareMatrix.identityMatrix(3).getDeterminant() );
		// System.out.println( "det(I4) = " + SquareMatrix.identityMatrix(4).getDeterminant() );
		// System.out.println( "det(I5) = " + SquareMatrix.identityMatrix(5).getDeterminant() );
		// System.out.println( "det(I6) = " + SquareMatrix.identityMatrix(6).getDeterminant() );
		// System.out.println( "det(I7) = " + SquareMatrix.identityMatrix(7).getDeterminant() );
		// System.out.println( "det(I8) = " + SquareMatrix.identityMatrix(8).getDeterminant() );
		// System.out.println( "det(I9) = " + SquareMatrix.identityMatrix(9).getDeterminant() );
		// System.out.println( "det(I10) = " + SquareMatrix.identityMatrix(10).getDeterminant() );

		// Open the keyboard input scanner. 
		input = new Scanner(System.in);

		boolean userWantsToContinue = false;
		do {
			System.out.println();
			System.out.println("***  Choose A Game  ***");
			System.out.println("1. Transpose");
			System.out.println("2. Elem. Row Operations");
			System.out.println("3. Matrix Addition");
			System.out.println("4. Matrix Multiplication");
			System.out.println("5. Matrix Exponential");
			System.out.println("6. Matrix Inverse");
			System.out.println("7. Matrix Determinant");
			System.out.println();
			System.out.print("Which game do you want to play (#)?  ");

			if(input.hasNextInt()) {
				int gameCode = input.nextInt();

				if(gameCode==1) {
					playTransposeGame();
				}
				else if(gameCode==2) {
					playRowOpGame();
				}
				else if(gameCode==3) {
					playAddingGame();
				}
				else if(gameCode==4) {
					playMultiplicationGame();
				}
				else if(gameCode==5) {
					playExponentialGame();
				}
				else if(gameCode==6) {
					playInverseGame();
				}
				else if(gameCode==7) {
					playDeterminantGame();
				}
				else {
					System.out.println("Invalid response. Now shutting down...  ");
					System.out.println("");
					break;
				}
			}

			// System.out.println();
			// System.out.print("Play another game (Y/N)?  N");

			// String response = input.nextLine();
			// do {
			// 	if(input.hasNextLine()) response = input.nextLine();
			// 	System.out.println("Response = \"" + response + "\"");
			// } while(response.length()!=0);

			// char thing = response.charAt(0);
			// userWantsToContinue = (thing == 'Y' || thing == 'y' || thing == 'T' || thing == 't');
			// if(userWantsToContinue) System.out.println("You responsed:  Yes");
			// else System.out.println("You responsed:  No");
		} while(userWantsToContinue);

		// Close the keyboard input scanner. 
		input.close();
		// System.out.println();

	}

	/////////////////////////////////
	//// Game 0: Matrix Creation ////
	/////////////////////////////////
	public static void createMatrix() {
	}

	//////////////////////////////////
	//// Game 1: Matrix Transpose ////
	//////////////////////////////////
	public static void playTransposeGame() {
		
		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Transpose ***");

		// Initialize the parameters of the matrices to be created. 
		int rows, cols;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tNumber of rows in matrix A: ");
		if( input.hasNextInt() ) rows = input.nextInt(); else return;
		System.out.print("\tNumber of cols in matrix A: ");
		if( input.hasNextInt() ) cols = input.nextInt(); else return;
		input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ){
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();


		// Create a new matrix object. 
		Matrix mA = new Matrix(rows,cols,askValues);
		// Transpose the matrix to obtain the output matrix. 
		Matrix mB = mA.getTranspose();
			
		// Print the two input matrices on the screen. 
		System.out.println();
		System.out.println(mA);
		// Print the output matrix on the screen. 
		System.out.println(mB);

		finishGame();
	}

	//////////////////////////////////////////////////
	//// Game 2: Matrix Elementary Row Operations ////
	//////////////////////////////////////////////////
	public static void playRowOpGame() {
		
		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Row Operations ***");

		// Initialize the parameters of the matrices to be created. 
		int rows, cols;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tNumber of rows in matrix A: ");
		if( input.hasNextInt() ) rows = input.nextInt(); else return;
		System.out.print("\tNumber of cols in matrix A: ");
		if( input.hasNextInt() ) cols = input.nextInt(); else return;
		input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ){
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();

		// Create a new matrix object. 
		Matrix mA = new Matrix(rows,cols,askValues);

		// Perform the row operation on the input matrix to obtain the output matrix. 
		Matrix mB = rowOpPrompt(mA);
			
		// Print the input matrix on the screen. 
		System.out.println();
		System.out.println(mA);
		// Print the output matrix on the screen. 
		System.out.println(mB);

		finishGame();
	}
	// Prompt user to choose an elementary row operation to perform. //
	public static Matrix rowOpPrompt(Matrix m) {
		Matrix result = new Matrix(m.rowCount,m.colCount);
		Scanner userInput = new Scanner(System.in);
		int choice = 0;
		boolean opPerformed = false;


		System.out.println();
		System.out.println("\t\t- Row Operations -");
		System.out.println("\t\t1. Rx <~> Ry");
		System.out.println("\t\t2. c*Rx");
		System.out.println("\t\t3. Rx + c*Ry");

		System.out.print("\t\tChoice:  ");
		if( userInput.hasNextInt() ) choice = userInput.nextInt();



		// Rx <~> Ry
		if (choice==1) {
			int x = 0, y = 0;

			// Prompt user for row operation parameters. 
			System.out.println("\t\tSwitch row x & row y");
			System.out.print("\t\tx: ");
			if( userInput.hasNextInt() ) x = userInput.nextInt();
			System.out.print("\t\ty: ");
			if( userInput.hasNextInt() ) y = userInput.nextInt();
			System.out.println("\t\tNow switching row"+x+" and row"+y+".");

			// Perform operation. 
			result = m.switchRows(x,y);
			opPerformed = true;
		}
		// c*Rx
		else if (choice==2) {
			int x = 0;
			double c = 0;

			// Prompt user for row operation parameters. 
			System.out.println("\t\tMultiply row x by a constant c ?");
			System.out.print("\t\tx: ");
			if( userInput.hasNextInt() ) x = userInput.nextInt();
			System.out.print("\t\tc: ");
			if( userInput.hasNextDouble() ) c = userInput.nextDouble();
			System.out.println("\t\tNow multiplying row"+x+" by "+c+".");

			// Perform operation. 
			result = m.multipyRow(x,c);
			opPerformed = true;
		}
		// Rx + c*Ry
		else if (choice==3) {
			int x = 0, y = 0;
			double c = 0;

			// Prompt user for row operation parameters. 
			System.out.println("\t\tWhich row (y) and multiplied by how much (c) do you wanna add to which row (x) ?");
			System.out.print("\t\ty: ");
			if( userInput.hasNextInt() ) y = userInput.nextInt();
			System.out.print("\t\tc: ");
			if( userInput.hasNextDouble() ) c = userInput.nextDouble(); 
			System.out.print("\t\tx: ");
			if( userInput.hasNextInt() ) x = userInput.nextInt();
			System.out.println("\t\tNow adding "+c+"*R"+y+" to R"+x+".");

			// Perform operation. 
			result = m.addRowMultiple(x,y,c);
			opPerformed = true;
		}


		if(opPerformed) {
			return result;
		}
		else {
			System.out.println("Invalid row operation. Original matrix returned as is.");
			return m;
		}
		
	}

	/////////////////////////////////
	//// Game 3: Matrix Addition ////
	/////////////////////////////////
	public static void playAddingGame() {
		
		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Addition ***");

		// Initialize the parameters of the matrices to be created. 
		int rows, cols;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tNumber of rows in matrices A & B: ");
		if( input.hasNextInt() ) rows = input.nextInt(); else return;
		System.out.print("\tNumber of cols in matrices A & B: ");
		if( input.hasNextInt() ) cols = input.nextInt(); else return;
		input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ){
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();


		// Create two new matrix objects. 
		Matrix mA = new Matrix(rows,cols,askValues); System.out.println();
		Matrix mB = new Matrix(rows,cols,askValues);
		// Add the two input matrices to obtain the output matrix. 
		Matrix mC = mA.addTo(mB);
			
		// Print the two input matrices on the screen. 
		System.out.println();
		System.out.println(mA);
		System.out.println(mB);
		// Print the output matrix on the screen. 
		System.out.println(mC);

		finishGame();
	}

	///////////////////////////////////////
	//// Game 4: Matrix Multiplication ////
	///////////////////////////////////////
	public static void playMultiplicationGame() {

		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Multiplication ***");

		// Initialize the parameters of the matrices to be created. 
		int rowsA = 3, colsA = 3;
		int rowsB = 3, colsB = 3;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tNumber of rows in matrix A: ");
		if( input.hasNextInt() ) rowsA = input.nextInt(); else return;
		System.out.print("\tNumber of cols in matrix A: ");
		if( input.hasNextInt() ) colsA = input.nextInt(); else return;
		System.out.print("\tNumber of rows in matrix B: ");
		if( input.hasNextInt() ) rowsB = input.nextInt(); else return;
		System.out.print("\tNumber of cols in matrix B: ");
		if( input.hasNextInt() ) colsB = input.nextInt(); else return;
		input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ){
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();


		// Create two new matrix objects. 
		Matrix mA = new Matrix(rowsA,colsA,askValues);
		Matrix mB = new Matrix(rowsB,colsB,askValues);
		// Multiply the two input matrices to obtain the output matrix. 
		Matrix mC = mA.multiplyBy(mB);
			
		// Print the two input matrices on the screen. 
		System.out.println();
		System.out.println(mA);
		System.out.println(mB);
		// Print the output matrix on the screen. 
		System.out.println(mC);

		finishGame();
	}

	////////////////////////////////////
	//// Game 5: Matrix Exponential ////
	////////////////////////////////////
	public static void playExponentialGame() {

		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Exponentials ***");

		// Initialize the parameters of the matrices to be created. 
		int sizeA = 3;
		int exp = 1;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tSize of matrix A: ");
		if( input.hasNextInt() ) sizeA = input.nextInt(); else return;
		input.nextLine();

		// Ask user for the exponent.
		System.out.print("\tExponent: ");
		if( input.hasNextInt() ) exp = input.nextInt(); else return;
		// input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ){
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();


		// Create a new matrix object. 
		SquareMatrix mA = new SquareMatrix(sizeA,askValues);

		// Multiply the input matrix by itself to obtain the output matrix. 
		SquareMatrix mB = mA.getExponential(exp);
			
		// Print the two input matrices on the screen. 
		System.out.println();
		System.out.println(mA);
		// Print the output matrix on the screen. 
		System.out.println(mB);

		finishGame();
	}

	////////////////////////////////
	//// Game 6: Matrix Inverse ////
	////////////////////////////////
	public static void playInverseGame() {
		
		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Inverse ***");

		// Initialize the parameters of the matrices to be created. 
		int size;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tSize of matrix A: ");
		if( input.hasNextInt() ) size = input.nextInt(); else return;
		input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ){
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();


		// Create a new matrix object. 
		SquareMatrix mA = new SquareMatrix(size,askValues);
		// Invert the input matrix to obtain the output matrix. 
		SquareMatrix mB = mA.getInverse();
			
		// Print the two input matrices on the screen. 
		System.out.println();
		System.out.println(mA);
		// Print the output matrix on the screen. 
		System.out.println(mB);

		finishGame();
	}

	////////////////////////////////////
	//// Game 7: Matrix Determinant ////
	////////////////////////////////////
	public static void playDeterminantGame() {

		// Print header. 
		System.out.println();
		System.out.println("\t*** Fun with Matrix Determinant ***");

		// Initialize the parameters of the matrices to be created. 
		int size = 3;

		// Ask the user for the specific parameters of the matrices to be created. 
		System.out.print("\tSize of matrix A: ");
		if( input.hasNextInt() ) size = input.nextInt(); else return;
		input.nextLine();

		// Determine whether user is willing to provide values for the matrices. Default is negative response. 
		boolean askValues = false;
		System.out.print("\tDo you want to provide the matrix values (Y/N)?  ");
		if( input.hasNextLine() ) {
			String response = input.nextLine();
			if(response.length()>0) {
				char r = response.charAt(0);
				if(r=='Y' || r=='y' || r=='T' || r=='t') askValues = true;
			}
		}
		System.out.println();


		// Create a new matrix object. 
		SquareMatrix mA = new SquareMatrix(size,askValues);
		// Obtain the determinant of the input matrix. 
		double determinant = mA.determinant();
			
		// Print the two input matrices on the screen. 
		System.out.println();
		System.out.println(mA);
		// Print the output value on the screen. 
		System.out.println("det(M) = " + determinant);

		finishGame();
	}


	////////////////////
	//// Game Ender ////
	////////////////////
	public static void finishGame() {
			System.out.println("Game Over");
			System.out.println();
	}

}
