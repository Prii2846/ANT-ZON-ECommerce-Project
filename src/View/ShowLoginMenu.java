package View;
import controller.BuyerController;
import controller.UserController;
import Utils.InputScanner;
import Constant.Printer;
import models.Platform;
import java.sql.SQLException;

public class ShowLoginMenu {
    // Singleton instance of InputScanner to handle user input
    private static final InputScanner input = InputScanner.getInstance();

    // Variable to store the selected platform (Amazon, Flipkart, ANT-ZON, etc.)
    private static Platform selectedPlatform = null;

    // Displays the platform selection menu and allows the user to choose a platform
    public static void showPlatformMenu() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println(Printer.showPlatformMenu());  // Show available platforms
            int choice = input.readInt("Enter your choice: ");  // Read user input for choice
            switch (choice) {
                case 1 -> selectedPlatform = new Platform(1, "Amazon");  // Set selected platform to Amazon
                case 2 -> selectedPlatform = new Platform(2, "Flipkart"); // Set selected platform to Flipkart
                case 3 -> selectedPlatform = new Platform(3, "ANT-ZON");  // Set selected platform to ANT-ZON
                case 4 -> {  // Exit option
                    Printer.printExitMessage();  // Print exit message
                    System.exit(0);  // Terminate the application
                }
                default -> Printer.printInvalidChoice();  // Invalid choice, ask again
            }
            showLoginMenu();  // After selecting a platform, show the login menu
        }
    }

    // Displays the login menu where the user can choose to login as Admin, Seller, or Buyer
    public static void showLoginMenu() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println(Printer.printMainMenu());  // Show the main menu options
            int choice = input.readInt("Enter your choice: ");  // Read user input for choice
            switch (choice) {
                case 1 -> handleAdminMenu();  // Handle admin login and registration
                case 2 -> handleSellerMenu(); // Handle seller login and registration
                case 3 -> handleBuyerMenu();  // Handle buyer login and registration
                case 4 -> showPlatformMenu();  // Go back to the platform selection menu
                case 5 -> {  // Exit option
                    Printer.printExitMessage();  // Print exit message
                    System.exit(0);  // Terminate the application
                }
                default -> Printer.printInvalidChoice();  // Invalid choice, ask again
            }
        }
    }

    // Handles the admin menu options for registration, login, and navigation
    private static void handleAdminMenu() throws SQLException, ClassNotFoundException {
        System.out.println(Printer.printAdminMenu());  // Show admin-specific menu options
        int choice = input.readInt("Enter your choice for Admin: ");  // Read admin choice
        switch (choice) {
            case 1 -> UserController.registerUser("Admin", selectedPlatform);  // Admin registration
            case 2 -> {  // Admin login
                String adminUsername = UserController.loginUser("Admin", selectedPlatform);
                if (adminUsername != null) {  // If login is successful
                    AdminView adminView = new AdminView(selectedPlatform);  // Create Admin view object
                    adminView.showAdminMenu(adminUsername);  // Show admin menu
                }
            }
            case 3 -> showLoginMenu();  // Go back to the login menu
            case 4 -> {  // Exit option
                Printer.printExitMessage();  // Print exit message
                System.exit(0);  // Terminate the application
            }
            default -> Printer.printInvalidChoice();  // Invalid choice, ask again
        }
    }

    // Handles the seller menu options for registration, login, and navigation
    private static void handleSellerMenu() throws SQLException, ClassNotFoundException {
        System.out.println(Printer.printSellerMenu());  // Show seller-specific menu options
        int choice = input.readInt("Enter your choice for Seller: ");  // Read seller choice
        switch (choice) {
            case 1 -> UserController.registerUser("Seller", selectedPlatform);  // Seller registration
            case 2 -> {  // Seller login
                String sellerUsername = UserController.loginUser("Seller", selectedPlatform);
                if (sellerUsername != null) {  // If login is successful
                    SellerView sellerView = new SellerView(selectedPlatform);  // Create Seller view object
                    sellerView.showSellerMenu(sellerUsername);  // Show seller menu
                }
            }
            case 3 -> showLoginMenu();  // Go back to the login menu
            case 4 -> {  // Exit option
                Printer.printExitMessage();  // Print exit message
                System.exit(0);  // Terminate the application
            }
            default -> Printer.printInvalidChoice();  // Invalid choice, ask again
        }
    }

    // Handles the buyer menu options for registration, login, and navigation
    private static void handleBuyerMenu() throws SQLException, ClassNotFoundException {
        System.out.println(Printer.printBuyerMenu());  // Show buyer-specific menu options
        int choice = input.readInt("Enter your choice for Buyer: ");  // Read buyer choice
        switch (choice) {
            case 1 -> UserController.registerUser("Buyer", selectedPlatform);  // Buyer registration
            case 2 -> {  // Buyer login
                String buyerUsername = UserController.loginUser("Buyer", selectedPlatform);
                if (buyerUsername != null) {  // If login is successful
                    BuyerController buyerController = new BuyerController();  // Create Buyer controller
                    // Pass BuyerController to BuyerView
                    BuyerView buyerView = new BuyerView(buyerController);
                    // Show buyer menu
                    buyerView.showBuyerMenu(buyerUsername, selectedPlatform);
                }
            }
            case 3 -> showLoginMenu();  // Go back to the login menu
            case 4 -> {  // Exit option
                Printer.printExitMessage();  // Print exit message
                System.exit(0);  // Terminate the application
            }
            default -> Printer.printInvalidChoice();  // Invalid choice, ask again
        }
    }

    // Getter method for the selected platform
    public static Platform getSelectedPlatform() {
        return selectedPlatform;
    }
}
