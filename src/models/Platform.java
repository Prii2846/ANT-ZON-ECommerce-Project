package models;

// Represents a platform in the eCommerce system
public class Platform {
    // The unique identifier for the platform
    private int platformId;

    // The name of the platform (e.g., website, mobile app)
    private String platformName;

    // Constructor to initialize the platform with an ID and name
    public Platform(int platformId, String platformName) {
        this.platformId = platformId;                 // Set the platform ID
        this.platformName = platformName;             // Set the platform name
    }

    // Getter for platformId
    public int getPlatformId() {
        return platformId;  // Return the platform ID
    }

    // Setter for platformId
    public void setPlatformId(int platformId) {
        this.platformId = platformId;  // Update the platform ID
    }

    // Getter for platformName
    public String getPlatformName() {
        return platformName;  // Return the platform name
    }

    // Setter for platformName
    public void setPlatformName(String platformName) {
        this.platformName = platformName;  // Update the platform name
    }
}
