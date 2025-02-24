package dbRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;
import database.DbConnection;
import models.CartItem;
import models.Platform;
/*
*******************************************************************************************************
*   @Class Name         : CartRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles cart-related database operations, including adding,
*                         retrieving, and removing items from the cart.
*******************************************************************************************************
*/
public class CartRepository {

    private Connection connection;
 /*
    *********************************************************
    *  @Constructor    : CartRepository
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Establishes a database connection for cart operations.
    *********************************************************
    */
   
    public CartRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
    }
  /*
    *********************************************************
    *  @Method Name    : addCartItem
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds an item to the user's cart in the database.
    *  @param          : CartItem cartItem - The cart item to be added.
    *  @param          : Platform platform - The platform where the item is added.
    *  @return         : boolean - Returns true if the item was successfully added.
    *********************************************************
    */
    
    public boolean addCartItem(CartItem cartItem, Platform platform) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_CART_ITEM)) {
        
            statement.setString(1, cartItem.getUsername());
            statement.setInt(2, cartItem.getProductId());
            statement.setString(3, cartItem.getProductName());
            statement.setInt(4, cartItem.getQuantity());
            statement.setDouble(5, cartItem.getPrice());
            statement.setInt(6, platform.getPlatformId());

     
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
           
            throw new SQLException("Error adding cart item to the database", e);
        }
    }

    /*
    *********************************************************
    *  @Method Name    : getCartItemsByUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all cart items for a given user and platform.
    *  @param          : String username - The username of the user.
    *  @param          : int platformId - The platform ID.
    *  @return         : List<CartItem> - Returns a list of cart items.
    *********************************************************
    */
    public List<CartItem> getCartItemsByUsername(String username, int platformId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_CART_ITEMS_BY_USERNAME)) {
           
            statement.setString(1, username);
            statement.setInt(2, platformId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int cartId = rs.getInt("id");
                    int productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");

                    
                    CartItem item = new CartItem(cartId, username, productId, quantity, productName, price);
                    cartItems.add(item);
                }
            }
        } catch (SQLException e) {
  
            throw new SQLException("Error retrieving cart items from the database", e);
        }
        return cartItems;
    }

    /*
    *********************************************************
    *  @Method Name    : removeCartItem
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Removes an item from the user's cart in the database.
    *  @param          : int productId - The ID of the product to remove.
    *  @param          : String username - The username of the user.
    *  @param          : Platform platform - The platform where the item exists.
    *  @return         : boolean - Returns true if the item was successfully removed.
    *********************************************************
    */
    public boolean removeCartItem(int productId, String username, Platform platform) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.REMOVE_CART_ITEM)) {
         
            statement.setInt(1, productId);
            statement.setString(2, username);
            statement.setInt(3, platform.getPlatformId());

   
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
       
            throw new SQLException("Error removing cart item from the database", e);
        }
    }
}
