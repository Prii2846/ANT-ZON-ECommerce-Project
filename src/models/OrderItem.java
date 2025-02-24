package models;
/*
*******************************************************************************************************
*   @Class Name         : OrderItem
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents an item in an order, containing details such as order ID,
*                         product ID, product name, quantity, and price.
*******************************************************************************************************
*/

public class OrderItem {
   
    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private double price;
   /*
    *********************************************************
    *  @Constructor    : OrderItem
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes an OrderItem with provided details.
    *  @param          : int orderId - Unique order identifier
    *                  : int productId - Unique product identifier
    *                  : String productName - Name of the product
    *                  : int quantity - Quantity of the product ordered
    *                  : double price - Price of the product
    *********************************************************
    */
   
    public OrderItem(int orderId, int productId, String productName, int quantity, double price) {
        this.orderId = orderId;               
        this.productId = productId;           
        this.productName = productName;       
        this.quantity = quantity;            
        this.price = price;                   
    }

     /*
    *********************************************************
    *  @Method Name    : getOrderId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the order ID associated with this item.
    *  @return         : int - Returns the order ID.
    *********************************************************
    */
    public int getOrderId() {
        return orderId; 
    }

   /*
    *********************************************************
    *  @Method Name    : setOrderId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the order ID associated with this item.
    *  @param          : int orderId - The order ID to be set.
    *********************************************************
    */
    public void setOrderId(int orderId) {
        this.orderId = orderId; 
    }

     /*
    *********************************************************
    *  @Method Name    : getProductId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product ID of this item.
    *  @return         : int - Returns the product ID.
    *********************************************************
    */
    public int getProductId() {
        return productId;  
    }

  /*
    *********************************************************
    *  @Method Name    : setProductId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the product ID of this item.
    *  @param          : int productId - The product ID to be set.
    *********************************************************
    */
    public void setProductId(int productId) {
        this.productId = productId;  
    }

    /*
    *********************************************************
    *  @Method Name    : getProductName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the name of the product.
    *  @return         : String - Returns the product name.
    *********************************************************
    */
    public String getProductName() {
        return productName;  
    }

       /*
    *********************************************************
    *  @Method Name    : setProductName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the name of the product.
    *  @param          : String productName - The product name to be set.
    *********************************************************
    */
    public void setProductName(String productName) {
        this.productName = productName;  
    }

     /*
    *********************************************************
    *  @Method Name    : getQuantity
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the quantity of the product ordered.
    *  @return         : int - Returns the ordered quantity.
    *********************************************************
    */
    public int getQuantity() {
        return quantity;  
    }

    /*
    *********************************************************
    *  @Method Name    : setQuantity
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the quantity of the product ordered.
    *  @param          : int quantity - The quantity to be set.
    *********************************************************
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity; 
    }

      /*
    *********************************************************
    *  @Method Name    : getPrice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the price of the product.
    *  @return         : double - Returns the price of the product.
    *********************************************************
    */
    public double getPrice() {
        return price;  
    }

      /*
    *********************************************************
    *  @Method Name    : setPrice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the price of the product.
    *  @param          : double price - The price to be set.
    *********************************************************
    */
    public void setPrice(double price) {
        this.price = price;  
    }

   
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
