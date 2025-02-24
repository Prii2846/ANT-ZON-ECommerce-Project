package models;

/*
*******************************************************************************************************
*   @Class Name         : User
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class represents a user in the eCommerce platform with attributes 
*                         such as ID, username, password, role, and platform.
*******************************************************************************************************
*/
public class User {
    private int id;                      
    private String username;              
    private String password;            
    private String role;                
    private Platform platform;           

   /*
    *********************************************************
    *  @Constructor    : User
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a User with ID, username, and role.
    *  @param          : int id - Unique user identifier
    *                  : String username - Username of the user
    *                  : String role - Role of the user (Admin, Seller, Buyer)
    *********************************************************
    */
    public User(int id, String username, String role) {
        this.id = id;                    
        this.username = username;         
        this.role = role;               
    }

   /*
    *********************************************************
    *  @Constructor    : User
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Initializes a User with username, password, role, and platform.
    *  @param          : String username - Username of the user
    *                  : String password - Password of the user
    *                  : String role - Role of the user (Admin, Seller, Buyer)
    *                  : Platform platform - Associated platform details
    *********************************************************
    */
    public User(String username, String password, String role, Platform platform) {
        this.username = username;        
        this.password = password;        
        this.role = role;                
        this.platform = platform;        
    }

 /*
    *********************************************************
    *  @Method Name    : getId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the user ID.
    *  @return         : int - Returns the unique user ID.
    *********************************************************
    */
    public int getId() {
        return id;
    }

  /*
    *********************************************************
    *  @Method Name    : setId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Sets the user ID.
    *  @param          : int id - The unique user ID to be set.
    *********************************************************
    */
    public void setId(int id) {
        this.id = id;                    
    }

 /*
    *********************************************************
    *  @Method Name    : getUsername
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the username.
    *  @return         : String - Returns the username.
    *********************************************************
    */
    public String getUsername() {
        return username;
    }

  /*
    *********************************************************
    *  @Method Name    : getPassword
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the password.
    *  @return         : String - Returns the user password.
    *********************************************************
    */
    public String getPassword() {
        return password;
    }
 /*
    *********************************************************
    *  @Method Name    : getRole
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the role of the user.
    *  @return         : String - Returns the user role.
    *********************************************************
    */
   
    public String getRole() {
        return role;
    }

     /*
    *********************************************************
    *  @Method Name    : getPlatformId
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Retrieves the platform ID associated with the user.
    *  @return         : int - Returns the platform ID.
    *********************************************************
    */
    public int getPlatformId() {
        return platform.getPlatformId();  
    }

   /*
    *********************************************************
    *  @Method Name    : toString
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Provides a string representation of the User object.
    *  @return         : String - Returns the user details in string format.
    *********************************************************
    */
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
    }
}
