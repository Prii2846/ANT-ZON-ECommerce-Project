package models;

// Represents an item in the shopping cart
public class CartItem {
    // Unique identifier for the cart item
    private int cartId;

    // Username of the user who owns the cart item
    private String username;

    // Unique identifier for the product
    private int productId;

    // Quantity of the product in the cart
    private int quantity;

    // Name of the product for easy display and reference
    private String productName;

    // Price of the product
    private double price;

    // Constructor to initialize all fields of the CartItem
    public CartItem(int cartId, String username, int productId, int quantity, String productName, double price) {
        this.cartId = cartId;                   // Initialize cart ID
        this.username = username;               // Set the username
        this.productId = productId;             // Set the product ID
        this.quantity = quantity;               // Set the quantity of the product
        this.productName = productName;         // Set the product name
        this.price = price;                     // Set the product price
    }

    // Getter for cartId
    public int getCartId() {
        return cartId;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for productId
    public int getProductId() {
        return productId;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Getter for productName
    public String getProductName() {
        return productName;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }
}
