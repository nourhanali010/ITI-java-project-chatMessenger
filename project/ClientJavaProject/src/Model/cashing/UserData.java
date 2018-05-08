/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.cashing;

import java.io.Serializable;

/**
 *
 * @author asmaa
 */
/**
 * This class represents the client and his/her information as static variables
 * for caching purposes during the opened session and contains same attributes as the UserDTO class
 * 
 */
public class UserData implements Serializable{
    
    /**
     *
     */
    public static int id;

    /**
     *
     */
    public static String name;

    /**
     *
     */
    public static String Email;

    /**
     *
     */
    public static boolean gender;

    /**
     *
     */
    public static String password;

    /**
     *
     */
    public static String image ;

    
}
