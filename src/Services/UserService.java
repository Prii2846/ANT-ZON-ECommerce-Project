package Services;

import Constant.*;
import dbRepository.UserRepository;
import models.Platform;
import models.User;

/*
*******************************************************************************************************
*   @Class Name         : UserService
*   @Author             : Priyanka Kumari (priyanka.kuamri@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This service class handles user authentication and registration functionalities,
*                         including checking for duplicate usernames, registering new users, and login authentication.
*******************************************************************************************************
*/
public class UserService {

     /*
    *********************************************************
    *  @Method Name    : registerUser
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Registers a new user after validating username uniqueness.
    *  @param          : String username - The username of the new user
    *                  : String password - The password of the new user
    *                  : String role - The role of the new user (Admin, Seller, Buyer)
    *                  : Platform platform - The platform in which the user is being registered
    *  @return         : String - Returns the registered username if successful, otherwise null
    *  @throws         : ClassNotFoundException
    *********************************************************
    */
    public static String registerUser(String username, String password, String role, Platform platform) throws ClassNotFoundException {
       
        if (UserRepository.isUsernameExists(username, platform.getPlatformId())) {
            Printer.printUsernameExists();
            return null;
        }

        
        String registeredUser = UserRepository.registerUser(new User(username, password, role, platform));

        
        if (registeredUser != null) {
            Printer.printRegistrationSuccess(role);
        }
        return registeredUser;
    }

/*
    *********************************************************
    *  @Method Name    : loginUser
    *  @Author         : Priyanka Kumari (priyanka.kuamri@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Authenticates a user based on provided credentials.
    *  @param          : String username - The username of the user
    *                  : String password - The password of the user
    *                  : String role - The role of the user (Admin, Seller, Buyer)
    *                  : Platform platform - The platform in which the user is logging in
    *  @return         : String - Returns the authenticated username if successful, otherwise null
    *  @throws         : ClassNotFoundException
    *********************************************************
    */
    public static String loginUser(String username, String password, String role, Platform platform) throws ClassNotFoundException {
        String authenticatedUser = UserRepository.authenticate(username, password, role, platform.getPlatformId());
        if (authenticatedUser != null) {
            Printer.printLoginSuccess(role);
        } else {
          
            Printer.printInvalidLogin(role);
        }
        return authenticatedUser;
    }
}
