package Utils;

import java.util.*;

/**
 * Utility class for reading input from the user. 
 * Implements the Singleton design pattern to ensure only one instance of the scanner is used.
 */
public class InputScanner {
    private static InputScanner instance; // Singleton instance
    private final Scanner sc; // Scanner object to read input from System.in

    // Private constructor to prevent instantiation
    private InputScanner(){
        this.sc = new Scanner(System.in); // Initialize the scanner
    }

    /**
     * Get the singleton instance of InputScanner.
     * 
     * @return The instance of InputScanner
     */
    public static InputScanner getInstance(){
        if(instance == null){
            instance = new InputScanner(); // Create the instance if it doesn't exist
        }
        return instance; // Return the singleton instance
    }

    /**
     * Reads a string from the user input.
     * 
     * @param data The prompt message to display to the user
     * @return The string entered by the user
     * @throws IllegalArgumentException If an invalid input is provided (empty string)
     */
    public String readString(String data) throws IllegalArgumentException{
        System.out.print(data); // Print prompt message
        String input = sc.nextLine().trim(); // Read input and trim any surrounding whitespace

        // If input is empty, throw an exception
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        return input; // Return the trimmed input string
    }

    /**
     * Reads an integer from the user input.
     * Continues prompting the user until a valid integer is entered.
     * 
     * @param data The prompt message to display to the user
     * @return The integer entered by the user
     * @throws NumberFormatException If the user enters a value that is not a valid integer
     */
    public int readInt(String data) throws NumberFormatException {
        System.out.print(data); // Print prompt message
        String input = sc.nextLine().trim(); // Read input and trim surrounding whitespace
        
        // Attempt to parse the input to an integer
        if (!input.matches("-?\\d+")) { // Validate input before parsing
            throw new NumberFormatException("Invalid number format");
        }

        return Integer.parseInt(input); // Return the parsed integer
    }

    /**
     * Reads a double from the user input.
     * Continues prompting the user until a valid double is entered.
     * 
     * @param data The prompt message to display to the user
     * @return The double entered by the user
     * @throws NumberFormatException If the user enters a value that is not a valid double
     */
    public double readDouble(String data) throws NumberFormatException {
        System.out.print(data); // Print prompt message
        String input = sc.nextLine().trim(); // Read input and trim surrounding whitespace

        // Attempt to parse the input to a double
        if (!input.matches("-?\\d+(\\.\\d+)?")) { // Validate input before parsing
            throw new NumberFormatException("Invalid number format");
        }

        return Double.parseDouble(input); // Return the parsed double
    }
}
