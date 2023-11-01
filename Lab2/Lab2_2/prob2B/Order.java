package prob2B;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private int orderNum;
	private LocalDate orderDate;
	private List<OrderLine> orderline;

	Order(int orderNum, double price, int count) {
		this.orderNum = orderNum;
		this.orderline = new ArrayList<>();
		this.orderDate = LocalDate.now();
		addOrderLine(price, count);
	}

	public int getOrderNum() {
		return this.orderNum;
	}

	public void addOrderLine(double price, int quantity) {
		orderline.add(new OrderLine(price, this, quantity));
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Order: %s", getOrderNum()));

		for (OrderLine myorder : orderline) {
			str.append(String.format("  %s %n", myorder.toString()));
		}
		return str.toString();
	}
}
