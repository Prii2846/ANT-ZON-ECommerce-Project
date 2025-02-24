package View;
import Utils.InputScanner;
import Constant.Printer;
import controller.BuyerController;
import models.Platform;
import java.sql.SQLException;

/*
    *******************************************************************************************************
    *   @Class Name         : BuyerView
    *   @Author             : Priyanka Kumari (Priyanka.Kumari@antrazal.com)
    *   @Company            : Antrazal
    *   @Date               : 22-02-2025
    *   @Description        : This class handles the Buyer functionalities, allowing users to 
    *                         browse products, manage their cart, wishlist, orders, and view statistics.
    *******************************************************************************************************
*/
public class BuyerView {
    private static final InputScanner input = InputScanner.getInstance();
    private final BuyerController buyerController;

    /*
    *********************************************************
     *  @Method Name    : BuyerView
     *  @Author         : Priyanka Kumari (Priyanka.Kumari@antrazal.com)
     *  @Company        : Antrazal
     *  @Description    : Constructor to initialize the BuyerController.
     *  @param          : BuyerController buyerController
     *  @return         : N/A                   
    *********************************************************
    */   
    public BuyerView(BuyerController buyerController){
        this.buyerController = buyerController;
    }

  /*
    *********************************************************
     *  @Method Name    : showBuyerMenu
     *  @Author         : Priyanka Kumari (Priyanka.Kumari@antrazal.com)
     *  @Company        : Antrazal
     *  @Description    : Displays the Buyer menu and handles user choices.
     *                    Allows navigation through Buyer functionalities.
     *  @param          : String buyerUsername, Platform platform
     *  @return         : N/A
    *********************************************************
    */
    
    public void showBuyerMenu(String buyerUsername, Platform platform) throws SQLException, ClassNotFoundException {
        while (true) {
           
            System.out.println(Printer.printBuyerOptions());

         
            int choice = input.readInt("Enter your choice: ");

         
            switch (choice) {
                case 1:
                    buyerController.viewAllProducts(platform); 
                    break;
                case 2:
                    buyerController.handleCartMenu(buyerUsername, platform); 
                    break;
                case 3:
                    buyerController.handleWishlistMenu(buyerUsername, platform);
                    break;
                case 4:
                    buyerController.handleOrderMenu(buyerUsername, platform); 
                    break;
                case 5:
                    buyerController.showBestSellingProducts(platform); 
                    break;
                case 6:
                    buyerController.showMostLikedProducts(platform); 
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
}
