package prob5;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		String[] names = {"Alexis", "Tim", "Kyleen", "KRISTY"};
		
		//a. Use Arrays.sort()to sort the names by ignore case using Method reference.
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println("Sorted Names: " + Arrays.toString(names));
	}

}
