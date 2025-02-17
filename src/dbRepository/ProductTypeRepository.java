package dbRepository;

import database.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Constant.SqlQueries;
import models.Platform;
import models.ProductType;

public class ProductTypeRepository {

    // Method to retrieve all product types from the database
    public static List<ProductType> getAllProductTypes() throws ClassNotFoundException {
        List<ProductType> productTypes = new ArrayList<>();

        // Establish connection to the database and prepare the SQL query
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SqlQueries.SELECT_ALL_PRODUCT_TYPES)) {

            // Execute the query and fetch the result set
            try (ResultSet rs = stmt.executeQuery()) {
                // Iterate over the result set and populate the list of product types
                while (rs.next()) {
                    String mainCategory = rs.getString("main_category");
                    String subCategory = rs.getString("sub_category");
                    String productType = rs.getString("product_type");
                    List<String> subCategoryTypesList = new ArrayList<>();
                    // Add the new ProductType to the list (subCategoryTypesList can be populated later)
                    productTypes.add(new ProductType(mainCategory, subCategory, subCategoryTypesList));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging purposes
            throw new RuntimeException("Error fetching product types", e); // Rethrow the exception wrapped in RuntimeException
        }
        return productTypes;  // Return the list of all product types
    }

    // Method to add a new product type to the database for a given platform
    public static boolean addProductType(ProductType productType, Platform platform) throws ClassNotFoundException {
        // SQL query to insert new product type into the database
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SqlQueries.INSERT_PRODUCT_TYPE)) {

            // Iterate through each product type in the list for the given ProductType object
            for (String type : productType.productType()) {
                stmt.setString(1, productType.mainCategory());  // Set the main category in the query
                stmt.setString(2, productType.subCategory());   // Set the sub category in the query
                stmt.setString(3, type);                        // Set the product type in the query
                stmt.setInt(4, platform.getPlatformId());       // Set the platform ID in the query
                
                stmt.addBatch();  // Add to the batch for efficient execution
            }

            // Execute the batch operation to insert all the product types
            stmt.executeBatch();
            return true;  // Return true if the operation was successful
        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging purposes
            return false;  // Return false if the operation failed
        }
    }
}
