/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.SignUp;

import DTOModel.UserDTO;
import Utilities.RMIUtilities;
import Utilities.ValidationUtilities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.login.ClientJavaProject;
import view.login.LoginController;
import static view.login.LoginController.controller;
import view.main.HomeController;

/**
 * FXML Controller class
 *
 * @author asmaa
 */
public class SignUpController implements Initializable {

    /**
     * refrence from main stage in app
     */
    public static Stage s;
    private static String TAG = SignUpController.class.getName() + ": ";

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField repass;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextField pic;
    @FXML
    private Button browse;

    ObservableList<String> genderlist;

    /**
     * Initializes the controller class.
     *
     * @param url ..
     * @param rb ..
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        rmi = RMIUtilities.getInstance();
        genderlist = FXCollections.observableArrayList("Male", "Female");
        gender.setItems(genderlist);
    }

    /**
     * This method is called to create new account with the information provided
     * by the user. It also does some basic validation on the input, insuring
     * correct input from the user.
     */
    @FXML
    private void signUpA() {

        boolean isDone = false;
        UserDTO obj = new UserDTO();
        obj.setUserId(34);
        obj.setUserImage(pic.getText());
        obj.setUserName(username.getText());
        obj.setUserEmail(email.getText());
        obj.setUserPassword(pass.getText());
        obj.setUserGender(gender.getValue());
        obj.setUserStatus("online");

        if (ValidationUtilities.checkStringEmpty(username.getText())
                & ValidationUtilities.checkStringEmpty(email.getText())
                & ValidationUtilities.checkStringEmpty(pass.getText())
                & ValidationUtilities.checkStringEmpty(gender.getValue())) {

            if (ValidationUtilities.checkEmail(email.getText())) {
                //valid email             
                if (pass.getText().equals(repass.getText())) {
                    //valid password confirmation & should continue
                    isDone = RMIUtilities.getInstance().setRegistration(obj);

                    if (Platform.isFxApplicationThread()) {
                        if (isDone) {
                            try {
                                //move to login page
                                boolean isLogin;
                                isLogin = RMIUtilities.getInstance().setLogin(obj);
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main/project.fxml"));
                                controller = new HomeController();
                                loader.setController(controller);
                                Parent root = loader.load();
                                System.out.println("homeeee root " + root);
                                Scene scene = new Scene(root, 890, 680);
                                System.out.println("homeeee scene " + scene);
                                ClientJavaProject.s.setScene(scene);
                                ClientJavaProject.s.show();
                            } catch (IOException ex) {
                                System.out.println(TAG + ex.getMessage());
                            }
                        } else {
                            //singup fail
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //show wrong email or password messege;
                                    ValidationUtilities.ShowDialog("Error", "Signup failed!");
                                }
                            });
                        }
                    } else {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                final boolean isDone = RMIUtilities.getInstance().setRegistration(obj);

                                if (isDone) {
                                    try {
                                        Parent root = FXMLLoader.load(getClass().getResource("/view/login/login.fxml"));
                                        Scene scene = new Scene(root);

                                        ClientLoginProject.s.setScene(scene);
                                        ClientLoginProject.s.show();
                                    } catch (IOException ex) {
                                        System.out.println(TAG + ex.getMessage());
                                    }
                                }
                            }
                        });
                    }

                } else {
                    //invalid password confirmation
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //show wrong email or password messege
                            ValidationUtilities.ShowDialog("Error", "Invalid password confirmation!");
                        }
                    });
                }
            } else {
                //invalid mail handling
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //show wrong email or password messege
                        ValidationUtilities.ShowDialog("Error", "Wrong email or password. Try again please.");
                    }
                });

            }

        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //incomplete fields
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Complete All Fields!");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeCancel);
                    alert.showAndWait();
                }
            });

        }

    }

}
