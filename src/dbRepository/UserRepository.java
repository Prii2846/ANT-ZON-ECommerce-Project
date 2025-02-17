package dbRepository;

import database.DbConnection;
import java.sql.*;
import java.util.*;

import Constant.SqlQueries;
import models.*;

public class UserRepository {
    PlatformRepository platformRepo = new PlatformRepository();

    // Method to register a new user
    public static String registerUser(User user) throws ClassNotFoundException {
        // Check if the username already exists for the platform
        if (isUsernameExists(user.getUsername(), user.getPlatformId())) {
            // Return null if the username exists
            return null;
        }

        // Try to insert the new user into the database
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getPlatformId());

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected > 0) {
                // Return the username if user registration is successful
                return user.getUsername();
            }

        } catch (SQLException e) {
            // Throw a runtime exception in case of SQL errors
            throw new RuntimeException("Error during registration: " + e.getMessage(), e);
        }
        return null;  // Return null if the registration fails
    }

    // Method to check if the username already exists for a specific platform
    public static boolean isUsernameExists(String username, int platformId) throws ClassNotFoundException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.CHECK_USERNAME_EXISTS)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, platformId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Return true if the username exists
            }

        } catch (SQLException e) {
            // Throw a runtime exception if an error occurs while checking username
            throw new RuntimeException("Error checking username: " + e.getMessage(), e);
        }
    }

    // Method to authenticate a user based on username, password, and role
    public static String authenticate(String username, String password, String role, int platformId) throws ClassNotFoundException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.AUTHENTICATE_USER)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setInt(4, platformId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username"); // Return the username if authentication is successful
                }
            }

        } catch (SQLException e) {
            // Throw a runtime exception if an error occurs during authentication
            throw new RuntimeException("Error during authentication: " + e.getMessage(), e);
        }
        return null;  // Return null if authentication fails
    }

    // Method to retrieve usernames by role for a specific platform
    public static List<String> getUsernamesByRole(String role, int platformId) throws ClassNotFoundException {
        List<String> usernames = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.GET_USERS_BY_ROLE)) {

            pstmt.setString(1, role);
            pstmt.setInt(2, platformId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    usernames.add(rs.getString("username"));  // Add usernames to the list
                }
            }

        } catch (SQLException e) {
            // Throw a runtime exception if an error occurs while fetching usernames
            throw new RuntimeException("Error fetching usernames: " + e.getMessage(), e);
        }
        return usernames;  // Return the list of usernames
    }

    // Method to fetch all users for a specific platform
    public void getAllUsersForPlatform(int platformId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_USER)) {

            stmt.setInt(1, platformId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Print the username and role of each user for the platform
                    System.out.println("User: " + rs.getString("username") + " | Role: " + rs.getString("role"));
                }
            }

        } catch (SQLException e) {
            // Throw a runtime exception if an error occurs while fetching users
            throw new RuntimeException("Error fetching users: " + e.getMessage(), e);
        }
    }
}
