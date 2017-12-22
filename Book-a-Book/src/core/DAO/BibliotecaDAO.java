/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import java.util.List;

/**
 *
 * @author mirko
 */
public class BibliotecaDAO extends AbstractDAO<Biblioteca>{
    
    /**
     * 
     * @param id[0] si aspetta un codice isil per identificare una biblioteca
     * @return una biblioteca in base al codice isil
     */

    @Override
    public Biblioteca doRetriveById(Object... id) {
        String isil = (String) id[0];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Biblioteca> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Biblioteca biblioteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Biblioteca biblioteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
