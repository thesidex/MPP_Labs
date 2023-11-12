package prob2;

import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MaxMin {

	public static void main(String[] args) {
		Supplier<Stream<Integer>> myIntStream = () -> Stream.of(22, 77, 15, 4, 2, 5);
		System.out.println("Max: " + myIntStream.get().max(Comparator.naturalOrder()).get());
		System.out.println("Min: " + myIntStream.get().min(Comparator.naturalOrder()).get());
	}

}
