package Services;

import dbRepository.WishlistRepository;
import models.Platform;
import models.WishListItem;

import java.sql.SQLException;
import java.util.List;

/*
*******************************************************************************************************
*   @Class Name         : WishlistService
*   @Author             : Priyanka Kumari (priyanka.kuamri@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class handles wishlist functionalities, including adding, 
*                         retrieving, and removing products from the wishlist.
*******************************************************************************************************
*/

public class WishlistService {
    private static WishlistService instance;
    private WishlistRepository wishlistRepository;

      /*
    *********************************************************
    *  @Constructor    : WishlistService
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Private constructor to initialize the WishlistRepository.
    *  @throws         : ClassNotFoundException, SQLException
    *********************************************************
    */
    private WishlistService() throws ClassNotFoundException, SQLException {
        this.wishlistRepository = new WishlistRepository();
    }

   /*
    *********************************************************
    *  @Method Name    : getInstance
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Singleton pattern to get an instance of WishlistService.
    *  @return         : WishlistService - Returns the instance of WishlistService.
    *  @throws         : ClassNotFoundException, SQLException
    *********************************************************
    */
    public static WishlistService getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new WishlistService();
        }
        return instance;
    }

    /*
    *********************************************************
    *  @Method Name    : addProductToWishlist
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a product to the user's wishlist.
    *  @param          : String username - The username of the user
    *                  : int productId - The ID of the product to add
    *                  : Platform platform - The platform details
    *  @return         : boolean - Returns true if the product is successfully added, otherwise false
    *********************************************************
    */
    public boolean addProductToWishlist(String username, int productId, Platform platform) {
      
        boolean success = wishlistRepository.addProductToWishlist(username, productId, platform.getPlatformId());

     
        if (success) {
            System.out.println("Product added to wishlist successfully");
            return true;
        } else {
            System.out.println("Failed to add product to wishlist");
        }
        return false;
    }

     /*
    *********************************************************
    *  @Method Name    : getWishlist
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the wishlist items of a user.
    *  @param          : String username - The username of the user
    *                  : Platform platform - The platform details
    *  @return         : List<WishListItem> - Returns the list of wishlist items
    *********************************************************
    */  
    public List<WishListItem> getWishlist(String username, Platform platform) {
        return wishlistRepository.getWishlist(username, platform.getPlatformId());
    }

     /*
    *********************************************************
    *  @Method Name    : removeFromWishlist
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Removes a product from the user's wishlist.
    *  @param          : String username - The username of the user
    *                  : int productId - The ID of the product to remove
    *                  : Platform platform - The platform details
    *  @return         : boolean - Returns true if the product is successfully removed, otherwise false
    *********************************************************
    */
    public boolean removeFromWishlist(String username, int productId, Platform platform) {
        boolean remove = wishlistRepository.removeFromWishlist(username, productId, platform.getPlatformId());
        if (remove) {
            System.out.println("Remove product successful");
            return true;
        } else {
            System.out.println("Not removed! Try again");
        }
        return false;
    }
}
