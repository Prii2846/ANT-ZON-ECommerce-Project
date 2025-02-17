package models;

import java.util.List;

// Represents an order placed by a user in the system
public class Order {
    // Unique identifier for the order
    private int orderId;

    // Username of the user who placed the order
    private String username;

    // Total amount for the order, including all items and any discounts
    private double totalAmount;

    // Unique transaction ID for the order, used for payment tracking
    private String transactionId;

    // List of items included in the order
    private List<OrderItem> orderItems;

    // Constructor to initialize all fields of the Order
    public Order(int orderId, String username, double totalAmount, String transactionId, List<OrderItem> orderItems) {
        this.orderId = orderId;                   // Set the order ID
        this.username = username;                 // Set the username for the order
        this.totalAmount = totalAmount;           // Set the total order amount
        this.transactionId = transactionId;       // Set the transaction ID for payment tracking
        this.orderItems = orderItems;             // Initialize the list of items in the order
    }

    // Getter for orderId
    public int getOrderId() {
        return orderId;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for totalAmount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Getter for transactionId
    public String getTransactionId() {
        return transactionId;
    }

    // Getter for orderItems
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    // Setter for orderId
    public void setOrderId(int orderId) {
        this.orderId = orderId;  // Update the order ID
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;  // Update the username
    }

    // Setter for totalAmount
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;  // Update the total order amount
    }

    // Setter for transactionId
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;  // Update the transaction ID
    }

    // Setter for orderItems
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;  // Update the list of items in the order
    }
}
