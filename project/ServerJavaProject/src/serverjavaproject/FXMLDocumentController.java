/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjavaproject;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import BusinessLayerImpl.ChatServerImpl;
import Utilities.RMIUtilities;
import static Utilities.RMIUtilities.getInstance;
import java.rmi.AccessException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
//import org.controlsfx.control.Notifications;

/**
 *
 * @author asmaa
 */
public class FXMLDocumentController implements Initializable {

    Registry reg1;
    ChatServerImpl serverImpl1;

    @FXML
    private ToggleButton btn;

    @FXML
    private ToggleButton t;

    @FXML
    private AnchorPane anchor;

    @FXML
    private HBox chartBox;

    @FXML
    private HBox onlineBox;

    @FXML
    private HBox messageBox;

    @FXML
    private HBox tableBox;

    private boolean isClicked = true;
    private int init = 1;

    /**
     * initialize
     *
     * @param url .
     * @param rb .
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t.setVisible(false);

    }

      /**
     * Draw Charts using data retrieved from database 
     * @param event
     * @throws @throws IOException

     */
    @FXML
    private void charts(MouseEvent event) throws IOException {

        chartBox.setStyle("-fx-background-color: #51475e;");
        tableBox.setStyle("-fx-background-color: #362f3f;");
        messageBox.setStyle("-fx-background-color: #362f3f;");
        onlineBox.setStyle("-fx-background-color: #362f3f;");
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("charts.fxml"));
        anchor.getChildren().setAll(node);
    }

     /**
     * Print table of all users
     * @throws IOException
     */
    @FXML
    private void userTable(MouseEvent event) throws IOException {

        tableBox.setStyle("-fx-background-color: #51475e;");
        chartBox.setStyle("-fx-background-color: #362f3f;");
        messageBox.setStyle("-fx-background-color: #362f3f;");
        onlineBox.setStyle("-fx-background-color: #362f3f;");
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("userTable.fxml"));
        anchor.getChildren().setAll(node);
    }
    
    

      /**
     * List all online users
     * @param event
     * @throws IOException
     * 
     */
    @FXML
    private void onlineUsers(MouseEvent event) throws IOException {

        onlineBox.setStyle("-fx-background-color: #51475e;");
        tableBox.setStyle("-fx-background-color: #362f3f;");
        messageBox.setStyle("-fx-background-color: #362f3f;");
        chartBox.setStyle("-fx-background-color: #362f3f;");
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("onlineUsers.fxml"));
        anchor.getChildren().setAll(node);
    }

     /**
     * Send message from server to all online users
     * @param event
     * @throws IOException
     */
    @FXML
    private void sendMessage(MouseEvent event) throws IOException {

        messageBox.setStyle("-fx-background-color: #51475e;");
        tableBox.setStyle("-fx-background-color: #362f3f;");
        onlineBox.setStyle("-fx-background-color: #362f3f;");
        chartBox.setStyle("-fx-background-color: #362f3f;");
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("sendMessage.fxml"));
        anchor.getChildren().setAll(node);

    }

    @FXML
    private void close(MouseEvent event) throws IOException {
        System.exit(0);

    }
    
    /**
     * Start server
     * 
     * @throws IOException
     */

    @FXML
    private void startServer() {

        RMIUtilities.getInstance();
        btn.setVisible(false);
        t.setVisible(true);
        System.out.println("start");

    }
    
 /**
     * Stop server
     * @param event
     * @throws IOException
     */
    @FXML
    private void stopServer() {
        RMIUtilities.getInstance().stopServer();
        System.out.println("stop");
        btn.setVisible(true);
        t.setVisible(false);
    }

}
