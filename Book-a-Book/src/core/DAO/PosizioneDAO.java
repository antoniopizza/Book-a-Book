/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Posizione;
import java.util.List;

/**
 *
 * @author mirko
 */
public class PosizioneDAO extends AbstractDAO<Posizione>{
    
    /**
     * 
     * @param id[0] si aspetta un codice che identifica la posizione di un libro, id[1] si aspetta il codice identificativo di una biblioteca, id[2] si apsetta il codice di un libro.
     * @return la posizione di un libro in base ai parametri passati.
     */

    @Override
    public Posizione doRetriveById(Object... id) {
        String etichetta = (String) id[0];
        String isil = (String) id[1];
        String isbn = (String) id[2];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Posizione> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Posizione posizione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Posizione posizione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
