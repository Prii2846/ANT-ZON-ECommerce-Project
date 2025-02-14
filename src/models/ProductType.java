package models;
import java.util.*;

public record ProductType(String mainCategory, String subCategory, List<String> productType) {


public static final ProductType MOBILE = new ProductType("Electronics", "Mobile", List.of("Smartphone_Android", "Smartphone_iOS", "Keypad"));
public static final ProductType LAPTOPS = new ProductType("Electronics", "Laptops", List.of("Business_Laptop", "Gaming_Laptop"));
public static final ProductType WEARABLES = new ProductType("Electronics", "Wearables", List.of("Smartwatches", "Fitness_Trackers"));
public static final ProductType PRINTERS = new ProductType("Electronics", "Printers", List.of("Inkjet_Printers", "Laser_Printers"));


public static final ProductType ROOM = new ProductType("Furniture", "Room", List.of("Sofa", "Bed"));
public static final ProductType OFFICE = new ProductType("Furniture", "Office", List.of("Desk", "Chair"));
public static final ProductType EVENT = new ProductType("Furniture", "Event", List.of("Stage", "Decoration"));


}
