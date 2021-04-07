
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// CLASS BEGINS, THIS CLASS IS REQUIRED
// two solutions : either using pattern or by simply checking
class ShoppingListAndCouponCodeListMatching {

	public static void main(String[] args) {
		ShoppingListAndCouponCodeListMatching solution = new ShoppingListAndCouponCodeListMatching();
		List<List<String>> codeList1 = new ArrayList<List<String>>();
		codeList1.add(Arrays.asList("apple", "apple"));  	codeList1.add(Arrays.asList("banana", "anything", "banana"));
		List<String> shoppingCart1 =Arrays.asList(  "orange", "apple", "apple", "banana", "orange", "banana");
		
		List<List<String>> codeList2 = new ArrayList<List<String>>();
		codeList2.add(Arrays.asList("apple", "apple"));  	codeList2.add(Arrays.asList("banana", "anything", "banana"));
		List<String> shoppingCart2 = Arrays.asList("banana", "orange", "banana", "apple", "apple");
		
		List<List<String>> codeList3 = new ArrayList<List<String>>();
		codeList3.add(Arrays.asList("apple", "apple"));  	codeList3.add(Arrays.asList("banana", "anything", "banana"));
		List<String> shoppingCart3 = Arrays.asList("apple", "banana", "apple", "banana", "orange", "banana");
		
		List<List<String>> codeList4 = new ArrayList<List<String>>();
		codeList4.add(Arrays.asList("apple", "apple"));  	codeList4.add(Arrays.asList("apple", "apple", "banana"));	 
		List<String> shoppingCart4 = Arrays.asList( "apple", "apple", "apple", "banana");
		
		List<List<String>> codeList5 = new ArrayList<List<String>>();
		codeList5.add(Arrays.asList("apple", "apple"));  	codeList5.add(Arrays.asList("banana", "anything", "banana"));
		List<String> shoppingCart5 = Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana");
		
		List<List<String>> codeList6 = new ArrayList<List<String>>();
		codeList6.add(Arrays.asList("apple", "apple"));  	codeList6.add(Arrays.asList("banana", "anything", "banana"));
		List<String> shoppingCart6 = Arrays.asList("apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana");
		
		List<List<String>> codeList7 = new ArrayList<List<String>>();
		codeList7.add(Arrays.asList("anything", "apple"));  	codeList7.add(Arrays.asList("banana", "anything", "banana"));
		List<String> shoppingCart7 =Arrays.asList("orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana");
		
		List<List<String>>  codeList8 =new ArrayList<List<String>>();
		codeList8.add(Arrays.asList("apple", "orange"));  	codeList8.add(Arrays.asList("orange", "banana", "orange"));
		List<String> shoppingCart8 = Arrays.asList("apple", "orange", "banana", "orange", "orange", "banana", "orange", "grape");

		List<List<String>> codeList9 = new ArrayList<List<String>>();
		codeList9.add(Arrays.asList( "a", "b", "a", "b", "c" ));
		List<String> shoppingCart9 = Arrays.asList("a", "b", "a", "b", "a", "b", "c");
		
		System.out.println(String.format("expected = 1, actual=%d",solution.checkWinner1(codeList1, shoppingCart1)));
		System.out.println(String.format("expected = 0, actual=%d",solution.checkWinner1(codeList2, shoppingCart2)));
		System.out.println(String.format("expected = 0, actual=%d",solution.checkWinner1(codeList3, shoppingCart3)));
		System.out.println(String.format("expected = 0, actual=%d",solution.checkWinner1(codeList4, shoppingCart4)));
		System.out.println(String.format("expected = 1, actual=%d",solution.checkWinner1(codeList5, shoppingCart5)));
		System.out.println(String.format("expected = 1, actual=%d",solution.checkWinner1(codeList6, shoppingCart6)));
		System.out.println(String.format("expected = 1, actual=%d",solution.checkWinner1(codeList7, shoppingCart7)));
		System.out.println(String.format("expected = 1, actual=%d",solution.checkWinner1(codeList8, shoppingCart8)));
		System.out.println(String.format("expected = 1, actual=%d",solution.checkWinner1(codeList9, shoppingCart9)));
	}

	public int checkWinner1(List<List<String>> codeList, List<String> shoppingCart) {
		String sl = shoppingCart.stream().collect(Collectors.joining("_"));
		StringBuilder sb = new StringBuilder();
		for (List<String> l : codeList) {
			for (String string : l) {
				if (string.equals("anything")) {
					string = "[a-z]+";
				}
				sb.append(string).append("_");
			}
			sb.append(".*");
		}
		String regex = sb.replace(sb.length() - 3, sb.length(), "").toString();
		System.out.println(regex);
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(sl);
		boolean find = matcher.find();
		return find ?1:0;
	}

	int cur = 0;
	int grpPointer = 0;

	public int checkWinner(List<List<String>> codeList, List<String> shoppingCart) {
		int i = 0;
		if (codeList.isEmpty())
			return 0;
		int win1 = codeList.size();

		int win2 = codeList.get(win1 - 1).size();
		while (i < shoppingCart.size()) {
			String fruit = shoppingCart.get(i);
			if (contains(codeList, fruit)) {
				while (i < shoppingCart.size()) {
					fruit = shoppingCart.get(i);
					if (contains(codeList, fruit)) {
						i++;
						grpPointer++;

						if (cur == win1 - 1 && grpPointer == win2) {
							return 1;
						}
						if (grpPointer == codeList.get(cur).size()) {
							grpPointer = 0;
							cur++;
						}
					} else {
						break;
					}
				}
				cur = 0;
				grpPointer = 0;
			}
			i++;
		}
		return 0;
	}

	private boolean contains(List<List<String>> codeList, String fruit) {
		List<String> list = codeList.get(cur);
		boolean flag = false;
		if (list.get(grpPointer).equals(fruit) || list.get(grpPointer).equals("anything")) {
			flag = true;
		}
		return flag;
	}
}