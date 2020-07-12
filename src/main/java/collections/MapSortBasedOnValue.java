package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapSortBasedOnValue implements Comparable<MapSortBasedOnValue> {

	private String PID;
	private String DESC = "SOMETHING";
	private Integer price;

	@Override
	public String toString() {
		return "PID " + PID + ", PRICE " + price + ", DESC " + DESC;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PID == null) ? 0 : PID.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		MapSortBasedOnValue other = (MapSortBasedOnValue) obj;
		if (PID == null) {
			if (other.PID != null)
				return false;
		} else if (!PID.equals(other.PID))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public int compareTo(MapSortBasedOnValue o) {// Ascending order
		if (this.price == o.price) {
			return 0;
		} else {
			return this.price > o.price ? 1 : -1;
		}
	}

	public static void main1(String[] args) {
		System.out.println("Ascending order");
		List<MapSortBasedOnValue> list = createProducts();

		for (MapSortBasedOnValue product : list) {
			System.out.println(product.getPrice());

		}
		Collections.sort(list);
		System.out.println("Product.main()");
		for (MapSortBasedOnValue product : list) {
			System.out.println(product.getPrice());
		}

	}

	private static List<MapSortBasedOnValue> createProducts() {
		List<MapSortBasedOnValue> list = new ArrayList<>();
		MapSortBasedOnValue p1 = new MapSortBasedOnValue();
		p1.setPID("MSFT");
		p1.setPrice(195);

		MapSortBasedOnValue p2 = new MapSortBasedOnValue();
		p2.setPID("FB");
		p2.setPrice(220);

		MapSortBasedOnValue p3 = new MapSortBasedOnValue();
		p3.setPID("GOOGL");
		p3.setPrice(1024);
		MapSortBasedOnValue p4 = new MapSortBasedOnValue();
		p4.setPID("TSLA");
		p4.setPrice(660);

		MapSortBasedOnValue p5 = new MapSortBasedOnValue();
		p5.setPID("BA");
		p5.setPrice(140);
		p5.DESC = "SOMETHING TOO";

		MapSortBasedOnValue p7 = new MapSortBasedOnValue();
		p7.setPID("BA");
		p7.setPrice(240);
		p7.DESC = "ANYTHING";
		MapSortBasedOnValue p6 = new MapSortBasedOnValue();
		p6.setPID("APL");
		p6.setPrice(299);

		list.add(p3);
		list.add(p2);
		list.add(p1);
		list.add(p5);
		list.add(p4);
		list.add(p6);
		list.add(p7);
		return list;
	}

	public static void main(String[] args) {
		
		int []t = {1,2,3};
		
		
		List<MapSortBasedOnValue> list = createProducts();
		list.stream().forEach(System.out::println);
		Map<MapSortBasedOnValue, MapSortBasedOnValue> pmap = new LinkedHashMap<>();
		Map<MapSortBasedOnValue, MapSortBasedOnValue> phMap = new HashMap<>();
		Map<Integer,Integer>  m  = new HashMap<>();
		 Arrays.stream(t).forEach(e -> m.put(e, e));;
		
		createProducts().stream().forEach(e -> pmap.put(e, e));
		createProducts().stream().forEach(e -> phMap.put(e, e));
		System.out.println("---Linked HASH MAP---------------");
		for (MapSortBasedOnValue p : pmap.keySet()) {
			System.out.println(p);
		}
//----topTenValues
		Map<MapSortBasedOnValue, MapSortBasedOnValue> topTenValues = pmap.entrySet().stream().
				sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(3) 
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("---topTenValues---------------");

		for (MapSortBasedOnValue p : topTenValues.keySet()) {
			System.out.println(p);
		}
//---------------------------------
		Map<MapSortBasedOnValue, MapSortBasedOnValue> valueSortedReverseMap = pmap.entrySet().stream().
				sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("---valueSortedReverseMap---------------");

		for (MapSortBasedOnValue p : valueSortedReverseMap.keySet()) {
			System.out.println(p);
		}
//-----------------------------
		Map<MapSortBasedOnValue, MapSortBasedOnValue> valueSortedMap = pmap.entrySet().stream().sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("---valueSortedMap---------------");

		for (MapSortBasedOnValue p : valueSortedMap.keySet()) {
			System.out.println(p);
		}
		Map<MapSortBasedOnValue, MapSortBasedOnValue> valueSortedMapUsingComparator = pmap.entrySet().stream()
				.sorted(Entry.comparingByValue((p1, p2) -> p1.PID.compareTo(p2.PID)))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("---valueSortedMapUsingComparator---------------");

		for (MapSortBasedOnValue p : valueSortedMapUsingComparator.keySet()) {
			System.out.println(p);
		}

		Map<MapSortBasedOnValue, MapSortBasedOnValue> keySortedMapUsingComparator = pmap.entrySet().stream()
				.sorted(Entry.comparingByKey((p1, p2) -> -1 * p1.PID.compareTo(p2.PID)))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("---keySortedMapUsingComparator---------------");

		for (MapSortBasedOnValue p : keySortedMapUsingComparator.keySet()) {
			System.out.println(p);
		}

		Map<MapSortBasedOnValue, MapSortBasedOnValue> keySortedMap = pmap.entrySet().stream().sorted(Entry.comparingByKey())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("---keySortedMap---------------");

		for (MapSortBasedOnValue p : keySortedMap.keySet()) {
			System.out.println(p);
		}
	}

}

