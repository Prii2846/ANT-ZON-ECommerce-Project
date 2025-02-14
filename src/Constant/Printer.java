package Constant;
import java.util.List;
import java.util.Map;
import models.*;

public class Printer {

    public static String showPlatformMenu(){
        return "\n==== SELECT AN ECOMMERCE PLATFORM ====\n" +
                "1. Amazon\n" + 
                "2. Flipkart\n" + 
                "3. ANT-ZON\n" + 
                "4. Exit";

         
    }
    public static String printMainMenu() {
        return "\n==== ECOMMERCE: ANT-ZON MAIN MENU ====\n" +
                "1. Admin\n" +
                "2. Seller\n" +
                "3. Buyer\n" +
                "4. Back\n"  +
                "5. Exit";
    }
    public static String printAdminMenu() {
        return "\n==== ADMIN MENU ====\n" +
                "1. Register Admin\n" +
                "2. Login as Admin\n" +
                "3. Back\n" +
                "4. Exit";
    }

    public static String printSellerMenu() {
        return "\n==== SELLER MENU ====\n" +
                "1. Register Seller\n" +
                "2. Login as Seller\n" +
                "3. Back\n" +
                "4. Exit";
    }
    public static String printBuyerMenu() {
        return "\n==== BUYER MENU ====\n" +
                "1. Register Buyer\n" +
                "2. Login as Buyer\n" +
                "3. Back\n" +
                "4. Exit";
    }

    public static void printInvalidChoice() {
        System.out.println("Invalid choice! Please try again.");
    }

    public static void printExitMessage() {
        System.out.println("Thank you for using E-commerce ANT-ZON!");
    }
    
    public static void printUsernameExists() {
        System.out.println("Username already exists! Please try another.");
    }
    public static void printInvalidLogin(String role) {
        System.out.println("Invalid " + role + " details! Please try again.");
    }

   
    public static void printRegistrationHeader(String role) {
        System.out.println("You are registering as " + role);
    }

    public static void printUsernameRules() {
        System.out.println("\nUsername must be 6-9 alphanumeric characters and no special characters allowed.");
    }

    public static void printInvalidUsername() {
        System.out.println("Invalid username! Must be 6-9 alphanumeric characters.");
    }

    public static void printPasswordRules() {
        System.out.println("\nPassword must be at least 8 characters long.");
        System.out.println("Must contain at least one uppercase letter (A-Z).");
        System.out.println("Must contain at least one number (0-9).");
        System.out.println("Must contain at least one special character (!@#$%^&*).");
    }

    public static void printInvalidPassword() {
        System.out.println("Invalid password! Must be at least 8 characters, include 1 uppercase, 1 number, and 1 special character.");
    }

    public static void printRegistrationSuccess(String role) {
        System.out.println(role + " registered successfully!");
    }

    public static void printLoginSuccess(String role) {
        System.out.println(role + " login successful!");
    }

  
    
