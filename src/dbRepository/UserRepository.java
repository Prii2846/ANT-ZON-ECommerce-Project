package dbRepository;
import database.DbConnection;
import java.sql.*;
import java.util.*;
import Constant.SqlQueries;
import models.*;

public class UserRepository {
    PlatformRepository platformRepo = new PlatformRepository();
    public static String registerUser(User user){
        if (isUsernameExists(user.getUsername(),user.getPlatformId())) {
            System.out.println("Error: Username already exists!");
            return null;
        }
    try (Connection connection = DbConnection.getConnection();
    PreparedStatement pstmt =  connection.prepareStatement(SqlQueries.INSERT_USER,Statement.RETURN_GENERATED_KEYS)){
        
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getRole());
        pstmt.setInt(4, user.getPlatformId());
        
        int rowAffected = pstmt.executeUpdate();
        if(rowAffected>0){
            return user.getUsername();     
    
}  
    }     
    catch (SQLException e) {
        System.err.println("Error during registration: " + e.getMessage());
        
       
    }
    return null;

    }
    public static boolean isUsernameExists(String username,int platformId) {
    
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SqlQueries.CHECK_USERNAME_EXISTS)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, platformId);
            try(ResultSet rs = pstmt.executeQuery()){
            return rs.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Error checking username: " + e.getMessage());
            return false;
        }
    }
    public static String authenticate(String username,String password,String role,int platformId){

        try (Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(SqlQueries.AUTHENTICATE_USER)){
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,role);
            pstmt.setInt(4, platformId);
            try(ResultSet rs = pstmt.executeQuery()){
            if(rs.next()){
                return rs.getString("username");
            }
        }
            
        } catch (SQLException e) {
            System.out.println("Error during authenticate " + e.getMessage());
            
        }
        return null;

    }
    public static List<String> getUsernamesByRole(String role, int platformId){
        List<String> usernames = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(SqlQueries.GET_USERS_BY_ROLE)) {
       
       pstmt.setString(1, role);
       pstmt.setInt(2, platformId);
       try(ResultSet rs = pstmt.executeQuery()){
       while (rs.next()) {
        usernames.add(rs.getString("username"));
    }
       }
} catch (SQLException e) {
    System.out.println("Error fetching usernames: " + e.getMessage());
}
return usernames;

    }
    public void getAllUsersForPlatform(int platformId) throws SQLException {
        try (Connection connection = DbConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_USER)){
        stmt.setInt(1, platformId);
        try(ResultSet rs = stmt.executeQuery()){
        while (rs.next()) {
            System.out.println("User: " + rs.getString("username") + " | Role: " + rs.getString("role"));
        }
    }
}
 catch (SQLException e) {
    e.printStackTrace();
 }
    }
}

