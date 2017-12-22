/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Bibliotecario;

/**
 *
 * @author mirko
 */
public class BibliotecarioDAO extends AbstractDAO<Bibliotecario>{
    
    /**
     * 
     * @param id[0] si aspetta un codice identificativo numerico di un bibliotecario
     * @return un bibliotecario in base al codice id
     */

    @Override
    public Bibliotecario doRetriveById(Object... id) {
        int idBibliotecario = (int) id[0];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bibliotecario doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Bibliotecario bibliotecario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Bibliotecario bibliotecario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
