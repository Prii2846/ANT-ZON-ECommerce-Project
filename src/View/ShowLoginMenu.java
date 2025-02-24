package View;
import controller.BuyerController;
import controller.UserController;
import Utils.InputScanner;
import Constant.Printer;
import models.Platform;
import java.sql.SQLException;

/*
*******************************************************************************************************
*   @Class Name         : ShowLoginMenu
*   @Author             : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class provides the main login interface where users can select their platform
*                         and log in as Admin, Seller, or Buyer.
*******************************************************************************************************
*/
public class ShowLoginMenu {
   
    private static final InputScanner input = InputScanner.getInstance();


    private static Platform selectedPlatform = null;

  /*
    *********************************************************
    *  @Method Name    : showPlatformMenu
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Displays the platform selection menu where the user selects Amazon, Flipkart, or ANT-ZON.
    *  @param          : None
    *  @return         : void
    *********************************************************
    */
    public static void showPlatformMenu() throws SQLException, ClassNotFoundException {
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
                default -> Printer.printInvalidChoice(); 
            }
            showLoginMenu();  
        }
    }

  
    /*
    *********************************************************
    *  @Method Name    : showLoginMenu
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Displays the login menu and directs users to their respective login sections.
    *  @param          : None
    *  @return         : void
    *********************************************************
    */
    public static void showLoginMenu() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println(Printer.printMainMenu());  
            int choice = input.readInt("Enter your choice: ");  
            switch (choice) {
                case 1 -> handleAdminMenu();  
                case 2 -> handleSellerMenu(); 
                case 3 -> handleBuyerMenu();  
                case 4 -> showPlatformMenu();  
                case 5 -> { 
                    Printer.printExitMessage(); 
                    System.exit(0);  
                }
                default -> Printer.printInvalidChoice();  
            }
        }
    }
   /*
    *********************************************************
    *  @Method Name    : handleAdminMenu
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Handles the login and registration flow for Admin users.
    *  @param          : None
    *  @return         : void
    *********************************************************
    */
 
    private static void handleAdminMenu() throws SQLException, ClassNotFoundException {
        System.out.println(Printer.printAdminMenu());  
        int choice = input.readInt("Enter your choice for Admin: "); 
        switch (choice) {
            case 1 -> UserController.registerUser("Admin", selectedPlatform);  
            case 2 -> {  
                String adminUsername = UserController.loginUser("Admin", selectedPlatform);
                if (adminUsername != null) { 
                    AdminView adminView = new AdminView(selectedPlatform);  
                    adminView.showAdminMenu(adminUsername);  
                }
            }
            case 3 -> showLoginMenu();  
            case 4 -> { 
                Printer.printExitMessage();  
                System.exit(0);  
            }
            default -> Printer.printInvalidChoice();  
        }
    }
    /*
    *********************************************************
    *  @Method Name    : handleSellerMenu
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Handles the login and registration flow for Seller users.
    *  @param          : None
    *  @return         : void
    *********************************************************
    */
    private static void handleSellerMenu() throws SQLException, ClassNotFoundException {
        System.out.println(Printer.printSellerMenu());  
        int choice = input.readInt("Enter your choice for Seller: ");  
        switch (choice) {
            case 1 -> UserController.registerUser("Seller", selectedPlatform);  
            case 2 -> {  
                String sellerUsername = UserController.loginUser("Seller", selectedPlatform);
                if (sellerUsername != null) { 
                    SellerView sellerView = new SellerView(selectedPlatform);  
                    sellerView.showSellerMenu(sellerUsername); 
                }
            }
            case 3 -> showLoginMenu();  
            case 4 -> {  
                Printer.printExitMessage();  
                System.exit(0);  
            }
            default -> Printer.printInvalidChoice(); 
        }
    }

      /*
    *********************************************************
    *  @Method Name    : handleBuyerMenu
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Handles the login and registration flow for Buyer users.
    *  @param          : None
    *  @return         : void
    *********************************************************
    */
    private static void handleBuyerMenu() throws SQLException, ClassNotFoundException {
        System.out.println(Printer.printBuyerMenu()); 
        int choice = input.readInt("Enter your choice for Buyer: ");  
        switch (choice) {
            case 1 -> UserController.registerUser("Buyer", selectedPlatform);  
            case 2 -> {  
                String buyerUsername = UserController.loginUser("Buyer", selectedPlatform);
                if (buyerUsername != null) {  
                    BuyerController buyerController = new BuyerController();  
                    BuyerView buyerView = new BuyerView(buyerController);
                    buyerView.showBuyerMenu(buyerUsername, selectedPlatform);
                }
            }
            case 3 -> showLoginMenu();  
            case 4 -> {  
                Printer.printExitMessage();  
                System.exit(0);  
            }
            default -> Printer.printInvalidChoice();  
        }
    }
  /*
    *********************************************************
    *  @Method Name    : getSelectedPlatform
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Returns the currently selected platform.
    *  @param          : None
    *  @return         : Platform (selected platform)
    *********************************************************
    */
   
    public static Platform getSelectedPlatform() {
        return selectedPlatform;
    }
}
