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
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userImage;
    private String userStatus;

    private int RequestId;
    private String requestName;

   
    public int getRequestId() {
        return RequestId;
    }

    /**
     *
     * @param RequestId .
     */
    public void setRequestId(int RequestId) {
        this.RequestId = RequestId;
    }

    /**
     *
     * @return .
     */
    public String getRequestName() {
        return requestName;
    }

    /**
     *
     * @param requestName .
     */
    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    /**
     *
     * @return .
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     *
     * @param userStatus .
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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
     * @param userEmail .
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

    public int getUserId() {
        return userId;
    }

  
    public String getUserName() {
        return userName;
    }

   
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

  
    public String getUserGender() {
        return userGender;
    }

   
    public String getUserImage() {
        return userImage;
    }    
}
