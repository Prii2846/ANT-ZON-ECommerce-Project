package Services;

import dbRepository.ProductRepository;
import models.Platform;
import models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    // Repository dependency for handling product operations
    private ProductRepository productRepository;

    // Constructor to initialize the ProductRepository
    public ProductService() throws ClassNotFoundException, SQLException {
        this.productRepository = new ProductRepository();
    }

    // Adds a new product to the system
    public boolean addProduct(Product product, Platform platform) {
        // Calls the ProductRepository's addProduct method to insert the product into the database
        return productRepository.addProduct(product, platform);
    }

    // Retrieves all products added by a specific seller
    public List<Product> getProductsBySeller(String sellerUsername, Platform platform) {
        // Calls the ProductRepository to fetch products based on the seller's username and platform
        return productRepository.getProductsBySeller(sellerUsername, platform);
    }

    // Updates an existing product's details
    public boolean updateProduct(Product product, Platform platform) {
        // Calls the ProductRepository's updateProduct method to modify the product details
        return productRepository.updateProduct(product, platform);
    }

    // Deletes a product from the system
    public boolean deleteProduct(int id, String sellerUsername, Platform platform) {
        // Calls the ProductRepository's deleteProduct method to remove the product based on its ID and seller
        return productRepository.deleteProduct(id, sellerUsername, platform);
    }

    // Retrieves a product by its unique ID
    public Product getProductById(int id, int platformId) {
        // Calls the ProductRepository to fetch the product details based on its ID and platform
        return productRepository.getProductById(id, platformId);
    }
}
