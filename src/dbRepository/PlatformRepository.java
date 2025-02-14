package dbRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Constant.SqlQueries;
import database.DbConnection;
import models.Platform;

public class PlatformRepository {

    public void initializePlatformTable() {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement createStmt = connection.prepareStatement(SqlQueries.CREATE_PLATFORM_TABLE);
             PreparedStatement insertStmt = connection.prepareStatement(SqlQueries.INSERT_PLATFORMS)) {

            createStmt.executeUpdate();  
            insertStmt.executeUpdate();  

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

     public Platform getPlatformByName(String platformName) {
        String query = "SELECT platform_id, platform_name FROM Platforms WHERE platform_name = ?";
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, platformName);
            try(ResultSet rs = pstmt.executeQuery()){

            if (rs.next()) {
                return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
            }
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Platform getPlatformById(int platformId) {
        String query = "SELECT platform_id, platform_name FROM Platforms WHERE platform_id = ?";
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
    
            pstmt.setInt(1, platformId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
   
    }
