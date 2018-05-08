/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import ClientImpl.ClientImpl;
import DTOModel.UserDTO;
import Model.cashing.UserData;
import Utilities.RMIUtilities;
import Utilities.ValidationUtilities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.SignUp.ClientLoginProject;
import view.SignUp.SignUpController;
import view.main.HomeController;

/**
 *
 * @author asmaa
 */
/**
 * class controller Login
 */
public class LoginController implements Initializable {
//    

    ClientImpl clientimpl;

    /**
     *
     */
    public static HomeController controller;

    /**
     *
     */
    public static SignUpController sign;
    @FXML
    private TextField txtViewEmail;
    @FXML
    private TextField txtViewPass;

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * This method take the information input by the user and does some basic
     * validation before verifying the password against the database
     */
    @FXML
    public void loginPressed() {
        boolean isDone = false;
        UserDTO obj = new UserDTO();
        obj.setUserEmail(txtViewEmail.getText());
        obj.setUserPassword(txtViewPass.getText());
        if (ValidationUtilities.checkStringEmpty(txtViewEmail.getText())
                && ValidationUtilities.checkStringEmpty(txtViewPass.getText())) {
            if (ValidationUtilities.checkEmail(txtViewEmail.getText())) {
                if (Platform.isFxApplicationThread()) {
                    isDone = RMIUtilities.getInstance().setLogin(obj);
                    System.out.println(isDone + "  2");
                    if (isDone) {
                        try {
                            RMIUtilities.getInstance().setUpdateStatus("online", UserData.id);
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

                        }
                    } else {
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
                            final boolean isDone = RMIUtilities.getInstance().setRegistration(obj);

                            if (isDone) {
                                try {
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
                                    //System.out.println(TAG + ex.getMessage());
                                }
                            }
                        }
                    });

                }
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //show invalid email messege
                        ValidationUtilities.ShowDialog("Error", "Inavlid Email address.");
                    }
                });
            }
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //show wrong email or password messege
                    ValidationUtilities.ShowDialog("Error", "Complete All Fields!");

                }
            });
        }
    }

    /**
     * This method is called when the user presses the sign up button. It
     * directs the user to sign up page
     */
    @FXML
    public void signUpPressed() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp/SignUp.fxml"));
            sign = new SignUpController();
            loader.setController(sign);
            Parent root = loader.load();
            System.out.println("homeeee root " + root);
            Scene scene = new Scene(root, 890, 680);
            System.out.println("homeeee scene " + scene);
            ClientJavaProject.s.setScene(scene);
            ClientJavaProject.s.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //}
}
