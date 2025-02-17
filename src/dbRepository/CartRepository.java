package dbRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;
import database.DbConnection;
import models.CartItem;
import models.Platform;

public class CartRepository {

    // Database connection
    private Connection connection;

    // Constructor to initialize the database connection
    public CartRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
    }

    /**
     * Adds a new item to the cart for a given user on a specific platform.
     *
     * @param cartItem  The cart item to add.
     * @param platform  The platform the cart belongs to.
     * @return          True if the item was successfully added, false otherwise.
     * @throws SQLException if there is an issue with the database operation.
     */
    public boolean addCartItem(CartItem cartItem, Platform platform) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_CART_ITEM)) {
            // Set parameters for the prepared statement
            statement.setString(1, cartItem.getUsername());
            statement.setInt(2, cartItem.getProductId());
            statement.setString(3, cartItem.getProductName());
            statement.setInt(4, cartItem.getQuantity());
            statement.setDouble(5, cartItem.getPrice());
            statement.setInt(6, platform.getPlatformId());

            // Execute the insert statement and return true if one or more rows were affected
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // Throw the exception to allow the caller to handle it
            throw new SQLException("Error adding cart item to the database", e);
        }
    }

    /**
     * Retrieves all cart items for a given username and platform ID.
     *
     * @param username   The username to retrieve cart items for.
     * @param platformId The platform ID associated with the cart.
     * @return           A list of CartItem objects.
     * @throws SQLException if there is an issue with the database operation.
     */
    public List<CartItem> getCartItemsByUsername(String username, int platformId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_CART_ITEMS_BY_USERNAME)) {
            // Set the username and platform ID for the query
            statement.setString(1, username);
            statement.setInt(2, platformId);

            // Execute the query and iterate through the result set to build the cart items list
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int cartId = rs.getInt("id");
                    int productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");

                    // Create a new CartItem object and add it to the list
                    CartItem item = new CartItem(cartId, username, productId, quantity, productName, price);
                    cartItems.add(item);
                }
            }
        } catch (SQLException e) {
            // Throw the exception to allow the caller to handle it
            throw new SQLException("Error retrieving cart items from the database", e);
        }
        return cartItems;
    }

    /**
     * Removes a cart item for a given user and product from the cart.
     *
     * @param productId  The product ID to remove from the cart.
     * @param username   The username associated with the cart.
     * @param platform   The platform associated with the cart.
     * @return           True if the item was successfully removed, false otherwise.
     * @throws SQLException if there is an issue with the database operation.
     */
    public boolean removeCartItem(int productId, String username, Platform platform) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.REMOVE_CART_ITEM)) {
            // Set the parameters for the query
            statement.setInt(1, productId);
            statement.setString(2, username);
            statement.setInt(3, platform.getPlatformId());

            // Execute the delete statement and return true if one or more rows were affected
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // Throw the exception to allow the caller to handle it
            throw new SQLException("Error removing cart item from the database", e);
        }
    }
}
