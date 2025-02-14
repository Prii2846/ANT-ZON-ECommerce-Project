// package dbRepository;

// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
// import Constant.SqlQueries;
// import database.DbConnection;
// import models.CartItem;
// import models.Platform;
// import models.Product;

// public class CartRepository {
   
//     private Connection connection;

//     public CartRepository() {
//         this.connection = DbConnection.getConnection();
//     }

    
//     public boolean addCartItem(String username, int productId, int quantity,int platformId) {
//         try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_CART_ITEM)) {
            
//             Product product = new ProductRepository().getProductById(productId,platformId);
            
//             statement.setString(1, username);
//             statement.setInt(2, productId);
//             statement.setString(3, product.getProductName());  
//             statement.setInt(4, quantity);
//             statement.setDouble(5, product.getPrice());  
//             statement.setInt(6, platformId);
            
//             return statement.executeUpdate() > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }
    

    
//     public List<CartItem> getCartItemsByUsername(String username,int platformId) {
//         List<CartItem> cartItems = new ArrayList<>();
//         try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_CART_ITEMS_BY_USERNAME)) {
//             statement.setString(1, username);
//             statement.setInt(2, platformId);
//             try(ResultSet rs = statement.executeQuery()){
//             while (rs.next()) {
//                 int cartId = rs.getInt("id");
//                 int productId = rs.getInt("product_id");
//                 String productName = rs.getString("product_name");
//                 int quantity = rs.getInt("quantity");
//                 double price = rs.getDouble("price");
    
//                 CartItem item = new CartItem(cartId, username, productId, quantity, productName, price);
//                 cartItems.add(item);
//             }
//         }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return cartItems;
//     }
    

//     public boolean removeCartItem(int cartId, String username, int platformId) {
//         try (PreparedStatement statement = connection.prepareStatement(SqlQueries.REMOVE_CART_ITEM)) {
//             statement.setInt(1, cartId);
//             statement.setString(2, username);
//             statement.setInt(3, platformId);

//             return statement.executeUpdate() > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }
    

  
//     public void clearCart(String username,int platformId) {
//         try (PreparedStatement statement = connection.prepareStatement(SqlQueries.CLEAR_CART)) {
//             statement.setString(1, username);
//             statement.setInt(2, platformId);
//             statement.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
   



  
    
// }

package dbRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;
import database.DbConnection;
import models.CartItem;
import models.Platform;
import models.Product;

public class CartRepository {

    private Connection connection;

    public CartRepository() {
        this.connection = DbConnection.getConnection();
    }
    public boolean addCartItem(CartItem cartItem,Platform platform) {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_CART_ITEM)) {
            statement.setString(1, cartItem.getUsername());
            statement.setInt(2, cartItem.getProductId());
            statement.setString(3, cartItem.getProductName());
            statement.setInt(4, cartItem.getQuantity());
            statement.setDouble(5, cartItem.getPrice());
            statement.setInt(6, platform.getPlatformId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<CartItem> getCartItemsByUsername(String username, int platformId) {
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
            e.printStackTrace();
        }
        return cartItems;
    }

  
    public boolean removeCartItem(int productId,String username, Platform platform) {
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.REMOVE_CART_ITEM)) {
            statement.setInt(1, productId);
            statement.setString(2, username);
            statement.setInt(3, platform.getPlatformId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

