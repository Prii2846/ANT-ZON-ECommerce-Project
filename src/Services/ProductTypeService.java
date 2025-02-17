package Services;

import dbRepository.ProductTypeRepository;
import java.util.List;
import models.*;

public class ProductTypeService {

    // Retrieves a list of all product types from the ProductTypeRepository
    public static List<ProductType> getAllProductTypes() throws ClassNotFoundException {
        // Calls the repository method to fetch all product types from the database
        return ProductTypeRepository.getAllProductTypes();
    }

    // Adds a new product type to the system
    public static boolean addProductType(ProductType productType, Platform platform) throws ClassNotFoundException {
        // Calls the ProductTypeRepository to add the new product type to the database
        return ProductTypeRepository.addProductType(productType, platform);
    }
}
