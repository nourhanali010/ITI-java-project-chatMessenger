/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import DTOModel.MessageDTO;
import Model.cashing.UserData;
import Utilities.RMIUtilities;
import XML.HistoryType;
import XML.MessageType;
import XML.ObjectFactory;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import view.cusomItem.chatMsg.MessageCell;
import view.cusomItem.chatMsg.MsgItemCotroller;
import view.login.LoginController;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * This is a controller class for the chat window created whenever a user
 * chooses a friend to chat with
 *
 * @author Nourhan
 */
public class ChaaatController implements Initializable {

    @FXML
    private ImageView imgMenuChat;
    @FXML
    private Label lableMenuChatName;
    @FXML
//    private ListView<Node> listViewMsg;
    private ListView<MessageDTO> listViewMsg;
    @FXML
    private ImageView imgEmoji;
    @FXML
    private TextField txtMsg;
    @FXML
    private ImageView imgSend;
    @FXML
    private ImageView transferFile;

    @FXML
    private ImageView sendFile;
    @FXML
    private ImageView saveChat;
    @FXML
    private ColorPicker color;

    /**
     *
     */
    public static String colorl;
    private ArrayList<MessageDTO> messages = new ArrayList<>();

    MsgItemCotroller itemController = new MsgItemCotroller();

    /**
     *
     */
    public static int i = 1;

    /**
     * Initializes the controller class.
     * @param url //
     * @param rb //
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listViewMsg.setCellFactory(new Callback<ListView<MessageDTO>, ListCell<MessageDTO>>() {
            public ListCell<MessageDTO> call(ListView<MessageDTO> p) {
                return new MessageCell();
            }
        });

    }

    @FXML
    private void imgSendClick2(KeyEvent event) {
    }

    /**
     * This method is called when the user presses on the send file button. It
     * prompts the user with a file chooser. And then it call the sendFileRmi()
     * method in the clientImpl class passing to it the required parameters
     */
    @FXML
    public void sendFileRmi() {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(txtMsg.getScene().getWindow()); //in case it didn't work try "new Stage()" as a parameter
            if (file != null) {
                FileInputStream fileInputStream = new FileInputStream(file);
                String userName = LoginController.controller.currentlyChosenFriend; //should be retrieved from the currently opened tab in the view
                RMIUtilities.getInstance().sendFileRmi(fileInputStream, userName, file.getName());
                System.out.println("chatcontroller finished");
            }
        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    private void setArialFont() {

        LoginController.controller.txtFont = 1;
    }

    @FXML
    private void setVerdanaFont() {
        LoginController.controller.txtFont = 2;
    }

    @FXML
    private void setComicFont() {
        LoginController.controller.txtFont = 3;
    }

    @FXML
    private void setColor() {
        colorl = toRGBCode(color.getValue());
        System.out.println("---------------------------------color");

    }

    /**
     * This method is responsible for displaying the receiver message on this
     * view
     *
     * @param msg display message on view
     */
    void viewMsg(MessageDTO msg) {

        if (Platform.isFxApplicationThread()) {
            MessageDTO msgM = new MessageDTO();
            msgM.setMsg(msg.getMsg());
            msgM.setFrom(msg.getFrom());
            msgM.setTo(msg.getTo());
            msgM.setSizeFont(20);
            listViewMsg.setCellFactory(new Callback<ListView<MessageDTO>, ListCell<MessageDTO>>() {
                public ListCell<MessageDTO> call(ListView<MessageDTO> p) {
                    return new MessageCell();
                }
            });
            listViewMsg.getItems().add(msgM);
            messages.add(msgM);
            System.err.println("<<<<<<<<" + listViewMsg);

        } else {
            Platform.runLater(() -> {
            });
            MessageDTO msgM = new MessageDTO();
            msgM.setMsg(msg.getMsg());
            msgM.setFrom(msg.getFrom());
            msgM.setTo(msg.getTo());
            listViewMsg.setCellFactory(new Callback<ListView<MessageDTO>, ListCell<MessageDTO>>() {
                public ListCell<MessageDTO> call(ListView<MessageDTO> p) {
                    return new MessageCell();
                }
            });
            messages.add(msgM);
            listViewMsg.getItems().add(msgM);
            System.err.println(">>>>>>>>>" + listViewMsg);
        }
    }

    /**
     * This method is responsible for sending a message when the user presses
     * ENTER when typing in the text field. It calls the RMI method
     * setTellOthers().
     *
     * @param event
     */
    @FXML
    private void enterTxtMsgPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                System.out.println("Ennterrrrrrrrrrrrrrr");
                if (txtMsg.getText().trim().length() != 0) {
                    MessageDTO message = new MessageDTO();
                    message.setMsg(txtMsg.getText());
                    message.setTo(LoginController.controller.userChat.getUserEmail());
                    message.setFrom(UserData.Email);
                    message.setColor("White");
                    message.setFontFamily("Arial");
                    message.setSizeFont(20);
                    // messages.add(++i, message);
                    RMIUtilities.getInstance().setTellOthers(message);
                    txtMsg.clear();
                    // messages.add(message);
                }
                break;
        }
    }

    /**
     * This method is used to saves an xml file used to produce an html file
     * have the same shape as the chat windows and its contents. It basically
     * saves the chat as an xml file.
     *
     * @throws TransformerException //
     */
    public void saveXml() throws TransformerException {
        try {
            JAXBContext context = JAXBContext.newInstance("XML");

            ObjectFactory factory = new ObjectFactory();

            HistoryType history = factory.createHistoryType();
            history.setOwner(UserData.Email);

            List<MessageType> messagesXML = history.getMessage();

            //-------------- parse object message ot messageType ------------------
            for (MessageDTO message : messages) {
                if (UserData.Email.equals(message.getFrom()) || UserData.Email.equals(message.getTo())) {
                    MessageType messageType = factory.createMessageType();
                    messageType.setFrom(message.getFrom());
                    messageType.setBody(message.getMsg());
                    messageType.setTo(message.getTo());
                    messageType.setSize(message.getSizeFont());
                    messagesXML.add(messageType);
                }
            }
            JAXBElement<HistoryType> createHistory = factory.createHistory(history);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //-------------- set Schcema ------------------
            marsh.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "history.xsd");
            //-------------- set XLST ------------------
            marsh.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml-stylesheet type='text/xsl' href='history.xsl'?>");
            marsh.marshal(createHistory, new FileOutputStream("XMLMODEL/history" + ".xml"));

        } catch (JAXBException ex) {
            Logger.getLogger(ChaaatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChaaatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param color /
     * @return /
     */
    public static String toRGBCode(javafx.scene.paint.Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

}
