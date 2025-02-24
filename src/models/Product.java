package models;


/*
*******************************************************************************************************
*   @Class Name         : Product
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents a product in the eCommerce system with details like 
*                         name, category, stock, price, and seller information.
*******************************************************************************************************
*/
public class Product {
  
    private int id;
    private String productName;
    private String mainCategory;
    private String subCategory;
    private String productType;
    private int stock;
    private double price;
    private String sellerUsername;

    /*
    *********************************************************
    *  @Constructor    : Product
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a Product with all necessary details.
    *  @param          : int id - Unique product identifier
    *                  : String productName - Name of the product
    *                  : String mainCategory - Main category of the product
    *                  : String subCategory - Subcategory of the product
    *                  : String productType - Type of the product
    *                  : int stock - Stock quantity of the product
    *                  : double price - Price of the product
    *                  : String sellerUsername - Seller associated with the product
    *********************************************************
    */
    public Product(int id, String productName, String mainCategory, String subCategory, 
                   String productType, int stock, double price, String sellerUsername) {
        this.id = id;                               
        this.productName = productName;           
        this.mainCategory = mainCategory;           
        this.subCategory = subCategory;            
        this.productType = productType;           
        this.stock = stock;                         
        this.price = price;                        
        this.sellerUsername = sellerUsername;     
    }

     /*
    *********************************************************
    *  @Constructor    : Product
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a Product with product type and stock only.
    *  @param          : String productType - Type of the product
    *                  : int stock - Stock quantity of the product
    *********************************************************
    */
    public Product(String productType, int stock) {
        this.productType = productType;            
        this.stock = stock;                         
    }

  /*
    *********************************************************
    *  @Method Name    : getId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product ID.
    *  @return         : int - Returns the unique product ID.
    *********************************************************
    */
    public int getId() {
        return id;
    }

     /*
    *********************************************************
    *  @Method Name    : setId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the product ID.
    *  @param          : int id - The unique product ID to be set.
    *********************************************************
    */
    public void setId(int id) {
        this.id = id;  
    }

    /*
    *********************************************************
    *  @Method Name    : getProductName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product name.
    *  @return         : String - Returns the name of the product.
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
    *  @param          : String productName - The name of the product to be set.
    *********************************************************
    */
    public void setProductName(String productName) {
        this.productName = productName; 
    }

   /*
    *********************************************************
    *  @Method Name    : getMainCategory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the main category of the product.
    *  @return         : String - Returns the main category.
    *********************************************************
    */
    public String getMainCategory() {
        return mainCategory;
    }

  
   /*
    *********************************************************
    *  @Method Name    : setMainCategory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the main category of the product.
    *  @param          : String mainCategory - The main category to be set.
    *********************************************************
    */
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;  
    }
/*
    *********************************************************
    *  @Method Name    : getSubCategory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the sub category of the product.
    *  @return         : String - Returns the sub category.
    *********************************************************
    */
    public String getSubCategory() {
        return subCategory;
    }
 /*
    *********************************************************
    *  @Method Name    : setSubCategory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the sub category of the product.
    *  @param          : String subCategory - The sub category to be set.
    *********************************************************
    */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;  
    }

    /*
    *********************************************************
    *  @Method Name    : getProductType
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the product type.
    *  @return         : String - Returns the type of the product.
    *********************************************************
    */
    public String getProductType() {
        return productType;
    }

   /*
    *********************************************************
    *  @Method Name    : setProductType
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the product type.
    *  @param          : String productType - The type of the product to be set.
    *********************************************************
    */
    public void setProductType(String productType) {
        this.productType = productType; 
    }

    /*
    *********************************************************
    *  @Method Name    : getStock
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the stock quantity of the product.
    *  @return         : int - Returns the stock quantity.
    *********************************************************
    */
    public int getStock() {
        return stock;
    }

     /*
    *********************************************************
    *  @Method Name    : setStock
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the stock quantity of the product.
    *  @param          : int stock - The stock quantity to be set.
    *********************************************************
    */
    public void setStock(int stock) {
        this.stock = stock; 
    }

    /*
    *********************************************************
    *  @Method Name    : getPrice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the price of the product.
    *  @return         : double - Returns the price.
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
    *  @Description    : set the price of the product.
    *  @return         : double - the price to be set.
    *********************************************************
    */

    public void setPrice(double price) {
        this.price = price; 
    }


    public String getSellerUsername() {
        return sellerUsername;
    }


    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;  
    }

    /*
    *********************************************************
    *  @Method Name    : toString
    *  @Description    : Returns a string representation of the product.
    *  @return         : String - Formatted product details.
    *********************************************************
    */ 
    @Override
    public String toString() {
        return "Product [id=" + id + ", productName=" + productName + ", mainCategory=" + mainCategory
                + ", subCategory=" + subCategory + ", productType=" + productType + ", stock=" + stock + ", price="
                + price + ", sellerUsername=" + sellerUsername + "]";
    }
}
