package prob2B;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Order o1 = new Order(1, 300, 2);
		o1.addOrderLine(100 ,1);
		o1.addOrderLine(200, 3);

		Order o2 = new Order(2, 200, 1);
		o2.addOrderLine(200, 4);

		Order o3 = new Order(3, 700, 2);
		o3.addOrderLine(300, 2);
		o3.addOrderLine(400, 1);

		List<Order> allOrders = new ArrayList<>();

		allOrders.add(o1);
		allOrders.add(o2);
		allOrders.add(o3);

		System.out.println(allOrders);
	}

}
