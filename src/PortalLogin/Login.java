package PortalLogin;
import java.sql.*;

import View.ShowLoginMenu;

public class Login {
    public static void main(String[] args) throws SQLException {
        try {

            ShowLoginMenu.showPlatformMenu();
      
         } catch (Exception e) {
            e.printStackTrace();
         }
 }
}
        
