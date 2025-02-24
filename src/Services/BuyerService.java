package Services;

import java.sql.SQLException;
import java.util.List;
import dbRepository.AdminRepository;
import models.Platform;

/*
*******************************************************************************************************
*   @Parent Class Name  : Services
*   @Class Name         : BuyerService
*   @Author             : <Priyanka Kumari>(priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class handles buyer-related statistics such as best-selling 
*                         and most-liked products. It communicates with AdminRepository to fetch data.
*******************************************************************************************************
*/
public class BuyerService {
    
      /*
    *********************************************************
     *  @Variable Name   : adminRepository
     *  @author         : <Priyanka Kumari>(priyanka.kumari@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Repository instance for database operations
    ********************************************************
    */
    private final AdminRepository adminRepository;

     /*
    *********************************************************
     *  @Method Name    : BuyerService Constructor
     *  @author         : <Priyanka Kumari>(priyanka.kumari@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Initializes the AdminRepository instance for data retrieval
     *  @throws         : ClassNotFoundException, SQLException
    ********************************************************
    */
    public BuyerService() throws ClassNotFoundException, SQLException {
        this.adminRepository = new AdminRepository();
    }
  /*
    *********************************************************
     *  @Method Name    : getBestSellingProducts
     *  @author         : <Priyanka Kumari>(priyanka.kumari@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Fetches a list of best-selling products for the given platform
     *  @param          : platform - The eCommerce platform (Amazon, Flipkart, ANT-ZON)
     *  @return         : List<String> - List of best-selling product names
     *  @throws         : SQLException
    ********************************************************
    */
    public List<String> getBestSellingProducts(Platform platform) throws SQLException {
       
        return adminRepository.getBestSellingProducts(platform);
    }

   /*
    *********************************************************
     *  @Method Name    : getMostLikedProducts
     *  @author         : <Priyanka Kumari>(priyanka.kumari@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Fetches a list of most liked products for the given platform
     *  @param          : platform - The eCommerce platform (Amazon, Flipkart, ANT-ZON)
     *  @return         : List<String> - List of most liked product names
     *  @throws         : SQLException
    ********************************************************
    */
    public List<String> getMostLikedProducts(Platform platform) throws SQLException {
       
        return adminRepository.getMostLikedProducts(platform);
    }
}
