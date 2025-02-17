package View;
import controller.AdminController;
import Utils.InputScanner;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import Constant.Printer;
import models.*;

public class AdminView {
    private static final InputScanner input = InputScanner.getInstance();
    private AdminController adminController;
    private final Platform platform;
    
    public AdminView(Platform platform) {
        this.platform = platform;
        try {
            this.adminController = new AdminController();
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions here
            e.printStackTrace(); // Or log the error as needed
        }
    }
    
     /**
     * Displays the main menu for admin functionalities and handles user choices.
          * @throws ClassNotFoundException 
         */
         public void showAdminMenu(String adminUsername) throws SQLException, ClassNotFoundException {
        while (true) {
            Printer.printAdminFunctionalities();
            int choice = input.readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> viewRevenueStatistics();
                case 2 -> viewSellerBuyerStatistics();
                case 3 -> viewAllUsers();
                case 4 -> monitorInventory();
                case 5 -> {
                    ShowLoginMenu.showLoginMenu();
                    return;
                }
                case 6 -> {
                    Printer.printExitMessage();
                    System.exit(0);
                }
                default -> Printer.printInvalidChoice();
            }
        }
    }
    /**
     * Retrieves and displays revenue statistics, including total revenue, revenue by category,
     * and revenue by subcategory.
          * @throws SQLException 
          */
         private void viewRevenueStatistics() throws SQLException {
        double totalRevenue = adminController.getTotalRevenue(platform);
        Map<String, Double> revenueByCategory = adminController.getRevenueByCategory(platform);
        Map<String, Double> revenueBySubcategory = adminController.getRevenueBySubcategory(platform);
        Printer.printRevenueStatistics(totalRevenue, revenueByCategory, revenueBySubcategory);
    }
     /**
     * Displays statistics on seller and buyer activity, including best-selling products,
     * most liked products, and top sellers.
          * @throws SQLException 
          */
         private void viewSellerBuyerStatistics() throws SQLException {
        List<String> bestSellingProducts = adminController.getBestSellingProducts(platform);
        List<String> mostLikedProducts = adminController.getMostLikedProducts(platform);
        List<String> topSellers = adminController.getTopSellingSellers(platform);
        Printer.printBestSellingProducts(bestSellingProducts);
        Printer.printMostLikedProducts(mostLikedProducts);
        Printer.printTopSellingSellers(topSellers);
    }
    
    /**
     * Retrieves and displays all registered users.
          * @throws SQLException 
          */
         private void viewAllUsers() throws SQLException {
        List<User> users = adminController.getAllUsers();
        Printer.printAllUsers(users);
    }
    
    /**
     * Monitors inventory by retrieving and displaying low-stock and out-of-stock products.
          * @throws SQLException 
         */
         private void monitorInventory() throws SQLException {
        List<Product> lowStockProducts = adminController.getLowStockProducts(platform);
        List<Product> outOfStockProducts = adminController.getOutOfStockProducts(platform);
        Printer.printLowStockProducts(lowStockProducts);
        Printer.printOutOfStockProducts(outOfStockProducts);
    }
}
