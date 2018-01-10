/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.BibliotecaDAO;
import core.DAO.CopiaDAO;
import core.DAO.LibroDAO;
import core.DAO.PosizioneDAO;
import core.DAO.PrenotazioneDAO;
import core.entities.Autore;
import core.entities.Biblioteca;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import core.utils.Criterio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerLibri {
    
    private BibliotecaDAO bibliotecaDAO;
    private PosizioneDAO posizioneDAO;
    private LibroDAO libroDAO;
    private CopiaDAO copiaDAO;
    private PrenotazioneDAO prenotazioneDAO;

    public ManagerLibri() {
        this.bibliotecaDAO = new BibliotecaDAO();
        this.posizioneDAO = new PosizioneDAO();
        this.posizioneDAO.setBibliotecaDAO(bibliotecaDAO);
        this.libroDAO = new LibroDAO();
        copiaDAO = this.posizioneDAO.getCopiaDAO();
    }

    public ManagerLibri(BibliotecaDAO bibliotecaDAO, PosizioneDAO posizioneDAO, LibroDAO libroDAO) {
        this.bibliotecaDAO = bibliotecaDAO;
        this.posizioneDAO = posizioneDAO;
        this.posizioneDAO.setBibliotecaDAO(bibliotecaDAO);
        this.libroDAO = libroDAO;
        copiaDAO = this.posizioneDAO.getCopiaDAO();
    }
    
    
    
    //da fare
    public Collection<Libro> cercaLibro(Criterio c,String val){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //da fare
    public boolean modificaDisponibilita(String isbn,String idBiblioteca,boolean flag){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    //da fare
    public Libro aggiuntaLibro
        (String isil,String isbn,String titolo,String editore,Calendar dataPubblicazione,String descrizione,List<Autore> autori,List<Posizione> posizioni){
            throw new UnsupportedOperationException("Not implemented yet");
    }
        
    /**
     * Metodo che permette l'aggiunta di una Copia di un Libro in una specifica Posizione della Biblioteca.
     * 
     * @param isbn (String) codice ISBN del Libro del quale si desidera creare una nuova copia,
     * @param isil (String) codice ISIL della Biblioteca che desidera creare la nuova copia,
     * @param idPosizione (String) id della Posizione in Biblioteca nella quale la copia verrà inserita,
     * @param idCopia (String) codice identificativo della nuova copia da aggiungere.
     * 
     * @return oggetto Copia corrispondente alla Copia creata, null se fallisce la creazione e l'inserimento.
     */
    public Copia aggiungiCopia(String isbn, String isil, String idPosizione, String idCopia){
        
        Posizione posizione = posizioneDAO.doRetriveById(idPosizione, isil);
        Libro libro = libroDAO.doRetriveById(isbn);
        
        Copia copia = new Copia(idCopia, "Non prenotato", "Disponibile", posizione, libro);
        
        if(copiaDAO.doRetriveById(idCopia, isbn, isil) == null) { //controlla se la copia è già presente nel database
            //Se non è presente
            if(copiaDAO.doInsert(copia) == -1) { //Inserisce la copia nel db
                return null; //return null se fallisce l'inserimento della copia
            }
        }
        return copia;
    } 
    
    /**
     * Metodo che permette l'eliminazione di una Copia di un Libro in una specifica Posizione della Biblioteca.
     * 
     * @param isbn (String) codice ISBN del Libro del quale si desidera creare una nuova copia,
     * @param isil (String) codice ISIL della Biblioteca che desidera creare la nuova copia,
     * @param idPosizione (String) id della Posizione in Biblioteca nella quale la copia verrà inserita,
     * @param idCopia (String) codice identificativo della nuova copia da aggiungere.
     * 
     * @return true se l'operazione è andata a buon fine, false altrimenti.
     */
    public boolean eliminaCopia(String isbn, String isil, String idPosizione,String idCopia){
        
        Posizione posizione = posizioneDAO.doRetriveById(idPosizione, isil);
        Libro libro = libroDAO.doRetriveById(isbn);
        
        Copia copia = new Copia(idCopia, "Non prenotato", "Disponibile", posizione, libro);
        
        if(copiaDAO.doRetriveById(idCopia, isbn, isil) != null) { //controlla se la copia è già presente nel database
            //Se è presente
            if(copiaDAO.doDelete(copia) == -1) { //elimina la copia dal db
                return false; //return false se fallisce l'eliminazione
            }
        }
        return true;
    }        
    
    /**
     * Metodo che permette di eliminare tutti i riferimenti di un Libro da una biblioteca,
     * eliminando tutte le Copie possedute e rimuovendo tutti i riferimenti dalle Prenotazioni.
     * @param isbn (String) codice ISBN del Libro che si desidera eliminare
     * @param isil (String) codice ISIL della Biblioteca che desidera eliminare il Libro
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    public boolean eliminaLibro(String isbn,String isil){
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Metodo che permette lo spostamento di una Copia da una Posizione ad un'altra Posizione,
     * modificando il riferimento alla Posizione attuale.
     * 
     * @param isbn (String) codice ISBN del Libro del quale si desidera creare una nuova copia,
     * @param isil (String) codice ISIL della Biblioteca che desidera creare la nuova copia,
     * @param idCopia (String) codice identificativo della nuova copia da aggiungere,
     * @param vecchiaPosizione (Posizione) oggetto Posizione contenente la posizione attuale della Copia,
     * @param nuovaPosizione (Posizione) oggetto Posizione nella quale si desidera spostare la Copia.
     * 
     * @return true se l'operazione è andata a buon fine, 
     * false se il parametro vecchiaPosizione non corrisponde alla posizione attuale della Copia, oppure se lo spostamento fallisce.
     */
    public boolean spostaCopie(String isbn,String isil,String idCopia,Posizione vecchiaPosizione,Posizione nuovaPosizione){
        
        Copia copia = copiaDAO.doRetriveById(idCopia, isbn, isil);
        
        if(copia.getPosizione().getEtichetta().equals(vecchiaPosizione.getEtichetta())) {   //controllo se la copia ricevuta ha la stessa etichetta della vecchia posizione ricevuta
            if(copiaDAO.doUpdatePosizione(copia, nuovaPosizione.getEtichetta()) == -1) { //aggiorna la posizione della copia con la nuova posizione
                return false; //return false se la modifica fallisce
            }
        } else { //la vecchiaPosizione ricevuta non corrisponde alla posizione contenuta nella copia
            return false; //return false in quanto la modifica non può essere effettuata
        }
        
        return true;
    }
    
    /**
     * Metodo che restituisce un oggetto Libro corrispondente al codice isbn ricevuto.
     * @param isbn (String) Codice ISBN del Libro di cui si desidera ricevere l'oggetto.
     * @return un oggetto Libro corrispondente all'SIBN, null altrimenti.
     */
    public Libro visualizzaLibro(String isbn){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * Metodo che consente l'aggiunta di una nuova Posizione all'interno di una Biblioteca
     * 
     * @param etichetta (String) Codice identificativo della nuova Posizione,
     * @param isil (String) Codice ISIL della Biblioteca che desidera aggiungere la nuova Posizione.
     * 
     * @return oggetto Posizione corrispondente alla Posizione aggiunta, null altrimenti.
     */
    public Posizione aggiungiPosizione(String etichetta, String isil){
        
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil); //prendo un oggetto biblioteca dalla DAO
        List<Copia> copie = new ArrayList<>();
        
        Posizione posizione = new Posizione(etichetta, biblioteca, copie);
        
        if(posizioneDAO.doRetriveById(etichetta, isil) == null) { //controlla se la posizione è già presente nel database
            //Se non è presente
            if(posizioneDAO.doInsert(posizione) == -1) { //inserisce la posizione nel db
                return null; //nel caso non riesca ad inserire il metodo restituisce null
            }
        }
        
        return posizione; //restituisce la posizione inserita
    }
    
    /**
     * Metodo che restituisce una Collection di Posizione contenente 
     * tutte le Posizioni in una Biblioteca che contengono un determinato Libro.
     * 
     * @param isbn (String) Codice ISBN del Libro di cui si desidera cercare le posizioni,
     * @param isil (String) Codice ISIL della Biblioteca che contiene tali posizioni.
     * 
     * @return la Collection di Posizione contenente tutte le posizioni che corrispondono ai parametri ricevuti.
     */
    public Collection<Posizione> visualizzaPosizioniLibro(String isbn, String isil){
        
        List<Posizione> posizioni = posizioneDAO.doRetriveByLibroAndBiblioteca(isbn, isil);
        return posizioni;
    }
    
}
