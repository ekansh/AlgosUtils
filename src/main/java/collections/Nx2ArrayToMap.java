package collections;

import java.util.*;

public class Nx2ArrayToMap {
	public static void main(String[] args) {
		List<List<Integer>> connections = new ArrayList<List<Integer>>();
		connections.add(Arrays.asList(0,1));
		connections.add(Arrays.asList(2,0));
		connections.add(Arrays.asList(1,3));
		connections.add(Arrays.asList(1,2));
		Nx2ArrayToMap sol = new Nx2ArrayToMap();
		System.out.println("int[][] list");
		sol.getIntMap(new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 } });
		System.out.println("List to  list");
		sol.getIntMap(connections);
		System.out.println("int[][] Node");
		sol.getNodeMap(new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 } });
		System.out.println("List Node");
		sol.getNodeMap(connections);
	}

	public Map<Integer, List<Integer>> getIntMap(int[][] ar) {
		Map<Integer, List<Integer>> m = new HashMap<>();
		for (int[] is : ar) {
			List<Integer> l1 = new ArrayList<Integer>();
			List<Integer> l2 = new ArrayList<Integer>();
			m.putIfAbsent(is[0], l1);
			m.putIfAbsent(is[1], l2);
			m.get(is[0]).add(is[1]);
			m.get(is[1]).add(is[0]);
		}
		print(m);
		return m;
	}

	public Map<Integer, Node> getNodeMap(int[][] ar) {
		HashMap<Integer, Node> m = new HashMap<>();
		for (int[] is : ar) {
			Node n1 = new Node(is[0]);
			Node n2 = new Node(is[1]);
			m.putIfAbsent(is[0], n1);
			m.putIfAbsent(is[1], n2);
			m.get(is[0]).l.add(n2);
			m.get(is[1]).l.add(n1);
		}
		printNodeMap(m);
		return m;
	}

	public Map<Integer, List<Integer>> getIntMap(List<List<Integer>> ar) {
		Map<Integer, List<Integer>> m = new HashMap<>();
		for (List<Integer> is : ar) {
			List<Integer> l1 = new ArrayList<Integer>();
			List<Integer> l2 = new ArrayList<Integer>();
			m.putIfAbsent(is.get(0), l1);
			m.putIfAbsent(is.get(1), l2);
			m.get(is.get(0)).add(is.get(1));
			m.get(is.get(1)).add(is.get(0));
		}
		print(m);
		return m;
	}

	public Map<Integer, Node> getNodeMap(List<List<Integer>> ar) {
		HashMap<Integer, Node> m = new HashMap<>();
		for (List<Integer> is : ar) {
			Node n1 = new Node(is.get(0));
			Node n2 = new Node(is.get(1));
			m.putIfAbsent(is.get(0), n1);
			m.putIfAbsent(is.get(1), n2);
			m.get(is.get(0)).l.add(n2);
			m.get(is.get(1)).l.add(n1);
		}
		printNodeMap(m);
		return m;
	}
	
	private void printNodeMap(Map<Integer, Node> m) {
		for (Integer k : m.keySet()) {
			System.out.print(k + "->");
			m.get(k).l.stream().forEach(e -> System.out.print(e + ","));
			System.out.println();
		}
	}
	private void print(Map<Integer, List<Integer>> m) {
		for (Integer k : m.keySet()) {
			System.out.print(k + "->");
			m.get(k).stream().forEach(e -> System.out.print(e + ","));
			System.out.println();
		}
	}

	private static class Node {
		int v;
		List<Node> l = new ArrayList<>();

		Node(int a) {
			v = a;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return v + "";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + v;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (v != other.v)
				return false;
			return true;
		}

	}
}
