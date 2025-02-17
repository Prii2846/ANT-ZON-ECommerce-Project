package View;
import Utils.InputScanner;
import Constant.Printer;
import controller.BuyerController;
import models.Platform;
import java.sql.SQLException;

public class BuyerView {
    private static final InputScanner input = InputScanner.getInstance();
    private final BuyerController buyerController;

    public BuyerView(BuyerController buyerController){
        this.buyerController = buyerController;
    }


    /**
     * Displays the Buyer menu and handles navigation.
     * @param buyerUsername The username of the logged-in buyer.
     * @param platform The platform being used (e.g., ANT-ZON).
     */
    public void showBuyerMenu(String buyerUsername, Platform platform) throws SQLException, ClassNotFoundException {
        while (true) {
            // Display the buyer options menu
            System.out.println(Printer.printBuyerOptions());

            // Read the user's choice
            int choice = input.readInt("Enter your choice: ");

            // Handle user's choice
            switch (choice) {
                case 1:
                    buyerController.viewAllProducts(platform); // View all products
                    break;
                case 2:
                    buyerController.handleCartMenu(buyerUsername, platform); // Manage Cart
                    break;
                case 3:
                    buyerController.handleWishlistMenu(buyerUsername, platform); // Manage Wishlist
                    break;
                case 4:
                    buyerController.handleOrderMenu(buyerUsername, platform); // Manage Orders
                    break;
                case 5:
                    buyerController.showBestSellingProducts(platform); // View Best-Selling Products
                    break;
                case 6:
                    buyerController.showMostLikedProducts(platform); // View Most-Liked Products
                    break;
                case 7:
                    ShowLoginMenu.showLoginMenu(); // Logout
                    return;
                case 8:
                    Printer.printExitMessage(); // Exit the system
                    System.exit(0);
                    break;
                default:
                    Printer.printInvalidChoice(); // Handle invalid input
                    break;
            }
        }
    }
}
