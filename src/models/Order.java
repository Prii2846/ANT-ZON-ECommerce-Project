package models;

import java.util.List;

public class Order {
    private int orderId;
    private String username;
    private double totalAmount;
    private String transactionId;  
    private List<OrderItem> orderItems;

    public Order(int orderId, String username, double totalAmount, String transactionId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.username = username;
        this.totalAmount = totalAmount;
        this.transactionId = transactionId;
        this.orderItems = orderItems;
    }

 
    public int getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

   
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
