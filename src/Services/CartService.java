package Services;
import dbRepository.CartRepository;
import dbRepository.ProductRepository;
import models.*;

import java.sql.SQLException;
import java.util.List;

/*
*******************************************************************************************************
*   @Class Name         : CartService
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class provides functionalities for cart operations, 
*                         including adding, removing, viewing cart items, and handling checkout.
*******************************************************************************************************
*/
public class CartService {
    private static CartService instance; 
    private final CartRepository cartRepository; 
    private static ProductRepository productRepository; 

      /*
    *********************************************************
    *  @Constructor Name : CartService
    *  @Author          : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company         : Antrazal
    *  @Description     : Initializes the CartRepository instance for database operations.
    *  @param           : None
    *  @throws          : ClassNotFoundException, SQLException
    *********************************************************
    */
    private CartService() throws ClassNotFoundException, SQLException {
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
    }

  
    public static CartService getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new CartService(); 
        }
        return instance;
    }

      /*
    *********************************************************
    *  @Method Name    : addToCart
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a product to the cart.
    *  @param          : User user - The user adding the product
    *                  : Product product - The product to be added
    *  @return         : boolean - True if added successfully, otherwise false
    *  @throws         : SQLException
    *********************************************************
    */
    public boolean addToCart(String username, int productId, int quantity, Platform platform) throws SQLException {
       
        Product product = productRepository.getProductById(productId, platform.getPlatformId());
        if (product == null) { 
            throw new SQLException("Product not found.");
        }

       
        if (product.getStock() < quantity) {
            throw new SQLException("Product is out of stock.");
        }

        CartItem cartItem = new CartItem(0, username, productId, quantity, product.getProductName(), product.getPrice());

        boolean isAdded = cartRepository.addCartItem(cartItem, platform);

        if (!isAdded) {
            throw new SQLException("Failed to add product to cart.");
        }
        System.out.println("Cart item added successfully!");
        return true;
    }

    /*
    *********************************************************
    *  @Method Name    : viewCart
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the list of products in a user's cart.
    *  @param          : User user - The user whose cart is being viewed
    *  @return         : List<Product> - List of products in the cart
    *  @throws         : SQLException
    *********************************************************
    */
    public void viewCart(String username, Platform platform) throws SQLException {
       
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

     /*
    *********************************************************
    *  @Method Name    : removeFromCart
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Removes a product from the cart.
    *  @param          : User user - The user removing the product
    *                  : Product product - The product to be removed
    *  @return         : boolean - True if removed successfully, otherwise false
    *  @throws         : SQLException
    *********************************************************
    */
    public boolean removeFromCart(int productId, String username, Platform platform) throws SQLException {
       
        boolean isRemoved = cartRepository.removeCartItem(productId, username, platform);

        if (!isRemoved) {
            throw new SQLException("Failed to remove item from cart.");
        }
        System.out.println("Item is removed successfully");
        return true;
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
                System.out.printf("%-5d %-20s %-15s %-15s %-20s %-10d %-10.2f %-15s\n", 
                    product.getId(), product.getProductName(), product.getMainCategory(), 
                    product.getSubCategory(), product.getProductType(), 
                    product.getStock(), product.getPrice(), product.getSellerUsername());
            }
        }
    }
}
