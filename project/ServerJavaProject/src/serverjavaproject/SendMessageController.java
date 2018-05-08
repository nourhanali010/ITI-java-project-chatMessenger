/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjavaproject;

import DAOImplementation.UserDAOImpl;
import Utilities.RMIUtilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author asmaa
 */
public class SendMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
        UserDAOImpl udi;

    @FXML
    private TextArea txtArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        udi= new UserDAOImpl();
    }  
    
    /**
     * send Admin mail  to all online users
     */
    
    @FXML
    private void sendPressed(){
        System.out.println(txtArea.getText());
//        udi.
        RMIUtilities.getInstance().annoniEmail(txtArea.getText());
        
    }
    
}
