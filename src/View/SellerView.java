package View;
import controller.SellerController;
import models.Platform;
import Utils.InputScanner;
import Constant.Printer;
import java.sql.SQLException;

public class SellerView {
    // Singleton instance of InputScanner to handle user input
    private static final InputScanner inputScanner = InputScanner.getInstance();

    // Instance of SellerController to handle seller-related operations
    private SellerController sellerController;

    // The platform on which the seller is operating (Amazon, Flipkart, ANT-ZON, etc.)
    private final Platform platform;

    // Constructor to initialize the SellerView with the selected platform
      // Constructor to initialize the SellerView with the selected platform
    public SellerView(Platform platform) {
        this.platform = platform;
        // Catching exceptions during SellerController instantiation
        try {
            this.sellerController = new SellerController();
        } catch (SQLException | ClassNotFoundException e) {
            // Handle the exception (logging, user feedback, etc.)
            e.printStackTrace();  // You can log the error or handle it in another way
            // You may also choose to rethrow the exception or display a user-friendly message
        }
    }

    // Displays the seller's dashboard menu and handles their actions
    public void showSellerMenu(String sellerUsername) throws SQLException, ClassNotFoundException {
        while (true) {
            // Display the seller's dashboard with options
            Printer.printSellerDashboard(sellerUsername);
            // Read the user's choice from the menu
            int choice = inputScanner.readInt("Enter your choice: ");

            // Process the user's choice
            switch (choice) {
                case 1: // Option to add a new product
                    sellerController.addProduct(sellerUsername, platform);
                    break;
                case 2: // Option to view all products
                    sellerController.viewProducts(sellerUsername, platform);
                    break;
                case 3: // Option to update an existing product
                    sellerController.updateProduct(sellerUsername, platform);
                    break;
                case 4: // Option to delete a product
                    sellerController.deleteProduct(sellerUsername, platform);
                    break;
                case 5: // Option to view the best-selling products
                    sellerController.showBestSellingProducts(platform);
                    break;
                case 6: // Option to view the most liked products
                    sellerController.showMostLikedProducts(platform);
                    break;
                case 7: // Option to go back to the login menu
                    ShowLoginMenu.showLoginMenu();
                    break;
                case 8: // Option to exit the application
                    Printer.printExitMessage();
                    System.exit(0);
                    break;
                default: // Invalid choice handler
                    Printer.printInvalidChoice();
                    break;
            }
        }
    }
}
