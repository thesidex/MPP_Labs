import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private String email;
    private ShoppingCart cart;

    public User(int uid, String name, String email) {
        this.userId = uid;
        this.name = name;
        this.email = email;
        cart = new ShoppingCart();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    // TODO
    public boolean purchaseItem() {
        return true;
    }

    // TODO
    public List<Item> browseItems() {
        List<Item> items = new ArrayList<Item>();
        return items;
    }

    public void SetUserId(int uid) {
        this.userId = uid;
    }
}
