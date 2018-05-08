/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cusomItem.chatMsg;

import DTOModel.MessageDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author asmaa
 */
public class MsgItemCotroller {

    @FXML
    private Label lableMsg;

    @FXML
    private Label lableMsgTime;

    @FXML
    private ImageView imgUserMsg;

    @FXML
    private Label labelUsrName;
    @FXML
    private HBox hboxMsgItem;

    private MessageDTO model;

    /**
     *
     * @return //
     */
    public Label getLableMsg() {
        return lableMsg;
    }

    /**
     * This method set the model/ cell of a message on the view depending on the
     * received MessageDTO object
     *
     * @param model //
     */
    public void setModel(MessageDTO model) {
        this.model = model;
        lableMsg.setText(model.getMsg());
        lableMsg.setWrapText(true);
        lableMsg.setWrapText(true);
        lableMsg.setStyle("-fx-background-color:#ca9f93; -fx-background-radius: 50 50 50 50 ");
    }

}
