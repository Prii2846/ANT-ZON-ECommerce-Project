package models;

public class WishListItem {
    private String username;
    private int productId;
    private String productName;  
    private double productPrice;

    public WishListItem(String username, int productId, String productName,double productPrice) {
        this.username = username;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;

    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "WishListItem [productId=" + productId + ", productName=" + productName + ", productPrice="
                + productPrice + "]";
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getProductPrice() {  
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
