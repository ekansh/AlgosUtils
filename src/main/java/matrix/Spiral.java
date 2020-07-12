package matrix;

import matrix.utils.CreateIntMatrix;
import matrix.utils.SimpleMatrixUtils;

/**
 * Create left, right, top , bottom boundaries int l =0, r = n-1, t =0, b = n-1;
 * Continue until the spiral shrunk to one cell while(l<=r && t<=b) At the top
 * Go from left to right Go from top+1 to bottom at the right hand side At the
 * bottom Go from right -1 to left At the left go from bottom-1 to top+1
 * Increment left and top and decrement right and bottom l++; r--; t++;b--;
 * 
 * @author Ekansh
 *
 */
public class Spiral {
	public static void main(String[] args) {
		Spiral s = new Spiral();
		int[][] m = s.generateMatrix(3);
		SimpleMatrixUtils.printMat(m);
	}

	public int[][] generateMatrix(int n) {
		int m[][] = new int[n][n];

		int l = 0, r = n - 1, t = 0, b = n - 1;
		int k = 1;
		while (l <= r && t <= b) {
			for (int j = l; j <= r; j++) {

				m[t][j] = k;
				System.out.print("t,j " + t + "-" + j + ":" + m[t][j] + "; ");
				k++;
			}
			System.out.println();
			for (int i = t + 1; i <= b; i++) {
				m[i][r] = k;
				System.out.print("i,r " + i + "-" + r + ":" + m[i][r] + "; ");
				k++;
			}
			System.out.println();
			if (l < r && t < b) {
				for (int j = r - 1; j >= l; j--) {
					m[b][j] = k;
					System.out.print("b,j " + b + "-" + j + ":" + m[b][j] + "; ");
					k++;
				}
				System.out.println();
				for (int i = b - 1; i >= t + 1; i--) {
					m[i][l] = k;
					System.out.print("i,l " + i + "-" + l + ":" + m[i][l] + "; ");
					k++;
				}
				System.out.println();
			}
			l++;
			r--;
			t++;
			b--;
		}
		return m;
	}
}