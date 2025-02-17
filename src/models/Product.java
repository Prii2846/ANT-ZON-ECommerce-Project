package models;

// Represents a product in the eCommerce system
public class Product {
    // Unique identifier for the product
    private int id;

    // The name of the product
    private String productName;

    // The main category of the product (e.g., Electronics, Furniture)
    private String mainCategory;

    // The subcategory of the product (e.g., Mobile, Laptops)
    private String subCategory;

    // The specific product type (e.g., Smartphone_Android, Gaming_Laptop)
    private String productType;

    // The stock quantity of the product
    private int stock;

    // The price of the product
    private double price;

    // The username of the seller who listed the product
    private String sellerUsername;

    // Constructor to initialize all fields of the product
    public Product(int id, String productName, String mainCategory, String subCategory, 
                   String productType, int stock, double price, String sellerUsername) {
        this.id = id;                               // Set the product ID
        this.productName = productName;             // Set the product name
        this.mainCategory = mainCategory;           // Set the main category
        this.subCategory = subCategory;             // Set the subcategory
        this.productType = productType;             // Set the product type
        this.stock = stock;                         // Set the stock quantity
        this.price = price;                         // Set the product price
        this.sellerUsername = sellerUsername;       // Set the seller's username
    }

    // Overloaded constructor for simplified product initialization
    public Product(String productType, int stock) {
        this.productType = productType;             // Set the product type
        this.stock = stock;                         // Set the stock quantity
    }

    // Getter for product ID
    public int getId() {
        return id;
    }

    // Setter for product ID
    public void setId(int id) {
        this.id = id;  // Update the product ID
    }

    // Getter for product name
    public String getProductName() {
        return productName;
    }

    // Setter for product name
    public void setProductName(String productName) {
        this.productName = productName;  // Update the product name
    }

    // Getter for main category
    public String getMainCategory() {
        return mainCategory;
    }

    // Setter for main category
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;  // Update the main category
    }

    // Getter for subcategory
    public String getSubCategory() {
        return subCategory;
    }

    // Setter for subcategory
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;  // Update the subcategory
    }

    // Getter for product type
    public String getProductType() {
        return productType;
    }

    // Setter for product type
    public void setProductType(String productType) {
        this.productType = productType;  // Update the product type
    }

    // Getter for stock quantity
    public int getStock() {
        return stock;
    }

    // Setter for stock quantity
    public void setStock(int stock) {
        this.stock = stock;  // Update the stock quantity
    }

    // Getter for product price
    public double getPrice() {
        return price;
    }

    // Setter for product price
    public void setPrice(double price) {
        this.price = price;  // Update the product price
    }

    // Getter for seller's username
    public String getSellerUsername() {
        return sellerUsername;
    }

    // Setter for seller's username
    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;  // Update the seller's username
    }

    // Overrides the default toString method to display product details
    @Override
    public String toString() {
        return "Product [id=" + id + ", productName=" + productName + ", mainCategory=" + mainCategory
                + ", subCategory=" + subCategory + ", productType=" + productType + ", stock=" + stock + ", price="
                + price + ", sellerUsername=" + sellerUsername + "]";
    }
}
