package matrix;

import java.util.*;

import  matrix.utils.CreateIntMatrix;

public class ReachToACelUsingDFS {
	public static void main(String[] args) {
		ReachToACelUsingDFS r = new ReachToACelUsingDFS();

		/// TEST 1
		int[][] grid = CreateIntMatrix.constructMatrixFromArgs(
				new String[] { "3", "4", "1", "0", "0", "0", "1", "1", "1", "1", "0", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		int steps = r.dfs(grid, 0, 0, 2, 3,0);
		System.out.println(String.format("actual %d, expected %d", steps, 5));
		/// TEST 2
		grid = CreateIntMatrix
				.constructMatrixFromArgs(new String[] { "3", "3", "1", "1", "1", "1", "0", "1", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		steps = r.dfs(grid, 1, 0, 0, 2,0);
		System.out.println(String.format("actual %d, expected %d", steps, 3));
		/// TEST 3
		grid = CreateIntMatrix
				.constructMatrixFromArgs(new String[] { "3", "3", "1", "1", "1", "1", "0", "1", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		steps = r.dfs(grid, 1, 0, 2, 2,0);
		System.out.println(String.format("actual %d, expected %d", steps, 3));
		/// TEST 4
		grid = CreateIntMatrix
				.constructMatrixFromArgs(new String[] { "3", "3", "1", "0", "1", "1", "0", "1", "1", "1", "1" });
		CreateIntMatrix.printMat(grid);
		steps = r.dfs(grid, 1, 0, 0, 2 ,0);
		System.out.println(String.format("actual %d, expected %d", steps, 5));
	}

	HashSet<List<Integer>> seen = new HashSet<>();
	public int dfs(int[][] grid, int start_row, int start_col, int end_row, int end_col, int len) {
		List<Integer> n = Arrays.asList(start_row, start_col);
		if( isRestricted(grid, start_row, start_col, 0)) { //  0 implies restriciton 
			return 0;
		}
		if(seen.contains(n) ) { //  0 implies restriciton 
			return 0;
		}
		if( start_row  == end_row &&start_col ==end_col ) {
			// you have reached destination.. see what you want to do with this information
			System.out.println("reached with len = "+len);
			return 1;
		}
		seen.add(n);
		dfs(grid, start_row+1, start_col,end_row, end_col ,len+1);
		dfs(grid, start_row-1, start_col,end_row, end_col ,len+1);
		dfs(grid, start_row, start_col+1,end_row, end_col ,len+1);
		dfs(grid, start_row, start_col-1,end_row, end_col ,len+1);

		
		return -1;
	}
	public int dfsStack(int[][] grid, int start_row, int start_col, int end_row, int end_col) {
		HashSet<List<Integer>> seen = new HashSet<>();
		Stack<List<Integer>> stk = new Stack<>();
		stk.push(Arrays.asList(start_row, start_col));
		int len = 0;
		while (!stk.isEmpty()) {
			int breadth =stk.size();
			for (int i = 0; i < breadth; i++) {
				List<Integer> e = stk.pop();
				if (seen.contains(e)) {
					continue;
				}
				seen.add(e);
				if (e.get(0) == end_row && e.get(1) == end_col) {
					return len;
				}
				pushAdjacentInQ(grid, stk, e, seen);
				print(stk, seen);
			}
			len++;
		}
		return -1;
	}
	private void print(Stack<List<Integer>> q, HashSet<List<Integer>> seen) {
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

	private void pushAdjacentInQ(int[][] grid, Stack<List<Integer>> stk, List<Integer> e, Set<List<Integer>> seen) {
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < dir.length; i++) {
			int Y = dir[i][0] + e.get(0);
			int X = dir[i][1] + e.get(1);
			List<Integer> n = Arrays.asList(Y, X);
			if (X >= 0 && X < grid[0].length && Y >= 0 && Y < grid.length && grid[Y][X] == 1 && !seen.contains(n)) {
				stk.push(n);
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
