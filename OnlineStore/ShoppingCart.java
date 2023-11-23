import java.util.*;

public class ShoppingCart {
	private int shoppingCartId;
	private double totalAmount;
	private List<Item> items;

	ShoppingCart() {
		items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public int getItemCount() {
		return items.size();
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setTotalAmount(double t){
		this.totalAmount = t;
	}
}
