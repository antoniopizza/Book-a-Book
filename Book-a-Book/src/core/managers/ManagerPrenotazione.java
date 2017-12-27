/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.*;
import core.entities.*;
import core.entities.Persona;
import core.entities.Prenotazione;
import core.utils.Criterio;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 *
 * @author manuel
 */
public class ManagerPrenotazione {
    
    public boolean prenotareLibro(Persona p,String isbn,String isil){
        LibroDAO libroDAO = new LibroDAO();
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        PrenotazioneDAO prenDAO = new PrenotazioneDAO();
        
        Calendar dataCreazione = new GregorianCalendar(); 
        Calendar dataScadenza = new GregorianCalendar();
        dataScadenza.add(Calendar.DAY_OF_MONTH, 90);
        Libro libroPrenotato;
        libroPrenotato = libroDAO.doRetriveById(isbn);
        Biblioteca bib;
        bib = bibliotecaDAO.doRetriveById(isil);
        Prenotazione prenot;
        prenot = new Prenotazione(dataCreazione,dataScadenza, null, p, "Da ritirare", bib, libroPrenotato);
        prenDAO.doInsert(prenot);
        
        return true;
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
