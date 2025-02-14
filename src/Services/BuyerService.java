package Services;

import java.util.List;

import dbRepository.AdminRepository;
import models.Platform;

public class BuyerService {
     private final AdminRepository adminRepository;
     private final BuyerService buyerService;

    private BuyerService() {
        this.adminRepository = new AdminRepository(); 
        this.buyerService = new BuyerService();
      }
        public List<String> getBestSellingProducts(Platform platform) {
        return adminRepository.getBestSellingProducts(platform);
    }

public List<String> getMostLikedProducts(Platform platform) {
    return adminRepository.getMostLikedProducts(platform);
}

    
}
