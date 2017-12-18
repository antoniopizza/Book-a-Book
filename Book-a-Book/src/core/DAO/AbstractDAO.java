/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

/**
 *
 * @author manuel
 */
public abstract class AbstractDAO<T> {
    /**
     * 
     * @param id
     * @return 
     */
    public abstract T doRetriveById(int id);
    
    /**
     * 
     * @return 
     */
    public abstract T doRetriveAll();
    
    /**
     * 
     * @param entity
     * @return 
     */
    public abstract int doInsert( T entity);
    
    
    /**
     * 
     * @param entity
     * @return 
     */
    public abstract int doUpdate(T entity);
    
    
}
