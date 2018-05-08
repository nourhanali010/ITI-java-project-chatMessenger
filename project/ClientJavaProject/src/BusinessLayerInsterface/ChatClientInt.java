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
/**
 * This is an interface that includes the RMI methods on the client's side
 */
public interface ChatClientInt extends Remote {

    public void notificationRequest(String email, int myid) throws RemoteException;

    public void receiveFileRmi(RemoteInputStream ris, String fileName) throws RemoteException;

    public void notificationAcceptRequest(String email) throws RemoteException;

    public boolean isAddAcceptedRequest(int myId, int userId, boolean isAccept) throws RemoteException;

    public void receive(MessageDTO msg) throws RemoteException;

    public void annonMsg(String msg) throws RemoteException;

    public void notificationStatus(String email,String status) throws RemoteException;

}
