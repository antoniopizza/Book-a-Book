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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerPrenotazione {

    public Prenotazione prenotareLibro(Persona p, String isbn, String isil) {
        
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        PosizioneDAO posDAO = new PosizioneDAO();
        posDAO.setBibliotecaDAO(bibliotecaDAO);
        CopiaDAO copiaDAO = posDAO.getCopiaDAO();
        PrenotazioneDAO prenDAO = new PrenotazioneDAO();
        Calendar dataCreazione = new GregorianCalendar();
        Calendar dataScadenza = new GregorianCalendar();
        Libro libro = new LibroDAO().doRetriveById(isbn);
      
        
        Biblioteca bib = bibliotecaDAO.doRetriveById(isil);
        Copia copiaPrenotata = null;
        List<Posizione> listaPosizioniLibro;
        listaPosizioniLibro = posDAO.doRetriveByLibroAndBiblioteca(isbn, isil);
        
        for(Posizione pos: listaPosizioniLibro){
            pos.setBiblioteca(bib);
            for(Copia c : copiaDAO.doRetriveByPosizioneAndIsbn(pos, isbn)){
                if(c.getDisponibilita().equals(Copia.DISPONIBILE_SI) && c.getStatus().equals(Copia.STATUS_NON_PRENOTATO)){
                   copiaPrenotata = c;
                   copiaPrenotata.setPosizione(pos);
                   copiaPrenotata.setLibro(libro);
                   break;
                }
                
            }
            if(copiaPrenotata!=null){
                break;
            }
            
        }
        if(copiaPrenotata==null){
            return null;
        }
        dataScadenza.add(Calendar.DAY_OF_MONTH, 90);
        Prenotazione prenot = new Prenotazione(dataCreazione, dataScadenza, null, p, Prenotazione.DA_RITIRARE, bib, copiaPrenotata);
        if(prenDAO.doInsert(prenot)!= -1){
            copiaPrenotata.setStatus(Copia.STATUS_PRENOTATO);
            System.out.println(prenot);
            
            if(copiaDAO.doUpdate(copiaPrenotata) != -1){
                return prenot;
            }else{
                prenDAO.doDelete(prenot);
                return null;
            }
        }else{
            return null;
        }
    }

    public Collection<Prenotazione> visualizzaPrenotazioni(Criterio cp) {
        PrenotazioneDAO prenDAO = new PrenotazioneDAO();
        Collection<Prenotazione> lista = new ArrayList<>();
        List<Prenotazione> listaPrenotazione;
        listaPrenotazione = prenDAO.doRetriveAll();
            for (int i = 0; i <= listaPrenotazione.size(); i++) {
                if (cp.isValid(listaPrenotazione.get(i))) {
                    lista.add(listaPrenotazione.get(i));
                }
            }
        
        return lista;
    }

    public boolean controlloPrenotazione(int idPrenotazione, String email, String status) {
        PrenotazioneDAO prenDAO = new PrenotazioneDAO();
        Prenotazione prenotazione = prenDAO.doRetriveById(idPrenotazione);
        prenotazione.setStatus(status);

        if (prenDAO.doUpdate(prenotazione) == -1) {
            return false;
        } else {
            return true;
        }
    }
    
    public Prenotazione visualizzaPrenotazione(int idPrenotazione){
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione prenotazione = prenotazioneDAO.doRetriveById(idPrenotazione);
        
        return prenotazione;
    }

}
