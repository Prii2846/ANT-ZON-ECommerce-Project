package models;

// Represents an item within an order in the eCommerce system
public class OrderItem {
    // The unique identifier for the order that this item belongs to
    private int orderId;

    // The unique identifier for the product in the order
    private int productId;

    // The name of the product in the order
    private String productName;

    // The quantity of the product ordered
    private int quantity;

    // The price of the product per unit
    private double price;

    // Constructor to initialize all fields of the OrderItem
    public OrderItem(int orderId, int productId, String productName, int quantity, double price) {
        this.orderId = orderId;               // Set the order ID
        this.productId = productId;           // Set the product ID
        this.productName = productName;       // Set the product name
        this.quantity = quantity;             // Set the quantity of the product
        this.price = price;                   // Set the price per unit of the product
    }

    // Getter for orderId
    public int getOrderId() {
        return orderId;  // Return the order ID
    }

    // Setter for orderId
    public void setOrderId(int orderId) {
        this.orderId = orderId;  // Update the order ID
    }

    // Getter for productId
    public int getProductId() {
        return productId;  // Return the product ID
    }

    // Setter for productId
    public void setProductId(int productId) {
        this.productId = productId;  // Update the product ID
    }

    // Getter for productName
    public String getProductName() {
        return productName;  // Return the product name
    }

    // Setter for productName
    public void setProductName(String productName) {
        this.productName = productName;  // Update the product name
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;  // Return the quantity of the product
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;  // Update the quantity of the product
    }

    // Getter for price
    public double getPrice() {
        return price;  // Return the price per unit of the product
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;  // Update the price per unit of the product
    }

    // Override toString to provide a string representation of the OrderItem
    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
