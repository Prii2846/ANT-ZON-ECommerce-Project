package controller;

import Services.UserService;
import Utils.InputScanner;
import Utils.Validator;
import Constant.Printer;
import models.Platform;

import java.sql.SQLException;
/*
*******************************************************************************************************
*   @Class Name         : UserController
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class manages user authentication, including registration and login.
*******************************************************************************************************
*/

public class UserController {
    private static final InputScanner input = InputScanner.getInstance(); 

    /*
    *********************************************************
    *  @Method Name    : registerUser
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Handles user registration for different roles.
    *  @param          : String role - The role of the user.
    *                    Platform platform - The platform being used.
    *  @throws         : SQLException, ClassNotFoundException
    *********************************************************
    */
        public static void registerUser(String role, Platform platform) throws SQLException, ClassNotFoundException {
        Printer.printRegistrationHeader(role); 
        Printer.printUsernameRules(); 

        String username;
        do {
        
            username = input.readString("Enter " + role + " Username: ");
            

            if (!Validator.isValidUsername(username)) {
                Printer.printInvalidUsername(); 
            }
        } while (!Validator.isValidUsername(username));

        Printer.printPasswordRules(); 

        String password;
        do {
            password = input.readString("Enter " + role + " Password: ");
            if (!Validator.isValidPassword(password)) {
                Printer.printInvalidPassword(); 
            }
        } while (!Validator.isValidPassword(password));
        String registeredUser = UserService.registerUser(username, password, role, platform);
        if (registeredUser != null) {
            Printer.printRegistrationSuccess(role);
        }
    }

   /*
    *********************************************************
    *  @Method Name    : loginUser
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Handles user login for different roles.
    *  @param          : String role - The role of the user.
    *                    Platform platform - The platform being used.
    *  @return         : String - Returns the username if login is successful.
    *  @throws         : SQLException, ClassNotFoundException
    *********************************************************
    */
         public static String loginUser(String role, Platform platform) throws SQLException, ClassNotFoundException {
     
        String username = input.readString("Enter " + role + " Username: ");
        String password = input.readString("Enter " + role + " Password: ");
        return UserService.loginUser(username, password, role, platform);
    }
}
