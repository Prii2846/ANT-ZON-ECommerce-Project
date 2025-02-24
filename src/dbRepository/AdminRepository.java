package dbRepository;
import java.sql.*;
import java.util.*;
import Constant.SqlQueries;
import database.*;
import models.*;
/*
*******************************************************************************************************
*   @Class Name         : AdminRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles admin-related database operations, including revenue 
*                         statistics, user management, and product inventory tracking.
*******************************************************************************************************
*/

public class AdminRepository {

    private final Connection connection;

  /*
    *********************************************************
    *  @Constructor    : AdminRepository
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Establishes a database connection for admin operations.
    *********************************************************
    */
    public AdminRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
    }
 /*
    *********************************************************
    *  @Method Name    : getTotalRevenue
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the total revenue for the specified platform.
    *  @param          : Platform platform - The platform for which revenue is calculated.
    *  @return         : double - Returns the total revenue amount.
    *********************************************************
    */
   
    public double getTotalRevenue(Platform platform) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.TOTAL_REVENUE_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0.0;
            }
        }
    }
 /*
    *********************************************************
    *  @Method Name    : getRevenueByCategory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves revenue statistics grouped by main category.
    *  @param          : Platform platform - The platform for which revenue is calculated.
    *  @return         : Map<String, Double> - Returns a map with category names as keys and revenue as values.
    *********************************************************
    */
  
    public Map<String, Double> getRevenueByCategory(Platform platform) throws SQLException {
        Map<String, Double> revenueMap = new HashMap<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.REVENUE_BY_CATEGORY_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    revenueMap.put(rs.getString("main_category"), rs.getDouble("revenue"));
                }
            }
        }
        return revenueMap;
    }
 /*
    *********************************************************
    *  @Method Name    : getRevenueBySubcategory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves revenue statistics grouped by subcategory.
    *  @param          : Platform platform - The platform for which revenue is calculated.
    *  @return         : Map<String, Double> - Returns a map with subcategory names as keys and revenue as values.
    *********************************************************
    */
 
    public Map<String, Double> getRevenueBySubcategory(Platform platform) throws SQLException {
        Map<String, Double> revenueMap = new HashMap<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.REVENUE_BY_SUBCATEGORY_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    revenueMap.put(rs.getString("sub_category"), rs.getDouble("revenue"));
                }
            }
        }
        return revenueMap;
    }
/*
    *********************************************************
    *  @Method Name    : getBestSellingProducts
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of best-selling products based on sales volume.
    *  @param          : Platform platform - The platform for which data is fetched.
    *  @return         : List<String> - Returns a list of best-selling product details.
    *********************************************************
    */
  
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        List<String> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.BEST_SELLING_PRODUCTS_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(rs.getString("product_type") + " - Sales: " + rs.getInt("total_sales"));
                }
            }
        }
        return products;
    }
/*
    *********************************************************
    *  @Method Name    : getMostLikedProducts
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of most liked products based on user likes.
    *  @param          : Platform platform - The platform for which data is fetched.
    *  @return         : List<String> - Returns a list of most liked product details.
    *********************************************************
    */
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        List<String> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.MOST_LIKED_PRODUCTS_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(rs.getString("product_type") + " - Likes: " + rs.getInt("likes"));
                }
            }
        }
        return products;
    }

   /*
    *********************************************************
    *  @Method Name    : getTopSellingSellers
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of top-selling sellers.
    *  @param          : Platform platform - The platform for which data is fetched.
    *  @return         : List<String> - List of top-selling sellers.
    *********************************************************
    */
    public List<String> getTopSellingSellers(Platform platform) throws SQLException {
        List<String> sellers = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.TOP_SELLING_SELLERS_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sellers.add("Seller: " + rs.getString("sellerUsername") + " - Sales: " + rs.getDouble("total_sales"));
                }
            }
        }
        return sellers;
    }
  /*
    *********************************************************
    *  @Method Name    : getAllUsers
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all users from the database.
    *  @return         : List<User> - List of users.
    *********************************************************
    */
   
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.ALL_USERS_QUERY)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("role")));
                }
            }
        }
        return users;
    }
  /*
    *********************************************************
    *  @Method Name    : getLowStockProducts
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves products that are running low on stock.
    *  @param          : Platform platform - The platform for which data is fetched.
    *  @return         : List<Product> - Returns a list of low-stock products.
    *********************************************************
    */
    public List<Product> getLowStockProducts(Platform platform) throws SQLException {
        List<Product> lowStockProducts = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SqlQueries.LOW_STOCK_PRODUCTS_QUERY)) {
            ps.setInt(1, platform.getPlatformId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String productType = rs.getString("product_type");
                    int stock = rs.getInt("stock");
                    lowStockProducts.add(new Product(productType, stock));
                }
            }
        }
        return lowStockProducts;
    }

      /*
    *********************************************************
    *  @Method Name    : getOutOfStockProducts
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves products that are completely out of stock.
    *  @param          : Platform platform - The platform for which data is fetched.
    *  @return         : List<Product> - Returns a list of out-of-stock products.
    *********************************************************
    */
    public List<Product> getOutOfStockProducts(Platform platform) throws SQLException {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.OUT_OF_STOCK_PRODUCTS_QUERY)) {
            pstmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("main_category"),
                        rs.getString("sub_category"),
                        rs.getString("product_type"),
                        rs.getInt("stock"),
                        rs.getDouble("price"),
                        rs.getString("sellerUsername")
                    );
                    productList.add(product);
                }
            }
        }
        return productList;
    }
}
