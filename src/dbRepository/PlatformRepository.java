package dbRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Constant.SqlQueries;
import database.DbConnection;
import models.Platform;

public class PlatformRepository {

    // Method to initialize the platform table in the database by creating and inserting required records
    public void initializePlatformTable() throws ClassNotFoundException {
        // Using try-with-resources to automatically close the resources (connection, statements)
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement createStmt = connection.prepareStatement(SqlQueries.CREATE_PLATFORM_TABLE);
             PreparedStatement insertStmt = connection.prepareStatement(SqlQueries.INSERT_PLATFORMS)) {

            // Execute the query to create the platform table if it doesn't exist
            createStmt.executeUpdate();  
            // Insert predefined platform records into the table
            insertStmt.executeUpdate();  

        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace for debugging purposes
            throw new RuntimeException("Error initializing platform table", e); // Rethrow the exception wrapped in RuntimeException
        }
    }

    // Method to retrieve a platform by its name
    public Platform getPlatformByName(String platformName) throws ClassNotFoundException {
        // SQL query to fetch platform details based on the platform name
        String query = "SELECT platform_id, platform_name FROM Platforms WHERE platform_name = ?";
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, platformName); // Set the platform name parameter in the query
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    // If platform exists, return a new Platform object with fetched data
                    return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging purposes
            throw new RuntimeException("Error fetching platform by name", e); // Rethrow the exception wrapped in RuntimeException
        }
        return null;  // Return null if no platform is found with the given name
    }

    // Method to retrieve a platform by its ID
    public Platform getPlatformById(int platformId) throws ClassNotFoundException {
        // SQL query to fetch platform details based on platform ID
        String query = "SELECT platform_id, platform_name FROM Platforms WHERE platform_id = ?";
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, platformId); // Set the platform ID parameter in the query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // If platform exists, return a new Platform object with fetched data
                    return new Platform(rs.getInt("platform_id"), rs.getString("platform_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace for debugging purposes
            throw new RuntimeException("Error fetching platform by ID", e); // Rethrow the exception wrapped in RuntimeException
        }
        return null;  // Return null if no platform is found with the given ID
    }
}
