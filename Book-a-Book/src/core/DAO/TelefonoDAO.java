/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Telefono;

/**
 *
 * @author mirko
 */
public class TelefonoDAO extends AbstractDAO<Telefono>{
    
    /**
     * 
     * @param id[0] si aspetta il prefisso di un numero telefonico, id[1] si aspetta un numero di telefono
     * @return un numero di telefono in base ai parametri inseriti.
     */

    @Override
    public Telefono doRetriveById(Object... id) {
        String prefisso = (String) id[0];
        String numero = (String) id[1];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Telefono doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Telefono telefono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Telefono telefono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
