package models;

public class CartItem {
    private int cartId;
    private String username;
    private int productId;
    private int quantity;
    private String productName;
    private double price;

    
    public CartItem(int cartId, String username, int productId, int quantity, String productName, double price) {
        this.cartId = cartId;
        this.username = username;
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }

    
    public int getCartId() { return cartId; }
    public String getUsername() { return username; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
}
