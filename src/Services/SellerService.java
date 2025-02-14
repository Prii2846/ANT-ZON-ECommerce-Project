package Services;
import models.*;
import dbRepository.AdminRepository;
import dbRepository.ProductRepository;
import java.sql.SQLException;
import java.util.List;

public class SellerService {
       private final AdminRepository adminRepository;

    public SellerService() {
        this.adminRepository = new AdminRepository();  
      }
    private final ProductRepository productRepository = new ProductRepository();

    public boolean addProduct(Product product,Platform platform) throws SQLException {
        return productRepository.addProduct(product,platform);
    }

    public Product getProductById(int productId,int platformId) throws SQLException {
        return productRepository.getProductById(productId,platformId);
    }

    public boolean updateProduct(Product product,Platform platform) throws SQLException {
        return productRepository.updateProduct(product,platform);
    }

    public boolean deleteProduct(int productId, String sellerUsername,Platform platform) throws SQLException {
        return productRepository.deleteProduct(productId, sellerUsername,platform);
    }

    public List<Product> getSellerProducts(String sellerUsername,Platform platform) throws SQLException {
        return productRepository.getProductsBySeller(sellerUsername,platform);
    }
    public List<String> getBestSellingProducts(Platform platform) {
        return adminRepository.getBestSellingProducts(platform);
    }

public List<String> getMostLikedProducts(Platform platform) {
    return adminRepository.getMostLikedProducts(platform);
}
}
