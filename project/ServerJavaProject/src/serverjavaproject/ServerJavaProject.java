/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjavaproject;

import BusinessLayerImpl.ChatServerImpl;
import DAOImplementation.UserDAOImpl;
import DBManeger.DBManeger;
import DTOModel.UserDTO;
import Utilities.RMIUtilities;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author asmaa
 */
public class ServerJavaProject extends Application {
    
        UserDTO userDTO = new UserDTO();
        UserDAOImpl user = new UserDAOImpl();
        DBManeger dBManager = new DBManeger();
 

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
