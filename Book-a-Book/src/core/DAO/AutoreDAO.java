/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Autore;
import java.util.List;

/**
 *
 * @author mirko
 */
public class AutoreDAO extends AbstractDAO<Autore>{
    
    /**
     * 
     * @param id[0] si aspetta un codice identificativo di un autore
     * @return un autore in base al codice id
     */

    @Override
    public Autore doRetriveById(Object... id) {
        int idAutore = (int) id[0];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Autore> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Autore> doRetriveByLibro(String isbn){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Autore autore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Autore autore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
