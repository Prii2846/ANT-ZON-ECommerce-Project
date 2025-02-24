package models;
import java.util.*;

/*
*******************************************************************************************************
*   @Class Name         : ProductType
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents different product types within main categories 
*                         and subcategories in the eCommerce platform.
*******************************************************************************************************
*/
public record ProductType(String mainCategory, String subCategory, List<String> productType) {
      /*
    *********************************************************
    *  @Constant        : MOBILE
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Mobile subcategory in Electronics.
    *                   Contains different types of mobile phones.
    *********************************************************
    */
    public static final ProductType MOBILE = new ProductType(
        "Electronics",                     
        "Mobile",                          
        List.of("Smartphone_Android",      
                "Smartphone_iOS", 
                "Keypad")
    );
      /*
    *********************************************************
    *  @Constant        : LAPTOPS
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Laptops subcategory in Electronics.
    *                   Contains different types of laptops.
    *********************************************************
    */

    public static final ProductType LAPTOPS = new ProductType(
        "Electronics",                     
        "Laptops",                          
        List.of("Business_Laptop",          
                "Gaming_Laptop")
    );
  /*
    *********************************************************
    *  @Constant        : WEARABLES
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Wearables subcategory in Electronics.
    *                   Contains different types of wearable devices.
    *********************************************************
    */
    public static final ProductType WEARABLES = new ProductType(
        "Electronics",                     
        "Wearables",                       
        List.of("Smartwatches",             
                "Fitness_Trackers")
    );

    /*
    *********************************************************
    *  @Constant        : PRINTERS
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Printers subcategory in Electronics.
    *                   Contains different types of printers.
    *********************************************************
    */
    
    public static final ProductType PRINTERS = new ProductType(
        "Electronics",                     
        "Printers",
        List.of("Inkjet_Printers",         
                "Laser_Printers")
    );

    /*
    *********************************************************
    *  @Constant        : ROOM
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Room subcategory in Furniture.
    *                   Contains different types of room furniture.
    *********************************************************
    */
    public static final ProductType ROOM = new ProductType(
        "Furniture",                       
        "Room",                           
        List.of("Sofa",                     
                "Bed")
    );

     /*
    *********************************************************
    *  @Constant        : OFFICE
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Office subcategory in Furniture.
    *                   Contains different types of office furniture.
    *********************************************************
    */
    public static final ProductType OFFICE = new ProductType(
        "Furniture",                        
        "Office",                        
        List.of("Desk",                   
                "Chair")
    );

/*
    *********************************************************
    *  @Constant        : EVENT
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Represents the Event subcategory in Furniture.
    *                   Contains different types of event-related furniture.
    *********************************************************
    */
    public static final ProductType EVENT = new ProductType(
        "Furniture",                       
        "Event",                            
        List.of("Stage",                  
                "Decoration")
    );
}
