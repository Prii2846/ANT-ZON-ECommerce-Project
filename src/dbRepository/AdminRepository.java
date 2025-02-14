package dbRepository;
import java.sql.*;
import java.util.*;
import Constant.SqlQueries;
import database.*;
import models.*;


public class AdminRepository {

    private final Connection connection;

    public AdminRepository(){
        this.connection = DbConnection.getConnection();
    }


    public double getTotalRevenue(Platform platform) {
        
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.TOTAL_REVENUE_QUERY)){
            stmt.setInt(1, platform.getPlatformId());
             try(ResultSet rs = stmt.executeQuery()){
            return rs.next() ? rs.getDouble(1) : 0.0;
        } 
    }catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public Map<String, Double> getRevenueByCategory(Platform platform) {
        Map<String, Double> revenueMap = new HashMap<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.REVENUE_BY_CATEGORY_QUERY)){
            stmt.setInt(1, platform.getPlatformId());
             try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    revenueMap.put(rs.getString("main_category"), rs.getDouble("revenue"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueMap;
    }

    public Map<String, Double> getRevenueBySubcategory(Platform platform) {
        Map<String, Double> revenueMap = new HashMap<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.REVENUE_BY_SUBCATEGORY_QUERY)){
            stmt.setInt(1, platform.getPlatformId());
             try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    revenueMap.put(rs.getString("sub_category"), rs.getDouble("revenue"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueMap;
    }

    public List<String> getBestSellingProducts(Platform platform) {
    List<String> products = new ArrayList<>();
    try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.BEST_SELLING_PRODUCTS_QUERY)){
        stmt.setInt(1, platform.getPlatformId());
         try(ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                products.add(rs.getString("product_type") + " - Sales: " + rs.getInt("total_sales"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return products;
}

public List<String> getMostLikedProducts(Platform platform) {
    List<String> products = new ArrayList<>();
    try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.MOST_LIKED_PRODUCTS_QUERY)){
        stmt.setInt(1, platform.getPlatformId());
         try(ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                products.add(rs.getString("product_type") + " - Likes: " + rs.getInt("likes"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return products;
}
public List<String> getTopSellingSellers(Platform platform) {
    List<String> sellers = new ArrayList<>();
    try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.TOP_SELLING_SELLERS_QUERY)){
        stmt.setInt(1, platform.getPlatformId());
         try(ResultSet rs = stmt.executeQuery()){
        while (rs.next()) {
            sellers.add("Seller: " + rs.getString("sellerUsername") + " - Sales: " + rs.getDouble("total_sales"));
        }
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return sellers;
}

public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.ALL_USERS_QUERY)){
         try(ResultSet rs = stmt.executeQuery()){
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("role")));
        }
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
}


public List<Product> getLowStockProducts(Platform platform) {
    List<Product> lowStockProducts = new ArrayList<>();

         try(PreparedStatement ps = connection.prepareStatement(SqlQueries.LOW_STOCK_PRODUCTS_QUERY)){
            ps.setInt(1, platform.getPlatformId());

        try(ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                String productType = rs.getString("product_type");
                int stock = rs.getInt("stock");

                System.out.println("Product: " + productType + ", Stock: " + stock); 

                lowStockProducts.add(new Product(productType, stock));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lowStockProducts;
}

public List<Product> getOutOfStockProducts(Platform platform) {
    List<Product> productList = new ArrayList<>();

  
         try(PreparedStatement pstmt = connection.prepareStatement(SqlQueries.OUT_OF_STOCK_PRODUCTS_QUERY)){
         pstmt.setInt(1, platform.getPlatformId());
         try(ResultSet rs = pstmt.executeQuery()){
        
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

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productList;
}



}

    

