package dbRepository;

import database.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Constant.SqlQueries;
import models.Platform;
import models.ProductType;
/*
*******************************************************************************************************
*   @Class Name         : ProductTypeRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles database operations related to product types,
*                         including retrieving and adding product type details.
*******************************************************************************************************
*/
public class ProductTypeRepository {

 /*
    *********************************************************
    *  @Method Name    : getAllProductTypes
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all product types available in the database.
    *  @return         : List<ProductType> - Returns a list of product types.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
    public static List<ProductType> getAllProductTypes() throws ClassNotFoundException {
        List<ProductType> productTypes = new ArrayList<>();

   
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SqlQueries.SELECT_ALL_PRODUCT_TYPES)) {

        
            try (ResultSet rs = stmt.executeQuery()) {
            
                while (rs.next()) {
                    String mainCategory = rs.getString("main_category");
                    String subCategory = rs.getString("sub_category");
                    String productType = rs.getString("product_type");
                    List<String> subCategoryTypesList = new ArrayList<>();
                
                    productTypes.add(new ProductType(mainCategory, subCategory, subCategoryTypesList));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw new RuntimeException("Error fetching product types", e); 
        }
        return productTypes; 
    }

      /*
    *********************************************************
    *  @Method Name    : addProductType
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a new product type to the database.
    *  @param          : ProductType productType - The product type details.
    *                    Platform platform - The platform for the product type.
    *  @return         : boolean - Returns true if insertion is successful, false otherwise.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
    public static boolean addProductType(ProductType productType, Platform platform) throws ClassNotFoundException {
   
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SqlQueries.INSERT_PRODUCT_TYPE)) {
            for (String type : productType.productType()) {
                stmt.setString(1, productType.mainCategory()); 
                stmt.setString(2, productType.subCategory());  
                stmt.setString(3, type);                      
                stmt.setInt(4, platform.getPlatformId());      
                
                stmt.addBatch();  
            }

         
            stmt.executeBatch();
            return true;  
        } catch (SQLException e) {
            e.printStackTrace();  
            return false;  
        }
    }
}
