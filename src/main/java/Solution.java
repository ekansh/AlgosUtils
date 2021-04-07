import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		List<Integer> l = new ArrayList<Integer>() {
			{
				add(5);
				add(3);
				add(2);
				add(8);
				add(1);
				add(1);
				add(2);
			}
		};// add(5);add(12);
		int move  = dp1(l, 0 );
		// l.stream().forEach(System.out::println);
		System.out.println(move);
	}

	private static int dp1(List<Integer> l, int pos ) {
		Integer move = null ;
		for (int i = pos; i < l.size(); i++) {
			if( i == pos) continue;
			if( l.get(pos)%2==0) {
				int k = dp1(l, pos + 1 );
				move = move == null ? k : Math.min(k, move);
			}else if (l.get(i) % 2 == 0) { // element is even then swap them
				swap(l, i, pos);
				int k = 1 + dp1(l, pos + 1 );
				move = move == null ? k : Math.min(k, move);
				swap(l, pos, i);
			}
		}
		return move== null ? 0: move;

	}

	private static void swap(List<Integer> l, int pos, int i) {
		int t = l.get(pos);
		l.set(pos, l.get(i));
		l.set(i, t);
	}
}
