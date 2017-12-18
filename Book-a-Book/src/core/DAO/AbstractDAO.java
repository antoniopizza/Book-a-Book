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
    
    public abstract T doRetriveById(int id);
    
    public abstract T doRetriveAll();
    
    public abstract int doInsert( T entity);
    
    public abstract int doUpdate(T entity);
    
    
}
