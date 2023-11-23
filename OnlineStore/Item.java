public class Item {
    private int itemId;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;

    public Item(int uid, String name, double price, int stockQuantity) {
        this.itemId = uid;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null)
            return false;
        if (!(ob instanceof Item p))
            return false;
        return p.name.equals(name);
    }
}
