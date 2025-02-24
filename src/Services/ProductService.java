package Services;

import dbRepository.ProductRepository;
import models.Platform;
import models.Product;

import java.sql.SQLException;
import java.util.List;

/*
*******************************************************************************************************
*   @Class Name         : ProductService
*   @Author             : Priyanka Kumari (priyanka.kuamri@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class provides functionalities for handling products, 
*                         including adding, retrieving, updating, and deleting products for sellers.
*******************************************************************************************************
*/
public class ProductService {
   
    private ProductRepository productRepository;

   /*
    *********************************************************
    *  @Constructor Name : ProductService
    *  @Author          : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company         : Antrazal
    *  @Description     : Initializes the product repository for product management.
    *  @param           : None
    *  @throws          : ClassNotFoundException, SQLException
    *********************************************************
    */   
    public ProductService() throws ClassNotFoundException, SQLException {
        this.productRepository = new ProductRepository();
    }

  /*
    *********************************************************
    *  @Method Name    : addProduct
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a new product for a seller.
    *  @param          : Product product - The product details
    *                  : Platform platform - The platform where the product is listed
    *  @return         : boolean - True if the product is added successfully, otherwise false
    *********************************************************
    */
    public boolean addProduct(Product product, Platform platform) {
      
        return productRepository.addProduct(product, platform);
    }
 /*
    *********************************************************
    *  @Method Name    : getProductsBySeller
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all products listed by a specific seller.
    *  @param          : String sellerUsername - The seller's username
    *                  : Platform platform - The platform where the products are listed
    *  @return         : List<Product> - List of products added by the seller
    *********************************************************
    */
    public List<Product> getProductsBySeller(String sellerUsername, Platform platform) {
      
        return productRepository.getProductsBySeller(sellerUsername, platform);
    }

    /*
    *********************************************************
    *  @Method Name    : updateProduct
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Updates an existing product's details.
    *  @param          : Product product - The updated product details
    *                  : Platform platform - The platform where the product is listed
    *  @return         : boolean - True if the product is updated successfully, otherwise false
    *********************************************************
    */
    public boolean updateProduct(Product product, Platform platform) {
       
        return productRepository.updateProduct(product, platform);
    }

  /*
    *********************************************************
    *  @Method Name    : deleteProduct
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Deletes a product listed by a seller.
    *  @param          : int id - The product ID to delete
    *                  : String sellerUsername - The seller's username
    *                  : Platform platform - The platform where the product is listed
    *  @return         : boolean - True if the product is deleted successfully, otherwise false
    *********************************************************
    */
    public boolean deleteProduct(int id, String sellerUsername, Platform platform) {
       
        return productRepository.deleteProduct(id, sellerUsername, platform);
    }

    /*
    *********************************************************
    *  @Method Name    : getProductById
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a product's details by its ID.
    *  @param          : int id - The product ID
    *                  : int platformId - The platform ID where the product is listed
    *  @return         : Product - The product details or null if not found
    *********************************************************
    */
    public Product getProductById(int id, int platformId) {
      
        return productRepository.getProductById(id, platformId);
    }
}
