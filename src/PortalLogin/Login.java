package PortalLogin;
import java.sql.*;
import View.ShowLoginMenu;

/*
*******************************************************************************************************
*   @Class Name         : Login
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class initializes the login menu for the eCommerce system.
*******************************************************************************************************
*/

public class Login {
      /*
    *********************************************************
    *  @Method Name    : main
    *  @Description    : Entry point of the application that shows the login menu.
    *  @throws         : SQLException
    *********************************************************
    */
    public static void main(String[] args) throws SQLException {
        try {

            ShowLoginMenu.showPlatformMenu();
      
         } catch (Exception e) {
            e.printStackTrace();
         }
         
        }
}
        
