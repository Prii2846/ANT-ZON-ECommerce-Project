package models;

import java.util.*;

// Represents a product type with a main category, subcategory, and list of product types
public record ProductType(String mainCategory, String subCategory, List<String> productType) {

    // ProductType constant representing Mobile subcategory under Electronics
    public static final ProductType MOBILE = new ProductType(
        "Electronics",                     // Main category: Electronics
        "Mobile",                           // Subcategory: Mobile
        List.of("Smartphone_Android",       // List of product types under Mobile
                "Smartphone_iOS", 
                "Keypad")
    );

    // ProductType constant representing Laptops subcategory under Electronics
    public static final ProductType LAPTOPS = new ProductType(
        "Electronics",                      // Main category: Electronics
        "Laptops",                          // Subcategory: Laptops
        List.of("Business_Laptop",          // List of product types under Laptops
                "Gaming_Laptop")
    );

    // ProductType constant representing Wearables subcategory under Electronics
    public static final ProductType WEARABLES = new ProductType(
        "Electronics",                      // Main category: Electronics
        "Wearables",                        // Subcategory: Wearables
        List.of("Smartwatches",             // List of product types under Wearables
                "Fitness_Trackers")
    );

    // ProductType constant representing Printers subcategory under Electronics
    public static final ProductType PRINTERS = new ProductType(
        "Electronics",                      // Main category: Electronics
        "Printers",                         // Subcategory: Printers
        List.of("Inkjet_Printers",          // List of product types under Printers
                "Laser_Printers")
    );

    // ProductType constant representing Room subcategory under Furniture
    public static final ProductType ROOM = new ProductType(
        "Furniture",                        // Main category: Furniture
        "Room",                             // Subcategory: Room
        List.of("Sofa",                     // List of product types under Room
                "Bed")
    );

    // ProductType constant representing Office subcategory under Furniture
    public static final ProductType OFFICE = new ProductType(
        "Furniture",                        // Main category: Furniture
        "Office",                           // Subcategory: Office
        List.of("Desk",                     // List of product types under Office
                "Chair")
    );

    // ProductType constant representing Event subcategory under Furniture
    public static final ProductType EVENT = new ProductType(
        "Furniture",                        // Main category: Furniture
        "Event",                            // Subcategory: Event
        List.of("Stage",                    // List of product types under Event
                "Decoration")
    );
}
