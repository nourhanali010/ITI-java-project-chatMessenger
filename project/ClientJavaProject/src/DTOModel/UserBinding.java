/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author asmaa
 */
public class UserBinding implements Serializable {

    private IntegerProperty userid;
    private StringProperty username;
    private StringProperty useremail;
    private StringProperty userpassword;
    private StringProperty gender;
    private IntegerProperty userage;
    private StringProperty userStatuse;

    private static final long serialVersionUID = 6529685098267757690L;
    
    
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(getUsername());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.username = new SimpleStringProperty((String) s.readObject());
    }

    public UserBinding(String username, String email, String gender, int age, String status) {
        this.username = new SimpleStringProperty(username);
        this.useremail = new SimpleStringProperty(email);
        this.gender = new SimpleStringProperty(gender);
        this.userage = new SimpleIntegerProperty(age);
        this.userStatuse = new SimpleStringProperty(status);
    }
    
    
  
    public UserBinding(String status) {

        this.userStatuse = new SimpleStringProperty(status);
    }

  
    public UserBinding(int id, String password, String username, String email, String gender, int age, String status) {
        this.userid = new SimpleIntegerProperty(id);
        this.userpassword = new SimpleStringProperty(password);
        this.username = new SimpleStringProperty(username);
        this.useremail = new SimpleStringProperty(email);
        this.gender = new SimpleStringProperty(gender);
        this.userage = new SimpleIntegerProperty(age);
        this.userStatuse = new SimpleStringProperty(status);
    }

   
    public UserBinding(String email, String password) {
        this.useremail = new SimpleStringProperty(email);
        this.userpassword = new SimpleStringProperty(password);
    }

   
    public IntegerProperty idProperty() {
        return userid;
    }


    public StringProperty statusProperty() {
        return userStatuse;
    }

   
    public IntegerProperty ageProperty() {
        return userage;
    }

    public StringProperty emailProperty() {
        return useremail;
    }
  
      public StringProperty nameProperty() {
        return username;
    }
      
    
    public StringProperty passwordProperty() {
        return userpassword;
    }

    public void setUserid(int userid) {
        this.userid.set(userid);
    }

  
    public int getUserid() {
        return userid.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    
    public String getUsername() {
        return username.get();
    }

  
    public void setUseremail(String useremail) {
        this.useremail.set(useremail);
    }

    public String getUseremail() {
        return useremail.get();
    }

    public void setUserpassword(String userpassword) {
        this.userpassword.set(userpassword);
    }

   
    public String getUserpassword() {
        return userpassword.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getGender() {
        return gender.get();
    }

   
    public void setUserage(int userage) {
        this.userage.set(userage);
    }

    
    public int getUserage() {
        return userage.get();
    }

   
    public void setUserStatuse(String userStatuse) {
        this.userStatuse.set(userStatuse);
    }

  
    public String getUserStatuse() {
        return userStatuse.get();
    }

}
