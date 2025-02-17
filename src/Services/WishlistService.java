package Services;

import dbRepository.WishlistRepository;
import models.Platform;
import models.WishListItem;

import java.sql.SQLException;
import java.util.List;

public class WishlistService {
    private static WishlistService instance;
    private WishlistRepository wishlistRepository;

    // Private constructor to enforce singleton pattern
    private WishlistService() throws ClassNotFoundException, SQLException {
        this.wishlistRepository = new WishlistRepository();
    }

    // Singleton instance method to ensure only one instance is created
    public static WishlistService getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new WishlistService();
        }
        return instance;
    }

    // Adds a product to the user's wishlist
    public boolean addProductToWishlist(String username, int productId, Platform platform) {
        // Attempt to add the product to the wishlist in the database
        boolean success = wishlistRepository.addProductToWishlist(username, productId, platform.getPlatformId());

        // Print success or failure messages based on the operation result
        if (success) {
            System.out.println("Product added to wishlist successfully");
            return true;
        } else {
            System.out.println("Failed to add product to wishlist");
        }
        return false;
    }

    // Retrieves the user's wishlist
    public List<WishListItem> getWishlist(String username, Platform platform) {
        // Fetch the wishlist from the database for the given username and platform
        return wishlistRepository.getWishlist(username, platform.getPlatformId());
    }

    // Removes a product from the user's wishlist
    public boolean removeFromWishlist(String username, int productId, Platform platform) {
        // Attempt to remove the product from the wishlist in the database
        boolean remove = wishlistRepository.removeFromWishlist(username, productId, platform.getPlatformId());

        // Print success or failure messages based on the operation result
        if (remove) {
            System.out.println("Remove product successful");
            return true;
        } else {
            System.out.println("Not removed! Try again");
        }
        return false;
    }
}
