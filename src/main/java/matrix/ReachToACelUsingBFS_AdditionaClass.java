package matrix;

import java.util.*;
 
import matrix.utils.CreateIntMatrix;

public class ReachToACelUsingBFS_AdditionaClass {
	public static void main(String[] args) {
		ReachToACelUsingBFS_AdditionaClass r = new ReachToACelUsingBFS_AdditionaClass();

		/// TEST 1
		int[][] grid = CreateIntMatrix.constructMatrixFromArgs(
				new String[] { "3", "4", "1", "0", "0", "0", "1", "1", "1", "1", "0", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		int minSteps = r.minSteps(grid, 0, 0, 2, 3);
		System.out.println(String.format("actual %d, expected %d", minSteps, 5));
		/// TEST 2
		grid = CreateIntMatrix
				.constructMatrixFromArgs(new String[] { "3", "3", "1", "1", "1", "1", "0", "1", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		minSteps = r.minSteps(grid, 1, 0, 0, 2);
		System.out.println(String.format("actual %d, expected %d", minSteps, 3));
		/// TEST 3
		grid = CreateIntMatrix
				.constructMatrixFromArgs(new String[] { "3", "3", "1", "1", "1", "1", "0", "1", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		minSteps = r.minSteps(grid, 1, 0, 2, 2);
		System.out.println(String.format("actual %d, expected %d", minSteps, 3));
		/// TEST 4
		grid = CreateIntMatrix
				.constructMatrixFromArgs(new String[] { "3", "3", "1", "0", "1", "1", "0", "1", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		minSteps = r.minSteps(grid, 1, 0, 0, 2);
		System.out.println(String.format("actual %d, expected %d", minSteps, 5));
	}

	public int minSteps(int[][] grid, int start_row, int start_col, int end_row, int end_col) {
		HashSet<Cell> seen = new HashSet<>();
		Queue<Cell> q = new LinkedList<>();
		q.offer(new Cell(start_row, start_col));
		int len = 0;

		while (!q.isEmpty()) {
			int breadth = q.size();
			for (int i = 0; i < breadth; i++) {
				Cell e = q.poll();
				if (seen.contains(e)) {
					continue;
				}
				seen.add(e);
				if (e.row == end_row && e.col == end_col) {
					return len;
				}
				pushAdjacentInQ(grid, q, e, seen);
			}
			len++;
		}
		return -1;
	}

	private void print(Queue<Cell> q, HashSet< Cell> seen) {
		if (q instanceof LinkedList<?>) {
			System.out.print("q= ");
			LinkedList<Cell> l = (LinkedList<Cell>) q;
			for (Cell is : l) {
				System.out.print(is.row + ":" + is.col + ", ");
			}
			System.out.println();
		}
		System.out.print("seen= ");
		for (Cell is :seen) {
			System.out.print(is.row + ":" + is.col + ", ");
		}
		System.out.println();
	}

	private void pushAdjacentInQ(int[][] grid, Queue<Cell> q, Cell e, Set<Cell> seen) {
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < dir.length; i++) {
			int Y = dir[i][0] + e.row;
			int X = dir[i][1] + e.col;
			Cell n = new Cell(Y,X);
			if (X >= 0 && X < grid[0].length && Y >= 0 && Y < grid.length && grid[Y][X] == 1 && !seen.contains(n)) {
				q.offer(n);
			}
		}
	}
 
	private static class Cell {
		int row, col;
		Cell(int x, int y) {
			row = x;
			col = y;
		}

		@Override
		public String toString() {
			return row + ":" + col;
		}
		// based on row and column only 
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}
		// based on row and column only 
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
		
	}

}
