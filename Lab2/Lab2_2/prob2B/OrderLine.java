package prob2B;

public class OrderLine {

	private double price;
	private Order order;
	private int linenum = 0;
	private int quatity = 0;
	
	public OrderLine(double price, Order order, int quantity) {
		this.price = price;
		this.order = order;
		this.linenum = linenum+1;
		this.quatity = quantity;
	}

	public double getPrice() {
		return price;
	}
	
	public Order getOrder() {
		return order;
	}

	@Override
	public String toString() {
		return " Quatity: "  + this.quatity + " Total: " + this.price;
	}
}
