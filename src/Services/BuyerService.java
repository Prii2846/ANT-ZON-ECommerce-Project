package Services;

import java.sql.SQLException;
import java.util.List;
import dbRepository.AdminRepository;
import models.Platform;

public class BuyerService {
    
    // Instance of AdminRepository for accessing admin-level data
    private final AdminRepository adminRepository;

    // Constructor to initialize AdminRepository
    public BuyerService() throws ClassNotFoundException, SQLException {
        this.adminRepository = new AdminRepository();
    }

    /**
     * Retrieves the list of best-selling products for the specified platform.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of best-selling product names.
     * @throws SQLException 
     */
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        // Call the repository to get the best-selling products based on the platform
        return adminRepository.getBestSellingProducts(platform);
    }

    /**
     * Retrieves the list of most liked products for the specified platform.
     * Throws SQLException if database access fails.
     * @param platform The platform instance for accessing platform-specific data.
     * @return A list of most liked product names.
     * @throws SQLException 
     */
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        // Call the repository to get the most liked products based on the platform
        return adminRepository.getMostLikedProducts(platform);
    }
}
