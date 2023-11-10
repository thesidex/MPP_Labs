package prob2;

import java.util.ArrayList;
import java.util.List;

public class Product {
	final String title;
	final double price;
	final int model;

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public int getModel() {
		return model;
	}

	public Product(String title, Double price, int model) {
		this.title = title;
		this.price = price;
		this.model = model;
	}

	@Override
	public String toString() {
		return String.format("\n %s : %s : %s", title, price, model);
	}
	
	public static void main(String[] args) {
		
		Product p1 = new Product("Shoes", 800.00, 1);
		Product p2 = new Product("Beverages", 1000.00, 2);
		Product p3 = new Product("Computers", 1500.00, 3);
		
		//a. Sort by implementing a comparator for price attribute and print product list.
		ProductPriceComparator priceComp = new ProductPriceComparator();
		System.out.println(priceComp.compare(p1, p2));
		
		//b. Sort by implementing a comparator for title attribute and print product list.
		ProductTitleComparator titleComp = new ProductTitleComparator();
		System.out.println(titleComp.compare(p1, p2));
		
		//c. Implement the sort method so that only one type of Comparator is used for the task a & b in a Java 7 Way using closure.
		ProductSort pi = new ProductSort();	
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(p1);
		prodList.add(p2);
		prodList.add(p3);
		pi.sort(prodList, ProductSort.SortMethod.BYTITLE);
		System.out.println(prodList);
		pi.sort(prodList, ProductSort.SortMethod.BYPRICE);
		System.out.println(prodList);
		
		//d. If the title is same use model as another attribute to sort. Do this by using lambdas.(Java 8 Way)
		ProductSort pi2 = new ProductSort();	
		pi2.sort(prodList, ProductSort.SortMethod.BYTITLE);
		System.out.println(prodList);
		pi2.sort(prodList, ProductSort.SortMethod.BYPRICE);
		System.out.println(prodList);
	}
}

/*
Task a & b – Using separate comparators – not closure (refer : comparator2 package )
Task c : Refer comparator3 package
Task d : Refer closures.java8 package
 */
