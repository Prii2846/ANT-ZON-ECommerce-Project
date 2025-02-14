package Services;
import dbRepository.ProductTypeRepository;
import java.util.List;
import models.*;

public class ProductTypeService {

    public static List<ProductType> getAllProductTypes() {
        return ProductTypeRepository.getAllProductTypes();
    }

    public static boolean addProductType(ProductType productType,Platform platform) {
        return ProductTypeRepository.addProductType(productType,platform);
    }
}

    