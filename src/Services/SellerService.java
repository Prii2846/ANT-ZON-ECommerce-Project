package Services;
import models.*;
import dbRepository.AdminRepository;
import dbRepository.ProductRepository;
import java.sql.SQLException;
import java.util.List;

/*
*******************************************************************************************************
*   @Class Name         : SellerService
*   @Author             : Priyanka Kumari (priyanka.kuamri@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class provides functionalities for sellers to manage products,
*                         including adding, updating, deleting, and retrieving products. It also
*                         includes methods to fetch best-selling and most-liked products.
*******************************************************************************************************
*/

public class SellerService {
   
    private final AdminRepository adminRepository;

     /*
    *********************************************************
    *  @Constructor    : SellerService
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes the SellerService and establishes 
    *                   connection with the AdminRepository.
    *  @throws         : ClassNotFoundException, SQLException
    *********************************************************
    */
    public SellerService() throws ClassNotFoundException, SQLException {
        this.adminRepository = new AdminRepository();
    }

 
    private final ProductRepository productRepository = new ProductRepository();

    /*
    *********************************************************
    *  @Method Name    : addProduct
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a new product to the platform.
    *  @param          : Product product - The product details
    *                  : Platform platform - The platform where the product is added
    *  @return         : boolean - True if the product is added successfully, otherwise false
    *  @throws         : SQLException
    *********************************************************
    */
    public boolean addProduct(Product product, Platform platform) throws SQLException {
        return productRepository.addProduct(product, platform);
    }
 /*
    *********************************************************
    *  @Method Name    : getProductById
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a product by its ID and platform.
    *  @param          : int productId - The product ID
    *                  : int platformId - The platform ID
    *  @return         : Product - The product details
    *  @throws         : SQLException
    *********************************************************
    */
   
    public Product getProductById(int productId, int platformId) throws SQLException {
        return productRepository.getProductById(productId, platformId);
    }

     /*
    *********************************************************
    *  @Method Name    : updateProduct
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Updates the details of an existing product.
    *  @param          : Product product - The updated product details
    *                  : Platform platform - The platform where the product exists
    *  @return         : boolean - True if the product is updated successfully, otherwise false
    *  @throws         : SQLException
    *********************************************************
    */
    public boolean updateProduct(Product product, Platform platform) throws SQLException {
        return productRepository.updateProduct(product, platform);
    }

   /*
    *********************************************************
    *  @Method Name    : deleteProduct
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Deletes a product from the platform.
    *  @param          : int productId - The product ID
    *                  : String sellerUsername - The seller's username
    *                  : Platform platform - The platform where the product exists
    *  @return         : boolean - True if the product is deleted successfully, otherwise false
    *  @throws         : SQLException
    *********************************************************
    */
    public boolean deleteProduct(int productId, String sellerUsername, Platform platform) throws SQLException {
        return productRepository.deleteProduct(productId, sellerUsername, platform);
    }

    /*
    *********************************************************
    *  @Method Name    : getSellerProducts
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of products added by a seller.
    *  @param          : String sellerUsername - The seller's username
    *                  : Platform platform - The platform where the products exist
    *  @return         : List<Product> - List of seller's products
    *  @throws         : SQLException
    *********************************************************
    */
    public List<Product> getSellerProducts(String sellerUsername, Platform platform) throws SQLException {
        return productRepository.getProductsBySeller(sellerUsername, platform);
    }
  /*
    *********************************************************
    *  @Method Name    : getBestSellingProducts
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of the best-selling products.
    *  @param          : Platform platform - The platform for which best-selling products are fetched
    *  @return         : List<String> - List of best-selling products
    *  @throws         : SQLException
    *********************************************************
    */
   
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        return adminRepository.getBestSellingProducts(platform);
    }

    /*
    *********************************************************
    *  @Method Name    : getMostLikedProducts
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of the most liked products.
    *  @param          : Platform platform - The platform for which most liked products are fetched
    *  @return         : List<String> - List of most liked products
    *  @throws         : SQLException
    *********************************************************
    */
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        return adminRepository.getMostLikedProducts(platform);
    }
}
