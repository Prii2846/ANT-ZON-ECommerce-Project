package dbRepository;

import java.sql.*;
import java.util.*;

import Constant.SqlQueries;
import database.DbConnection;
import models.*;
/*
*******************************************************************************************************
*   @Class Name         : WishlistRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles database operations related to the wishlist,
*                         including adding, retrieving, and removing products from the wishlist.
*******************************************************************************************************
*/
public class WishlistRepository {
    private Connection connection;
 /*
    *********************************************************
    *  @Constructor    : WishlistRepository
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes the database connection.
    *  @throws         : ClassNotFoundException, SQLException
    *********************************************************
    */

    public WishlistRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
    }

      /*
    *********************************************************
    *  @Method Name    : addProductToWishlist
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a product to the user's wishlist.
    *  @param          : String username - The user's username.
    *                    int productId - The product ID.
    *                    int platformId - The platform ID.
    *  @return         : boolean - Returns true if the product is added successfully, false otherwise.
    *********************************************************
    */
    public boolean addProductToWishlist(String username, int productId, int platformId) {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_TO_WISHLIST)) {
       
            statement.setString(1, username);
            statement.setInt(2, productId);
            statement.setInt(3, platformId);

   
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 /*
    *********************************************************
    *  @Method Name    : getWishlist
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the wishlist of a specific user.
    *  @param          : String username - The user's username.
    *                    int platformId - The platform ID.
    *  @return         : List<WishListItem> - Returns a list of wishlist items.
    *********************************************************
    */
    public List<WishListItem> getWishlist(String username, int platformId) {
        List<WishListItem> wishlist = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_WISHLIST)) {
            stmt.setString(1, username);
            stmt.setInt(2, platformId);

        
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    wishlist.add(new WishListItem(username, rs.getInt("id"), rs.getString("product_name"), rs.getDouble("price")));
                }
            }
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        return wishlist;
    }
    /*
    *********************************************************
    *  @Method Name    : removeFromWishlist
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Removes a product from the user's wishlist.
    *  @param          : String username - The user's username.
    *                    int productId - The product ID.
    *                    int platformId - The platform ID.
    *  @return         : boolean - Returns true if the product is removed successfully, false otherwise.
    *********************************************************
    */
  
    public boolean removeFromWishlist(String username, int productId, int platformId) {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.REMOVE_FROM_WISHLIST)) {
        
            stmt.setString(1, username);
            stmt.setInt(2, productId);
            stmt.setInt(3, platformId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
