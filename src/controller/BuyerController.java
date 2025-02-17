package controller;
import Services.*;
import Utils.InputScanner;
import models.*;
import Constant.Printer;
import java.sql.SQLException;
import java.util.List;

public class BuyerController {
    private final CartService cartService;
    private final OrderService orderService;
    private final WishlistService wishlistService;
    private final SellerService sellerService = new SellerService();
    private static final InputScanner input = InputScanner.getInstance();

        // Constructor that initializes the services and throws exceptions if needed
        public BuyerController() throws SQLException, ClassNotFoundException {
            this.cartService = CartService.getInstance();
            this.orderService = OrderService.getInstance();
            this.wishlistService = WishlistService.getInstance();
        }

    /**
     * Displays all products available in the platform.
     * @param platform The platform for product listing.
     */
    public void viewAllProducts(Platform platform) throws SQLException {
        cartService.viewAllProducts(platform);
    }

    /**
     * Handles the Cart menu for the buyer.
     * @param buyerUsername The username of the buyer.
     * @param platform The platform being used.
     */
    public void handleCartMenu(String buyerUsername, Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printCartMenu());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    int productId = input.readInt("Enter Product ID to add: ");
                    int quantity = input.readInt("Enter Quantity: ");
                    cartService.addToCart(buyerUsername, productId, quantity, platform); // Add product to cart
                    break;
                case 2:
                    cartService.viewAllProducts(platform);
                    productId = input.readInt("Enter Product ID to remove: ");
                    cartService.removeFromCart(productId, buyerUsername, platform); // Remove product from cart
                    break;
                case 3:
                    cartService.viewCart(buyerUsername, platform); // View items in the cart
                    break;
                case 4:
                    return; // Go back to previous menu
                case 5:
                    Printer.printExitMessage(); // Exit the system
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); // Handle invalid input
                    break;
            }
        }
    }

    /**
     * Handles the Wishlist menu for the buyer.
     * @param buyerUsername The username of the buyer.
     * @param platform The platform being used.
     */
    public void handleWishlistMenu(String buyerUsername, Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printWishlistMenu());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    int productId = input.readInt("Enter Product ID to add: ");
                    wishlistService.addProductToWishlist(buyerUsername, productId, platform); // Add product to wishlist
                    break;
                case 2:
                    cartService.viewAllProducts(platform);
                    int productIdToRemove = input.readInt("Enter Product ID to remove: ");
                    wishlistService.removeFromWishlist(buyerUsername, productIdToRemove, platform); // Remove product from wishlist
                    break;
                case 3:
                    List<WishListItem> wishlist = wishlistService.getWishlist(buyerUsername, platform);
                    if (wishlist.isEmpty()) {
                        Printer.printNoWishlistItems(); // No items in wishlist
                    } else {
                        Printer.printWishlist();
                        for (WishListItem item : wishlist) {
                            Printer.printWishlistItem(item); // Display wishlist items
                        }
                    }
                    break;
                case 4:
                    return; // Go back to previous menu
                case 5:
                    Printer.printExitMessage(); // Exit the system
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); // Handle invalid input
                    break;
            }
        }
    }

    /**
     * Handles the Order menu for the buyer.
     * @param buyerUsername The username of the buyer.
     * @param platform The platform being used.
          * @throws ClassNotFoundException 
          */
         public void handleOrderMenu(String buyerUsername, Platform platform) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println(Printer.printOrderMenu());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    orderService.placeOrder(buyerUsername, platform); // Place an order
                    break;
                case 2:
                    List<Order> orderHistory = orderService.viewOrderHistory(buyerUsername, platform);
                    if (orderHistory.isEmpty()) {
                        Printer.printNoOrderHistory(); // No orders found
                    } else {
                        Printer.printOrderHistoryHeader();
                        for (Order order : orderHistory) {
                            Printer.printOrder(order); // Display order history
                        }
                    }
                    break;
                case 3:
                    return; // Go back to previous menu
                case 4:
                    Printer.printExitMessage(); // Exit the system
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); // Handle invalid input
                    break;
            }
        }
    }

    /**
     * Displays best-selling products for the platform.
     * @param platform The platform being used.
          * @throws SQLException 
          */
         public void showBestSellingProducts(Platform platform) throws SQLException {
        List<String> bestSellingProducts = sellerService.getBestSellingProducts(platform);
        Printer.printBestSellingProducts(bestSellingProducts); // Display best-selling products
    }

    /**
     * Displays most-liked products for the platform.
     * @param platform The platform being used.
          * @throws SQLException 
          */
         public void showMostLikedProducts(Platform platform) throws SQLException {
        List<String> mostLikedProducts = sellerService.getMostLikedProducts(platform);
        Printer.printMostLikedProducts(mostLikedProducts); // Display most-liked products
    }
}
