/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author asmaa
 */
public class ValidationUtilities {
      private final static  Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-.]+([A-Za-z0-9-_.]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$");
    private final static  Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9_.]{3,20}");
    private final static  Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]{3,10}");
    private final static  Pattern IP_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    private static Matcher match;

    /**
     *
     * @param header .
     * @param contentText .
     */
    public static void ShowDialog(String header,String contentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(contentText);
        alert.setHeaderText(header);
        alert.showAndWait();
        
    }
    /**
     * check that email address not empty or correct
     * @param email .
     * @return .
     * 
     */
    public static boolean checkEmail(String email){
         match = EMAIL_PATTERN.matcher(email);
         return !email.trim().equals("") && match.matches();
    }
    
    /**
     * check that username not empty or correct
     * @param username .
     * @return  .
     * 
     */
    public static boolean checkUserName(String username){
        match = USERNAME_PATTERN.matcher(username);
        return   checkStringEmpty(username) && match.matches();
    }
    
    
    /**
     * check that name not empty or correct
     * @param name .
     * @return  .
     *  
     */
    public static boolean checkName(String name){
        match = NAME_PATTERN.matcher(name);
        return   checkStringEmpty(name) && match.matches();
    }
    
   
    public static boolean checkIP(String ip){
        match = IP_PATTERN.matcher(ip);
        return match.matches();
    }
   
    public static boolean checkStringEmpty(String str ){
         return !(str == null || str.trim().length() == 0 || "".equals(str.trim())) ;
    }
    
    
  
    public static boolean checkStringLength(String str , int min , int max){
        return !(str.length() > max || str.length() < min );
    }
}
