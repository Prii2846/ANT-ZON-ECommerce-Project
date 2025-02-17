package models;

// Represents a user in the system
public class User {
    private int id;                       // Unique identifier for the user
    private String username;              // Username for the user
    private String password;              // Password for the user (should be encrypted in a real system)
    private String role;                  // Role of the user (e.g., Admin, Seller, Buyer)
    private Platform platform;            // Platform associated with the user

    // Constructor for creating a User with ID, username, and role
    public User(int id, String username, String role) {
        this.id = id;                     // Initialize the user's ID
        this.username = username;         // Initialize the user's username
        this.role = role;                 // Initialize the user's role
    }

    // Constructor for creating a User with username, password, role, and platform
    public User(String username, String password, String role, Platform platform) {
        this.username = username;         // Initialize the user's username
        this.password = password;         // Initialize the user's password
        this.role = role;                 // Initialize the user's role
        this.platform = platform;         // Initialize the user's associated platform
    }

    // Returns the user's ID
    public int getId() {
        return id;
    }

    // Sets the user's ID
    public void setId(int id) {
        this.id = id;                     // Update the user's ID
    }

    // Returns the user's username
    public String getUsername() {
        return username;
    }

    // Returns the user's password
    public String getPassword() {
        return password;
    }

    // Returns the user's role
    public String getRole() {
        return role;
    }

    // Returns the platform ID associated with the user
    public int getPlatformId() {
        return platform.getPlatformId();  // Retrieve the platform's ID
    }

    // Provides a string representation of the User object
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", role=" + role + "]";
    }
}
