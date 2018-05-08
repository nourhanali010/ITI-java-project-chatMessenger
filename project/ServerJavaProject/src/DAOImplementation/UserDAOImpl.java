/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplementation;

import DAOInterface.UserDAOInt;
import DTOModel.RequestModel;
import DTOModel.UserBinding;
import DTOModel.UserDTO;
import Model.cashing.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author asmaa
 */
public class UserDAOImpl implements UserDAOInt {

    private static final String TAG = UserDAOImpl.class.getName() + ": ";

    UserDTO userDTO = new UserDTO();

    public PreparedStatement pst = null;
    public Statement stmt = null;
    ResultSet rs = null;
    String checkFriend = "SELECT contactuserid FROM contactlist where contactId=?;";
    String friendRequest = "INSERT INTO friendrequest" + "(senderid,recieverid,requestname)" + "VALUES (?,?,?)";
    String selectByEmail = "select * FROM user WHERE useremail =?;";
    String deleteRequest = "DELETE FROM friendrequest WHERE requestid =?;";
    String selectRequest = "select * FROM friendrequest WHERE requestid =?;";

    String insertQuery = "INSERT INTO user"
            + "(userName, userEmail, userPassword, userGender, userAge,userStatus)"
            + " VALUES (?,?,?,?,?,?)";

    String addContact = "INSERT INTO contactlist"
            + "(contactId, contactuserid)"
            + " VALUES (?,?)";
    String retrieveQuery = "select * from user where userId=?";
    String updateQuery = "UPDATE user SET userName=?, userEmail=?,userpassword=?,gender=?,userage=?,userStatus=? WHERE userid=?";
    String selectUser = "select userid,username,useremail,userpassword,usergender,userstatus"
            + " from user where useremail= ? and userpassword=? ; ";

    String deletRequestFriend = "DELETE FROM friendrequest WHERE requestid =?";

    String deleteQuery = "DELETE FROM user WHERE userid =?";

    String maleQuery = "SELECT COUNT(*) from user WHERE usergender='male'";
    String femaleQuery = "SELECT COUNT(*) from user WHERE usergender='female'";

    String allOnlineQuery = "SELECT userid from user WHERE userstatus=1 or userstatus='online'";
    String allUserQuery = "SELECT * from user";
    String tableUpdateQuery = "UPDATE user SET username=?, usergender=?, userage=?, userstatus=? WHERE userid=?";

    String onlineQuery = "SELECT COUNT(*) from user WHERE userstatus=1";
    String offlineQuery = "SELECT COUNT(*) from user WHERE userstatus=4";

    String age1Query = "SELECT COUNT(*) from user WHERE userage>=15 AND userage<20";
    String age2Query = "SELECT COUNT(*) from user WHERE userage>20 AND userage<=28";
    String age3Query = "SELECT COUNT(*) from user WHERE userage>=29 AND userage<=35";

