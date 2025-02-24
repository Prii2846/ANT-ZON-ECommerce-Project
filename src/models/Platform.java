package models;

/*
*******************************************************************************************************
*   @Class Name         : Platform
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents an eCommerce platform with a unique platform ID and name.
*******************************************************************************************************
*/
public class Platform {
  
    private int platformId;
    private String platformName;

   /*
    *********************************************************
    *  @Constructor    : Platform
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a Platform with provided details.
    *  @param          : int platformId - Unique platform identifier
    *                  : String platformName - Name of the platform
    *********************************************************
    */
    public Platform(int platformId, String platformName) {
        this.platformId = platformId;                 
        this.platformName = platformName;            
    }

     /*
    *********************************************************
    *  @Method Name    : getPlatformId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the platform ID.
    *  @return         : int - Returns the unique platform ID.
    *********************************************************
    */
    public int getPlatformId() {
        return platformId;  
    }

        /*
    *********************************************************
    *  @Method Name    : setPlatformId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the platform ID.
    *  @param          : int platformId - The unique platform ID to be set.
    *********************************************************
    */
    public void setPlatformId(int platformId) {
        this.platformId = platformId;  
    }

     /*
    *********************************************************
    *  @Method Name    : getPlatformName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the platform name.
    *  @return         : String - Returns the platform name.
    *********************************************************
    */
    public String getPlatformName() {
        return platformName; 
    }

   /*
    *********************************************************
    *  @Method Name    : setPlatformName
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the platform name.
    *  @param          : String platformName - The platform name to be set.
    *********************************************************
    */
    public void setPlatformName(String platformName) {
        this.platformName = platformName;  
    }
}
