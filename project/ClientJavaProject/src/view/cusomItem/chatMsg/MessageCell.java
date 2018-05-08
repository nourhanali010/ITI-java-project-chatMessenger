/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cusomItem.chatMsg;

import DTOModel.MessageDTO;
import Model.cashing.UserData;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import view.main.HomeController;

/**
 *
 * @author asmaa
 */
public class MessageCell extends ListCell<MessageDTO> {

    /**
     * This
     *
     * @param model /
     * @param bln update cell message in your view
     */
    @Override
    protected void updateItem(MessageDTO model, boolean bln) {
        super.updateItem(model, bln);

        if (model != null) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL location;

            if (model.getFrom().equals(UserData.Email)) {
                location = MsgItemCotroller.class.getResource("SenderMsgItem.fxml");
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

            } else {
                location = MsgItemCotroller.class.getResource("RecievermsgItem.fxml");

                fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            }
            try {
                Node root = (Node) fxmlLoader.load(location.openStream());
               MsgItemCotroller   controller = (MsgItemCotroller) fxmlLoader.getController();
                controller.setModel(model);
                System.out.println("messageeeeeeee*****");
                setGraphic(root);
            } catch (IOException ioe) {
                throw new IllegalStateException(ioe);
            }
        } else {
            setGraphic(null);
        }
    }
}
