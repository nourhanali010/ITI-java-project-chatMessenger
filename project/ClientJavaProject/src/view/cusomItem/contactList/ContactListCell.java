/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cusomItem.contactList;

import DTOModel.UserDTO;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import view.login.LoginController;


/**
 * class to load cell of contact list and update items
 * @author asmaa
 */
public class ContactListCell extends ListCell<UserDTO> {

    FXMLLoader loader;

//    public static ContactItemController controller;

    @Override
    protected void updateItem(UserDTO model, boolean bln) {
        super.updateItem(model, bln);

        if (model != null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("contactItem.fxml"));
               ContactItemController  controller = new ContactItemController();
                loader.setController(controller);
                Node root = loader.load();

                System.out.println(controller);
                controller.setModel(model);
                setGraphic(root);
                this.setOnMousePressed((MouseEvent event) -> {
                    try {
                        if(!LoginController.controller.isStartGroup){
                         if( !LoginController.controller.isAllContactList){
                         LoginController.controller.addGroupMember(model);
                         }else {
                             LoginController.controller.tapHandler(model);
                               LoginController.controller.getResieverData(model);
                         }
                        }else{
                            
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ContactListCell.class.getName()).log(Level.SEVERE, null, ex);
                    }


                });
            } catch (IOException ex) {
                Logger.getLogger(ContactListCell.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            setGraphic(null);
        }
    }
}
