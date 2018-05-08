/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayerImpl;

import BusinessLayerInsterface.ChatClientInt;
import BusinessLayerInsterface.ChatServerInt;
import DAOImplementation.UserDAOImpl;
import DBManeger.DBManeger;
import DTOModel.MessageDTO;
import DTOModel.RequestModel;
import DTOModel.UserDTO;

import com.healthmarketscience.rmiio.RemoteInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asmaa
 */
public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInt {

  public static  Map<String,ChatClientInt> clientsVector =new HashMap<String,ChatClientInt>();
    UserDAOImpl user = new UserDAOImpl();
    DBManeger dbm = new DBManeger();


    /**
     *
     * @throws RemoteException .
     */
    public ChatServerImpl() throws RemoteException {

    }

    /**
     * enable user to login
     * @param user . 
     * @return . 
     */
    @Override
    public UserDTO login(UserDTO user) {
        dbm.DBStartConnection();
        Connection con = dbm.getConnection();

        return this.user.retrieve(con, user);
    }

    
    /**
     * enable user to sign up for the first time
     * @param obj .
     * @return .
     */
  @Override
    public boolean signUp(UserDTO obj) {

        dbm.DBStartConnection();
        Connection con = dbm.getConnection();
        if (user.create(con, obj)) {
            return true;
        }else  return false;

//        return null;
    }


     /**
     * Add a new contact
     * @param email .
     * @param myid .
     * @return .
     */
    @Override
    public boolean addContact(String email, int myid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Check whether the friend request is accepted or not
     * @param myId .
     * @param userId .
     * @param isAccept .
     * @return .
     */
    @Override
    public boolean isAddAccepted(int myId, int userId, boolean isAccept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
     /**
       * Register the client remote object at server side
       * @param clientRef .
       * @param UserName .
       * @throws RemoteException .
       */
    @Override
    public void register(String UserName,ChatClientInt clientRef) throws RemoteException {
       // clientsVector.add(clientRef);
        clientsVector.put(UserName, clientRef);
        
        System.out.println("Client added " +UserName);
    }

     /**
     * Unregister the client remote object
     * @param clientRef .
     * @throws RemoteException .
     */
    @Override
    public void unRegister(ChatClientInt clientRef) throws RemoteException {
        clientsVector.remove(clientRef);
        System.out.println("Client removed");
    }

     
    /**
     * Send messages between users
     * @param msg .
     * @throws RemoteException .
     */
    @Override
    public void sendMsg(MessageDTO msg) throws RemoteException
    {
         System.out.println("user: " +msg.getFrom()+" "+msg.getMsg());
         System.out.println("user: " +msg.getTo()+" "+msg.getMsg());
                  System.out.println("vectorrrrr__________1"+clientsVector.get("asmaa@gmail"));

         System.out.println("vectorrrrr__________"+clientsVector.containsKey(msg.getTo()));
        if(clientsVector.containsKey(msg.getTo())){
            System.out.println("setMessage_------------------");
            ChatClientInt client;
            ChatClientInt client1;
            client= clientsVector.get(msg.getTo());
            client1=clientsVector.get(msg.getFrom());
            client.receive(msg);
            client1.receive(msg);
        }
       
    }
    
    
 /**
     * List user's contact list
     * @param id .
     * @return  .
     * @throws RemoteException .
     */
    @Override
    public ArrayList<UserDTO> contactList(int id) throws RemoteException {
        dbm.DBStartConnection();
        Connection con = dbm.getConnection();
        ArrayList<UserDTO> userD = new ArrayList<>();
        userD =user.returnContactList(con, id);
        return userD;
    }
    
    /**
     * List user's all friend requests
     * @param id .
     * @return .
     * @throws RemoteException .
     */
     @Override
    public ArrayList<UserDTO> requestFriendList(int id) throws RemoteException {
        dbm.DBStartConnection();
        Connection con = dbm.getConnection();
        ArrayList<UserDTO> userD = new ArrayList<>();
        userD =user.requestFriendResult(con, id);
        return userD;
    }


    /**
     * Request to add a new friend
     * @param email .
     * @param myid .
     * @return  .
     * @throws RemoteException .
     */
    @Override
    public boolean addRequestFriend(String email , int myid) throws RemoteException {
        UserDTO U =new UserDTO();
        dbm.DBStartConnection();
        Connection con = dbm.getConnection();
      U= user.selectEmail(con,email, myid);
      if(U!=null){
          boolean m = user.checkFriend(con, myid);
          if(m){
              System.out.println("zeroooooooo");
              user.requestFriend(con,myid,U.getUserId(),email);
          System.out.println("0000000000000000000 "+clientsVector.containsKey(email));
           if(clientsVector.containsKey(email)){
               System.out.println("99999999999999999999");
               ChatClientInt client;
               client=clientsVector.get(email);
               client.notificationRequest(email, myid);
                       return true;
           }else return false;     

           }else return false;
           
      }
      else{ return false;}
    }  
    
     /**
     * Handle the file transfer by sending a request to the remote object with unique email sent as a parameter
     * @param ris .
     * @param user .
     * @param fileName .
     * @throws RemoteException .
     */

     @Override
    public void transferHandle(RemoteInputStream ris, String user, String fileName) throws RemoteException {
        
        Iterator it = clientsVector.entrySet().iterator();
        
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getValue());
            if (pair.getKey().equals(user)) {
                System.out.println(user + "receiveFileRmi should be called");
                ChatClientInt inter = (ChatClientInt) pair.getValue();
                inter.receiveFileRmi(ris, fileName);
            }
        }
        
    }
	
	
    
       /**
     * Update the user's status
     * @param value .
     * @param id .
     * @throws RemoteException .
     */
    @Override
    public void updateUserStatus(String value, int id) throws RemoteException {
        try {
            DBManeger dbm = new DBManeger();
            dbm.DBStartConnection();
            Connection con = dbm.getConnection();
            String que = "update user set userstatus = ? where userid = ?";
            PreparedStatement ps = con.prepareStatement(que);
            ps.setString(1, value);
            ps.setInt(2, id);
            ps.executeUpdate();
            ArrayList<UserDTO> U =new ArrayList<>();
           U= user.returnContactList(con, id);
           ArrayList<UserDTO> userList = new ArrayList<>();
           for(int i =0; i<U.size() ; i++){
              userList.add( user.retrieve(con, U.get(i).getUserId()));
           }
            for (int i = 0; i < userList.size(); i++) {
                 if(clientsVector.containsKey(userList.get(i).getUserEmail())){
                 ChatClientInt client;
               client=clientsVector.get(userList.get(i).getUserEmail());
               client.notificationStatus(userList.get(i).getUserEmail(),value);
           }
            }
          
            
            dbm.DBStopConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(ChatServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public UserDTO selectByEmail(String email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      /**
     * delete request if accepted or deny
     * @param reciverEmail .
     * @param requestId .
     * @param type .
     * @throws RemoteException .
     */
    @Override
    public boolean deleteRequest(String reciverEmail, int requestId,boolean type) throws RemoteException {
     DBManeger dbm = new DBManeger();
            dbm.DBStartConnection();
            Connection con = dbm.getConnection();
            
            if(type){
               RequestModel request= user.selectRequest(con, requestId);
               if(request !=null){
                user.insertContactList(con,request.getSenderid(), request.getRecieverid());
               }
               user.deleteRequest(con, requestId);

                if(clientsVector.containsKey(reciverEmail)){
               System.out.println("99999999999999999999");
               ChatClientInt client;
               client=clientsVector.get(reciverEmail);
               client.notificationAcceptRequest(reciverEmail);
//               user.
               return true;
            }else return false;
    }else return user.deleteRequest(con, requestId);




}

    /**
     * Send announcement from server to all online users
     * @param msg .
     * @throws RemoteException .
     */
    @Override
    public  void sendAnnoun(String msg) throws RemoteException {
        DBManeger dbm = new DBManeger();
            dbm.DBStartConnection();
            Connection con = dbm.getConnection();
            ArrayList<UserDTO> users = new ArrayList<>();
       users  =  user.allOnlineUsers(con);

                         System.out.println("uuuuuuuuu: "+users);

     
       if(users!=null){
                  System.out.println("users");

           for (int i = 0; i<users.size() ; i++) {
      System.out.println("rmi922222222222222222");

             if(clientsVector.containsKey(users.get(i).getUserEmail())){
                  ChatClientInt client;
               client=clientsVector.get(users.get(i).getUserEmail());
               client.annonMsg(msg);                 
             }
               
           }
       }
    }
    
    
}
