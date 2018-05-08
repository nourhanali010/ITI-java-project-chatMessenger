/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientImpl;

import BusinessLayerInsterface.ChatClientInt;
import DTOModel.MessageDTO;
import Utilities.ValidationUtilities;
import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import view.login.LoginController;
import view.main.HomeController;

/**
 *
 * @author asmaa
 */

/**
 * This class represents the remote object of the client side. And it implements the chatClientInt interface.
 */
public class ClientImpl  extends UnicastRemoteObject implements ChatClientInt{

     /**
     * Constructor of this class
     * @param controller /
     * @throws RemoteException /
     */
    public ClientImpl(HomeController controller) throws RemoteException{
        LoginController.controller = controller;
        
    }
    
   
    /**
     * This method handles the notification for the add friend feature. It receives a notification and calls the
     * notification() method in the main controller HomeController to view the notification on the screen 
     * @param email email address of the notification receiver
     * @param myid id of the notification sender
     * 
     */
@Override
    public void notificationRequest(String email, int myid) throws RemoteException {
        System.out.println("===================notifi");
        LoginController.controller.notification(email," ","want add u");
    }
    
     /**
     * This method receives a file over RMI using RMIIO library and it prompts the user to accept
     * or decline the file transfer. In case of acceptance the method calls the writeToFile() method 
     * passing to it the necessary parameters
     * @param ris a remote input stream is used to transfer files over RMI
     * @param fileName the name of the file to  be received
     * @throws RemoteException /
     */
    @Override
    public void receiveFileRmi(RemoteInputStream ris, String fileName) throws RemoteException {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("File Confirmation");
            alert.setContentText("Do you want to recieve this file: " + fileName + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                new Thread(() -> {
                    InputStream is = null;
                    try {
                        is = RemoteInputStreamClient.wrap(ris);
                        writeToFile(is, fileName);
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            is.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Platform.runLater(() -> {
                        //could be replaced by notification
                        ValidationUtilities.ShowDialog("Note", "File is recieved");
                    });
                }).start();
            }
        });
    }

     /**
     * This method is called by the receiveFileRmi() method to write the file to the disk
     * it takes the file name as parameter and saves the file to the default "D:/" path.
     * @param is
     * @param fileName 
     */
    private void writeToFile(InputStream is, String fileName) {

        try {
            File file = new File("/home/asmaa/Desktop/"+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] byteArray = new byte[4096];
            int counter = 0;
            
            while ((counter = is.read(byteArray)) > 0) {
                fos.write(byteArray, 0, counter);
            }
                        
            fos.flush();
            if (fos != null) {
                fos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


  /**
     * This method checks whether the add-friend request is accepted or not
     * @param myId request sender id
     * @param userId request receiver id
     * @param isAccept indication of request result
     *
     * 
     */
    @Override
    public boolean isAddAcceptedRequest(int myId, int userId, boolean isAccept) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This methods handles the process of receiving a message. Its calls the setmsg() method in the chat window controller ChaaatController.
     * And passes the message object as a parameter.
     * @param msg 
     * the message object to be received
     *
     */
    @Override
    public void receive(MessageDTO msg) throws RemoteException {
        System.out.println(msg.getMsg()+"chatImp");
       LoginController.controller.setmsg(msg);
    }
    
     /**
     * This methods handles the notification process in case the add friend process was successful. 
     * It calls the notification() method to show the "accepted" notification
     * @param email
     * email of the notification receiver
     *
     */

    @Override
    public void notificationAcceptRequest(String email) throws RemoteException {
        LoginController.controller.notification(email," ","accept ur request");
    }
    
     /**
     * This methods handles the notification process in case the annon mail send successful. 
     * It calls the notification() method to show the "accepted" notification
     * 
     */

    @Override
    public void annonMsg(String msg) throws RemoteException {
        LoginController.controller.notification(msg,"","Admin");
    }

    @Override
    public void notificationStatus(String email,String status) throws RemoteException {
  LoginController.controller.notification(email,"",status);   
    }

 
    }


    
