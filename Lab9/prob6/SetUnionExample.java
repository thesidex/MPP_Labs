package prob6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUnionExample {
	public static void main(String[] args) {
		List<Set<String>> sets = List.of(new HashSet<>(List.of("A", "B")), new HashSet<>(List.of("D")),
				new HashSet<>(List.of("1", "3", "5")));

		Set<String> result = union(sets);
		System.out.println(result);
	}

	public static Set<String> union(List<Set<String>> sets) {
		return sets.stream().reduce(new HashSet<>(), (set1, set2) -> {
			set1.addAll(set2);
			return set1;
		});
	}
}