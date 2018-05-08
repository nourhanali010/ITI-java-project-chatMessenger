/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.SignUp;

import view.login.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author asmaa
 */
public class ClientLoginProject extends Application {
    
    /**
     * A reference to the stage
     */
    public static Stage s;
    @Override
    public void start(Stage stage) throws Exception {
                s= stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("viewCss.css").toExternalForm());
        

        stage.setScene(scene);
       // stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
