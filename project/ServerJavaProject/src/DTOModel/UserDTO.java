/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author asmaa
 */
public class UserDTO implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userImage;
    private int userAge;
    private String userStatus;
    
    private int RequestId;
    private String requestName;
    
   // private int contactId;

    public int getRequestId() {
        return RequestId;
    }

    public void setRequestId(int RequestId) {
        this.RequestId = RequestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

     
  

public UserDTO(int id,String name , String status , String email){
    this.userId=id;
     this.userName = name;
     this.userStatus= status;
     this.userEmail = email;
     
 }

    public UserDTO() {
    }


    /**
     *
     * @param userId .
     */

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @param userName .
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param userEmail  .
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     *
     * @param userPassword .
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * @param userGender .
     */
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    /**
     *
     * @param userImage .
     */
    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }


    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

   
    /**
     *
     * @return .
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return .
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return .
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     *
     * @return .
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * @return .
     */
    public String getUserGender() {
        return userGender;
    }





    public int getUserAge() {
        return userAge;
    }


    public String getUserStatus() {
        return userStatus;
    }


    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
        
        
   
    
    
}
