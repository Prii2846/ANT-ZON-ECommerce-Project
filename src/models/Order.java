package models;

import java.util.List;

/*
*******************************************************************************************************
*   @Class Name         : Order
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents an order in the eCommerce platform, containing details 
*                         such as order ID, username, total amount, transaction ID, and order items.
*******************************************************************************************************
*/
public class Order {

    private int orderId;
    private String username;
    private double totalAmount;
    private String transactionId;
    private List<OrderItem> orderItems;
     /*
    *********************************************************
    *  @Constructor    : Order
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes an Order with provided details.
    *  @param          : int orderId - Unique order identifier
    *                  : String username - Customer username
    *                  : double totalAmount - Total order amount
    *                  : String transactionId - Unique transaction ID
    *                  : List<OrderItem> orderItems - List of ordered items
    *********************************************************
    */
    public Order(int orderId, String username, double totalAmount, String transactionId, List<OrderItem> orderItems) {
        this.orderId = orderId;                  
        this.username = username;                 
        this.totalAmount = totalAmount;          
        this.transactionId = transactionId;      
        this.orderItems = orderItems;            
    }

   /*
    *********************************************************
    *  @Method Name    : getOrderId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the order ID.
    *  @return         : int - Returns the unique order ID.
    *********************************************************
    */
    public int getOrderId() {
        return orderId;
    }

      /*
    *********************************************************
    *  @Method Name    : getUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the username of the customer who placed the order.
    *  @return         : String - Returns the username.
    *********************************************************
    */
    public String getUsername() {
        return username;
    }

     /*
    *********************************************************
    *  @Method Name    : getTotalAmount
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the total amount of the order.
    *  @return         : double - Returns the total order amount.
    *********************************************************
    */
    public double getTotalAmount() {
        return totalAmount;
    }
 /*
    *********************************************************
    *  @Method Name    : getTransactionId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the unique transaction ID of the order.
    *  @return         : String - Returns the transaction ID.
    *********************************************************
    */
    
    public String getTransactionId() {
        return transactionId;
    }

      /*
    *********************************************************
    *  @Method Name    : getOrderItems
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the list of order items.
    *  @return         : List<OrderItem> - Returns the list of ordered items.
    *********************************************************
    */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /*
    *********************************************************
    *  @Method Name    : setOrderId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the order ID.
    *  @param          : int orderId - The unique order ID to be set.
    *********************************************************
    */
    public void setOrderId(int orderId) {
        this.orderId = orderId;  
    }
/*
    *********************************************************
    *  @Method Name    : setUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the username of the customer.
    *  @param          : String username - The username to be set.
    *********************************************************
    */
   
    public void setUsername(String username) {
        this.username = username;  
    }

     /*
    *********************************************************
    *  @Method Name    : setTotalAmount
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the total amount for the order.
    *  @param          : double totalAmount - The total amount to be set.
    *********************************************************
    */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount; 
    }

  /*
    *********************************************************
    *  @Method Name    : setTransactionId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the transaction ID for the order.
    *  @param          : String transactionId - The transaction ID to be set.
    *********************************************************
    */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId; 
    }

     /*
    *********************************************************
    *  @Method Name    : setOrderItems
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the list of order items.
    *  @param          : List<OrderItem> orderItems - The list of order items to be set.
    *********************************************************
    */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems; 
    }
}
