/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import ClientImpl.ClientImpl;
import DTOModel.MessageDTO;
import DTOModel.UserDTO;
import Model.cashing.UserData;
import Utilities.RMIUtilities;
import Utilities.ValidationUtilities;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import view.cusomItem.chatMsg.MessageCell;
import view.cusomItem.chatMsg.MsgItemCotroller;
import view.cusomItem.contactList.ContactItemController;
import view.cusomItem.contactList.ContactListCell;
import view.login.ClientJavaProject;
import view.login.LoginController;

/**
 *
 * @author asmaa
 * @author nourhan
 */
public class HomeController implements Initializable {

    String receiver;

    @FXML
    private HBox hBoxG;

    @FXML
    private TextField txtGroupName;

    @FXML
    private Button btnStartGroup;

    @FXML
    private HBox hBoxAllContacts;

    @FXML
    private HBox hBoxGroup;

    @FXML
    private HBox hBoxChat;

    @FXML
    private ListView<MessageDTO> listViewMsg;

    @FXML
    private ListView<UserDTO> listViewMenu;

    private TextField txtMsg;

    @FXML
    private Label imgMyImage;

    /**
     *
     */
    public static int txtFont = 1;

    MsgItemCotroller itemController = new MsgItemCotroller();

    /**
     *
     */
    public static boolean isAllContactList = false;

    /**
     *
     */
    public static boolean isStartGroup = false;
    String name;

    /**
     *
     */
    public static UserDTO userChat = new UserDTO();
    @FXML
    private Button btnClear;
    @FXML
    private Text txtCount;

    @FXML
    private TabPane tabpane;

    private Tab tab;

    int count = 0;

    @FXML
    private TextField txtEmailReq;

    /**
     *
     */
    public static String currentlyChosenFriend;
    int i = 0;

    /**
     *
     */
    public ArrayList<UserDTO> userGroup = new ArrayList<>();
    ContactItemController contactController = new ContactItemController();
    ArrayList<UserDTO> user = new ArrayList<>();
    ClientImpl clientimpl;
    @FXML
    private MenuItem Away;
    @FXML
    private MenuItem Busy;
    @FXML
    private MenuItem Available;
    @FXML
    private MenuItem Offline;
    @FXML
    private ComboBox<String> statusBox;
    @FXML
    private Label signout;

    /**
     *
     * @return //
     */
    public TabPane getTabpane() {
        return tabpane;
    }

    /**
     *
     * @return ..
     */
    public Tab getTab() {
        return tab;
    }
    ChaaatController ct;

    HashMap<Integer, String> tabs = new HashMap<>();
    HashMap<String, ChaaatController> controllerMap = new HashMap<>();

    /**
     *
     * @param userT to name tab with name's friend
     * @throws IOException To Create New Tab when want to chat with a friend
     */
    public void tapHandler(UserDTO userT) throws IOException {
        if (!tabs.containsValue(userT.getUserEmail())) {

            System.out.println("tabHandler------");
            currentlyChosenFriend = userT.getUserEmail();

            Tab tt = new Tab();
            tabs.put(userT.getUserId(), userT.getUserEmail());
            tabpane.getTabs().add(tt);

            tt.setText(userT.getUserName());
            tabpane.setVisible(true);
            tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chaaat.fxml"));
            ct = new ChaaatController();
            controllerMap.put(userT.getUserEmail(), ct);
            loader.setController(ct);
            tt.setContent((Node) loader.load());
            System.out.println("-----------------------------------------------------");
            System.out.println(ct);
        }
    }

