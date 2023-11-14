package prob3;

import java.util.Arrays;
import java.util.List;

public class ListContains {
	
	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() {
		List<String> list = Arrays.asList("Bob", "Joe", "Tom");
		boolean result = ListContains.contains(list, "Tom");
		System.out.println(result);
		
		List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
		boolean result2 = ListContains.contains(list2, 5);
		System.out.println(result2);
	}
	
	 public static <T> boolean contains(List<T> list, T element) {
        for (T x : list) {
            if (x == null && element == null) {
                return true;
            }
            if (element == null) {
                continue;
            }
            if (element.equals(x)) {
                return true;
            }
        }
        return false;
    }
	 
	 public static boolean contains1 (List<String> list, String s) {
		for (String x : list) {
			if(x == null && s == null) return true;
			if(s == null && x == null) continue;
			if(x.equals(s)) return true;
		}
		return false;
	}
}
