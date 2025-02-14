package Utils;

public class Validator {
    public static boolean isValidUsername(String username){
        return username.matches("^[a-zA-Z0-9]{5,9}$");
    }
    public static boolean isValidPassword(String password){
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }
    
}
