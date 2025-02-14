
package dbRepository;

import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Services.*;
import Constant.SqlQueries;
import database.DbConnection;

public class ProductRepository {
   
    private Connection connection;

    public ProductRepository() {
        this.connection = DbConnection.getConnection();
    }
    public Product getProductById(int productId,int platformId) {
        
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_PRODUCT_BY_ID)) {
            statement.setInt(1, productId);
            statement.setInt(2, platformId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("product_name");
                String mainCategory = resultSet.getString("main_category");
                String subCategory = resultSet.getString("sub_category");
                String productType = resultSet.getString("product_type");
                int stock = resultSet.getInt("stock");
                double price = resultSet.getDouble("price");
                String sellerUsername = resultSet.getString("sellerUsername");

                return new Product(id, productName, mainCategory, subCategory, productType, stock, price,sellerUsername);
            }else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public boolean addProduct(Product product,Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.INSERT_PRODUCT,Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getMainCategory());
            pstmt.setString(3, product.getSubCategory());
            pstmt.setString(4, product.getProductType());
            pstmt.setInt(5, product.getStock());
            pstmt.setDouble(6, product.getPrice());
            pstmt.setString(7, product.getSellerUsername());
            pstmt.setInt(8, platform.getPlatformId());
            int rowAffected = pstmt.executeUpdate(); 

  
            try (ResultSet rs = pstmt.getGeneratedKeys()) { 
                if (rs.next()) {
                    int productId = rs.getInt(1); 
                    System.out.println(" Product added successfully with Product ID: " + productId);
                }
            }
            
            return rowAffected > 0; 
        } catch (SQLException e) {
            System.out.println(" Error adding product: " + e.getMessage());
        }
        return false;  
    }

    public List<Product> getProductsBySeller(String sellerUsername,Platform platform) {
      
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.SELECT_PRODUCTS_BY_SELLER)) {
            pstmt.setString(1, sellerUsername);
            pstmt.setInt(2, platform.getPlatformId());

           try(ResultSet rs = pstmt.executeQuery()){
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"), 
                    rs.getString("product_name"),
                    rs.getString("main_category"), 
                    rs.getString("sub_category"),
                    rs.getString("product_type"), 
                    rs.getInt("stock"),
                    rs.getDouble("price"),
                    rs.getString("sellerUsername")
                ));
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public boolean updateProduct(Product product,Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.UPDATE_PRODUCT)) {
            
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getMainCategory());
            pstmt.setString(3, product.getSubCategory());
            pstmt.setString(4, product.getProductType());
            pstmt.setInt(5, product.getStock());
            pstmt.setDouble(6, product.getPrice());
            pstmt.setInt(7, product.getId());
            pstmt.setInt(8, platform.getPlatformId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean deleteProduct(int id, String sellerUsername,Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.DELETE_PRODUCT)) {
            pstmt.setInt(1, platform.getPlatformId());
            pstmt.setInt(2, id);
            pstmt.setString(3, sellerUsername);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Product> getAllProducts(Platform platform) {
        List<Product> products = new ArrayList<>(); 
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.SELECT_ALL_PRODUCTS)) {
            pstmt.setInt(1, platform.getPlatformId());
            try(ResultSet rs = pstmt.executeQuery()){
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"), 
                    rs.getString("product_name"),
                    rs.getString("main_category"), 
                    rs.getString("sub_category"),
                    rs.getString("product_type"), 
                    rs.getInt("stock"),
                    rs.getDouble("price"),
                    rs.getString("sellerUsername")
                ));
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public int getStockByProductId(int productId,int platformId) {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_STOCK_PRODUCT)) {
            stmt.setInt(1, platformId);
            stmt.setInt(2, productId);
            try(ResultSet rs = stmt.executeQuery()){
            if (rs.next()) {
                return rs.getInt("stock");
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}

