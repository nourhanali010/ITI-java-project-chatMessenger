package Utilities;

import BusinessLayerInsterface.ChatServerInt;
import ClientImpl.ClientImpl;
import DTOModel.MessageDTO;
import DTOModel.UserDTO;
import Model.cashing.UserData;
import com.healthmarketscience.rmiio.SimpleRemoteInputStream;
import java.io.FileInputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author asmaa
 */
/**
 * singleton class to make one remote object with RMI implementation
 */
public class RMIUtilities {

    /**
     * An instance of this class
     */
    public static RMIUtilities rmiUtilities = null;
    private static String TAG = RMIUtilities.class.getName() + ": ";

    /**
     * An object of the registry
     */
    Registry reg;
    /**
     * A reference to the server remote object
     */
    ChatServerInt server;

    /**
     * Constructor of the class
     */
    public RMIUtilities() {
        initialization();
    }

    /**
     * This method returns the same object of this class every time its called
     *
     * 
     * 
     */
    public static synchronized RMIUtilities getInstance() {

        if (rmiUtilities == null) {
            rmiUtilities = new RMIUtilities();
        }
        return rmiUtilities;
    }

  
    private void initialization() {
        try {
            reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
            try {
                server = (ChatServerInt) reg.lookup("serverChat");
            } catch (NotBoundException ex) {
                System.out.println(TAG + ex.getMessage());
            } catch (AccessException ex) {
                System.out.println(TAG + ex.getMessage());
            }

        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "No connection with server", "Error 404", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
            System.out.println(TAG + ex.getMessage());
        }

    }

    /**
     *
     * @param fileInputStream .
     * @param userName .
     * @param fileName .
     */
    public void sendFileRmi(FileInputStream fileInputStream, String userName, String fileName) {
        new Thread(() -> {
            try {
                SimpleRemoteInputStream istream = new SimpleRemoteInputStream(fileInputStream);
                server.transferHandle(istream.export(), userName, fileName);
                System.out.println("callservertrmi finished");
            } catch (RemoteException ex) {
                Logger.getLogger(RMIUtilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    /**
     *
     * @param email .
     * @param myid .
     */
    public void setSendRequestFriend(String email, int myid) {
        System.out.println("client UTIL request");

        try {
            server.addRequestFriend(email, myid);
        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage());
        }
//        
    }

    /**
     *
     * @param id .
     * 
     */
    public ArrayList<UserDTO> setContactList(int id) {
        ArrayList<UserDTO> user = new ArrayList<>();
        try {
            user = server.contactList(id);

        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }
        for (int i = 0; i < user.size(); i++) {
            System.out.println(user.get(i).getUserName());
        }
        return user;
    }

    /**
     *
     * @param id .
     * 
     */
    public ArrayList<UserDTO> setRequestList(int id) {
        ArrayList<UserDTO> user = new ArrayList<>();
        try {
            user = server.requestFriendList(id);

        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }
        for (int i = 0; i < user.size(); i++) {
            System.out.println("requestrequest::::  " + user.get(i).getRequestName());
        }
        return user;
    }

    /**
     *
     * @param obj .
     * 
     */
    public boolean setRegistration(UserDTO obj) {
        boolean isDone = false;

        try {

            isDone = server.signUp(obj);

        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage());

        }
        return isDone;

    }

    /**
     *
     * @param reciverEmail .
     * @param requestId .
     * @param type .
     * 
     */
    public boolean deletRequest(String reciverEmail, int requestId, boolean type) {
        try {
            return server.deleteRequest(reciverEmail, requestId, type);
//            return true
        } catch (RemoteException ex) {
            Logger.getLogger(RMIUtilities.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    /**
     *
     * @param user .
     *
     */
    public boolean setLogin(UserDTO user) {
        boolean isDone = false;
        UserDTO userD = new UserDTO();

        try {

//            isDone = 
            userD = server.login(user);
            if (userD != null) {
                isDone = true;
                UserData.id = userD.getUserId();
                UserData.name = userD.getUserName();
                UserData.Email = userD.getUserEmail();
                System.out.println("omar el sneeeen " + UserData.Email);
            } else {
                isDone = false;
            }

        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage());
            isDone = false;

        }
        return isDone;

    }

    /**
     *
     * @param name .
     * @param clientImpl .
     */
    public void setHelloRef(String name, ClientImpl clientImpl) {
        try {
            server.register(name, clientImpl);
        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage());
        }

    }

    /**
     *
     * @param msg .
     */
    public void setTellOthers(MessageDTO msg) {
        try {
            server.sendMsg(msg);
        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage());
        }
    }

    /**
     *
     * @param value .
     * @param id .
     */
    public void setUpdateStatus(String value, int id) {
        try {
            server.updateUserStatus(value, id);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param clientimpl .
     */
    public void unregister(ClientImpl clientimpl) {
        try {
            server.unRegister(clientimpl);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
