package Utils;
/*
*******************************************************************************************************
*   @Class Name         : Validator
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : Utility class for validating user credentials such as username and password.
*******************************************************************************************************
*/
public class Validator {
      /*
    *********************************************************
    *  @Method Name    : isValidUsername
    *  @Description    : Validates if the username meets the required pattern.
    *  @param          : String (username)
    *  @return         : boolean (true if valid, false otherwise)
    *********************************************************
    */
    public static boolean isValidUsername(String username){
        return username.matches("^[a-zA-Z0-9]{5,9}$");
    }
      /*
    *********************************************************
    *  @Method Name    : isValidPassword
    *  @Description    : Validates if the password meets the required pattern.
    *  @param          : String (password)
    *  @return         : boolean (true if valid, false otherwise)
    *********************************************************
    */
    public static boolean isValidPassword(String password){
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }
    
}
