package database;

import java.sql.*;

public class DbConnection {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce"; // Database URL
    private static final String USERNAME = "root";  // Database username
    private static final String PASSWORD = "Prii2846274@"; // Database password
    private static Connection connection = null; // Singleton connection instance

    // Private constructor to prevent instantiation
    private DbConnection() { }

    /**
     * Get the database connection. 
     * This method returns a single instance of the connection.
     * It creates the connection if it does not already exist or if the previous connection is closed.
     * 
     * @return The connection object to the database
     * @throws SQLException If the connection to the database fails
     * @throws ClassNotFoundException If the MySQL driver is not found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Check if the connection is not established or if it is closed
        if (connection == null || connection.isClosed()) {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        // Return the established connection
        return connection;
    }
}
