/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayerInsterface;

import DTOModel.MessageDTO;
import com.healthmarketscience.rmiio.RemoteInputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author asmaa
 */
public interface ChatClientInt extends Remote {

    /**
     * Receive a file over RMI using RMIIO library and it's called when the
     * receiver accepts the request
     *
     * @param ris .
     * @param fileName .
     * @throws RemoteException .
     */
    public void receiveFileRmi(RemoteInputStream ris, String fileName) throws RemoteException;

    /**
     * Notify that a friend request has been received
     *
     * @param email .
     * @param myid .
     * @throws RemoteException .
     */
    public void notificationRequest(String email, int myid) throws RemoteException;

    /**
     * Notify that friend request is accepted
     *
     * @param email .
     * @throws RemoteException .
     */
    public void notificationAcceptRequest(String email) throws RemoteException;

    /**
     * Check whether friend request is accepted or not
     *
     * @param myId .
     * @param userId .
     * @param isAccept .
     * @return .
     * @throws RemoteException .
     */
    public boolean isAddAcceptedRequest(int myId, int userId, boolean isAccept) throws RemoteException;

    /**
     *
     * @param msg .
     * @throws RemoteException .
     */
    public void receive(MessageDTO msg) throws RemoteException;

    public void annonMsg(String msg) throws RemoteException;
    
    public void notificationStatus(String email,String status) throws RemoteException;

}
