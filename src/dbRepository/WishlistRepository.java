package dbRepository;

import java.sql.*;
import java.util.*;

import Constant.SqlQueries;
import database.DbConnection;
import models.*;

public class WishlistRepository {

    // Connection object to interact with the database
    private Connection connection;

    // Constructor that initializes the connection object
    public WishlistRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
    }

    /**
     * Adds a product to the wishlist of a specific user.
     *
     * @param username   The username of the user adding the product to the wishlist.
     * @param productId  The ID of the product being added to the wishlist.
     * @param platformId The platform ID associated with the user's platform.
     * @return true if the product was successfully added to the wishlist, false otherwise.
     */
    public boolean addProductToWishlist(String username, int productId, int platformId) {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_TO_WISHLIST)) {
            // Setting parameters for the SQL query
            statement.setString(1, username);
            statement.setInt(2, productId);
            statement.setInt(3, platformId);

            // Execute the query and return true if product was added, false if no row was affected
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // Print the error and return false in case of failure
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the wishlist of a specific user.
     *
     * @param username   The username of the user whose wishlist is being fetched.
     * @param platformId The platform ID associated with the user's platform.
     * @return A list of WishListItem objects representing the user's wishlist.
     */
    public List<WishListItem> getWishlist(String username, int platformId) {
        List<WishListItem> wishlist = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_WISHLIST)) {
            // Setting parameters for the SQL query
            stmt.setString(1, username);
            stmt.setInt(2, platformId);

            // Execute the query and process the result set
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Add each item to the wishlist list
                    wishlist.add(new WishListItem(username, rs.getInt("id"), rs.getString("product_name"), rs.getDouble("price")));
                }
            }
        } catch (SQLException e) {
            // Print the error in case of failure
            e.printStackTrace();
        }
        return wishlist; // Return the list of wishlist items
    }

    /**
     * Removes a product from the wishlist of a specific user.
     *
     * @param username   The username of the user removing the product from the wishlist.
     * @param productId  The ID of the product being removed from the wishlist.
     * @param platformId The platform ID associated with the user's platform.
     * @return true if the product was successfully removed, false otherwise.
     */
    public boolean removeFromWishlist(String username, int productId, int platformId) {
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.REMOVE_FROM_WISHLIST)) {
            // Setting parameters for the SQL query
            stmt.setString(1, username);
            stmt.setInt(2, productId);
            stmt.setInt(3, platformId);

            // Execute the query and return true if the product was removed, false otherwise
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Print the error and return false in case of failure
            e.printStackTrace();
            return false;
        }
    }
}
