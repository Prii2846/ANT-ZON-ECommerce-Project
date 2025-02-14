package View;
import java.util.*;

public class InputScanner {
    private static InputScanner instance;
    private final Scanner sc;

    private InputScanner(){
        this.sc = new Scanner(System.in);

    }
    public static InputScanner getInstance(){
        if(instance==null){
            instance = new InputScanner();
        }
        return instance;
    }

    public String readString(String data){
          System.out.print(data);
          return sc.nextLine().trim();

    }
    public int readInt(String data){
        while (true) { 
            try {
                System.out.print(data);
                String input = sc.nextLine();
                return Integer.parseInt(input.trim());

            } catch (NumberFormatException e) {
                System.out.println("please enter a valid number");
            }
            
        }

    }
    public double readDouble(String data){
        while (true) { 
            try {
                System.out.print(data);
                String input = sc.nextLine();
                return Double.parseDouble(input.trim());

            } catch (NumberFormatException e) {
                System.out.println("please enter a valid number");

            }
            
        }
    }
   

    
}
