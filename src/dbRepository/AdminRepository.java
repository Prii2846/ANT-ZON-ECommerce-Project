package dbRepository;
import java.sql.*;
import java.util.*;
import Constant.SqlQueries;
import database.*;
import models.*;

public class AdminRepository {

    private final Connection connection;

    // Constructor to establish database connection
    public AdminRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
    }

    /**
     * Get the total revenue for the specified platform.
     * 
     * @param platform The platform for which revenue is calculated.
     * @return Total revenue as a double.
     * @throws SQLException If any database error occurs.
     */
    public double getTotalRevenue(Platform platform) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.TOTAL_REVENUE_QUERY)) {
            stmt.setInt(1, platform.getPlatformId());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0.0;
            }
        }
    }

    /**
     * Get revenue by main category for the specified platform.
     * 
     * @param platform The platform for which revenue is retrieved.
     * @return A map of category names and their corresponding revenues.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get revenue by subcategory for the specified platform.
     * 
     * @param platform The platform for which revenue is retrieved.
     * @return A map of subcategory names and their corresponding revenues.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get the list of best-selling products for the specified platform.
     * 
     * @param platform The platform for which best-selling products are retrieved.
     * @return A list of best-selling product details.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get the list of most liked products for the specified platform.
     * 
     * @param platform The platform for which most liked products are retrieved.
     * @return A list of most liked product details.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get the list of top-selling sellers for the specified platform.
     * 
     * @param platform The platform for which top-selling sellers are retrieved.
     * @return A list of top-selling seller details.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get the list of all users in the system.
     * 
     * @return A list of all users.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get the list of low-stock products for the specified platform.
     * 
     * @param platform The platform for which low-stock products are retrieved.
     * @return A list of low-stock product details.
     * @throws SQLException If any database error occurs.
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

    /**
     * Get the list of out-of-stock products for the specified platform.
     * 
     * @param platform The platform for which out-of-stock products are retrieved.
     * @return A list of out-of-stock product details.
     * @throws SQLException If any database error occurs.
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
