package matrix;

import java.util.*;

import  matrix.utils.CreateIntMatrix;

public class ReachToACelUsingBFS {
	public static void main(String[] args) {
		ReachToACelUsingBFS r = new ReachToACelUsingBFS();

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
		HashSet<List<Integer>> seen = new HashSet<>();
		Queue<List<Integer>> q = new LinkedList<>();
		q.offer(Arrays.asList(start_row, start_col));
		int len = 0;

		while (!q.isEmpty()) {
			int breadth = q.size();
			for (int i = 0; i < breadth; i++) {
				List<Integer> e = q.poll();
				if (seen.contains(e)) {
					continue;
				}
				seen.add(e);
				if (e.get(0) == end_row && e.get(1) == end_col) {
					return len;
				}
				pushAdjacentInQ(grid, q, e, seen);
				print(q, seen);
			}
			len++;
		}
		return -1;
	}

	private void print(Queue<List<Integer>> q, HashSet<List<Integer>> seen) {
//		if (q instanceof LinkedList<?>) {
//			System.out.print("q= ");
//			LinkedList<List<Integer>> l = (LinkedList<List<Integer>>) q;
//			for (List<Integer> is : l) {
//				System.out.print(is.get(0) + ":" + is.get(1) + ", ");
//			}
//			System.out.println();
//		}
//		System.out.print("seen= ");
//		for (List<Integer> is : seen) {
//			System.out.print(is.get(0) + ":" + is.get(1) + ", ");
//		}
//		System.out.println();
	}

	private void pushAdjacentInQ(int[][] grid, Queue<List<Integer>> q, List<Integer> e, Set<List<Integer>> seen) {
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < dir.length; i++) {
			int Y = dir[i][0] + e.get(0);
			int X = dir[i][1] + e.get(1);
			List<Integer> n = Arrays.asList(Y, X);
			if (X >= 0 && X < grid[0].length && Y >= 0 && Y < grid.length && grid[Y][X] == 1 && !seen.contains(n)) {
				q.offer(n);
			}
		}
	}

	private boolean isRestricted(int[][] grid, int r, int c, int restriction) {
		if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0 || grid[r][c] == restriction) {
			return true;
		}
		return false;
	}

	 

}
