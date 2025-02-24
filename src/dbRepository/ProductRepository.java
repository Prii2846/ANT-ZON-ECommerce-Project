package dbRepository;
import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;
import database.DbConnection;

/*
*******************************************************************************************************
*   @Class Name         : ProductRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles database operations related to products,
*                         including adding, retrieving, updating, and deleting product details.
*******************************************************************************************************
*/
public class ProductRepository {

    private Connection connection; 

   
    public ProductRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection(); 
    }

/*
    *********************************************************
    *  @Method Name    : getProductById
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a product by its ID.
    *  @param          : int productId - The ID of the product to retrieve.
    *  @return         : Product - Returns the product object if found, otherwise null.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
    public Product getProductById(int productId, int platformId) {
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

        
                return new Product(id, productName, mainCategory, subCategory, productType, stock, price, sellerUsername);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching product by ID: " + e.getMessage(), e);
        }
        return null; 
    }

  /*
    *********************************************************
    *  @Method Name    : addProduct
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Inserts a new product into the database.
    *  @param          : Product product - The product object to be added.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
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

            int rowAffected = pstmt.executeUpdate(); 

        
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int productId = rs.getInt(1);
                    System.out.println("Product added successfully with Product ID: " + productId);
                }
            }

            return rowAffected > 0; 
        } catch (SQLException e) {
            throw new RuntimeException("Error adding product: " + e.getMessage(), e);
        }
    }
 /*
    *********************************************************
    *  @Method Name    : getProductsBySeller
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of products added by a specific seller.
    *  @param          : String sellerUsername - Seller's username.
    *                    Platform platform - The platform from which products are fetched.
    *  @return         : List<Product> - Returns a list of products added by the seller.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
 
    public List<Product> getProductsBySeller(String sellerUsername, Platform platform) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.SELECT_PRODUCTS_BY_SELLER)) {
            pstmt.setString(1, sellerUsername); 
            pstmt.setInt(2, platform.getPlatformId());  

            try (ResultSet rs = pstmt.executeQuery()) { 
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
            throw new RuntimeException("Error fetching products by seller: " + e.getMessage(), e);
        }
        return products; 
    }
     /*
    *********************************************************
    *  @Method Name    : updateProduct
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Updates product details.
    *  @param          : Product product - The product object containing updated details.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */

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

            return pstmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product: " + e.getMessage(), e);
        }
    }

  /*
    *********************************************************
    *  @Method Name    : deleteProduct
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Deletes a product by its ID.
    *  @param          : int productId - The ID of the product to delete.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
    public boolean deleteProduct(int id, String sellerUsername, Platform platform) {
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.DELETE_PRODUCT)) {
            pstmt.setInt(1, id);  
            pstmt.setString(2, sellerUsername);  
            pstmt.setInt(3, platform.getPlatformId()); 

            return pstmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage(), e);
        }
    }

   /*
    *********************************************************
    *  @Method Name    : getAllProducts
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of all products.
    *  @return         : List<Product> - Returns a list of all product objects.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
    public List<Product> getAllProducts(Platform platform) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SqlQueries.SELECT_ALL_PRODUCTS)) {
            pstmt.setInt(1, platform.getPlatformId()); 

            try (ResultSet rs = pstmt.executeQuery()) { 
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
            throw new RuntimeException("Error fetching all products: " + e.getMessage(), e);
        }
        return products; 
    }

 /*
    *********************************************************
    *  @Method Name    : getStockByProductId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the available stock of a product based on its ID and platform ID.
    *  @param          : int productId - The ID of the product.
    *                    int platformId - The platform ID for the product.
    *  @return         : int - Returns the stock count of the product.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
    public int getStockByProductId(int productId, int platformId) {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_STOCK_PRODUCT)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, platformId);   

            try (ResultSet rs = stmt.executeQuery()) { 
                if (rs.next()) { 
                    return rs.getInt("stock");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching stock by product ID: " + e.getMessage(), e);
        }
        return 0; 
    }
}
