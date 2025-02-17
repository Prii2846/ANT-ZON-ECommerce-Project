package Services;
import dbRepository.CartRepository;
import dbRepository.ProductRepository;
import models.*;

import java.sql.SQLException;
import java.util.List;

public class CartService {
    private static CartService instance; // Singleton instance of CartService
    private final CartRepository cartRepository; // Repository to manage cart data
    private static ProductRepository productRepository; // Repository to manage product data

    // Private constructor for singleton pattern
    private CartService() throws ClassNotFoundException, SQLException {
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
    }

    // Get the singleton instance of CartService
    public static CartService getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new CartService(); // Create instance if not already created
        }
        return instance;
    }

    /**
     * Adds a product to the user's cart.
     *
     * @param username  The username of the buyer.
     * @param productId The ID of the product to add.
     * @param quantity  The quantity of the product to add.
     * @param platform  The eCommerce platform being used.
     * @throws SQLException if the product is not found or out of stock.
     */
    public boolean addToCart(String username, int productId, int quantity, Platform platform) throws SQLException {
        // Fetch the product from the repository by its ID and platform
        Product product = productRepository.getProductById(productId, platform.getPlatformId());
        if (product == null) { // Check if product exists
            throw new SQLException("Product not found.");
        }

        // Check if the requested quantity is available in stock
        if (product.getStock() < quantity) {
            throw new SQLException("Product is out of stock.");
        }

        // Create a CartItem object for the cart entry
        CartItem cartItem = new CartItem(0, username, productId, quantity, product.getProductName(), product.getPrice());

        // Add the CartItem to the cart using the repository
        boolean isAdded = cartRepository.addCartItem(cartItem, platform);

        if (!isAdded) {
            throw new SQLException("Failed to add product to cart.");
        }
        System.out.println("Cart item added successfully!");
        return true;
    }

    /**
     * Displays the contents of the user's cart.
     *
     * @param username The username of the buyer.
     * @param platform The eCommerce platform being used.
     * @throws SQLException If an error occurs while fetching cart items from the database.
     */
    public void viewCart(String username, Platform platform) throws SQLException {
        // Fetch all cart items for the given username and platform
        List<CartItem> cartItems = cartRepository.getCartItemsByUsername(username, platform.getPlatformId());

        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Cart Items:");
            // Loop through each cart item and display its details
            for (CartItem item : cartItems) {
                System.out.println("Product ID: " + item.getProductId() +
                                   " | Product: " + item.getProductName() +
                                   " | Quantity: " + item.getQuantity() +
                                   " | Price: $" + item.getPrice());
            }
        }
    }

    /**
     * Removes a product from the user's cart.
     *
     * @param productId The ID of the product to remove.
     * @param username  The username of the buyer.
     * @param platform  The eCommerce platform being used.
     * @throws SQLException if the item could not be removed.
     */
    public boolean removeFromCart(int productId, String username, Platform platform) throws SQLException {
        // Attempt to remove the item from the cart using the repository
        boolean isRemoved = cartRepository.removeCartItem(productId, username, platform);

        if (!isRemoved) {
            throw new SQLException("Failed to remove item from cart.");
        }
        System.out.println("Item is removed successfully");
        return true;
    }

    /**
     * Displays all available products on the platform.
     *
     * @param platform The eCommerce platform being used.
     * @throws SQLException If an error occurs while fetching products from the database.
     */
    public static void viewAllProducts(Platform platform) throws SQLException {
        // Fetch all products for the given platform
        List<Product> products = productRepository.getAllProducts(platform);

        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\nAvailable Products:");

            // Print table headers for product details
            System.out.printf("%-5s %-20s %-15s %-15s %-20s %-10s %-10s %-15s\n", 
                "ID", "Product Name", "Main Category", "Sub Category", 
                "Product Type", "Stock", "Price", "Seller Username");

            System.out.println("-------------------------------------------------------------------------------------------"
             + "---------------------------------------------------");

            // Loop through each product and display its details in tabular format
            for (Product product : products) {
                System.out.printf("%-5d %-20s %-15s %-15s %-20s %-10d %-10.2f %-15s\n", 
                    product.getId(), product.getProductName(), product.getMainCategory(), 
                    product.getSubCategory(), product.getProductType(), 
                    product.getStock(), product.getPrice(), product.getSellerUsername());
            }
        }
    }
}
