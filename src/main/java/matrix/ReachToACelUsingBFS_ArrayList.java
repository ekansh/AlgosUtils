package matrix;

import java.util.*;
 

import  matrix.utils.CreateIntMatrix;
public class ReachToACelUsingBFS_ArrayList {
	public static void main(String[] args) {
		ReachToACelUsingBFS_ArrayList r = new ReachToACelUsingBFS_ArrayList();
		List<List<Integer>> grid =new ArrayList<>();
		grid.add(Arrays.asList(1,1,1));
		grid.add(Arrays.asList(1,0,1));
		grid.add(Arrays.asList(1,1,1));
		 
		int minSteps = r.minSteps(grid, 1, 0, 2, 2);
		System.out.println(String.format("actual %d, expected %d", minSteps, 3));
	}

	public int minSteps(List<List<Integer>> grid, int start_row, int start_col, int end_row, int end_col) {
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

	private void pushAdjacentInQ(List<List<Integer>> grid, Queue<List<Integer>> q, List<Integer> e, Set<List<Integer>> seen) {
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < dir.length; i++) {
			int Y = dir[i][0] + e.get(0);
			int X = dir[i][1] + e.get(1);
			List<Integer> n = Arrays.asList(Y, X);
			if (X >= 0 && X < grid.get(0).size() && Y >= 0 && Y < grid.size() && grid.get(Y).get(X) == 1 && !seen.contains(n)) {
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
