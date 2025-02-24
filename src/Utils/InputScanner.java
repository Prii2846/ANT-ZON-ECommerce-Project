package Utils;

import java.util.*;

/*
*******************************************************************************************************
*   @Class Name         : InputScanner
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : Utility class for handling user input through a singleton Scanner instance.
*******************************************************************************************************
*/
public class InputScanner {
    private static InputScanner instance; 
    private final Scanner sc; 

   /*
    *********************************************************
    *  @Constructor    : InputScanner
    *  @Description    : Private constructor to initialize the Scanner instance.
    *********************************************************
    */
    private InputScanner(){
        this.sc = new Scanner(System.in); 
    }

    /*
    *********************************************************
    *  @Method Name    : getInstance
    *  @Description    : Returns the singleton instance of InputScanner.
    *  @return         : InputScanner instance
    *********************************************************
    */
    public static InputScanner getInstance(){
        if(instance == null){
            instance = new InputScanner(); 
        }
        return instance; 
    }

        /*
    *********************************************************
    *  @Method Name    : readString
    *  @Description    : Reads a string input from the user.
    *  @param          : String (Prompt message)
    *  @return         : String (User input)
    *  @throws         : IllegalArgumentException if input is empty
    *********************************************************
    */
    public String readString(String data) throws IllegalArgumentException{
        System.out.print(data); 
        String input = sc.nextLine().trim(); 

        
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        return input; 
    }

       /*
    *********************************************************
    *  @Method Name    : readInt
    *  @Description    : Reads an integer input from the user.
    *  @param          : String (Prompt message)
    *  @return         : int (User input)
    *  @throws         : NumberFormatException if input is not a valid integer
    *********************************************************
    */
    public int readInt(String data) throws NumberFormatException {
        System.out.print(data); 
        String input = sc.nextLine().trim(); 
        if (!input.matches("-?\\d+")) { 
            throw new NumberFormatException("Invalid number format");
        }

        return Integer.parseInt(input); 
    }

     /*
    *********************************************************
    *  @Method Name    : readDouble
    *  @Description    : Reads a double input from the user.
    *  @param          : String (Prompt message)
    *  @return         : double (User input)
    *  @throws         : NumberFormatException if input is not a valid double
    *********************************************************
    */
    public double readDouble(String data) throws NumberFormatException {
        System.out.print(data); 
        String input = sc.nextLine().trim(); 

       
        if (!input.matches("-?\\d+(\\.\\d+)?")) { 
            throw new NumberFormatException("Invalid number format");
        }

        return Double.parseDouble(input); 
    }
}
