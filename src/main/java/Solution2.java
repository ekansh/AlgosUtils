import java.util.*;
import java.util.stream.Stream;

public class Solution2 {
	public static void main1 (String[] args) {
		int x = 1000;
		List<Integer> l = new ArrayList<Integer>() {
			{
				add(2);
				add(5);
				add(4);
				add(6);
				add(8);
			}
		};// add(5);add(12);
		for (int i = 0; i < 1000000; i++) {
			l.add(i* new Random().nextInt(8));
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(x, (a, b) -> b - a);
		PriorityQueue<Integer> win = new PriorityQueue<Integer>((a, b) -> a - b);
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < x; i++) {
			win.add(l.get(i));
		}
		pq.add(win.peek());

		for (int i = x; i < l.size(); i++) {
			win.remove(l.get(i-x));
			win.add(l.get(i));
			pq.add(win.peek());
		}
		long t2 = System.currentTimeMillis();
		System.out.println((t2 - t1) + ":" + pq.peek());
	}

	public static void main (String[] args) {
		int x = 10000;
		List<Integer> l = new ArrayList<Integer>() {
			{
				add(2);
				add(5);
				add(4);
				add(6);
				add(8);
			}
		};// add(5);add(12);
		for (int i = 0; i < 1000000; i++) {
			l.add(i);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
		long t1 = System.currentTimeMillis();
		for (int i = 0; i + x <= l.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = i; j < i + x; j++) {
				min = Math.min(l.get(j), min);
			}
			pq.add(min);
		}
		long t2 = System.currentTimeMillis();
		System.out.println((t2 - t1) + "" + pq.peek());
	}

}
