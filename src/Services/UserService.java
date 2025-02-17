package Services;

import Constant.*;
import dbRepository.UserRepository;
import models.Platform;
import models.User;

public class UserService {

    // Registers a new user in the system
    public static String registerUser(String username, String password, String role, Platform platform) throws ClassNotFoundException {
        // Check if the username already exists for the given platform
        if (UserRepository.isUsernameExists(username, platform.getPlatformId())) {
            // Print message if username already exists
            Printer.printUsernameExists();
            return null;
        }

        // Attempt to register the user in the database
        String registeredUser = UserRepository.registerUser(new User(username, password, role, platform));

        // Print success message if registration is successful
        if (registeredUser != null) {
            Printer.printRegistrationSuccess(role);
        }

        // Return the registered username (or null if registration failed)
        return registeredUser;
    }

    // Authenticates a user during login
    public static String loginUser(String username, String password, String role, Platform platform) throws ClassNotFoundException {
        // Attempt to authenticate the user with the provided credentials and role
        String authenticatedUser = UserRepository.authenticate(username, password, role, platform.getPlatformId());

        // Print success message if authentication is successful
        if (authenticatedUser != null) {
            Printer.printLoginSuccess(role);
        } else {
            // Print error message if authentication fails
            Printer.printInvalidLogin(role);
        }

        // Return the authenticated username (or null if authentication failed)
        return authenticatedUser;
    }
}