    public static void printSellerDashboard(String sellerUsername) {
        System.out.println("\n=== SELLER DASHBOARD (" + sellerUsername + ") ===");
        System.out.println("1. Add Product");
        System.out.println("2. View My Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("5. View Best-Selling Products"); 
        System.out.println("6. View Most Liked Products");  
        System.out.println("7. Logout");
        System.out.println("8. Back");
        System.out.println("9. Exit");

    }

    public static void printCategoryMenu() {
        System.out.println("\n=== SELECT CATEGORY ===");
        System.out.println("1. Electronics");
        System.out.println("2. Furniture");
        System.out.println("3. Back");
        System.out.println("4. Exit");
       
        
    }


public static void printSubcategoryMenu(String mainCategory) {
    System.out.println("\nSelect Subcategory in " + mainCategory + ":");
    if (mainCategory.equals(Constant.CATEGORY_ELECTRONICS)) {
        System.out.println("1. Mobile");
        System.out.println("2. Laptops");
        System.out.println("3. Wearables");
        System.out.println("4. Printers");
        System.out.println("5. Back");
        System.out.println("6. Exit");
    } else if (mainCategory.equals(Constant.CATEGORY_FURNITURE)) {
        System.out.println("1. Room");
        System.out.println("2. Office");
        System.out.println("3. Event");
        System.out.println("4. Back");
        System.out.println("5. Exit");
    }
}



    public static void printProductTypeMenu(String subCategory) {
        System.out.println("\n===== SELECT PRODUCT TYPE =====");
                
                switch (subCategory) {
                    case Constant.SUBCATEGORY_MOBILE -> {
                        System.out.println("1. Smartphone Android");
                        System.out.println("2. Smartphone iOS");
                        System.out.println("3. Keypad");
                        System.out.println("4. Back");  
                        System.out.println("5. Exit");
                    }
                    case Constant.SUBCATEGORY_LAPTOPS -> {
                        System.out.println("1. Business Laptop");
                        System.out.println("2. Gaming Laptop");
                        System.out.println("3. Back");
                        System.out.println("4. Exit");
                       
                    }
                    
                    case Constant.SUBCATEGORY_WEARABLES -> {
                        System.out.println("1. Smartwatches");
                        System.out.println("2. Fitness Trackers");
                        System.out.println("3. Back");
                        System.out.println("4. Exit");
                       
                    }
                    case Constant.SUBCATEGORY_PRINTERS -> {
                        System.out.println("1. Inkjet Printers");
                        System.out.println("2. Laser Printers");
                        System.out.println("3. Back");
                        System.out.println("4. Exit");
                       
                    }
                    case Constant.SUBCATEGORY_ROOM -> {
                        System.out.println("1. Sofa");
                        System.out.println("2. Bed");
                        System.out.println("3. Back");
                        System.out.println("4. Exit");
                      
                    }
                    case Constant.SUBCATEGORY_OFFICE -> {
                        System.out.println("1. Desk");
                        System.out.println("2. Chair");
                        System.out.println("3. Back");
                        System.out.println("4. Exit");
                    }
                    case Constant.SUBCATEGORY_EVENT -> {
                        System.out.println("1. Stage");
                        System.out.println("2. Decoration");
                        System.out.println("3. Back");
                        System.out.println("4. Exit");
                    }
                    default -> System.out.println("Invalid subcategory. Please try again.");
                

            }
    }
    
    public static void printNoProductsMessage() {
        System.out.println("\nYou have no products listed.");
    }

    public static void printProductAddedSuccess() {
        System.out.println("\nProduct added successfully!");
    }

    public static void printProductAddedFailure() {
        System.out.println("\nFailed to add product. Please try again.");
    }

    public static void printProductUpdatedSuccess() {
        System.out.println("\nProduct updated successfully!");
    }

    public static void printProductUpdatedFailure() {
        System.out.println("\nFailed to update product. Please try again.");
    }

    public static void printProductDeletedSuccess() {
        System.out.println("\nProduct deleted successfully!");
    }

    public static void printProductDeletedFailure() {
        System.out.println("\nFailed to delete product. Please try again.");
    }

    public static void printInvalidProductId() {
        System.out.println("\nInvalid Product ID. Please try again.");
    }


    public static void printProductList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("\nNo products available.");
            return;
        }
        System.out.println("\n===== PRODUCT LIST =====");
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + 
                               ", Name: " + product.getProductName() + 
                               ", Price: Rs" + product.getPrice() + 
                               ", Stock: " + product.getStock() + 
                               ", Category: " + product.getMainCategory());
        }
    }

    public static String printBuyerOptions() {
        return "\n==== BUYER OPTIONS ====\n" +
                "1. View All Products\n" +
                "2. Manage Cart\n" +
                "3. Manage Wishlist\n" +
                "4. Manage Orders\n" +
                "5. View Best-Selling Products\n" +
                "6. View Most Liked Products\n" +
                "7. Back\n" +
                "8. Exit";
    }
    public static String printCartMenu() {
        return "\n==== CART MENU ====\n" +
                "1. Add to Cart\n" +
                "2. Remove from Cart\n" +
                "3. View Cart\n" +
                "4. Back\n" + 
                "5. Exit";
    }
    public static String printWishlistMenu() {
        return "\n==== WISHLIST MENU ====\n" +
                "1. Add to Wishlist\n" +
                "2. Remove from Wishlist\n" +
                "3. View Wishlist\n" +
                "4. Back\n"+
                "5. Exit";

    }
    public static String printOrderMenu() {
        return "\n==== ORDER MENU ====\n" +
                "1. Place Order\n" +
                "2. View Order History\n" +
                "3. Back\n" +
                "4. Exit";
    }
   
        public static void printStatisticsMenu() {
            System.out.println("\n===== Statistics Menu =====");
            System.out.println("1. View Best-Seller Products");
            System.out.println("2. View Most Liked Products");
            System.out.println("3. Back\n");
            System.out.println("4. Exit");
           
        }
    

        public static void printAdminFunctionalities() {
            System.out.println("\n========== Admin Dashboard ==========");
            System.out.println("1. View Revenue Statistics");
            System.out.println("2. View Seller & Buyer Statistics");
            System.out.println("3. view All Users");
            System.out.println("4. Monitor Inventory");
            System.out.println("5. Back ");
            System.out.println("6. Exit");
        }

        public static void printRevenueStatistics(double totalRevenue, Map<String, Double> revenueByCategory, Map<String, Double> revenueBySubcategory) {
            System.out.println("\n========== Revenue Statistics ==========");
            System.out.println("\nTotal Revenue: $" + totalRevenue);
            System.out.println("\nRevenue by Category:");
            for (Map.Entry<String, Double> entry : revenueByCategory.entrySet()) {
                System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
            }
    
            System.out.println("\nRevenue by Subcategory:");
            for (Map.Entry<String, Double> entry : revenueBySubcategory.entrySet()) {
                System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
            }
        }
    
        public static void printBestSellingProducts(List<String> products) {
            System.out.println("\n========== Best Selling Products ==========");
            if (products.isEmpty()) {
                System.out.println("No data available.");
            } else {
                for (String product : products) {
                    System.out.println(product);
                }
            }
        }
    
        public static void printMostLikedProducts(List<String> products) {
            System.out.println("\n========== Most Liked Products ==========");
            if (products.isEmpty()) {
                System.out.println("No data available.");
            } else {
                for (String product : products) {
                    System.out.println(product);
                }
            }
        }
    
        public static void printTopSellingSellers(List<String> sellers) {
            System.out.println("\n========== Top Selling Sellers ==========");
            if (sellers.isEmpty()) {
                System.out.println("No data available.");
            } else {
                for (String seller : sellers) {
                    System.out.println("Seller ID: " + seller);
                }
            }
        }
    
        public static void printAllUsers(List<User> users) {
            System.out.println("\n========== User List ==========");
            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                for (User user : users) {
                    System.out.println(user);
                }
            }
        }
    
        public static void printLowStockProducts(List<Product> products) {
            System.out.println("\n========== Low Stock Products ==========");
            if (products.isEmpty()) {
                System.out.println("All products are sufficiently stocked.");
            } else {
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        }
    
        public static void printOutOfStockProducts(List<Product> products) {
            System.out.println("\n========== Out of Stock Products ==========");
            if (products.isEmpty()) {
                System.out.println("No out-of-stock products.");
            } else {
                for (Product product : products) {
                    System.out.println(product);
                }
            }
         
        }

        
            public static void printNoProductsAvailable() {
                System.out.println("\nNo products available to update.");
            }
        
            public static void printProductNotFound() {
                System.out.println("\nProduct not found!");
            }
        
            public static void printUpdateMenu() {
                System.out.println("\nSelect what you want to update:");
                System.out.println("1. Update Product Name");
                System.out.println("2. Update Price");
                System.out.println("3. Update Stock");
                System.out.println("4. Update All Fields");
                System.out.println("5. Back");
                System.out.println("6. Exit");
            }
        
            public static void printUpdateSuccess(String field) {
                System.out.println("\n" + field + " updated successfully!");
            }
        
            public static void printUpdateFailed(String field) {
                System.out.println("\nFailed to update " + field + ".");
            }
        
        public static void printNoWishlistItems() {
            System.out.println("No items in your wishlist.");
        }
    
        public static void printWishlist() {
            System.out.println("\nYour Wishlist:");
        }
    
        public static void printWishlistItem(WishListItem item) {
            System.out.println(item); 
        
        }
        public static void printNoOrderHistory() {
            System.out.println("No order history found.");
        }
    
        public static void printOrderHistoryHeader() {
            System.out.println("\nYour Order History:");
        }
    
        public static void printOrder(Order order) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Username: " + order.getUsername());
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Transaction ID: " + order.getTransactionId());
            System.out.println("Items:");
            for (OrderItem item : order.getOrderItems()) {
                System.out.println("  - " + item.getProductName() + " (Quantity: " + item.getQuantity() + ")");
            }
            System.out.println("----------------------------------------------------");
        }
    
    } 
        
    
    
   



