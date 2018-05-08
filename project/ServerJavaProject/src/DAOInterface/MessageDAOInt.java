/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTOModel.MessageDTO;
import java.sql.Connection;

/**
 *
 * @author asmaa
 */
public interface MessageDAOInt extends DAOInt<Connection,MessageDTO>{
    
    /**
     *
     * @param connection .
     * @param obj.
     * @return .
     */
    @Override
      public boolean create(Connection connection,MessageDTO obj);

    /**
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    @Override
    public boolean update(Connection connection,MessageDTO obj);

    /**
     *
     * @param connection .
     * @param id .
     * @return .
     */
    @Override
    public MessageDTO retrieve(Connection connection,MessageDTO id);

    /**
     *
     * @param connection .
     * @param id .
     * @return .
     */
    @Override
    public boolean delete(Connection connection,int id);

}
