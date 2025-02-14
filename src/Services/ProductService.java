
package Services;
import dbRepository.ProductRepository;
import models.Platform;
import models.Product;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }
   
    public boolean addProduct(Product product,Platform platform) {
        return productRepository.addProduct(product,platform);  
    }
    
    public List<Product> getProductsBySeller(String sellerUsername,Platform platform) {
        return productRepository.getProductsBySeller(sellerUsername,platform);
    }


    public boolean updateProduct(Product product,Platform platform) {
        return productRepository.updateProduct(product,platform);
    }

    
    public boolean deleteProduct(int id, String sellerUsername,Platform platform) {
        return productRepository.deleteProduct(id, sellerUsername,platform);
    }
    public Product getProductById(int id,int platformId) {
        return productRepository.getProductById(id,platformId);
    }
}
