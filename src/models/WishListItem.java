package models;

// Represents an item in a user's wishlist
public class WishListItem {
    private String username;        // Username of the user who added the item to the wishlist
    private int productId;          // Unique identifier of the product
    private String productName;     // Name of the product
    private double productPrice;    // Price of the product

    // Constructor to initialize a wishlist item with username, product ID, name, and price
    public WishListItem(String username, int productId, String productName, double productPrice) {
        this.username = username;             // Set the username
        this.productId = productId;           // Set the product ID
        this.productName = productName;       // Set the product name
        this.productPrice = productPrice;     // Set the product price
    }

    // Gets the username of the user
    public String getUsername() {
        return username;
    }

    // Sets the username of the user
    public void setUsername(String username) {
        this.username = username;             // Update the username
    }

    // Gets the product ID
    public int getProductId() {
        return productId;
    }

    // Sets the product ID
    public void setProductId(int productId) {
        this.productId = productId;           // Update the product ID
    }

    // Gets the product name
    public String getProductName() {
        return productName;
    }

    // Sets the product name
    public void setProductName(String productName) {
        this.productName = productName;       // Update the product name
    }

    // Gets the product price
    public double getProductPrice() {
        return productPrice;
    }

    // Sets the product price
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;     // Update the product price
    }

    // Provides a string representation of the wishlist item
    @Override
    public String toString() {
        return "WishListItem [productId=" + productId + ", productName=" + productName 
                + ", productPrice=" + productPrice + "]";
    }
}
