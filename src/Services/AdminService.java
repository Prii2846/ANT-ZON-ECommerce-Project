package Services;
import java.sql.SQLException;
import java.util.*;
import models.*;
import dbRepository.AdminRepository;

public class AdminService {

    private final AdminRepository adminRepository;  

    // Constructor initializing the AdminRepository
    public AdminService() throws ClassNotFoundException, SQLException {
        this.adminRepository = new AdminRepository();  
    }

    /**
     * Retrieves a list of all users from the repository.
     * Throws SQLException if database access fails.
     * @throws SQLException 
     */
    public List<User> getAllUsers() throws SQLException {
        return adminRepository.getAllUsers(); // Delegates to repository
    }

    /**
     * Retrieves the total revenue from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @throws SQLException 
     */
    public double getTotalRevenue(Platform platform) throws SQLException {
        return adminRepository.getTotalRevenue(platform); // Delegates to repository
    }

    /**
     * Retrieves revenue by category from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A map of category names to their respective revenue values.
     * @throws SQLException 
     */
    public Map<String, Double> getRevenueByCategory(Platform platform) throws SQLException {
        return adminRepository.getRevenueByCategory(platform); // Delegates to repository
    }

    /**
     * Retrieves revenue by subcategory from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A map of subcategory names to their respective revenue values.
     * @throws SQLException 
     */
    public Map<String, Double> getRevenueBySubcategory(Platform platform) throws SQLException {
        return adminRepository.getRevenueBySubcategory(platform); // Delegates to repository
    }

    /**
     * Retrieves a list of best-selling products from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of best-selling product names.
     * @throws SQLException 
     */
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        return adminRepository.getBestSellingProducts(platform); // Delegates to repository
    }

    /**
     * Retrieves a list of most liked products from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of most liked product names.
     * @throws SQLException 
     */
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        return adminRepository.getMostLikedProducts(platform); // Delegates to repository
    }

    /**
     * Retrieves a list of top-selling sellers from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of top seller names.
     * @throws SQLException 
     */
    public List<String> getTopSellingSellers(Platform platform) throws SQLException {
        return adminRepository.getTopSellingSellers(platform); // Delegates to repository
    }

    /**
     * Retrieves a list of low-stock products from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of Product objects representing low-stock products.
     * @throws SQLException 
     */
    public List<Product> getLowStockProducts(Platform platform) throws SQLException {
        return adminRepository.getLowStockProducts(platform); // Delegates to repository
    }

    /**
     * Retrieves a list of out-of-stock products from the repository.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of Product objects representing out-of-stock products.
     * @throws SQLException 
     */
    public List<Product> getOutOfStockProducts(Platform platform) throws SQLException {
        return adminRepository.getOutOfStockProducts(platform); // Delegates to repository
    }
}
