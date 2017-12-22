/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Prenotazione;
import java.util.List;

/**
 *
 * @author mirko
 */
public class PrenotazioneDAO extends AbstractDAO<Prenotazione>{
    
    /**
     * 
     * @param id[0] si aspetta un codice identificativo numerico per la prenotazione.
     * @return una prenotazione in base al codice id
     */

    @Override
    public Prenotazione doRetriveById(Object... id) {
        int idPrenotazione = (int) id[0];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prenotazione> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Prenotazione prenotazione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Prenotazione prenotazione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
