package prob4;

import java.time.LocalDate;

public class CustOrderFactory {
	public static Customer createCustomer(String name) {
		if (name == null)
			throw new NullPointerException("Customer name cannot be null");
		return new Customer(name);
	}

	public static Order newOrder(Customer cust, LocalDate orderDate) {
		if (cust == null)
			throw new NullPointerException("Null customer");
		Order ord = new Order(orderDate);
		cust.addOrder(ord);
		return ord;
	}

	public static void addOrder(Customer cust, Order order) {
		if (cust == null)
			throw new NullPointerException("Null customer");
		cust.addOrder(order);
	}

	public static Item newItem(String name) {
		return new Item(name);
	}

	public static void addItem(Order order, Item item) {
		if (order == null)
			throw new NullPointerException("Null order");
		order.addItem(item);
	}
}
