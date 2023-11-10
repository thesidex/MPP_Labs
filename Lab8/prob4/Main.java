package prob4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
	public static void main(String[] args) {
		List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Cherries", "blums");

		// a. Print the given list using forEach with Lambdas
		fruits.forEach(word -> System.out.println(word));
		
		System.out.println("\n---------------\n");
		
		// b. Print the given list using method reference
		MyConsumer con = new MyConsumer();
		fruits.forEach(con::accept);
	}

	static class MyConsumer implements Consumer<String> {
		public void accept(String mystr) {
			System.out.println(mystr);
		}
	}
}
