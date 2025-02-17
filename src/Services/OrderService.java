package Services;
import dbRepository.*;
import models.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class OrderService {
    // Singleton instance for OrderService
    private static OrderService instance;

    // Repository dependencies for handling orders, carts, and products
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    // Private constructor to initialize repositories
    private OrderService() throws ClassNotFoundException, SQLException {
        this.orderRepository = new OrderRepository();
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
    }

    // Singleton method to ensure only one instance of OrderService is created
    public static OrderService getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    // Generates a random transaction ID for order placement
    private String generateTransactionId() {
        Random random = new Random();
        return "TXN-" + (1000000000 + random.nextInt(900000000)); // 10-digit random number prefixed with "TXN-"
    }

    // Finds the appropriate ProductType for a given main category, subcategory, and product type name
    private ProductType findProductType(String mainCategory, String subCategory, String productTypeName) {
        // List of all fixed product types
        List<ProductType> allProductTypes = List.of(
            ProductType.MOBILE, ProductType.LAPTOPS, ProductType.WEARABLES, ProductType.PRINTERS,
            ProductType.ROOM, ProductType.OFFICE, ProductType.EVENT
        );

        // Iterates through each product type to find a matching type
        for (ProductType type : allProductTypes) {
            if (type.mainCategory().equalsIgnoreCase(mainCategory) &&
                type.subCategory().equalsIgnoreCase(subCategory) &&
                type.productType().contains(productTypeName)) {
                return type;
            }
        }
        return null; // Returns null if no matching product type is found
    }

    // Places an order for the user
    public boolean placeOrder(String username, Platform platform) throws SQLException, ClassNotFoundException {
        // Retrieves all cart items for the user from the CartRepository
        List<CartItem> cartItems = cartRepository.getCartItemsByUsername(username, platform.getPlatformId());

        // If the cart is empty, display a message and return false
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before placing an order.");
            return false;
        }

        // Initialize variables for total amount and discount flags
        System.out.println("\n==== YOUR CART ITEMS ====");
        double totalAmount = 0.0;
        boolean hasDiscountProduct = false;
        boolean hasEventFurniture = false;

        // Iterates through each cart item to calculate the total amount and check for discounts
        for (CartItem item : cartItems) {
            // Check for specific discounted products
            if (item.getProductName().equalsIgnoreCase("Dell 7640 Laptop") ||
                item.getProductName().equalsIgnoreCase("Lenovo 5540")) {
                hasDiscountProduct = true; // Flag for Dell/Lenovo product discount
            }

            // Retrieves the product details to check its category and type
            var product = productRepository.getProductById(item.getProductId(), platform.getPlatformId());
            if (product != null) {
                ProductType productType = findProductType(product.getMainCategory(), product.getSubCategory(), product.getProductType());

                // Check if the product is from the Event category of Furniture
                if (productType != null &&
                    productType.mainCategory().equalsIgnoreCase("Furniture") &&
                    productType.subCategory().equalsIgnoreCase("Event")) {
                    hasEventFurniture = true; // Flag for additional discount
                }
            }

            // Add the product price * quantity to the total amount
            totalAmount += item.getPrice() * item.getQuantity();
        }

        // Apply a 2.5% discount if a discounted product is present
        if (hasDiscountProduct) totalAmount *= 0.975;

        // Apply an additional 2.5% discount for Event category furniture
        if (hasEventFurniture) totalAmount *= 0.975;

        // Generate a unique transaction ID for the order
        String transactionId = generateTransactionId();

        // Place the order by calling the OrderRepository's placeOrder method
        boolean orderPlaced = orderRepository.placeOrder(username, cartItems, totalAmount, transactionId, platform.getPlatformId());

        // Display success or failure message based on the order placement result
        if (orderPlaced) {
            System.out.println("Order placed successfully! Transaction ID: " + transactionId);
            return true;
        } else {
            System.out.println("Failed to place order.");
            return false;
        }
    }

    // Retrieves and returns the user's order history
    public List<Order> viewOrderHistory(String username, Platform platform) throws SQLException {
        // Fetches the order history from the OrderRepository
        return orderRepository.getOrderHistory(username, platform.getPlatformId());
    }
}
