package Services;

import dbRepository.WishlistRepository;
import models.Platform;
import models.WishListItem;
import java.util.List;

public class WishlistService {
    private static WishlistService instance;
    private WishlistRepository wishlistRepository;

    private WishlistService() {
        this.wishlistRepository = new WishlistRepository();
    }

    public static WishlistService getInstance() {
        if (instance == null) {
            instance = new WishlistService();
        }
        return instance;
    }

    public boolean addProductToWishlist(String username, int productId,Platform platform) {
        boolean success = wishlistRepository.addProductToWishlist(username, productId,platform.getPlatformId());
        if(success){
            System.out.println("Product added to wishlist successfully");
            return true;
        }else{
            System.out.println("Failed to add product to wishlist");
        }
        return false;

    }

    public List<WishListItem> getWishlist(String username,Platform platform) {
        return wishlistRepository.getWishlist(username,platform.getPlatformId());
        
    }

    public boolean removeFromWishlist(String username, int productId,Platform platform) {
        boolean remove =  wishlistRepository.removeFromWishlist(username, productId,platform.getPlatformId());
        if(remove){
            System.out.println("remove product successfull");
            return true;
        }else{
            System.out.println("Not remove ! try again");
        }
        return false;
    }
}
