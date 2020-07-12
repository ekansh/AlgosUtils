package matrix;

import matrix.utils.SimpleMatrixUtils;
/** 
 * rotate matrix by 90 degree
 * @author Ekansh
 *
 */
public class RotateMatrix {
	static int n = 6;

	public static void main(String[] args) {
		int[][] mat = SimpleMatrixUtils.initalizeMatrix(n, n);
		SimpleMatrixUtils.printMat(mat);
		rotate(mat);
		SimpleMatrixUtils.printMat(mat);
	}
	//2,2 to 4,4
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < (n + 1) / 2; i++) {
			for (int j = 0; j < n / 2; j++) {
				int temp = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
				matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = matrix[i][j];
				matrix[i][j] = temp;
			}
		}
	}
}