    private final ChangeListener<String> NAME_CHANGE_LISTENER = new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> property, String oldValue, String newValue) {
            updateNameView(newValue);
        }
    };
    private UserBinding model;

    private void updateNameView() {
        updateNameView(model.getUsername());
    }

    private void updateNameView(String newValue) {
//        labelView.setText(newValue);
    }

    private void updateView() {
        model.nameProperty().addListener(NAME_CHANGE_LISTENER);
        updateNameView();
    }

    /**
     * Delete a specific friend request based on its id
     *
     * @param connection .
     * @param requuestId.
     * @return .
     */
    public boolean deleteRequest(Connection connection, int requuestId) {
        try {
            String deleteRequest = "DELETE FROM friendrequest WHERE requestid =?;";
            pst = connection.prepareStatement(deleteRequest);
            pst.setInt(1, requuestId);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
//            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Check whether user has already have this friend on its contact list
     *
     * @param connection .
     * @param senderId .
     * @return .
     */
    public boolean checkFriend(Connection connection, int senderId) {
//        int i  = 0;
        try {

            pst = connection.prepareStatement(checkFriend);
            pst.setInt(1, senderId);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("result seeeet");
                return false;

            } else {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    /**
     * Get user data based on its e-mail
     *
     * @param connection .
     * @param email .
     * @param sender .
     * @return .
     */
    public UserDTO selectEmail(Connection connection, String email, int sender) {
        UserDTO friend = new UserDTO();
        try {

            pst = connection.prepareStatement(selectByEmail);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs != null) {
                rs.next();
                friend.setUserId(rs.getInt("userid")); /// reciever
                friend.setUserName(rs.getString("username"));
                friend.setUserEmail(rs.getString("useremail"));
                friend.setUserPassword(rs.getString("userpassword"));
                friend.setUserGender(rs.getString("usergender"));
                friend.setUserAge(rs.getInt("userage"));
                friend.setUserStatus(rs.getString("userstatus"));
            }

            return friend;
        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
            return null;
        }
    }

    public RequestModel selectRequest(Connection connection, int requestId) {
        try {
            System.out.println("id: " + requestId);
            pst = connection.prepareStatement(selectRequest);
            pst.setInt(1, requestId);
            rs = pst.executeQuery();
            if (rs != null) {
                rs.next();
                RequestModel model = new RequestModel();
                model.setSenderid(rs.getInt("senderid"));
                model.setRecieverid(rs.getInt("recieverid"));

                return model;

            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
            return null;
        }
    }

    /**
     * Get user object with its data(name,id,email,password,gender,age,status)
     *
     * @param connection . 
     * @param id .
     * @return .
     */
    public UserDTO retrieve(Connection connection, int id) {
        UserDTO newU = null;
        try {
            pst = connection.prepareStatement(retrieveQuery);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs != null) {
                rs.next();
                newU = new UserDTO();
                newU.setUserId(rs.getInt("userid"));
                newU.setUserName(rs.getString("username"));
                newU.setUserEmail(rs.getString("useremail"));
                newU.setUserPassword(rs.getString("userpassword"));
                newU.setUserGender(rs.getString("usergender"));
                newU.setUserAge(rs.getInt("userage"));
                newU.setUserStatus(rs.getString("userstatus"));
                //deal with image here
            }
        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }
        return newU;
    }

    /**
     *
     * @param connection .
     * @param id . 
     * @return .
     */
    public ArrayList<UserDTO> requestFriendResult(Connection connection, int id) {
        ArrayList<UserDTO> user = new ArrayList<>();
        System.out.println("kkkkk" + id);
        String selectRequsdtFriend = "SELECT * FROM friendrequest where recieverid=?;";

        try {
            stmt = connection.createStatement();
            pst = connection.prepareStatement(selectRequsdtFriend);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            System.out.println("user selected successfully11");
            if (rs != null) {
                while (rs.next()) {

                    UserDTO userI = new UserDTO();

                    userI.setRequestId(rs.getInt("requestid"));
                    userI.setRequestName(rs.getString("requestname"));

                    user.add(userI);

                }
                for (int i = 0; i < user.size(); i++) {
                    System.out.println(user.get(i).getUserName());
                }
//                
            } else {
                System.out.println("no user in table!");
            }

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());

        }
        return user;

    }

    /**
     * list the user's contact list
     *
     * @param connection .
     * @param id .
     * @return .
     */
    public ArrayList<UserDTO> returnContactList(Connection connection, int id) {
        ArrayList<UserDTO> user = new ArrayList<>();
        System.out.println("kkkkk" + id);
        String contactListQuery = "select u.* from user u, contactlist c where c.contactid=? and u.userid in (c.contactuserid);";

        try {
            stmt = connection.createStatement();
            pst = connection.prepareStatement(contactListQuery);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            System.out.println("user selected successfully11");
            if (rs != null) {
                while (rs.next()) {

                    UserDTO userI = new UserDTO();
                    userI.setUserId(rs.getInt("userid"));
                    userI.setUserName(rs.getString("username"));
                    userI.setUserEmail(rs.getString("useremail"));
                    userI.setUserStatus(rs.getString("userstatus"));
                    user.add(userI);

                }
                for (int i = 0; i < user.size(); i++) {
                    System.out.println(user.get(i).getUserName());
                }
//                
            } else {
                System.out.println("no user in table!");
            }

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());

        }
        return user;

    }

    /**
     * Create a new user
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    @Override
    public boolean create(Connection connection, UserDTO obj) {

        try {
            pst = connection.prepareStatement(insertQuery);
            pst.setString(1, obj.getUserName());
            pst.setString(2, obj.getUserEmail());
            pst.setString(3, obj.getUserPassword());
            pst.setString(4, obj.getUserGender());
            pst.setInt(5, obj.getUserAge());
            pst.setString(6, obj.getUserStatus());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
            return false;
        }

    }

    public boolean insertContactList(Connection connection, int contactId, int contactuserid) {
        try {
            pst = connection.prepareStatement(addContact);
            pst.setInt(1, contactId);
            pst.setInt(2, contactuserid);
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    /**
     * Check whether friend request is sent or not
     *
     * @param connection .
     * @param senderId . 
     * @param recieverId .
     * @param reciverEmail .
     * @return .
     */
    public boolean requestFriend(Connection connection, int senderId, int recieverId, String reciverEmail) {

        try {
            pst = connection.prepareStatement(friendRequest);
            pst.setInt(1, senderId);
            pst.setInt(2, recieverId);
            pst.setString(3, reciverEmail);
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
            return false;
        }

    }

    /**
     * Update a specific user's data
     *
     * @param connection .
     * @param obj .
     * @return . 
     */
    public boolean update(Connection connection, UserDTO obj) {
        try {
            pst = connection.prepareStatement(updateQuery);
            pst.setString(1, obj.getUserEmail());
            pst.setString(2, obj.getUserPassword());
            pst.setString(3, obj.getUserGender());
            pst.setInt(4, obj.getUserAge());
            pst.setString(5, obj.getUserStatus());
            pst.setInt(6, obj.getUserId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
            return false;
        }
        return true;

    }

    /**
     * List all online users
     *
     * @param con . 
     * @return .
     */
    public ArrayList<UserDTO> allOnlineUsers(Connection con) {
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<UserDTO> user = new ArrayList<>();
        try {
            PreparedStatement state = con.prepareStatement(allOnlineQuery);
            ResultSet rs = state.executeQuery();
            System.out.println("ppppppppppp: ");

            if (rs != null) {
                while (rs.next()) {
                    idList.add(rs.getInt("userid"));
                }
                for (int i = 0; i < idList.size(); i++) {
                    user.add(this.retrieve(con, idList.get(i)));
                    System.out.println("ppppppppppp: " + user.get(i).getUserName());
                }
            } else {
                System.out.println("no user in table!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Retrieve a specific user's data
     *
     * @param connection .
     * @param user .
     * @return .
     */
    @Override
    public UserDTO retrieve(Connection connection, UserDTO user) {

        try {
            pst = connection.prepareStatement(selectUser, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, user.getUserEmail());
            pst.setString(2, user.getUserPassword());
            rs = pst.executeQuery();

            System.out.println("user selected successfully");
            boolean isDone = false;
            while (rs.next()) {
                isDone = true;
                UserData.id = rs.getInt("userid");
                UserData.name = rs.getString("username");
                UserData.Email = rs.getString("useremail");
                UserData.password = rs.getString("userpassword");
                UserData.gender = rs.getString("usergender");
                UserData.status = rs.getString("userstatus");

                user.setUserId(UserData.id);
                user.setUserEmail(UserData.Email);
                user.setUserGender(UserData.gender);
                user.setUserImage(UserData.image);
                user.setUserName(UserData.name);
                user.setUserPassword(UserData.password);
            }
            System.out.println(isDone);
            if (isDone) {
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage().toString());
            return null;
        }
    }

    @Override
    public boolean delete(Connection connection, int id) {
        return true;
    }

    /**
     * Count number of male users
     *
     * @param connection .
     * @return .
     */
    public int countMale(Connection connection) {

        int maleCount = 0;

        try {

            stmt = connection.createStatement();

            rs = stmt.executeQuery(maleQuery);
            rs.next();
            maleCount = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return maleCount;
    }

    /**
     * Count number of female users
     *
     * @param connection .
     * @return .
     */
    public int countFemale(Connection connection) {

        int femaleCount = 0;

        try {

            stmt = connection.createStatement();
            rs = stmt.executeQuery(femaleQuery);
            rs.next();
            femaleCount = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return femaleCount;
    }

    /**
     * List all the users
     *
     * @param con .
     * @return .
     */
    @Override
    public ArrayList<UserDTO> getAllUsers(Connection con) {

        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<UserDTO> user = new ArrayList<>();
        try {
            PreparedStatement state = con.prepareStatement(allUserQuery);
            ResultSet rs = state.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    idList.add(rs.getInt("userId"));
                }
                for (int i = 0; i < idList.size(); i++) {
                    user.add(this.retrieve(con, idList.get(i)));
                    System.out.println(user.get(i).getUserName());
                }
            } else {
                System.out.println("no user in table!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Update the user table
     *
     * @param con .
     * @param obj .
     * @return .
     */
    public boolean tableUpdate(Connection con, UserDTO obj) {
        try {
            PreparedStatement ps = con.prepareStatement(tableUpdateQuery);
            ps.setString(1, obj.getUserName());
            ps.setString(2, obj.getUserGender());
            ps.setInt(3, obj.getUserAge());
            ps.setString(4, obj.getUserStatus());
            ps.setInt(5, obj.getUserId());

            if (ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Count number of online users
     *
     * @param connection .
     * @return .
     */
    public int countOnline(Connection connection) {

        int onlineCount = 0;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(onlineQuery);
            rs.next();
            onlineCount = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return onlineCount;
    }

    /**
     * Count number of offline users
     *
     * @param connection .
     * @return . 
     */
    public int countOffline(Connection connection) {

        int offlineCount = 0;

        try {

            stmt = connection.createStatement();
            rs = stmt.executeQuery(offlineQuery);
            rs.next();
            offlineCount = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return offlineCount;
    }

    /**
     * Count age of first segment (between 15-20 years old)
     *
     * @param connection .
     * @return .
     */
    public int countAge1(Connection connection) {

        int age1Count = 0;

        try {

            stmt = connection.createStatement();
            rs = stmt.executeQuery(age1Query);
            rs.next();
            age1Count = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return age1Count;
    }

    /**
     * Count number of users having age between 20-28 years old (second segment)
     *
     * @param connection .
     * @return .
     */
    public int countAge2(Connection connection) {

        int age2Count = 0;

        try {

            stmt = connection.createStatement();
            rs = stmt.executeQuery(age2Query);
            rs.next();
            age2Count = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return age2Count;
    }

    /**
     * Count number of users having age between 29-35 years old (third segment)
     *
     * @param connection .
     * @return .
     */
    public int countAge3(Connection connection) {

        int age3Count = 0;

        try {

            stmt = connection.createStatement();
            rs = stmt.executeQuery(age3Query);
            rs.next();
            age3Count = rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println(TAG + ex.getMessage().toString());
        }

        return age3Count;

    }

}
