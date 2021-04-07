package graphs;

import java.util.*;

public class CriticalConnections {
		public static void main(String[] args) {
			CriticalConnections c = new CriticalConnections();
			List<List<Integer>> con  =    new ArrayList<List<Integer>>();
			con.add(Arrays.asList(0, 1));
			con.add(Arrays.asList(0, 2));
			con.add(Arrays.asList(1, 3));
			con.add(Arrays.asList(2, 3)); 
			con.add(Arrays.asList(2, 5));
			con.add(Arrays.asList(5, 6));
			con.add(Arrays.asList(3, 4));
			 
			List<List<Integer>> cc = c.criticalConnections(4, con);
			for (List<Integer> list : cc) {
				list.stream().forEach(e->System.out.print(e+","));
				System.out.println();
			}
		}
    	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		HashMap<Integer, Node> m = new HashMap< >();
		for (List<Integer> list : connections) {
			Integer i1 = list.get(0);
			m.putIfAbsent(i1,new Node(i1));
			
			Integer i2 = list.get(1);
			m.putIfAbsent(i2,new Node(i2));
			Node n1 = m.get(i1);
			Node n2 = m.get(i2);
			n1.l.add(n2);
			n2.l.add(n1);
		}
		Set<String> tested= new HashSet<String>();
		for (Integer i : m.keySet()) {
			Node node = m.get(i);
			for ( Node con  : node.l) {
				String testingA = i+":"+con.v;
				String testingB =con.v +":"+i;
				if(tested.contains(testingA) ||tested.contains(testingB)) {
					continue;
				}
				boolean r =conTest(con,node);
				if(!r) {
					result.add(new ArrayList<Integer>() {{add(i);add(con.v);}});
				}
				tested.add(testingB);tested.add(testingA);
			}
		}
		return result;
		
	}
	private boolean conTest( Node n1, Node n2) {
		Queue<Node> q  = new LinkedList<>();
		q.add(n1);
		Set<Node > seen  = new HashSet<> ();
		boolean reached =false;
		while(!reached && !q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node poll = q.poll();
				if(seen.contains(poll)) continue;
				seen.add(poll);
				if( poll.v ==n2.v) {
					reached = true;
					break;
				}
				for(Node conns : poll.l) {
					if(poll.v == n1.v && conns.v == n2.v) {
						continue;
					}
					q.add(conns);
				}
			}
		
		}
		return reached;
	}
	private static class Node{
		int v;
		List<Node> l  = new ArrayList<>();
		Node(int a ) {
			v =a;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return v+"";
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
