package Services;
import dbRepository.UserRepository;
import models.Platform;
import models.User;
import Constant.*;

public class UserService {
    public static String registerUser(String username, String password, String role, Platform platform) {
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

    public static String loginUser(String username, String password, String role, Platform platform) {
        String authenticatedUser = UserRepository.authenticate(username, password, role, platform.getPlatformId());
        if (authenticatedUser != null) {
            Printer.printLoginSuccess(role);
        } else {
            Printer.printInvalidLogin(role);
        }
        return authenticatedUser;
    }

    
}
