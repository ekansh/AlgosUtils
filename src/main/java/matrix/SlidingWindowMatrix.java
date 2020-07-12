package matrix;

import matrix.utils.CreateIntMatrix;

public class SlidingWindowMatrix {
	public static void main(String[] args) {
		SlidingWindowMatrix r = new SlidingWindowMatrix();

		/// TEST 1
		int[][] grid = CreateIntMatrix.initalizeMatrix(5, 3);
		CreateIntMatrix.printMat(grid);
		r.window(grid,0,0,3,0);
	}

	// pass index
	public void window(int[][] grid, int sr, int sc, int er, int ec) {
		int rowLen =er - sr + 1;
		int colLen =ec - sc + 1;
		while (er < grid.length && ec < grid[0].length) {
			int win[][] = new int[rowLen][colLen];
			for (int i1=0,i = sr; i <= er; i++, i1++) {
				for (int j1 = 0,j = sc; j <= ec; j++, j1++) {
					win[i1][j1] = grid[i][j];
				}
			}
			System.out.println(String.format("window: sr=%d, sc=%d, er=%d,  ec=%d",   sr,   sc,   er,   ec));
			CreateIntMatrix.printMat(win);
			if (ec + 1 == grid[0].length) {
				sc = 0;
				ec = colLen-1;
				sr++;er++;
			} else {
				sc++;ec++;
			}
		}
		
	}
}
