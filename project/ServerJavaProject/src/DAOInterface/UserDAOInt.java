/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTOModel.UserDTO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author asmaa
 */
public interface UserDAOInt extends DAOInt<Connection, UserDTO> {

    /**
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    @Override
    public boolean create(Connection connection, UserDTO obj);

    /**
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    @Override
    public boolean update(Connection connection, UserDTO obj);

    public UserDTO retrieve(Connection connection, int id);

    public ArrayList<UserDTO> allOnlineUsers(Connection con);

    /**
     *
     * @param connection .
     * @param user .
     * @return .
     */
    @Override
    public UserDTO retrieve(Connection connection, UserDTO user);

    /**
     *
     * @param connection .
     * @param id .
     * @return .
     */
    @Override
    public boolean delete(Connection connection, int id);

    public ArrayList<UserDTO> getAllUsers(Connection con);

}
