package database;
import java.sql.*;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USERNAME = "root";  
    private static final String PASSWORD = "Prii2846274@"; 
    private static Connection connection = null;

    private DbConnection() { }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection failed! " + e.getMessage());
        }
        return connection;
    }

}

