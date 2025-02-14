package Services;
import java.util.List;
import java.util.Map;
import Constant.Printer;
import models.*;

import dbRepository.AdminRepository;
import models.User;

public class AdminService {
   
    private final AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepository();  
      }

       public List<User> getAllUsers() {
        return adminRepository.getAllUsers();
    }
   
        public double getTotalRevenue(Platform platform) {
            return adminRepository.getTotalRevenue(platform);
        }

        public Map<String,Double> getRevenueByCategory(Platform platform) {
            return adminRepository.getRevenueByCategory(platform);
        }
        public Map<String, Double> getRevenueBySubcategory(Platform platform) {
            return adminRepository.getRevenueBySubcategory(platform);
        }
        public List<String> getBestSellingProducts(Platform platform) {
            return adminRepository.getBestSellingProducts(platform);
        }

    public List<String> getMostLikedProducts(Platform platform) {
        return adminRepository.getMostLikedProducts(platform);
    }
     
    public List<String> getTopSellingSellers(Platform platform) {
        return adminRepository.getTopSellingSellers(platform);
    }
     
    public List<Product> getLowStockProducts(Platform platform) {
        return adminRepository.getLowStockProducts(platform);
    }


        public List<Product> getOutOfStockProducts(Platform platform) {
        return adminRepository.getOutOfStockProducts(platform);
    }
}

    

