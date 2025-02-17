package Services;
import models.*;
import dbRepository.AdminRepository;
import dbRepository.ProductRepository;
import java.sql.SQLException;
import java.util.List;

public class SellerService {
    // Instance of AdminRepository for retrieving statistics like best-selling and most-liked products
    private final AdminRepository adminRepository;

    // Constructor initializes the admin repository
    public SellerService() throws ClassNotFoundException, SQLException {
        this.adminRepository = new AdminRepository();
    }

    // Instance of ProductRepository for handling product-related operations
    private final ProductRepository productRepository = new ProductRepository();

    // Adds a new product to the database
    public boolean addProduct(Product product, Platform platform) throws SQLException {
        // Calls the product repository to add the product to the appropriate platform
        return productRepository.addProduct(product, platform);
    }

    // Retrieves a product by its ID for a specific platform
    public Product getProductById(int productId, int platformId) throws SQLException {
        // Calls the product repository to fetch the product details
        return productRepository.getProductById(productId, platformId);
    }

    // Updates an existing product's information
    public boolean updateProduct(Product product, Platform platform) throws SQLException {
        // Calls the product repository to update the product details in the database
        return productRepository.updateProduct(product, platform);
    }

    // Deletes a product from the system based on its ID and seller's username
    public boolean deleteProduct(int productId, String sellerUsername, Platform platform) throws SQLException {
        // Calls the product repository to remove the product from the seller's inventory
        return productRepository.deleteProduct(productId, sellerUsername, platform);
    }

    // Retrieves all products listed by a specific seller
    public List<Product> getSellerProducts(String sellerUsername, Platform platform) throws SQLException {
        // Calls the product repository to get all products by the seller
        return productRepository.getProductsBySeller(sellerUsername, platform);
    }

    // Retrieves a list of the best-selling products from the admin repository
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        // Calls the admin repository to fetch the list of best-selling products for the platform
        return adminRepository.getBestSellingProducts(platform);
    }

    // Retrieves a list of the most-liked products from the admin repository
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        // Calls the admin repository to fetch the list of most-liked products for the platform
        return adminRepository.getMostLikedProducts(platform);
    }
}
