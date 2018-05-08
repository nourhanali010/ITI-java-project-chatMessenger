/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayerInsterface;
import DTOModel.MessageDTO;
import DTOModel.UserBinding;
import DTOModel.UserDTO;
import com.healthmarketscience.rmiio.RemoteInputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author asmaa
 */

/**
 * This is an interface that includes the RMI methods on the server's side
 */
public interface ChatServerInt extends Remote {

   /**
     * This method enable the client to login 
     * @param user user object created from the info given by the user
     * @return user object created from the database if the validation was correct
     * @throws RemoteException .
     */
    public UserDTO login(UserDTO user) throws RemoteException;

    /**
     * This method enable the client to sign up 
     * @param obj user object created from the info given by the user
     * @return user object created from the database if the validation was correct
     * @throws RemoteException .
     */
    public boolean signUp(UserDTO obj) throws RemoteException;

   /**
     * This methods enables the user to add a friend in his/her contact list
     * @param email email of the user to be added
     * @param myid id of the request sender
     * @return true or false depending on the result of the request
     * @throws RemoteException .
     */
    public boolean addContact(String email, int myid) throws RemoteException;

      /**
     * This method is used to check whether the add request receiver accepted the request or not
     * @param myId .
     * @param userId .
     * @param isAccept .
     * @return  .
     *
     * @throws RemoteException .
     */
    public boolean isAddAccepted(int myId, int userId, boolean isAccept) throws RemoteException;

/**
     * This method is used to delete the request in case of request rejection
     * @param reciverEmail .
     * @param requestId .
     * @param type . 
     * @return  .
     * 
     * @throws RemoteException .
     */    
    public boolean deleteRequest(String reciverEmail, int requestId,boolean type)throws RemoteException;

   
    /**
     * this method is used to is called to update the list view of the contact list of the client
     * by returning a list of friends from the database
     * @param id id of the client to retrieve the friend list for
     * @return an array of user objects
     * @throws RemoteException .
     */
    public ArrayList<UserDTO> contactList(int id) throws RemoteException;
    
    /**
     * This method is used to retrieve the contact list of the user to view it on the interface
     * @param id .
     * @return  .
     * 
     * @throws RemoteException .
     */
    public ArrayList<UserDTO>requestFriendList(int id) throws RemoteException;

/**
     * This method handle the file transfer by sending a request to the remote object with
     * unique email sent as a parameter
     * @param ris a remote input stream, used in file transfer over RMI 
     * @param user email of the file receiver
     * @param fileName name of the file to be received
     * @throws RemoteException .
     */
    public void transferHandle(RemoteInputStream ris, String user, String fileName) throws RemoteException;
 /**
     * This method is user to update the user status in the data base
     * @param value status string value i.e online, offline...
     * @param id id of the user to be updated
     * @throws RemoteException .
     */	
    public void updateUserStatus(String value, int id) throws RemoteException;
     /**
     * This method is called to register the client remote object at server side
     * @param name .
     * @param clientRef .
     * @throws RemoteException .
     */
   public void register(String name,ChatClientInt clientRef) throws RemoteException;
     /**
     * This method is used to add a request in the request list
     * @param email .
     * @param myid .
     * @return .
     * @throws RemoteException .
     */ 
   public boolean addRequestFriend(String email , int myid) throws RemoteException;

     /**
     * This method is called to unregister the remote object of the user
     * @param clientRef ..
     * @throws RemoteException ..
     */
   public void unRegister(ChatClientInt clientRef) throws RemoteException;
    
    /**
     * This method is called is to send a messages between clients 
     * @param message message object containing message content, sender id, receiver id
     * @throws RemoteException ..
     */
   public void sendMsg(MessageDTO message) throws RemoteException;
   /**
     * This method is used to send an announcement from the server to the users
     * @param msg .
     * @throws RemoteException ..
     */  
   public void sendAnnoun(String msg)throws RemoteException;

   /**
     * This method is used to retrieve a user object depending on the mail
     * @param email //
     * @return //
     * @throws RemoteException //
     */
    public UserDTO selectByEmail(String email) throws RemoteException;


}
