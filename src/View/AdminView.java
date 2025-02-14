package View;
import Constant.Printer;
import Services.AdminService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import Services.*;
import Constant.Printer;
import models.*;

public class AdminView {
    private static final View.InputScanner input = View.InputScanner.getInstance();
    private final AdminService adminService = new AdminService();
    private final Platform platform;

    public AdminView(Platform platform){
        this.platform = platform;
    }

    public void showAdminMenu(String adminUsername) throws SQLException {
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
     private void viewRevenueStatistics() {
        double totalRevenue = adminService.getTotalRevenue(platform);
        Map<String, Double> revenueByCategory = adminService.getRevenueByCategory(platform);
        Map<String, Double> revenueBySubcategory = adminService.getRevenueBySubcategory(platform);
        Printer.printRevenueStatistics(totalRevenue, revenueByCategory, revenueBySubcategory);
    }

    private void viewSellerBuyerStatistics() {
        List<String> bestSellingProducts = adminService.getBestSellingProducts(platform);
        List<String> mostLikedProducts = adminService.getMostLikedProducts(platform);
        List<String> topSellers = adminService.getTopSellingSellers(platform);

        Printer.printBestSellingProducts(bestSellingProducts);
        Printer.printMostLikedProducts(mostLikedProducts);
        Printer.printTopSellingSellers(topSellers);
    }

    private void viewAllUsers() {
        List<User> users = adminService.getAllUsers();
        Printer.printAllUsers(users);
    }

    private void monitorInventory() {
        List<Product> lowStockProducts = adminService.getLowStockProducts(platform);
        List<Product> outOfStockProducts = adminService.getOutOfStockProducts(platform);

        Printer.printLowStockProducts(lowStockProducts);
        Printer.printOutOfStockProducts(outOfStockProducts);
    }
}
