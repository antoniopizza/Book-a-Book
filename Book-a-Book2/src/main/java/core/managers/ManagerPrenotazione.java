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
        PersonaDAO personaDAO = new PersonaDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        
        Calendar dataCreazione = new GregorianCalendar();
        Calendar dataScadenza = new GregorianCalendar();
        Libro libro = new LibroDAO().doRetriveById(isbn);

        Biblioteca bib = bibliotecaDAO.doRetriveById(isil);
        Copia copiaPrenotata = null;
        List<Posizione> listaPosizioniLibro;
        listaPosizioniLibro = posDAO.doRetriveByLibroAndBiblioteca(isbn, isil);

        // cerchiamo la prima copia disponibile
        for (Posizione pos : listaPosizioniLibro) {
            pos.setBiblioteca(bib);
            for (Copia c : copiaDAO.doRetriveByPosizioneAndIsbn(pos, isbn)) {
                if (c.getDisponibilita().equals(Copia.DISPONIBILE_SI) && c.getStatus().equals(Copia.STATUS_NON_PRENOTATO)) {
                    copiaPrenotata = c;
                    copiaPrenotata.setPosizione(pos);
                    copiaPrenotata.setLibro(libro);
                    break;
                }

            }
            if (copiaPrenotata != null) {
                break;
            }

        }
        if (copiaPrenotata == null) {
            return null;
        }
        dataScadenza.add(Calendar.DAY_OF_MONTH, 3);
        Prenotazione prenot = new Prenotazione(dataCreazione, dataScadenza, null, p, Prenotazione.DA_RITIRARE, bib, copiaPrenotata);
        int idPrenotazione,idPersona;
        
        //CONTROLLO L'INDIRIZZO
        
        if(indirizzoDAO.doRetriveById(p.getIndirizzo().getVia(),p.getIndirizzo().getCitta(),p.getIndirizzo().getCivico()) == null){
            if(indirizzoDAO.doInsert(p.getIndirizzo()) == -1){
                return null;
            }
        }
        
        //controllo se esiste la persona 
        if(p.getId() == 0 || p.getAccount() == null){
            
            if((idPersona = personaDAO.doInsert(p)) == -1){
                return null;
            } else {
                p.setId(idPersona);
            }
        }
        
        if ((idPrenotazione = prenDAO.doInsert(prenot)) != -1) {
            prenot.setId(idPrenotazione);
            copiaPrenotata.setStatus(Copia.STATUS_PRENOTATO);
            System.out.println(prenot);

            if (copiaDAO.doUpdate(copiaPrenotata) != -1) {
                return prenot;
            } else {
                prenDAO.doDelete(prenot);
                return null;
            }
        } else {
            return null;
        }
    }

    public Collection<Prenotazione> visualizzaPrenotazioni(Criterio cp) {
        PrenotazioneDAO prenDAO = new PrenotazioneDAO();
        Collection<Prenotazione> lista = new ArrayList<>();
        List<Prenotazione> listaPrenotazione;
        listaPrenotazione = prenDAO.doRetriveAll();
        
        if(listaPrenotazione == null){
            System.out.println("Something wrong happened");
            return lista;            
        }
        
        for (int i = 0; i < listaPrenotazione.size(); i++) {
            if (cp.isValid(listaPrenotazione.get(i))) {
                lista.add(listaPrenotazione.get(i));
            }
        }

        return lista;
    }

    public boolean controlloPrenotazione(int idPrenotazione, String email, String status) {
        PrenotazioneDAO prenDAO = new PrenotazioneDAO();
        Calendar dataScadenza = new GregorianCalendar();
        Calendar dataConsegna = new GregorianCalendar();
        Prenotazione prenotazione = prenDAO.doRetriveById(idPrenotazione);
        prenotazione.setStatus(status);

        if (prenotazione.getStatus().equals(Prenotazione.RESTITUITO) || prenotazione.getStatus().equals(Prenotazione.ANNULLATA)) {
            prenotazione.getCopia().setStatus(Copia.STATUS_NON_PRENOTATO);
            
            BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
            PosizioneDAO posDAO = new PosizioneDAO();
            posDAO.setBibliotecaDAO(bibliotecaDAO);
            CopiaDAO copiaDAO = posDAO.getCopiaDAO();
            
            copiaDAO.doUpdate(prenotazione.getCopia());
        }
        
        if(prenotazione.getStatus().equals(Prenotazione.RITIRATO)){
            dataScadenza.add(Calendar.DAY_OF_MONTH, 30);
            prenotazione.setDataScadenza(dataScadenza);
        } else if(prenotazione.getStatus().equals(Prenotazione.RESTITUITO)){
            dataConsegna.getTime();
            prenotazione.setDataConsegna(dataConsegna);
        }
        
        return prenDAO.doUpdate(prenotazione) != -1;
    }

    public Prenotazione visualizzaPrenotazione(int idPrenotazione) {
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione prenotazione = prenotazioneDAO.doRetriveById(idPrenotazione);

        return prenotazione;
    }

}