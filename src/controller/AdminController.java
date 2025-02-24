package controller;
import Services.AdminService;
import models.*;

import java.sql.SQLException;
import java.util.*;
/*
*******************************************************************************************************
*   @Class Name         : AdminController
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class manages admin functionalities such as revenue tracking, 
*                         product statistics, user management, and inventory monitoring.
*******************************************************************************************************
*/
public class AdminController {
    private final AdminService adminService;
  /*
    *********************************************************
    *  @Constructor    : AdminController
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes the AdminService instance.
    *  @throws         : ClassNotFoundException, SQLException
    *********************************************************
    */
    public AdminController() throws ClassNotFoundException, SQLException {
        this.adminService = new AdminService();
    }
       /*
    *********************************************************
    *  @Method Name    : getTotalRevenue
    *  @Description    : Retrieves total revenue for the platform.
    *  @param          : Platform platform - The platform being used.
    *  @return         : double - Total revenue amount.
    *  @throws         : SQLException
    *********************************************************
    */
    public double getTotalRevenue(Platform platform) throws SQLException {
        return adminService.getTotalRevenue(platform);
    }
     /*
    *********************************************************
    *  @Method Name    : getRevenueByCategory
    *  @Description    : Retrieves revenue data categorized by main categories.
    *  @param          : Platform platform - The platform being used.
    *  @return         : Map<String, Double> - Revenue mapped by categories.
    *  @throws         : SQLException
    *********************************************************
    */
         public Map<String, Double> getRevenueByCategory(Platform platform) throws SQLException {
        return adminService.getRevenueByCategory(platform);
    }
     /*
    *********************************************************
    *  @Method Name    : getRevenueBySubcategory
    *  @Description    : Retrieves revenue data categorized by subcategories.
    *  @param          : Platform platform - The platform being used.
    *  @return         : Map<String, Double> - Revenue mapped by subcategories.
    *  @throws         : SQLException
    *********************************************************
    */
    
         public Map<String, Double> getRevenueBySubcategory(Platform platform) throws SQLException {
        return adminService.getRevenueBySubcategory(platform);
    }

    /*
    *********************************************************
    *  @Method Name    : getBestSellingProducts
    *  @Description    : Retrieves a list of best-selling products.
    *  @param          : Platform platform - The platform being used.
    *  @return         : List<String> - Best-selling product names.
    *  @throws         : SQLException
    *********************************************************
    */
         public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        return adminService.getBestSellingProducts(platform);
    }
 /*
    *********************************************************
    *  @Method Name    : getMostLikedProducts
    *  @Description    : Retrieves a list of most-liked products.
    *  @param          : Platform platform - The platform being used.
    *  @return         : List<String> - Most liked product names.
    *  @throws         : SQLException
    *********************************************************
    */
         public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        return adminService.getMostLikedProducts(platform);
    }
     /*
    *********************************************************
    *  @Method Name    : getTopSellingSellers
    *  @Description    : Retrieves a list of top-selling sellers.
    *  @param          : Platform platform - The platform being used.
    *  @return         : List<String> - Top-selling sellers.
    *  @throws         : SQLException
    *********************************************************
    */
         public List<String> getTopSellingSellers(Platform platform) throws SQLException {
        return adminService.getTopSellingSellers(platform);
    }
    /*
    *********************************************************
    *  @Method Name    : getAllUsers
    *  @Description    : Retrieves a list of all registered users.
    *  @return         : List<User> - List of users.
    *  @throws         : SQLException
    *********************************************************
    */
         public List<User> getAllUsers() throws SQLException {
        return adminService.getAllUsers();
    }
     /*
    *********************************************************
    *  @Method Name    : getLowStockProducts
    *  @Description    : Retrieves a list of products that are low in stock.
    *  @param          : Platform platform - The platform being used.
    *  @return         : List<Product> - Low-stock products.
    *  @throws         : SQLException
    *********************************************************
    */
         public List<Product> getLowStockProducts(Platform platform) throws SQLException {
        return adminService.getLowStockProducts(platform);
    }
     /*
    *********************************************************
    *  @Method Name    : getOutOfStockProducts
    *  @Description    : Retrieves a list of out-of-stock products.
    *  @param          : Platform platform - The platform being used.
    *  @return         : List<Product> - Out-of-stock products.
    *  @throws         : SQLException
    *********************************************************
    */
         public List<Product> getOutOfStockProducts(Platform platform) throws SQLException {
        return adminService.getOutOfStockProducts(platform);
    }
}

