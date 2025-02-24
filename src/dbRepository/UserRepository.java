package dbRepository;

import database.DbConnection;
import java.sql.*;
import java.util.*;

import Constant.SqlQueries;
import models.*;

/*
*******************************************************************************************************
*   @Class Name         : UserRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles database operations related to user management,
*                         including registration, authentication, and user retrieval.
*******************************************************************************************************
*/
public class UserRepository {
    PlatformRepository platformRepo = new PlatformRepository();
/*
    *********************************************************
    *  @Method Name    : registerUser
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Registers a new user in the database.
    *  @param          : User user - The user details.
    *  @return         : String - Returns the username if registration is successful, null otherwise.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */

    public static String registerUser(User user) throws ClassNotFoundException {
        if (isUsernameExists(user.getUsername(), user.getPlatformId())) {
            return null;
        }

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getPlatformId());

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected > 0) {
                return user.getUsername();
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error during registration: " + e.getMessage(), e);
        }
        return null; 
    }

 /*
    *********************************************************
    *  @Method Name    : isUsernameExists
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Checks if a username already exists in the database.
    *  @param          : String username - The username to check.
    *                    int platformId - The platform ID.
    *  @return         : boolean - Returns true if the username exists, false otherwise.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
    public static boolean isUsernameExists(String username, int platformId) throws ClassNotFoundException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.CHECK_USERNAME_EXISTS)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, platformId);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); 
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error checking username: " + e.getMessage(), e);
        }
    }
  /*
    *********************************************************
    *  @Method Name    : authenticate
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Authenticates a user based on username, password, and role.
    *  @param          : String username - The username.
    *                    String password - The password.
    *                    String role - The user role.
    *                    int platformId - The platform ID.
    *  @return         : String - Returns the username if authentication is successful, null otherwise.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */

    public static String authenticate(String username, String password, String role, int platformId) throws ClassNotFoundException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.AUTHENTICATE_USER)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setInt(4, platformId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username"); 
                }
            }

        } catch (SQLException e) {
        
            throw new RuntimeException("Error during authentication: " + e.getMessage(), e);
        }
        return null; 
    }

   /*
    *********************************************************
    *  @Method Name    : getUsernamesByRole
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves usernames based on role and platform.
    *  @param          : String role - The user role.
    *                    int platformId - The platform ID.
    *  @return         : List<String> - Returns a list of usernames.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
    public static List<String> getUsernamesByRole(String role, int platformId) throws ClassNotFoundException {
        List<String> usernames = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.GET_USERS_BY_ROLE)) {

            pstmt.setString(1, role);
            pstmt.setInt(2, platformId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    usernames.add(rs.getString("username"));
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error fetching usernames: " + e.getMessage(), e);
        }
        return usernames;  
    }

   /*
    *********************************************************
    *  @Method Name    : getAllUsersForPlatform
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all users for a given platform.
    *  @param          : int platformId - The platform ID.
    *  @throws         : RuntimeException - If an SQL error occurs.
    *********************************************************
    */
    public void getAllUsersForPlatform(int platformId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_USER)) {

            stmt.setInt(1, platformId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("User: " + rs.getString("username") + " | Role: " + rs.getString("role"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users: " + e.getMessage(), e);
        }
    }
}
