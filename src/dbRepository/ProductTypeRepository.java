package dbRepository;
import database.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Constant.SqlQueries;
import models.Platform;
import models.ProductType;

public class ProductTypeRepository {

   
    public static List<ProductType> getAllProductTypes() {
        List<ProductType> productTypes = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SqlQueries.SELECT_ALL_PRODUCT_TYPES)){
           
             try(ResultSet rs = stmt.executeQuery()){

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
        }
        return productTypes;
    }

    public static boolean addProductType(ProductType productType,Platform platform) {
    
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