    /**
     *
     * @param userT //
     * @param groupName //
     * create tab to make chat one to many
     */
    public void tapHandlerGroup(ArrayList<UserDTO> userT, String groupName) {
        if (!tabs.containsValue(groupName)) {
            try {
                Tab tt = new Tab();
                tabs.put((++count), groupName);
                tabpane.getTabs().add(tt);
                tt.setText(groupName);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chaaat.fxml"));
                tt.setContent((Node) loader.load());
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     */
    @FXML
    public void btnClearPresed() {
        userGroup.clear();
        txtCount.setText("0");
        i = 0;
    }

    /**
     *
     * @param user add friend to group chat
     */
    public void addGroupMember(UserDTO user) {
        i++;
        txtCount.setText(Integer.toString(i));
        userGroup.add(user);
    }

    /**
     *
     * @param user set data to userChat
     */
    public void getResieverData(UserDTO user) {
        userChat.setUserName(user.getUserImage());
        userChat.setUserEmail(user.getUserEmail());
    }

    /**
     * send request to anew friend
     */
    @FXML
    private void iconRequestPressed() {
        System.out.println("icon clicked request");
        System.out.println(txtEmailReq.getText());
        if (ValidationUtilities.checkStringEmpty(txtEmailReq.getText())) {
            RMIUtilities.getInstance().setSendRequestFriend(txtEmailReq.getText().toString(), UserData.id);
        } else {
            ValidationUtilities.ShowDialog("Request", "plz enter request mail");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabpane.setVisible(false);
        try {
            clientimpl = new ClientImpl(LoginController.controller);

            RMIUtilities.getInstance().setHelloRef(UserData.Email, clientimpl);
            System.out.println("-----------------init");
            hBoxG.setVisible(false);
            listViewMsg.setCellFactory(new Callback<ListView<MessageDTO>, ListCell<MessageDTO>>() {
                public ListCell<MessageDTO> call(ListView<MessageDTO> p) {
                    return new MessageCell();
                }
            });

            listViewMenu.setCellFactory(new Callback<ListView<UserDTO>, ListCell<UserDTO>>() {
                public ListCell<UserDTO> call(ListView<UserDTO> p) {
                    return new ContactListCell();
                }
            });
            System.out.println(contactController);
//        contactList();
        } catch (RemoteException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        statusBox.getItems().addAll(
                "online",
                "offline",
                "busy",
                "away");

        imgMyImage.setText(UserData.name);

    }

    /**
     * show request list of friends
     */
    public void requestList() {
        listViewMenu.getItems().clear();

        user = RMIUtilities.getInstance().setRequestList((UserData.id));//t(UserData.id);
        if (user != null) {
            for (int i = 0; i < user.size(); i++) {
                listViewMenu.getItems().addAll(user.get(i));

            }
        }

    }

    /**
     * show your contactList
     */
    public void contactList() {

        user = RMIUtilities.getInstance().setContactList(UserData.id);
        if (user != null) {
            for (int i = 0; i < user.size(); i++) {
                listViewMenu.getItems().addAll(user.get(i));

            }
        }
    }

    /**
     * This method shows the notification to the user with whom it's from
     * @param email //
     * @param msg //
     * @param content //
     */
    public void notification(String email, String msg, String content) {

        if (Platform.isFxApplicationThread()) {
            Notifications notifBuilder = Notifications.create()
                    .title(content)
                    .text(email)
                    .graphic(null)
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(5));

            notifBuilder.showConfirm();
        } else {
            Platform.runLater(() -> {

                Notifications notifBuilder = Notifications.create()
                        .title(content)
                        .text(email + " " + content)
                        .graphic(null)
                        .position(Pos.BOTTOM_RIGHT)
                        .hideAfter(Duration.seconds(5));

                notifBuilder.showConfirm();
            });

        }
    }

    /**
     * This methods is called by the client remote object to display the message
     * to the user. The methods then calls viewMsg() method from
     * ChaaatController
     *
     * @param msg receive message
     */
    public void setmsg(MessageDTO msg) {

        System.out.println("==============================================");
        String id = msg.getFrom();
        System.out.println("iiiiiiiiiiiiiiid: " + tabs.containsValue(id));

        ct.viewMsg(msg);
//       
    }

    /**
     *
     * @param event send message for your message
     */
    public void enterTxtMsgPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                if (txtMsg.getText().trim().length() != 0) {
                    MessageDTO message = new MessageDTO();
                    message.setMsg(txtMsg.getText());
                    message.setTo(userChat.getUserEmail());
                    message.setFrom(UserData.Email);

                    RMIUtilities.getInstance().setTellOthers(message);
                    txtMsg.clear();
                }
                break;

        }

    }

    /**
     * click on image to send message
     *
     */
    public void imgSendClick2() {
        if (!txtMsg.getText().equals(null)) {
            txtMsg.clear();
        }

        if (!txtMsg.getText().equals(null)) {
            txtMsg.clear();
        }
    }

    /**
     * to open list of contact list
     */
    @FXML
    public void allContacClick() {
        hBoxG.setVisible(false);
        isAllContactList = true;
        isStartGroup = false;

        //#51475e clicked
        //#362f3f un
        if (Platform.isFxApplicationThread()) {
            listViewMenu.getItems().clear();
            contactList();
            hBoxAllContacts.setStyle("-fx-background-color: #51475e ");
            hBoxChat.setStyle("-fx-background-color: #362f3f ");
            hBoxGroup.setStyle("-fx-background-color: #362f3f ");
        } else {
            Platform.runLater(() -> {
                listViewMenu.getItems().clear();
                contactList();
                contactController.setInVisableCheckBox();
                hBoxAllContacts.setStyle("-fx-background-color: #51475e ");
                hBoxChat.setStyle("-fx-background-color: #362f3f ");
                hBoxGroup.setStyle("-fx-background-color: #362f3f ");
            });
        }
    }

    /**
     * create new group
     */
    @FXML
    public void GroupClick() {
        hBoxG.setVisible(true);
        isAllContactList = false;
        isStartGroup = false;

        if (Platform.isFxApplicationThread()) {
            listViewMenu.getItems().clear();
            contactList();
            hBoxGroup.setStyle("-fx-background-color:  #51475e ");
            hBoxAllContacts.setStyle("-fx-background-color: #362f3f");
            hBoxChat.setStyle("-fx-background-color: #362f3f ");
        } else {
            Platform.runLater(() -> {
                listViewMenu.getItems().clear();
                contactList();
                hBoxGroup.setStyle("-fx-background-color:  #51475e ");
                hBoxAllContacts.setStyle("-fx-background-color: #362f3f");
                hBoxChat.setStyle("-fx-background-color: #362f3f ");
            });
        }

    }

    /**
     * request friend list
     */
    @FXML
    public void ChatClick() {
        hBoxG.setVisible(false);
        isStartGroup = true;

        requestList();
        hBoxChat.setStyle("-fx-background-color: #51475e ");

        hBoxGroup.setStyle("-fx-background-color:  #362f3f ");
        hBoxAllContacts.setStyle("-fx-background-color: #362f3f");
    }

    @FXML
    private void btnStartGroupChat() {
        ArrayList<UserDTO> user = new ArrayList<>();
        if (ValidationUtilities.checkStringEmpty(txtGroupName.getText())) {/*txtGroupName.getText().trim().length() != 0*/

            tapHandlerGroup(userGroup, txtGroupName.getText());
        } else {
            ValidationUtilities.ShowDialog("group name", "plz enter ur group name");
        }
    }

    /**
     * update statue
     */
    @FXML
    public void userStatusUpdate() {

        RMIUtilities.getInstance().setUpdateStatus(statusBox.getValue(), UserData.id);

    }

    /**
     * sign out
     */

    @FXML
    private void signoutAction(MouseEvent event) {

        RMIUtilities.getInstance().setUpdateStatus("offline", UserData.id);
        RMIUtilities.getInstance().unregister(clientimpl);

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 890, 680);
            System.out.println("login scene " + scene);
            ClientJavaProject.s.setScene(scene);
            ClientJavaProject.s.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
