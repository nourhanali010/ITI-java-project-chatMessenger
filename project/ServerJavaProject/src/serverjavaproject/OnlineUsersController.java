/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjavaproject;

import DAOImplementation.UserDAOImpl;
import DBManeger.DBManeger;
import DTOModel.UserDTO;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author asmaa
 */
public class OnlineUsersController implements Initializable {

    @FXML
    private ListView<?> list;
    @FXML
    private Button refre;
    
    DBManeger dbm;
    UserDAOImpl udi;
    Connection con;

     /**
     * Initializes the controller class.
     * @param url .
     * @param rb .
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dbm = new DBManeger();
        udi = new UserDAOImpl();
        refresh();
    }    

     /**
     * List all online users
     */
    @FXML
    private void refresh() {
        dbm.DBStartConnection();
        con = dbm.getConnection();
        
        ArrayList<UserDTO> userList = udi.allOnlineUsers(con);
        ArrayList<HBox> boxList = null;
        //ObservableList data = null;
        
        if (userList != null) {
            boxList = new ArrayList<>();
            for (int i = 0; i < userList.size(); i++) {
                HBox hb = new HBox(new Text(userList.get(i).getUserName()));
                hb.setPadding(new Insets(5));
                hb.setStyle("-fx-background-color: #eac9bf");
                boxList.add(hb);                
            }
            ObservableList data = FXCollections.observableArrayList(boxList);
            list.setItems(data);
        }
        
        dbm.DBStopConnection();
    }
    
}