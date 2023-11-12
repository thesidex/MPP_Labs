package prob5;

import java.util.Arrays;
import java.util.stream.Stream;

public class Section {
    public static Stream<String> streamSection(Stream<String> stream, int m, int n) {
        return stream
                .skip(m)
                .limit(n - m + 1);
    }

    public static void main(String[] args) {
        // Make three calls for the streamSection() method with different inputs
        streamSection(nextStream(), 1, 3).forEach(System.out::println);
        streamSection(nextStream(), 2, 5).forEach(System.out::println);
        streamSection(nextStream(), 0, 2).forEach(System.out::println);
    }

    // Support method for the main method -- for testing
    private static Stream<String> nextStream() {
        return Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii").stream();
    }
}



