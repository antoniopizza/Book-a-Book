/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.PrenotazioneDAO;
import core.entities.Persona;
import core.entities.Prenotazione;
import core.utils.Criterio;
import java.util.Collection;

/**
 *
 * @author manuel
 */
public class ManagerPrenotazione {
    
    public boolean prenotareLibro(Persona p,String isbn,String isil){
        throw new UnsupportedOperationException("Not implemennted yet");
    }
    
    public Collection<Prenotazione> visualizzaPrenotazioni(Criterio cp){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    
    public boolean controlloPrenotazione(int idPrenotazione, String email, String status){
       PrenotazioneDAO prenDAO = new PrenotazioneDAO();
       Prenotazione prenotazione = prenDAO.doRetriveById(idPrenotazione);
       prenotazione.setStatus(status);
       
       if(prenDAO.doUpdate(prenotazione) == -1){
           return false;
       } else {
           return true;
       }
    }
    
}
