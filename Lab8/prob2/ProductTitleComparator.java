package prob2;

import java.util.Comparator;

public class ProductTitleComparator implements Comparator<Product> {
	@Override
	public int compare(Product e1, Product e2) {
		if (e1.equals(e2))
			return 0;
		else if (e1.title.compareTo(e2.title) == 0)
			return 1;
		else
			return -1;
	}
}