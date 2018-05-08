/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

/**
 *
 * @author asmaa .
 * @param <I> .
 * @param <T> .
 */
public interface DAOInt<I, T> {

    /**
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    public boolean create(I connection, T obj);

    /**
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    public boolean update(I connection, T obj);

    /**
     *
     * @param connection .
     * @param obj .
     * @return .
     */
    public T retrieve(I connection, T obj);

    /**
     *
     * @param connection .
     * @param id .
     * @return .
     */
    public boolean delete(I connection, int id);

}
