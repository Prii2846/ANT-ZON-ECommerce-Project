package dbRepository;
import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;
import database.DbConnection;

// Repository class to manage product-related database operations
public class ProductRepository {

    private Connection connection; // Database connection object

    // Constructor to initialize the database connection
    public ProductRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection(); // Establish connection using DbConnection
    }

    // Retrieves a product by its ID and platform ID
    public Product getProductById(int productId, int platformId) {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.SELECT_PRODUCT_BY_ID)) {
            statement.setInt(1, productId);   // Set product ID in the query
            statement.setInt(2, platformId);  // Set platform ID in the query

            ResultSet resultSet = statement.executeQuery(); // Execute the query

            if (resultSet.next()) { // If a result is found
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("product_name");
                String mainCategory = resultSet.getString("main_category");
                String subCategory = resultSet.getString("sub_category");
                String productType = resultSet.getString("product_type");
                int stock = resultSet.getInt("stock");
                double price = resultSet.getDouble("price");
                String sellerUsername = resultSet.getString("sellerUsername");

                // Create and return a Product object
                return new Product(id, productName, mainCategory, subCategory, productType, stock, price, sellerUsername);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching product by ID: " + e.getMessage(), e);
        }
        return null; // Return null if product is not found
    }

    // Adds a new product to the database
    public boolean addProduct(Product product, Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getMainCategory());
            pstmt.setString(3, product.getSubCategory());
            pstmt.setString(4, product.getProductType());
            pstmt.setInt(5, product.getStock());
            pstmt.setDouble(6, product.getPrice());
            pstmt.setString(7, product.getSellerUsername());
            pstmt.setInt(8, platform.getPlatformId());

            int rowAffected = pstmt.executeUpdate(); // Execute the insert statement

            // Get the generated product ID
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int productId = rs.getInt(1);
                    System.out.println("Product added successfully with Product ID: " + productId);
                }
            }

            return rowAffected > 0; // Return true if the product was successfully added
        } catch (SQLException e) {
            throw new RuntimeException("Error adding product: " + e.getMessage(), e);
        }
    }

    // Retrieves all products for a specific seller and platform
    public List<Product> getProductsBySeller(String sellerUsername, Platform platform) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.SELECT_PRODUCTS_BY_SELLER)) {
            pstmt.setString(1, sellerUsername);  // Set seller username
            pstmt.setInt(2, platform.getPlatformId());  // Set platform ID

            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query
                while (rs.next()) { // Loop through each product
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
            throw new RuntimeException("Error fetching products by seller: " + e.getMessage(), e);
        }
        return products; // Return the list of products
    }

    // Updates an existing product in the database
    public boolean updateProduct(Product product, Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.UPDATE_PRODUCT)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getMainCategory());
            pstmt.setString(3, product.getSubCategory());
            pstmt.setString(4, product.getProductType());
            pstmt.setInt(5, product.getStock());
            pstmt.setDouble(6, product.getPrice());
            pstmt.setInt(7, product.getId());
            pstmt.setInt(8, platform.getPlatformId());

            return pstmt.executeUpdate() > 0; // Return true if the update was successful
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product: " + e.getMessage(), e);
        }
    }

    // Deletes a product by its ID, seller username, and platform
    public boolean deleteProduct(int id, String sellerUsername, Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.DELETE_PRODUCT)) {
            pstmt.setInt(1, id);  // Set platform ID
            pstmt.setString(2, sellerUsername);  // Set product ID
            pstmt.setInt(3, platform.getPlatformId());  // Set seller username

            return pstmt.executeUpdate() > 0; // Return true if the deletion was successful
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage(), e);
        }
    }

    // Retrieves all products for a specific platform
    public List<Product> getAllProducts(Platform platform) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.SELECT_ALL_PRODUCTS)) {
            pstmt.setInt(1, platform.getPlatformId()); // Set platform ID

            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query
                while (rs.next()) { // Loop through each product
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
            throw new RuntimeException("Error fetching all products: " + e.getMessage(), e);
        }
        return products; // Return the list of products
    }

    // Retrieves the stock quantity for a specific product and platform
    public int getStockByProductId(int productId, int platformId) {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_STOCK_PRODUCT)) {
            stmt.setInt(1, productId);  // Set platform ID
            stmt.setInt(2, platformId);   // Set product ID

            try (ResultSet rs = stmt.executeQuery()) { // Execute the query
                if (rs.next()) { // If stock is found
                    return rs.getInt("stock"); // Return the stock quantity
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching stock by product ID: " + e.getMessage(), e);
        }
        return 0; // Return 0 if no stock is found
    }
}
