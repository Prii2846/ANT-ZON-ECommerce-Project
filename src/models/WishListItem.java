package models;
/*
*******************************************************************************************************
*   @Class Name         : WishListItem
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents a Wishlist item in the eCommerce platform.
*                         It contains details like username, product ID, product name, and price.
*******************************************************************************************************
*/

public class WishListItem {
    private String username;        
    private int productId;          
    private String productName;     
    private double productPrice;   

    /*
    *********************************************************
    *  @Constructor    : WishListItem
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a Wishlist item with user and product details.
    *  @param          : String username - Username of the user
    *                  : int productId - ID of the product
    *                  : String productName - Name of the product
    *                  : double productPrice - Price of the product
    *********************************************************
    */
    public WishListItem(String username, int productId, String productName, double productPrice) {
        this.username = username;             
        this.productId = productId;           
        this.productName = productName;       
        this.productPrice = productPrice;    
    }
  /*
    *********************************************************
    *  @Method Name    : getUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the username.
    *  @return         : String - Returns the username.
    *********************************************************
    */
  
    public String getUsername() {
        return username;
    }
  /*
    *********************************************************
    *  @Method Name    : setUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the username.
    *  @param          : String username - The username to be set.
    *********************************************************
    */
 
    public void setUsername(String username) {
        this.username = username;            
    }

    /*
    *********************************************************
    *  @Method Name    : getProductId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product ID.
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
    *  @Description    : Sets the product ID.
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
    *  @Description    : Retrieves the product name.
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
    *  @Description    : Sets the product name.
    *  @param          : String productName - The product name to be set.
    *********************************************************
    */
    public void setProductName(String productName) {
        this.productName = productName;     
    }


    /*
    *********************************************************
    *  @Method Name    : getProductPrice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product price.
    *  @return         : double - Returns the product price.
    *********************************************************
    */
    public double getProductPrice() {
        return productPrice;
    }
  /*
    *********************************************************
    *  @Method Name    : setProductPrice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the product price.
    *  @param          : double productPrice - The product price to be set.
    *********************************************************
    */
   
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;   
    }

 /*
    *********************************************************
    *  @Method Name    : toString
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Provides a string representation of the WishListItem object.
    *  @return         : String - Returns the Wishlist item details in string format.
    *********************************************************
    */
    @Override
    public String toString() {
        return "WishListItem [productId=" + productId + ", productName=" + productName 
                + ", productPrice=" + productPrice + "]";
    }
}
