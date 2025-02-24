package Services;
import dbRepository.*;
import models.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/*
*******************************************************************************************************
*   @Class Name         : OrderService
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class provides functionalities for handling orders,
*                         including placing orders, applying discounts, generating transaction IDs,
*                         and viewing order history.
*******************************************************************************************************
*/
public class OrderService {
 
    private static OrderService instance;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

/*
    *********************************************************
    *  @Constructor Name : OrderService
    *  @Author          : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company         : Antrazal
    *  @Description     : Initializes the repositories required for order processing.
    *  @param           : None
    *  @throws          : ClassNotFoundException, SQLException
    *********************************************************
    */   
    private OrderService() throws ClassNotFoundException, SQLException {
        this.orderRepository = new OrderRepository();
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
    }

    /*
    *********************************************************
    *  @Method Name    : getInstance
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Implements Singleton pattern to return a single instance of OrderService.
    *  @return         : OrderService - The singleton instance
    *  @throws         : ClassNotFoundException, SQLException
    *********************************************************
    */
    public static OrderService getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    /*
    *********************************************************
    *  @Method Name    : generateTransactionId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Generates a unique transaction ID for order processing.
    *  @return         : String - The generated transaction ID
    *********************************************************
    */
    private String generateTransactionId() {
        Random random = new Random();
        return "TXN-" + (1000000000 + random.nextInt(900000000)); 
    }

     /*
    *********************************************************
    *  @Method Name    : findProductType
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Finds the product type based on category details.
    *  @param          : String mainCategory - The main category of the product
    *                  : String subCategory - The subcategory of the product
    *                  : String productTypeName - The product type name
    *  @return         : ProductType - The matched product type or null if not found
    *********************************************************
    */
    private ProductType findProductType(String mainCategory, String subCategory, String productTypeName) {
     
        List<ProductType> allProductTypes = List.of(
            ProductType.MOBILE, ProductType.LAPTOPS, ProductType.WEARABLES, ProductType.PRINTERS,
            ProductType.ROOM, ProductType.OFFICE, ProductType.EVENT
        );

        
        for (ProductType type : allProductTypes) {
            if (type.mainCategory().equalsIgnoreCase(mainCategory) &&
                type.subCategory().equalsIgnoreCase(subCategory) &&
                type.productType().contains(productTypeName)) {
                return type;
            }
        }
        return null; 
    }
 /*
    *********************************************************
    *  @Method Name    : placeOrder
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Places an order for the user, applying discounts and generating a transaction ID.
    *  @param          : String username - The user placing the order
    *                  : Platform platform - The platform ID for order processing
    *  @return         : boolean - True if the order is placed successfully, otherwise false
    *  @throws         : SQLException, ClassNotFoundException
    *********************************************************
    */
  
    public boolean placeOrder(String username, Platform platform) throws SQLException, ClassNotFoundException {
       
        List<CartItem> cartItems = cartRepository.getCartItemsByUsername(username, platform.getPlatformId());

       
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before placing an order.");
            return false;
        }

      
        System.out.println("\n==== YOUR CART ITEMS ====");
        double totalAmount = 0.0;
        boolean hasDiscountProduct = false;
        boolean hasEventFurniture = false;

      
        for (CartItem item : cartItems) {
           
            if (item.getProductName().equalsIgnoreCase("Dell 7640 Laptop") ||
                item.getProductName().equalsIgnoreCase("Lenovo 5540")) {
                hasDiscountProduct = true; 
            }

          
            var product = productRepository.getProductById(item.getProductId(), platform.getPlatformId());
            if (product != null) {
                ProductType productType = findProductType(product.getMainCategory(), product.getSubCategory(), product.getProductType());

                
                if (productType != null &&
                    productType.mainCategory().equalsIgnoreCase("Furniture") &&
                    productType.subCategory().equalsIgnoreCase("Event")) {
                    hasEventFurniture = true; 
                }
            }

           
            totalAmount += item.getPrice() * item.getQuantity();
        }

      
        if (hasDiscountProduct) totalAmount *= 0.975;

      
        if (hasEventFurniture) totalAmount *= 0.975;

   
        String transactionId = generateTransactionId();

    
        boolean orderPlaced = orderRepository.placeOrder(username, cartItems, totalAmount, transactionId, platform.getPlatformId());

        
        if (orderPlaced) {
            System.out.println("Order placed successfully! Transaction ID: " + transactionId);
            return true;
        } else {
            System.out.println("Failed to place order.");
            return false;
        }
    }

     /*
    *********************************************************
    *  @Method Name    : viewOrderHistory
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the order history for a user.
    *  @param          : String username - The user whose order history is being retrieved
    *                  : Platform platform - The platform ID for fetching order data
    *  @return         : List<Order> - List of orders for the user
    *  @throws         : SQLException
    *********************************************************
    */
    public List<Order> viewOrderHistory(String username, Platform platform) throws SQLException {
      
        return orderRepository.getOrderHistory(username, platform.getPlatformId());
    }
}
