
package View;
import Utils.Validator;
import Constant.*;
import View.SellerView;
import Services.SellerService;
import Services.UserService;
import java.sql.SQLException;
import java.util.List;
import dbRepository.*;
import models.Platform;


public class ShowLoginMenu {
    private static final View.InputScanner input = View.InputScanner.getInstance();
    private static Platform selectedPlatform=null;

    public static void showPlatformMenu() throws SQLException {
        while (true) {
           System.out.println(Printer.showPlatformMenu());
           int choice = input.readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> selectedPlatform = new Platform(1, "Amazon");
                case 2 -> selectedPlatform = new Platform(2, "Flipkart");
                case 3 -> selectedPlatform = new Platform(3, "ANT-ZON");
                case 4 -> {
                    Printer.printExitMessage();
                    System.exit(0);
                }
                default -> {
                    Printer.printInvalidChoice();
                    continue;
                }
            }
            showLoginMenu();
        }
    }

    public static void showLoginMenu() throws SQLException {
        while (true) {
            System.out.println(Printer.printMainMenu());
            int choice = input.readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> handleAdminMenu();
                case 2 -> handleSellerMenu();
                case 3 -> handleBuyerMenu();
                case 4 -> showPlatformMenu();
                case 5 -> {
                    Printer.printExitMessage();  System.exit(0);
                    break;
                }
                   
                default -> Printer.printInvalidChoice();
            }
        }
    }

    private static void handleAdminMenu() throws SQLException {
        System.out.println(Printer.printAdminMenu());
        int choice = input.readInt("Enter your choice for Admin: ");
        switch (choice) {
            case 1 -> registerUser("Admin");
            case 2 -> 
            {
                String adminUsername = loginUser("Admin");
                if (adminUsername != null) {
                Platform platform = ShowLoginMenu.getSelectedPlatform();
                    AdminView adminView = new AdminView(platform);
                    adminView.showAdminMenu(adminUsername);
                }
               
            }
            case 3 -> showLoginMenu();
            case 4 -> {Printer.printExitMessage(); System.exit(0);
            break;
            }
            default -> Printer.printInvalidChoice();
        }
    }


    private static void handleSellerMenu() throws SQLException {
        System.out.println(Printer.printSellerMenu()); 
        int choice = input.readInt("Enter your choice for Seller: ");
        switch (choice) {
            case 1 -> 
               registerUser("Seller");
            case 2 -> {
                String sellerUsername = loginUser("Seller");
                if (sellerUsername != null) {
                    Platform platform = ShowLoginMenu.getSelectedPlatform();
                    SellerView sellerView = new SellerView(platform);
                    sellerView.showSellerMenu(sellerUsername,platform);
                     
                }
            }
            case 3 -> showLoginMenu();
            case 4 ->{Printer.printExitMessage();  System.exit(0);
            break;
            }
            default -> Printer.printInvalidChoice();
        }
    }
      

    private static void handleBuyerMenu() throws SQLException {
        System.out.println(Printer.printBuyerMenu());
        int choice = input.readInt("Enter your choice for Buyer: ");
        switch (choice) {
            case 1 -> 
                 registerUser("Buyer");
            case 2 -> {
                String buyerUsername = loginUser("Buyer");
                if (buyerUsername != null) {
                    Platform platform = ShowLoginMenu.getSelectedPlatform();
                    BuyerView buyerView = new BuyerView(platform); 
                    buyerView.showBuyerMenu(buyerUsername,platform);
                }
            }
            case 3 -> {
                showLoginMenu();
            }  
            case 4 ->{ Printer.printExitMessage(); System.exit(0);
            break;
            }
            default -> Printer.printInvalidChoice();
        }
    }
    public static Platform getSelectedPlatform() {
        return selectedPlatform;
    }
    

    private static String registerUser(String role) throws SQLException {
        Printer.printRegistrationHeader(role);
        Printer.printUsernameRules();

        String username;
        do {
            username = input.readString("Enter " + role + " Username: ");
            if (!Validator.isValidUsername(username)) {
                Printer.printInvalidUsername();
            }
        } while (!Validator.isValidUsername(username));

        Printer.printPasswordRules();

        String password;
        do {
            password = input.readString("Enter " + role + " Password: ");
            if (!Validator.isValidPassword(password)) {
                Printer.printInvalidPassword();
            }
        } while (!Validator.isValidPassword(password));

        Platform platform = ShowLoginMenu.getSelectedPlatform();
        String registeredUser = UserService.registerUser(username, password, role,platform);
        if (registeredUser != null) {
            Printer.printRegistrationSuccess(role);
            return registeredUser;
        }
        return null;
    }

    private static String loginUser(String role) throws SQLException {
        String username = input.readString("Enter " + role + " Username: ");
        String password = input.readString("Enter " + role + " Password: ");
        
        Platform platform = ShowLoginMenu.getSelectedPlatform();
        String authenticatedUser = UserService.loginUser(username, password, role,platform);
        if (authenticatedUser != null) {
            Printer.printLoginSuccess(role);
            return authenticatedUser;
        } else {
            Printer.printInvalidLogin(role);
            return null;
        }
    }
}
