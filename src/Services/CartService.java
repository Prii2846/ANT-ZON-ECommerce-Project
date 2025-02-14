

package Services;
import dbRepository.CartRepository;
import dbRepository.ProductRepository;
import models.*;

import java.sql.SQLException;
import java.util.List;

public class CartService {
    private static CartService instance;
    private final CartRepository cartRepository;
    private static ProductRepository productRepository;

    private CartService() {
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
    }

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public boolean addToCart(String username, int productId, int quantity, Platform platform) {
        Product product = productRepository.getProductById(productId, platform.getPlatformId());
        if (product == null) {
            System.out.println("Product not found.");
            return false;
        }

        if (product.getStock() < quantity) {
            System.out.println("Product is out of stock.");
            return false;
        }

        CartItem cartItem = new CartItem(0, username, productId, quantity, product.getProductName(), product.getPrice());
        boolean isAdded = cartRepository.addCartItem(cartItem,platform);

        if (isAdded) {
            System.out.println("Product added to cart successfully.");
            return true;
        } else {
            System.out.println("Failed to add product to cart.");
            return false;
        }
    }

    public void viewCart(String username, Platform platform) {
        List<CartItem> cartItems = cartRepository.getCartItemsByUsername(username, platform.getPlatformId());
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Cart Items:");
            for (CartItem item : cartItems) {
                System.out.println("Product ID: " + item.getProductId() +
                                   " | Product: " + item.getProductName() +
                                   " | Quantity: " + item.getQuantity() +
                                   " | Price: $" + item.getPrice());
            }
        }
    }

    public boolean removeFromCart(int productId,String username,Platform platform) {
        boolean isRemoved = cartRepository.removeCartItem(productId,username,platform);
        if (isRemoved) {
            System.out.println("Item removed from cart successfully.");
        } else {
            System.out.println("Failed to remove item from cart.");
        }
        return isRemoved;
    }
  
    public static void viewAllProducts(Platform platform) throws SQLException {
        List<Product> products = productRepository.getAllProducts(platform);
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\nAvailable Products:");

            System.out.printf("%-5s %-20s %-15s %-15s %-20s %-10s %-10s %-15s\n", 
            "ID", "Product Name", "Main Category", "Sub Category", 
            "Product Type", "Stock", "Price", "Seller Username");
            System.out.println("-------------------------------------------------------------------------------------------"
             + "---------------------------------------------------");
            for (Product product : products) {
                // System.out.println(product);
                System.out.printf("%-5d %-20s %-15s %-15s %-20s %-10d %-10.2f %-15s\n", 
                product.getId(), product.getProductName(), product.getMainCategory(), 
                product.getSubCategory(), product.getProductType(), 
                product.getStock(), product.getPrice(), product.getSellerUsername());
            }
        }
    }
    

}

