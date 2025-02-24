package View;
import controller.SellerController;
import models.Platform;
import Utils.InputScanner;
import Constant.Printer;
import java.sql.SQLException;


/*
    *******************************************************************************************************
    *   @Class Name         : SellerView
    *   @Author             : Priyanka Kumari (Priyanka.Kumari@antrazal.com)
    *   @Company            : Antrazal
    *   @Date               : 22-02-2025
    *   @Description        : This class manages the Seller functionalities, allowing users to 
    *                         add, update, delete, and view products, as well as check sales statistics.
    *******************************************************************************************************
*/
public class SellerView {

    private static final InputScanner inputScanner = InputScanner.getInstance();
    private SellerController sellerController;
    private final Platform platform;

  /*
    *********************************************************
     *  @Constructor   : SellerView
     *  @Author        : Priyanka Kumari (Priyanka.Kumari@antrazal.com)
     *  @Company       : Antrazal
     *  @Description   : Initializes the SellerView with a Platform instance.
     *                  It also initializes the SellerController to handle operations.
     *  @param         : Platform platform
     *  @return        : N/A                   
    *********************************************************
    */
    public SellerView(Platform platform) {
        this.platform = platform;
        try {
            this.sellerController = new SellerController();
        } catch (SQLException | ClassNotFoundException e) {
           
            e.printStackTrace();
        }
    }

  /*
    *********************************************************
     *  @Method Name   : showSellerMenu
     *  @Author        : Priyanka Kumari (Priyanka.Kumari@antrazal.com)
     *  @Company       : Antrazal
     *  @Description   : Displays the Seller menu and processes user choices.
     *                  Allows the seller to manage products and view statistics.
     *  @param         : String sellerUsername
     *  @return        : N/A                   
    *********************************************************
    */
    public void showSellerMenu(String sellerUsername) throws SQLException, ClassNotFoundException {
        while (true) {
            
            Printer.printSellerDashboard(sellerUsername);
         
            int choice = inputScanner.readInt("Enter your choice: ");

           
            switch (choice) {
                case 1: 
                    sellerController.addProduct(sellerUsername, platform);
                    break;
                case 2: 
                    sellerController.viewProducts(sellerUsername, platform);
                    break;
                case 3: 
                    sellerController.updateProduct(sellerUsername, platform);
                    break;
                case 4: 
                    sellerController.deleteProduct(sellerUsername, platform);
                    break;
                case 5:
                    sellerController.showBestSellingProducts(platform);
                    break;
                case 6: 
                    sellerController.showMostLikedProducts(platform);
                    break;
                case 7: 
                    ShowLoginMenu.showLoginMenu();
                    break;
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
}
