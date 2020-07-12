package matrix.utils;

import java.util.ArrayList;
import java.util.List;

public class CreateCharMatrix {
	// TODO create List<List<Integer> matrix creation utility
	/**
	 * Will create int matrix
	 * 
	 * @param String args will contain : row, column and row*column number to
	 *               construct matrix
	 * @return int[][]
	 */
	public static char[][] constructMatrixFromArgs(String[] args) {
		char mat[][] = null;
		int i = 0;
		int j = 0;
		int r = Integer.valueOf(args[0]);
		int c = Integer.valueOf(args[1]);
		mat = new char[r][c];
		for (int k = 2; k < args.length; k++) {
			if (j == c && i == r) {
				break;
			}
			if (j == c) {
				j = 0;
				i++;
			}
			mat[i][j] = args[k].charAt(0);
			j++;
		}

		return mat;

	}
 
	public static void printMat(char[][] matEager) {
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
	 * Will init matrix
	 * 
	 * @param r row
	 * @param c column
	 * @return Nothing
	 */
	public static char[][] initalizeMatrix(int r, int c) {
		int count = 0;
		char mat[][] = new char[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				mat[i][j] = (char)('A'+count);
				count++;
			}
		}
		return mat;
	}
	public static void main(String[] args) {
		char[][] m = initalizeMatrix(4, 5);
		printMat(m);
	}
}
