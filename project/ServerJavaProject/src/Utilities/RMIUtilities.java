/**
 * @singleton class to make one remote object from  RMI
 */
package Utilities;

import BusinessLayerImpl.ChatServerImpl;
import BusinessLayerInsterface.ChatClientInt;
import BusinessLayerInsterface.ChatServerInt;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author asmaa
 */
public class RMIUtilities {

    /**
     *
     */
    public static RMIUtilities rmiUtilities = null;
    private static String TAG = RMIUtilities.class.getName() + ": ";
    Registry reg;
    ChatServerInt server;
    ChatClientInt client;
    ChatServerImpl serverImpl;

    private RMIUtilities() {
        initialization();
    }

    /**
     *
     * @return .
     */
    public static synchronized RMIUtilities getInstance() {

        if (rmiUtilities == null) {
            rmiUtilities = new RMIUtilities();
        }
        return rmiUtilities;
    }

    private void initialization() {
        try {
            serverImpl = new ChatServerImpl();
            
            reg = LocateRegistry.createRegistry(5000);
            reg.rebind("serverChat", serverImpl);
            System.out.println("binded Sucessful ....");

        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

    }

    
    public void stopServer() {

        try {

            reg.unbind("serverChat");
        } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        } catch (NotBoundException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

    }

    public void annoniEmail(String msg){
        try {
                   System.out.println("rmiiiiiiiiiiiiiiiiiiiiiiiii");

                   serverImpl.sendAnnoun(msg);
                    } catch (RemoteException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }
    }
}
