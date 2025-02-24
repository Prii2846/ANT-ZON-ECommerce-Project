package database;

import java.sql.*;
/*
*******************************************************************************************************
*   @Class Name         : DbConnection
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class manages the database connection using JDBC for the eCommerce system.
*******************************************************************************************************
*/
public class DbConnection {
  
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce"; 
    private static final String USERNAME = "root";  
    private static final String PASSWORD = "Prii2846274@"; 
    private static Connection connection = null; 

 /*
    *********************************************************
    *  @Constructor    : DbConnection
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Private constructor to prevent instantiation.
    *********************************************************
    */
    private DbConnection() { }

    /*
    *********************************************************
    *  @Method Name    : getConnection
    *  @Description    : Establishes and returns a database connection.
    *  @throws         : SQLException, ClassNotFoundException
    *  @return         : Connection - The database connection instance.
    *********************************************************
    */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
      
        if (connection == null || connection.isClosed()) {
      
            Class.forName("com.mysql.cj.jdbc.Driver");

         
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

      
        return connection;
    }
}
