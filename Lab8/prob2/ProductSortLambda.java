package prob2;

import java.util.Collections;
import java.util.List;

public class ProductSortLambda {
	static enum SortMethod {BYTITLE, BYPRICE};
	public void sort(List<Product> emps,  SortMethod method) {	

		Collections.sort(emps, (e1,e2) ->
		{
			if(method == SortMethod.BYTITLE) {
				if(e1.equals(e2)) return 0;
				else if(e1.title.compareTo(e2.title) == 1 || 
						(e1.title.compareTo(e2.title) == 0 && e1.price > e2.price)) return 1;
				else return -1;
			} else {
				if(e1.equals(e2)) return 0;
				else if(e1.price > e2.price || 
						(e1.price == e2.price && e1.title.compareTo(e2.title) == 1)) return 1;
				else return -1;
			}
		});			
	}
}
