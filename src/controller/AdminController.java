package controller;
import Services.AdminService;
import models.*;

import java.sql.SQLException;
import java.util.*;

public class AdminController {
    private final AdminService adminService;

    public AdminController() throws ClassNotFoundException, SQLException {
        this.adminService = new AdminService();
    }
     /**
     * Retrieves the total revenue from the AdminService.
     * @throws SQLException 
     */
    public double getTotalRevenue(Platform platform) throws SQLException {
        return adminService.getTotalRevenue(platform);
    }
    /**
     * Retrieves revenue by category from the AdminService.
          * @throws SQLException 
          */
         public Map<String, Double> getRevenueByCategory(Platform platform) throws SQLException {
        return adminService.getRevenueByCategory(platform);
    }
    
     /**
     * Retrieves revenue by subcategory from the AdminService.
          * @throws SQLException 
          */
         public Map<String, Double> getRevenueBySubcategory(Platform platform) throws SQLException {
        return adminService.getRevenueBySubcategory(platform);
    }

    /**
     * Retrieves a list of best-selling products from the AdminService.
          * @throws SQLException 
          */
         public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        return adminService.getBestSellingProducts(platform);
    }

    /**
     * Retrieves a list of the most liked products from the AdminService.
          * @throws SQLException 
          */
         public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        return adminService.getMostLikedProducts(platform);
    }
     /**
     * Retrieves a list of top-selling sellers from the AdminService.
          * @throws SQLException 
          */
         public List<String> getTopSellingSellers(Platform platform) throws SQLException {
        return adminService.getTopSellingSellers(platform);
    }
    /**
     * Retrieves a list of all users from the AdminService.
          * @throws SQLException 
         */
         public List<User> getAllUsers() throws SQLException {
        return adminService.getAllUsers();
    }
    /**
     * Retrieves a list of low-stock products from the AdminService.
          * @throws SQLException 
          */
         public List<Product> getLowStockProducts(Platform platform) throws SQLException {
        return adminService.getLowStockProducts(platform);
    }
    
    /**
     * Retrieves a list of out-of-stock products from the AdminService.
          * @throws SQLException 
          */
         public List<Product> getOutOfStockProducts(Platform platform) throws SQLException {
        return adminService.getOutOfStockProducts(platform);
    }
}

