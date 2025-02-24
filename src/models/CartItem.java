package models;

/*
*******************************************************************************************************
*   @Class Name         : CartItem
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents a cart item in the eCommerce platform, storing details 
*                         such as cart ID, username, product ID, quantity, product name, and price.
*******************************************************************************************************
*/
public class CartItem {
    private int cartId;
    private String username;
    private int productId;
    private int quantity;
    private String productName;
    private double price;


    /*
    *********************************************************
    *  @Constructor    : CartItem
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a CartItem with provided details.
    *  @param          : int cartId - Unique cart item identifier
    *                  : String username - User who added the item
    *                  : int productId - Product ID of the item
    *                  : int quantity - Quantity of the product
    *                  : String productName - Name of the product
    *                  : double price - Price of the product
    *********************************************************
    */
    public CartItem(int cartId, String username, int productId, int quantity, String productName, double price) {
        this.cartId = cartId;                   
        this.username = username;              
        this.productId = productId;             
        this.quantity = quantity;               
        this.productName = productName;       
        this.price = price;                     
    }

  /*
    *********************************************************
    *  @Method Name    : getCartId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the cart item ID.
    *  @return         : int - Returns the unique cart ID.
    *********************************************************
    */
    public int getCartId() {
        return cartId;
    }

  /*
    *********************************************************
    *  @Method Name    : getUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the username of the user who added the item to the cart.
    *  @return         : String - Returns the username.
    *********************************************************
    */
    public String getUsername() {
        return username;
    }
  /*
    *********************************************************
    *  @Method Name    : getProductId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product ID of the cart item.
    *  @return         : int - Returns the product ID.
    *********************************************************
    */
   
    public int getProductId() {
        return productId;
    }

    /*
    *********************************************************
    *  @Method Name    : getQuantity
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the quantity of the product in the cart.
    *  @return         : int - Returns the quantity.
    *********************************************************
    */
    public int getQuantity() {
        return quantity;
    }
 /*
    *********************************************************
    *  @Method Name    : getProductName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the name of the product in the cart.
    *  @return         : String - Returns the product name.
    *********************************************************
    */
    public String getProductName() {
        return productName;
    }


    /*
    *********************************************************
    *  @Method Name    : getPrice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the price of the product in the cart.
    *  @return         : double - Returns the price of the product.
    *********************************************************
    */
    public double getPrice() {
        return price;
    }
}
