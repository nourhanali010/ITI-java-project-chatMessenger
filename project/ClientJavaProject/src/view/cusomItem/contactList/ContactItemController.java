/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cusomItem.contactList;

import DTOModel.UserBinding;
import DTOModel.UserDTO;
import Utilities.RMIUtilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import view.login.LoginController;
import view.main.HomeController;
import static view.main.HomeController.isStartGroup;

/**
 *
 * @author asmaa
 */
/**
 * Controller class of the contact list cell used to view a cell for a contact
 * depending on the contact info
 *
 */
public class ContactItemController implements Initializable {

    @FXML
    private Text textContact;
    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnAccept;

    @FXML
    private Button btnReject;

    /**
     *
     * @return //
     */
    public Button getBtnAccept() {
        return btnAccept;
    }

    /**
     *
     * @return //
     */
    public Button getBtnReject() {
        return btnReject;
    }

    /**
     *
     * @return ..
     */
    public AnchorPane getAnchor() {
        return anchor;
    }

    @FXML
    private ImageView imgStatus;

    @FXML
    private CheckBox checkBoxContact;

    private UserDTO model;
    private UserBinding modelBinding;
    private UserDTO userChat = new UserDTO();

    /**
     *
     * @return ..
     */
    public UserDTO getUserChat() {
        return userChat;
    }

    /**
     *
     * @return ..
     */
    public CheckBox getCheckBoxContact() {
        return checkBoxContact;
    }

    Thread t = new Thread();

    /**
     * initializer of this controller.
     *
     * @param location ..
     * @param resources ..
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkBoxContact.setVisible(false);

        if (!LoginController.controller.isStartGroup) {
            imgStatus.setVisible(true);
            btnAccept.setVisible(false);
            btnReject.setVisible(false);
        } else {
            btnAccept.setVisible(true);
            btnReject.setVisible(true);
            imgStatus.setVisible(false);

        }

    }

    /**
     * Checks whether the value of this cell check box
     * 
     * @return ..
     */

    public boolean isCheckBox() {
        return checkBoxContact.isSelected();
    }

    /**
     * This method is called to set the cell view depending on the data from
     * user object
     *
     * @param user set data to user chat
     */
    public void getResieverData(UserDTO user) {
        userChat.setUserName(user.getUserImage());
        System.out.println(user.getUserName());
//        return userChat;
    }

    /**
     *
     */
    public void setInVisableCheckBox() {
        if (Platform.isFxApplicationThread()) {
            checkBoxContact.setVisible(false);
        } else {
            Platform.runLater(() -> {
                checkBoxContact.setVisible(false);
            });
        }
    }

    private final ChangeListener<String> LABEL_CHANGE_LISTENER = new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> property, String oldValue, String newValue) {
            updateStatusBindingView(newValue);
            if (!oldValue.equals(newValue)) {
                System.out.println(model.getUserName() + " okay");
            }

        }
    };

    /**
     *
     * @param model ..
     */
    public void setModel(UserDTO model) {
        this.model = model;
        modelBinding = new UserBinding(model.getUserStatus());
        if (model != null) {
            System.out.println("moooooooooooooooooodel-----");
            updateView();
            updateStatusBindingView();
        }
    }

    private void updateStatusBindingView() {
        updateStatusBindingView(model.getUserStatus());
    }

    private void updateStatusBindingView(String status) {
        modelBinding.statusProperty().addListener(LABEL_CHANGE_LISTENER);

    }

    private void updateView() {
        updateNameView();
        updateStatusView();

    }

    /**
     * Update name in your view
     */
    private void updateNameView() {
        if (LoginController.controller.isStartGroup) {
            updateNameView(model.getRequestName());
        } else {
            updateNameView(model.getUserName());
        }
    }

    private void updateNameView(String username) {
        textContact.setText(username);
    }

    private void updateStatusView() {
        if (!LoginController.controller.isStartGroup) {
            updateStatusView(model.getUserStatus());
        }
    }

    /**
     *
     * @param userStatuse set image to your status
     */
    private void updateStatusView(String userStatuse) {
        if (userStatuse.equals("online")) {
            imgStatus.setImage(new Image("/src/img/available_with_text.png"));
        }
        if (userStatuse.equals("away")) {
            imgStatus.setImage(new Image("/src/img/away_with_text.png"));
        }
        if (userStatuse.equals("busy")) {
            imgStatus.setImage(new Image("/src/img/busy_with_text.png"));
        }
        if (userStatuse.equals("offline")) {
            imgStatus.setImage(new Image("/src/img/available_with_text.png"));
        }

    }

    /**
     * accept request
     */
    @FXML
    private void btnAcceptPressed() {
        RMIUtilities.getInstance().deletRequest(model.getRequestName(), model.getRequestId(), true);

    }

    /**
     * reject request
     */
    @FXML
    private void btnRejectPressed() {
        RMIUtilities.getInstance().deletRequest(model.getRequestName(), model.getRequestId(), false);

    }

}
