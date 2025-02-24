package Services;

import dbRepository.ProductTypeRepository;
import java.util.List;
import models.*;

/*
*******************************************************************************************************
*   @Class Name         : ProductTypeService
*   @Author             : Priyanka Kumari (priyanka.kuamri@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class provides functionalities for handling product types,
*                         including retrieving and adding product types for different platforms.
*******************************************************************************************************
*/
public class ProductTypeService {

     /*
    *********************************************************
    *  @Method Name    : getAllProductTypes
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves all available product types.
    *  @param          : None
    *  @return         : List<ProductType> - List of all product types
    *  @throws         : ClassNotFoundException
    *********************************************************
    */
    public static List<ProductType> getAllProductTypes() throws ClassNotFoundException {
        return ProductTypeRepository.getAllProductTypes();
    }

  /*
    *********************************************************
    *  @Method Name    : addProductType
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Adds a new product type to the platform.
    *  @param          : ProductType productType - The product type details
    *                  : Platform platform - The platform where the product type is added
    *  @return         : boolean - True if the product type is added successfully, otherwise false
    *  @throws         : ClassNotFoundException
    *********************************************************
    */
    public static boolean addProductType(ProductType productType, Platform platform) throws ClassNotFoundException {
     
        return ProductTypeRepository.addProductType(productType, platform);
    }
}
