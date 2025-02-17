package controller;

import Services.UserService;
import Utils.InputScanner;
import Utils.Validator;
import Constant.Printer;
import models.Platform;

import java.sql.SQLException;

/**
 * Controller for user registration and login.
 * Handles input, validation, and interaction with the UserService layer.
 */
public class UserController {
    private static final InputScanner input = InputScanner.getInstance(); // Singleton instance for reading input

    /**
     * Registers a new user for the specified role (e.g., Buyer, Seller, Admin).
     * 
     * @param role     The role of the user (e.g., "Buyer" or "Seller").
     * @param platform The platform on which the user is registering.
     * @throws SQLException If an error occurs while interacting with the database.
          * @throws ClassNotFoundException 
          */
         public static void registerUser(String role, Platform platform) throws SQLException, ClassNotFoundException {
        Printer.printRegistrationHeader(role); // Print registration header
        Printer.printUsernameRules(); // Display username validation rules

        String username;
        do {
            // Prompt the user to enter a username
            username = input.readString("Enter " + role + " Username: ");
            
            // Validate the username and print an error message if invalid
            if (!Validator.isValidUsername(username)) {
                Printer.printInvalidUsername(); // Notify the user of invalid input
            }
        } while (!Validator.isValidUsername(username)); // Loop until a valid username is entered

        Printer.printPasswordRules(); // Display password validation rules

        String password;
        do {
            // Prompt the user to enter a password
            password = input.readString("Enter " + role + " Password: ");
            
            // Validate the password and print an error message if invalid
            if (!Validator.isValidPassword(password)) {
                Printer.printInvalidPassword(); // Notify the user of invalid input
            }
        } while (!Validator.isValidPassword(password)); // Loop until a valid password is entered

        // Register the user by calling the service layer
        String registeredUser = UserService.registerUser(username, password, role, platform);
        
        // If the registration is successful, print a success message
        if (registeredUser != null) {
            Printer.printRegistrationSuccess(role);
        }
    }

    /**
     * Logs in a user for the specified role (e.g., Buyer, Seller, Admin).
     * 
     * @param role     The role of the user attempting to log in (e.g., "Buyer" or "Seller").
     * @param platform The platform being used for login.
     * @return The username if login is successful, or null if the login fails.
     * @throws SQLException If an error occurs while interacting with the database.
          * @throws ClassNotFoundException 
          */
         public static String loginUser(String role, Platform platform) throws SQLException, ClassNotFoundException {
        // Prompt the user to enter a username and password
        String username = input.readString("Enter " + role + " Username: ");
        String password = input.readString("Enter " + role + " Password: ");

        // Attempt to log in the user using the service layer
        return UserService.loginUser(username, password, role, platform);
    }
}
