/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Indirizzo;
import java.util.List;

/**
 *
 * @author mirko
 */
public class IndirizzoDAO extends AbstractDAO<Indirizzo>{

    /**
     * 
     * @param id[0] si aspetta una string che corrisponde alla via, id[1] si aspetta una stringa che corrisponde alla città, id[2] si aspetta una stringa che corrisponde al civico di ua strada.
     * @return un indirizzo in base alle stringhe che gli vengono passate. 
     */
    
    @Override
    public Indirizzo doRetriveById(Object... id) {
        String  via = (String) id[0];
        String citta = (String) id[1];
        String civico = (String) id[2];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Indirizzo> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Indirizzo indirizzo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Indirizzo indirizzo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
