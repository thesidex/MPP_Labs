package prob2;

import java.util.List;

public class SecondSmallestFinder {

	public static <T extends Comparable<T>> T secondSmallest(List<T> list) {
		if (list == null || list.size() < 2) {
			throw new IllegalArgumentException("List must have at least two elements");
		}

		T smallest = list.get(0);
		T secondSmallest = list.get(1);

		if (smallest.compareTo(secondSmallest) > 0) {
			T temp = smallest;
			smallest = secondSmallest;
			secondSmallest = temp;
		}

		for (int i = 2; i < list.size(); i++) {
			T current = list.get(i);

			if (current.compareTo(smallest) < 0) {
				secondSmallest = smallest;
				smallest = current;
			} else if (current.compareTo(secondSmallest) < 0 && current.compareTo(smallest) > 0) {
				secondSmallest = current;
			}
		}

		return secondSmallest;
	}

	public static void main(String[] args) {

		List<Integer> intList = List.of(5, 2, 8, 1, 7);
		Integer secondSmallestInt = secondSmallest(intList);
		System.out.println("Second Smallest Integer: " + secondSmallestInt);

		List<String> stringList = List.of("apple", "orange", "banana", "watermelon");
		String secondSmallestString = secondSmallest(stringList);
		System.out.println("Second Smallest String: " + secondSmallestString);
	}
}
