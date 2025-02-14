
package Services;
import dbRepository.*;
import models.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import database.DbConnection;

public class OrderService {
    private static OrderService instance;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private OrderService() {
        this.orderRepository = new OrderRepository();
        this.cartRepository = new CartRepository();
        this.productRepository = new ProductRepository();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    private String generateTransactionId() {
        Random random = new Random();
        return "TXN-" + (1000000000 + random.nextInt(900000000));
    }

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

    public boolean placeOrder(String username, Platform platform) {
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
  
    public List<Order> viewOrderHistory(String username, Platform platform) {
        return orderRepository.getOrderHistory(username, platform.getPlatformId());
    }
}


