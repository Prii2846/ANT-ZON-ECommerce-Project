package dbRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Constant.SqlQueries;
import database.DbConnection;
import models.Platform;
/*
*******************************************************************************************************
*   @Class Name         : PlatformRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles database operations related to platforms,
*                         including initializing platform data and retrieving platform details.
*******************************************************************************************************
*/
public class PlatformRepository {
  /*
    *********************************************************
    *  @Method Name    : initializePlatformTable
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes the platform table and inserts default platform records.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
    
    public void initializePlatformTable() throws ClassNotFoundException {
  
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement createStmt = connection.prepareStatement(SqlQueries.CREATE_PLATFORM_TABLE);
             PreparedStatement insertStmt = connection.prepareStatement(SqlQueries.INSERT_PLATFORMS)) {

            createStmt.executeUpdate();  
   
            insertStmt.executeUpdate();  

        } catch (SQLException e) {
            e.printStackTrace();  
            throw new RuntimeException("Error initializing platform table", e); 
        }
    }
/*
    *********************************************************
    *  @Method Name    : getPlatformByName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves platform details by platform name.
    *  @param          : String platformName - The name of the platform to retrieve.
    *  @return         : Platform - Returns the platform object if found, otherwise null.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
    public Platform getPlatformByName(String platformName) throws ClassNotFoundException {
   
        String query = "SELECT platform_id, platform_name FROM Platforms WHERE platform_name = ?";
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, platformName); 
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
               
                    return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  
            throw new RuntimeException("Error fetching platform by name", e); 
        }
        return null;  
    }

   /*
    *********************************************************
    *  @Method Name    : getPlatformById
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves platform details by platform id.
    *  @param          : String platformid - The id of the platform to retrieve.
    *  @return         : Platform - Returns the platform object if found, otherwise null.
    *  @throws         : ClassNotFoundException - If the database driver is not found.
    *********************************************************
    */
    public Platform getPlatformById(int platformId) throws ClassNotFoundException {
  
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
            throw new RuntimeException("Error fetching platform by ID", e); 
        }
        return null; 
    }
 
}
