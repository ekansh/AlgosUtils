package matrix;

import java.util.*;

import matrix.utils.CreateIntMatrix;

class Sol {
	public static void main(String[] args) {
		int[][] grid = CreateIntMatrix
				.constructMatrixFromIntArgs(new int[] { 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1 });
		Sol s = new Sol();
		int[] a1 = new int[] {1,3,3,7,7};
		int[] b1 = new int[] { 0,1,2,2,4,4};
		s.process(a1, b1, 9);
		System.out.println();
	}
 


	private Set<List<Integer>> getPairs(List<int[]> a, List<int[]> b, int target) {
		Collections.sort(a, (i, j) -> i[1] - j[1]);
		Collections.sort(b, (i, j) -> i[1] - j[1]);
		Set<List<Integer>> result = new LinkedHashSet<>();
		int max = Integer.MIN_VALUE;
		max = getBestSingleProcessSize(a,max,target,result,1);
		max = Math.max(getBestSingleProcessSize(b,max,target,result,2),max);
		int m = a.size() , n = b.size(), i = 0,j = n - 1;
		while (i < m && j >= 0) {
			int sum = a.get(i)[1] + b.get(j)[1];
			if (sum > target) {
				--j;
			} else { // sum <=target
				if (sum > max) { // sum >max ..we found new candidate
					max = sum;
					result.clear();
//					List<Integer> l =new ArrayList<Integer>();
//					l.add(a.get(i)[0]);l.add(b.get(j)[0]);
//					result.add(l);// insert the candidate
				}
				if (sum == max) {
					List<Integer> l =new ArrayList<Integer>();
					l.add(a.get(i)[0]);l.add(b.get(j)[0]);
					result.add(l);// insert the candidate
					// handle dups 
					int sa=i, eb =j;
					while ( i+1<m  && a.get(i)[1] ==a.get(i+1)[1])
						i++;
					while ( j-1>=0  && b.get(j)[1] ==b.get(j-1)[1]) 
						j--;
					getAllCombinations(a,b,sa,i,j,eb,result);
				} 
				// sum less than target and less than max then increment i;
				// also increment i if sum == max ( where max <=target)
				//in case incrementing i over shoots,.. we will do j--
				i++;
			}
		}
//		handleDuplicates(a,b,result,max);
		for (List<Integer> js : result) {
			System.out.println(js.get(0) + ":" + js.get(1));
		}
		return result;
	}
	private void handleDuplicates(List<int[]> a, List<int[]> b, Set<List<Integer>> result, int max) {
		for (List<Integer> list : result) {
			if ( list.get(0) !=-1 && list.get(1)!=-1){
				int i=0,j=0;
				for ( i =0; a.get(i)[0]!=list.get(0);i++);
				for ( j =0; b.get(j)[0]!=list.get(1);j++);
				int sa =i, sb=j;
				while ( i+1<a.size()  && a.get(i)[1] ==a.get(i+1)[1])
					i++;
				while (j+1 <a.size()  && b.get(j)[1] ==b.get(j+1)[1]) 
					j++;
				getAllCombinations(a, b, sa, i, sb, j, result);
			}
		}
		
	}

	private int getBestSingleProcessSize(List<int[]> a, int max, int target,Set<List<Integer>> result, int id ) {
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i)[1]>target) break;
			else {
				if( a.get(i)[1]>max) {
					max =a.get(i)[1];
					result.clear();
				}
				if( max  == a.get(i)[1]) {
					List<Integer> l =new ArrayList<Integer>();
					if( id ==1 ) {l.add(a.get(i)[0]);l.add(-1);};
					if( id ==2 ) {l.add(-1);l.add(a.get(i)[0]); };
					result.add(l);
				}
			}
		}
		return max;
	}
	private void  getAllCombinations(List<int[]> a, List<int[]> b, int sa,int ea, int sb,  int eb,Set<List<Integer>> op) {
		if( sb == eb && ea ==sa) return ;
		for ( int i =sb; i<=eb; i++) {
			for ( int j =sa; j<=ea; j++) {
				List<Integer> l =new ArrayList<Integer>();
				l.add(a.get(j)[0]);
				l.add(b.get(i)[0]);
				op.add(l);
			}
		}
	}
	public List<List<Integer>> process(int[] a1, int[] b1, int K) {
		List<int[]> a = new ArrayList<int[]>();
		List<int[]> b = new ArrayList<int[]>();
		for (int i = 0; i < a1.length; i++) {
			a.add(new int[] { i, a1[i] });
		}
		for (int i = 0; i < b1.length; i++) {
			b.add(new int[] { i, b1[i] });
		}
		getPairs(a, b, K);
		return null;
	}
}