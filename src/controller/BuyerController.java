package controller;
import Services.*;
import Utils.InputScanner;
import models.*;
import Constant.Printer;
import java.sql.SQLException;
import java.util.List;
/*
*******************************************************************************************************
*   @Class Name         : BuyerController
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class manages buyer functionalities such as viewing products, 
*                         managing cart, handling wishlist, and processing orders.
*******************************************************************************************************
*/
public class BuyerController {
    private final CartService cartService;
    private final OrderService orderService;
    private final WishlistService wishlistService;
    private final SellerService sellerService = new SellerService();
    private static final InputScanner input = InputScanner.getInstance();
   /*
    *********************************************************
    *  @Constructor    : BuyerController
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes service instances for cart, order, and wishlist management.
    *  @throws         : SQLException, ClassNotFoundException
    *********************************************************
    */
        public BuyerController() throws SQLException, ClassNotFoundException {
            this.cartService = CartService.getInstance();
            this.orderService = OrderService.getInstance();
            this.wishlistService = WishlistService.getInstance();
        }

   
    /*
    *********************************************************
    *  @Method Name    : viewAllProducts
    *  @Description    : Displays all available products on the platform.
    *  @param          : Platform platform - The platform being used.
    *  @throws         : SQLException
    *********************************************************
    */
    public void viewAllProducts(Platform platform) throws SQLException {
        cartService.viewAllProducts(platform);
    }
  /*
    *********************************************************
    *  @Method Name    : handleCartMenu
    *  @Description    : Manages the buyer's cart functionalities such as adding,
    *                    removing, and viewing cart items.
    *  @param          : String buyerUsername - The username of the buyer.
    *                    Platform platform - The platform being used.
    *  @throws         : SQLException
    *********************************************************
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
                    cartService.addToCart(buyerUsername, productId, quantity, platform); 
                    break;
                case 2:
                    cartService.viewAllProducts(platform);
                    productId = input.readInt("Enter Product ID to remove: ");
                    cartService.removeFromCart(productId, buyerUsername, platform); 
                    break;
                case 3:
                    cartService.viewCart(buyerUsername, platform); 
                    break;
                case 4:
                    return; 
                case 5:
                    Printer.printExitMessage(); 
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); 
                    break;
            }
        }
    }
 /*
    *********************************************************
    *  @Method Name    : handleWishlistMenu
    *  @Description    : Manages the wishlist functionalities such as adding,
    *                    removing, and viewing wishlist items.
    *  @param          : String buyerUsername - The username of the buyer.
    *                    Platform platform - The platform being used.
    *  @throws         : SQLException
    *********************************************************
    */
    public void handleWishlistMenu(String buyerUsername, Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printWishlistMenu());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    int productId = input.readInt("Enter Product ID to add: ");
                    wishlistService.addProductToWishlist(buyerUsername, productId, platform); 
                    break;
                case 2:
                    cartService.viewAllProducts(platform);
                    int productIdToRemove = input.readInt("Enter Product ID to remove: ");
                    wishlistService.removeFromWishlist(buyerUsername, productIdToRemove, platform); 
                    break;
                case 3:
                    List<WishListItem> wishlist = wishlistService.getWishlist(buyerUsername, platform);
                    if (wishlist.isEmpty()) {
                        Printer.printNoWishlistItems(); 
                    } else {
                        Printer.printWishlist();
                        for (WishListItem item : wishlist) {
                            Printer.printWishlistItem(item); 
                        }
                    }
                    break;
                case 4:
                    return;
                case 5:
                    Printer.printExitMessage(); 
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); 
                    break;
            }
        }
    }
 /*
    *********************************************************
    *  @Method Name    : handleOrderMenu
    *  @Description    : Manages order functionalities such as placing an order
    *                    and viewing order history.
    *  @param          : String buyerUsername - The username of the buyer.
    *                    Platform platform - The platform being used.
    *  @throws         : SQLException, ClassNotFoundException
    *********************************************************
    */
         public void handleOrderMenu(String buyerUsername, Platform platform) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println(Printer.printOrderMenu());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    orderService.placeOrder(buyerUsername, platform); 
                    break;
                case 2:
                    List<Order> orderHistory = orderService.viewOrderHistory(buyerUsername, platform);
                    if (orderHistory.isEmpty()) {
                        Printer.printNoOrderHistory(); 
                    } else {
                        Printer.printOrderHistoryHeader();
                        for (Order order : orderHistory) {
                            Printer.printOrder(order); 
                        }
                    }
                    break;
                case 3:
                    return; 
                case 4:
                    Printer.printExitMessage(); 
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); 
                    break;
            }
        }
    }
/*
    *********************************************************
    *  @Method Name    : showBestSellingProducts
    *  @Description    : Retrieves and displays the best-selling products from the seller service.
    *  @param          : Platform platform - The platform being used.
    *  @throws         : SQLException
    *********************************************************
    */
   
         public void showBestSellingProducts(Platform platform) throws SQLException {
        List<String> bestSellingProducts = sellerService.getBestSellingProducts(platform);
        Printer.printBestSellingProducts(bestSellingProducts);
    }

       /*
    *********************************************************
    *  @Method Name    : showMostLikedProducts
    *  @Description    : Retrieves and displays the most liked products from the seller service.
    *  @param          : Platform platform - The platform being used.
    *  @throws         : SQLException
    *********************************************************
    */
         public void showMostLikedProducts(Platform platform) throws SQLException {
        List<String> mostLikedProducts = sellerService.getMostLikedProducts(platform);
        Printer.printMostLikedProducts(mostLikedProducts); 
    }
}
