package models;

public class Product {
    private int id;
    private String productName;
    private String mainCategory;
    private String subCategory;
    private String productType;
    private int stock;
    private double price;
    private String sellerUsername;

    public Product(int id,String productName, String mainCategory, String subCategory, 
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



    public Product(String productType2, int stock2) {
        //TODO Auto-generated constructor stub
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    public int getStock(){
        return stock;

    }
    public void setStock(int stock){
        this.stock = stock;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", productName=" + productName + ", mainCategory=" + mainCategory
                + ", subCategory=" + subCategory + ", productType=" + productType + ", stock=" + stock + ", price="
                + price + ", sellerUsername=" + sellerUsername + "]";
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
