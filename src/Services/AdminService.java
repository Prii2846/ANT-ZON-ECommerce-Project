package Services;
import java.sql.SQLException;
import java.util.*;
import models.*;
import dbRepository.AdminRepository;

/*
*******************************************************************************************************
*   @Class Name         : AdminService
*   @Author             : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class provides functionalities for admin operations,
*                         including user management, revenue tracking, product statistics, 
*                         and inventory monitoring.
*******************************************************************************************************
*/
public class AdminService {

    private final AdminRepository adminRepository;  

      /*
    *********************************************************
    *  @Constructor Name : AdminService
    *  @Author          : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company         : Antrazal
    *  @Description     : Initializes the AdminRepository instance for database operations.
    *  @param           : None
    *  @throws          : ClassNotFoundException, SQLException
    *********************************************************
    */
    public AdminService() throws ClassNotFoundException, SQLException {
        this.adminRepository = new AdminRepository();  
    }
  /*
    *********************************************************
    *  @Method Name    : getAllUsers
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all registered users from the database.
    *  @param          : None
    *  @return         : List<User> - List of all users
    *  @throws         : SQLException
    *********************************************************
    */
   
    public List<User> getAllUsers() throws SQLException {
        return adminRepository.getAllUsers();
    }



/*
*********************************************************
*  @Method Name    : getTotalRevenue
*  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
*  @Company        : Antrazal
*  @Description    : Retrieves total revenue across all categories for the selected platform.
*  @param          : Platform platform - The selected e-commerce platform
*  @return         : double - Total revenue
*  @throws         : SQLException
*********************************************************
*/
    public double getTotalRevenue(Platform platform) throws SQLException {
        return adminRepository.getTotalRevenue(platform);
    }

   /*
    *********************************************************
    *  @Method Name    : getRevenueByCategory
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves revenue categorized by product categories.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : Map<String, Double> - Category-wise revenue
    *  @throws         : SQLException
    *********************************************************
    */
    public Map<String, Double> getRevenueByCategory(Platform platform) throws SQLException {
        return adminRepository.getRevenueByCategory(platform); 
    }
  /*
    *********************************************************
    *  @Method Name    : getRevenueBySubcategory
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves revenue categorized by product subcategories.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : Map<String, Double> - Subcategory-wise revenue
    *  @throws         : SQLException
    *********************************************************
    */
    public Map<String, Double> getRevenueBySubcategory(Platform platform) throws SQLException {
        return adminRepository.getRevenueBySubcategory(platform); 
    }

      /*
    *********************************************************
    *  @Method Name    : getBestSellingProducts
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of the best-selling products.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : List<String> - Best-selling product names
    *  @throws         : SQLException
    *********************************************************
    */
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
        return adminRepository.getBestSellingProducts(platform); 
    }
  /*
    *********************************************************
    *  @Method Name    : getMostLikedProducts
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of the most liked products.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : List<String> - Most liked product names
    *  @throws         : SQLException
    *********************************************************
    */
   
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
        return adminRepository.getMostLikedProducts(platform); 
    }
   /*
    *********************************************************
    *  @Method Name    : getTopSellingSellers
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of top-selling sellers.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : List<String> - Top-selling seller usernames
    *  @throws         : SQLException
    *********************************************************
    */
   
    public List<String> getTopSellingSellers(Platform platform) throws SQLException {
        return adminRepository.getTopSellingSellers(platform); 
    }
  /*
    *********************************************************
    *  @Method Name    : getLowStockProducts
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of products with low stock levels.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : List<Product> - Products with low stock
    *  @throws         : SQLException
    *********************************************************
    */
 
    public List<Product> getLowStockProducts(Platform platform) throws SQLException {
        return adminRepository.getLowStockProducts(platform);
    }

      /*
    *********************************************************
    *  @Method Name    : getOutOfStockProducts
    *  @Author         : <Priyanka Kumari> (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves a list of products with out of stock levels.
    *  @param          : Platform platform - The selected e-commerce platform
    *  @return         : List<Product> - Products with out of  stock
    *  @throws         : SQLException
    *********************************************************
    */
    public List<Product> getOutOfStockProducts(Platform platform) throws SQLException {
        return adminRepository.getOutOfStockProducts(platform); 
    }
}
