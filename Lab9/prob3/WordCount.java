package prob3;

import java.util.List;

public class WordCount {

	public static void main(String[] args) {
		List<String> words = List.of("apple", "banana", "cherry", "dater", "grape", "kiwi");

        char charC = 'a';
        char charD = 'g';
        int wordLength = 5;

        int result = countWords(words, charC, charD, wordLength);

        System.out.println("Number of words: " + result);
	}

	public static int countWords(List<String> words, char c, char d, int len) {
        return (int) words.stream()
                .filter(word -> word.length() == len)
                .filter(word -> word.indexOf(c) != -1)
                .filter(word -> word.indexOf(d) == -1)
                .count();
    }
}
