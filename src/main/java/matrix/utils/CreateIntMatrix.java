package matrix.utils;

import java.util.ArrayList;
import java.util.List;

public class CreateIntMatrix {
	// TODO create List<List<Integer> matrix creation utility 
	/**
	 * Will create int matrix
	 * @param String args will contain : row, column and row*column number to construct matrix
	 * @return int[][]
	 */
	public static int[][] constructMatrixFromArgs(String[] args ) {
		int intArgs[] = new int [args.length];
		int i =0;
		for (String s : args) {
			Integer valueOf = Integer.valueOf(s);
			intArgs[i]=valueOf;
			i++;
		}
		int mat[][] =constructMatrixFromIntArgs(intArgs); 
		return mat;
	}
	/**
	 * Will create int matrix
	 * @param int[] args will contain : row, column and row*column number to construct matrix
	 * @return int[][]
	 */
	public static int[][] constructMatrixFromIntArgs(int[] args ) {
		 
		int mat[][] = null;
		int i = 0;
		int j = 0;
		int r = Integer.valueOf(args[0]);
		int c = Integer.valueOf(args[1]);
		mat = new int[r][c];
		for (int k = 2; k < args.length; k++) {
			if (j == c && i == r) {
				break;
			}
			if (j == c) {
				j = 0;
				i++;
			}
			mat[i][j] = Integer.valueOf(args[k]);
			j++;
		}

		return mat;
	}
	 

	public static void printMat(int[][] matEager) {
		int r = matEager.length;
		int c = matEager[0].length;
		System.out.println("{");
		for (int i = 0; i < r; i++) {
			System.out.print("\t[");
			for (int j = 0; j < c; j++) {
				System.out.print(matEager[i][j] + ":");
			}
			System.out.println("]");
		}
		System.out.println("}");
	}
	
	public static void printMat(int[][] matEager, List<List<Integer>> cordinates, List<Character> marker) {
		if ( marker == null ) {
			marker = new ArrayList<Character>() {{add('F');add('S');add('T');}};
		}
		int cordinatesCount =0;
		int r = matEager.length;
		int c = matEager[0].length;
		System.out.println("{");
		for (int i = 0; i < r; i++) {
			System.out.print("\t[");
			for (int j = 0; j < c; j++) {
				
				System.out.print(matEager[i][j] + ":");
			}
			System.out.println("]");
		}
		System.out.println("}");
	}
	/**
	 * Will print matrix
	 * @param r row
	 * @param c column
	 * @return Nothing
	 */
	public static int[][] initalizeMatrix(int r, int c) {
		int count = 0;
		int mat[][] = new int[r][];
		for (int i = 0; i < r; i++) {
			mat[i] = new int[c];
			for (int j = 0; j < c; j++) {
				mat[i][j] = count++;
			}
		}
		return mat;
	}

}
