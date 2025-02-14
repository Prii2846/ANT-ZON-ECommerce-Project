package dbRepository;
import java.sql.*;
import java.util.*;

import Constant.SqlQueries;
import database.DbConnection;
import models.*;


public class WishlistRepository {
    
    private Connection connection;

    public WishlistRepository() {
        this.connection = DbConnection.getConnection();
    }

   
    public boolean addProductToWishlist(String username, int productId,int platformId) {
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

    public List<WishListItem> getWishlist(String username,int platformId) {
        List<WishListItem> wishlist = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_WISHLIST)) {
            stmt.setString(1, username);
            stmt.setInt(2, platformId);
            try(ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                wishlist.add(new WishListItem(username, rs.getInt("id"), rs.getString("product_name"),rs.getDouble("price")));
        } 
    }
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    public boolean removeFromWishlist(String username, int productId,int platformId) {
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