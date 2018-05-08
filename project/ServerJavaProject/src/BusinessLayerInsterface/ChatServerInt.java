/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayerInsterface;
import DTOModel.MessageDTO;
import DTOModel.UserBinding;
import DTOModel.UserDTO;
//import Model.Message;
import com.healthmarketscience.rmiio.RemoteInputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author asmaa
 */
public interface ChatServerInt extends Remote{

    /**
     *
     * @param user .
     * @return .
     * @throws RemoteException .
     */
    public UserDTO login(UserDTO user) throws RemoteException;

    /**
     *
     * @param obj .
     * @return .
     * @throws RemoteException .
     */
    public boolean signUp(UserDTO obj) throws RemoteException;

    /**
     *
     * @param email .
     * @param myid .
     * @return .
     * @throws RemoteException .
     */
    public boolean addContact(String email, int myid) throws RemoteException;

    /**
     *
     * @param myId .
     * @param userId .
     * @param isAccept .
     * @return .
     * @throws RemoteException .
     */
    public boolean isAddAccepted(int myId, int userId, boolean isAccept) throws RemoteException;

    /**
     *
     * @param id .
     * @return .
     * @throws RemoteException .
     */
    public ArrayList<UserDTO> contactList(int id) throws RemoteException;
    public ArrayList<UserDTO>requestFriendList(int id) throws RemoteException;
public boolean deleteRequest(String reciverEmail, int requestId,boolean type)throws RemoteException;
    
    public void transferHandle(RemoteInputStream ris, String user, String fileName) throws RemoteException;
	public void updateUserStatus(String value, int id) throws RemoteException;
    
   public void register(String name,ChatClientInt clientRef) throws RemoteException;
    public boolean addRequestFriend(String email , int myid) throws RemoteException;

    /**
     *
     * @param clientRef .
     * @throws RemoteException .
     */
   public void unRegister(ChatClientInt clientRef) throws RemoteException;
    
    /**
     *
     * @param message .
     * @throws RemoteException .
     */
  public void sendMsg(MessageDTO message) throws RemoteException;
  public void sendAnnoun(String msg)throws RemoteException;
    public UserDTO selectByEmail(String email) throws RemoteException;

}