
package View;
import Services.*;
import View.InputScanner;
import dbRepository.*;
import models.*;
import Constant.Printer;
import java.sql.SQLException;
import java.util.List;

public class BuyerView {
    private static final InputScanner input = InputScanner.getInstance();
    private static final CartService cartService = CartService.getInstance();
    private static final OrderService orderService = OrderService.getInstance();
    private static final WishlistService wishlistService = WishlistService.getInstance();
    private static final SellerService sellerService = new SellerService();
    private final Platform platform;

    public BuyerView(Platform platform){
        this.platform = platform;
    }

    

    public static void showBuyerMenu(String buyerUsername,Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printBuyerOptions());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewAllProducts(platform);
                    break;
                case 2:
                    handleCartMenu(buyerUsername,platform);
                    break;
                case 3:
                    handleWishlistMenu(buyerUsername,platform);
                    break;
                case 4:
                    handleOrderMenu(buyerUsername,platform);
                    break;
                case 5:
                    showBestSellingProducts(platform);
                    break;
                case 6:
                    showMostLikedProducts(platform);  
                    break;    
                    
                case 7:
                    ShowLoginMenu.showLoginMenu();
                    return;
                case 8:
                    Printer.printExitMessage();
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice();
                    break;
            }
        }
    }


    private static void viewAllProducts(Platform platform) throws SQLException {
        cartService.viewAllProducts(platform);
    }

    private static void handleCartMenu(String buyerUsername,Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printCartMenu());
            int choice = input.readInt("Enter your choice: ");


            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    int productId = input.readInt("Enter Product ID to add: ");
                    int quantity = input.readInt("Enter Quantity: ");
                    cartService.addToCart(buyerUsername, productId, quantity,platform);
                    break;
                case 2:
                    cartService.viewAllProducts(platform);
                    productId = input.readInt("Enter Product ID to remove: ");
                    cartService.removeFromCart(productId,buyerUsername,platform);
                    break;
                case 3:
                    cartService.viewCart(buyerUsername,platform);
                    break;
                case 4:
                    BuyerView.showBuyerMenu(buyerUsername,platform);
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

    private static void handleWishlistMenu(String buyerUsername,Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printWishlistMenu());
            int choice = input.readInt("Enter your choice: ");
    


            switch (choice) {
                case 1:
                     cartService.viewAllProducts(platform);
                    int productId = input.readInt("Enter Product ID to add: ");
                    wishlistService.addProductToWishlist(buyerUsername, productId,platform);
                    break;
                case 2:
                    cartService.viewAllProducts(platform);
                    int productIdToRemove = input.readInt("Enter Product ID to remove: ");
                    wishlistService.removeFromWishlist(buyerUsername, productIdToRemove,platform);
                    break;
                case 3:
                    List<WishListItem> wishlist = wishlistService.getWishlist(buyerUsername, platform);
                    if (wishlist.isEmpty()) {
                       Printer.printNoWishlistItems();
                    } 
                      else {
                       Printer.printWishlist();
                      for (WishListItem item : wishlist) {
                         Printer.printWishlistItem(item);
                       }
                    }
                    break;

                case 4:
                     BuyerView.showBuyerMenu(buyerUsername,platform);
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

    private static void handleOrderMenu(String buyerUsername,Platform platform) throws SQLException {
        while (true) {
            System.out.println(Printer.printOrderMenu());
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    cartService.viewAllProducts(platform);
                    orderService.placeOrder(buyerUsername,platform);
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
                    BuyerView.showBuyerMenu(buyerUsername,platform);
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

    public static void showBestSellingProducts(Platform platform) {
        List<String> bestSellingProducts = sellerService.getBestSellingProducts(platform);
        Printer.printBestSellingProducts(bestSellingProducts);
        
        
        }
        
        
    public static void showMostLikedProducts(Platform platform) {
        List<String> mostLikedProducts = sellerService.getMostLikedProducts(platform);
        Printer.printMostLikedProducts(mostLikedProducts);
        }
    

}







