/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.AutoreDAO;
import core.DAO.BibliotecaDAO;
import core.DAO.CopiaDAO;
import core.DAO.LibroDAO;
import core.DAO.PosizioneDAO;
import core.entities.Autore;
import core.entities.Biblioteca;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import core.utils.Criterio;

/**
 *
 * @author manuel
 */
public class ManagerLibri {

    private BibliotecaDAO bibliotecaDAO;
    private PosizioneDAO posizioneDAO;
    private LibroDAO libroDAO;
    private CopiaDAO copiaDAO;
    private AutoreDAO autoreDAO;

    public ManagerLibri() {
        this.bibliotecaDAO = new BibliotecaDAO();
        this.posizioneDAO = new PosizioneDAO();
        this.posizioneDAO.setBibliotecaDAO(bibliotecaDAO);
        this.libroDAO = new LibroDAO();
        copiaDAO = this.posizioneDAO.getCopiaDAO();
        this.autoreDAO = new AutoreDAO();
    }

    public ManagerLibri(BibliotecaDAO bibliotecaDAO, PosizioneDAO posizioneDAO, LibroDAO libroDAO) {
        this.bibliotecaDAO = bibliotecaDAO;
        this.posizioneDAO = posizioneDAO;
        this.posizioneDAO.setBibliotecaDAO(bibliotecaDAO);
        this.libroDAO = libroDAO;
        copiaDAO = this.posizioneDAO.getCopiaDAO();
        this.autoreDAO = new AutoreDAO();
    }

    /**
     * Metodo che ricerca un libro nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di libri, null se c'è qualche 
     * errore.
     */
    public Collection<Libro> cercaLibro(Criterio c) {

        List<Libro> listToReturn = new ArrayList<>();
        List<Libro> allBooks = libroDAO.doRetriveAll();        

        if(allBooks == null || allBooks.isEmpty()){
            return null;
        }
        
        for (Libro b : allBooks) {
            if (c.isValid(b)) {
                listToReturn.add(b);
            }
        }

        return listToReturn;
    }

    /**
     * Metodo che modifica la disponibilità di un libro in una biblioteca
     *
     * @param isbn
     * @param idBiblioteca
     * @param flag
     * @return
     */
    public boolean modificaDisponibilita(String isbn, String idBiblioteca, boolean flag) {
        Biblioteca biblio = bibliotecaDAO.doRetriveById(idBiblioteca);
        if (biblio == null) {
            return false;
        }

        List<Posizione> posizioni = posizioneDAO.doRetriveAllByIsil(idBiblioteca);
        if (posizioni == null || posizioni.isEmpty()) {
            return false;
        }
        biblio.setPosizioni(posizioni);

        for (Posizione p : biblio.getPosizioni()) {
            for (Copia c : p.getCopie()) {
                if (flag) {
                    c.setDisponibilita(Copia.DISPONIBILE_SI);
                } else {
                    c.setDisponibilita(Copia.DISPONIBILE_NO);
                }

                if (copiaDAO.doUpdate(c) == -1) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * Metodo che consente di aggiungere un libro al database globale
     *
     * @param isbn del libro
     * @param titolo del libro
     * @param editore del libro
     * @param dataPubblicazione del libro
     * @param descrizione del libro
     * @param autori del libro
     * @return Il libro appena inserito, oppure null
     */
    public Libro aggiuntaLibro(String isbn, String titolo, String editore, Calendar dataPubblicazione, String descrizione, String pathFoto, List<Autore> autori) {

        Libro book = new Libro(isbn, titolo, editore, dataPubblicazione, descrizione, pathFoto);

        for (Autore a : autori) {
            Autore retrived;
            //controllo se gli autori esistiono
            if ((retrived = autoreDAO.doRetriveByNome(a.getNome())) == null) {
                int idAutore;
                //se non esiste lo inserisco
                if ((idAutore = autoreDAO.doInsert(a)) != -1) {
                    a.setId(idAutore);
                } else {
                    book = null;
                    break;
                }
            } else {
                a = retrived;
            }

            book.addAutore(a);

            if (libroDAO.doInsert(book) == -1) {
                return null;
            }

        }

        return book;
    }

    /**
     * Aggiunge le copie di un libro ad una biblioteca
     *
     * @param isil
     * @param book
     * @param posizioni le posizioni da aggiungere NB devono avere le copie.
     * @return true se va a buon fine, false altrimenti.
     */
    public boolean aggiungiLibroBiblioteca(String isil, Libro book, Collection<Posizione> posizioni) {
        Biblioteca biblio = bibliotecaDAO.doRetriveById(isil);
        boolean toReturn = true;

        for (Posizione p : posizioni) {
            if (toReturn) {
                p.setBiblioteca(biblio);

                //controllo se esiste la posizione
                if(posizioneDAO.doRetriveById(p.getEtichetta(),biblio.getIsil()) == null){
                    //se non esiste la inserisco
                    if (posizioneDAO.doInsert(p) == -1) {
                        toReturn = false;
                        break;
                    }
                }

                for (Copia cop : p.getCopie()) {
                    cop.setLibro(book);
                    if (copiaDAO.doInsert(cop) == -1) {
                        toReturn = false;
                        break;
                    }
                }
            }
        }
        return toReturn;
    }

    /**
     * Metodo che permette l'aggiunta di una Copia di un Libro in una specifica
     * Posizione della Biblioteca.
     *
     * @param isbn (String) codice ISBN del Libro del quale si desidera creare
     * una nuova copia,
     * @param isil (String) codice ISIL della Biblioteca che desidera creare la
     * nuova copia,
     * @param idPosizione (String) id della Posizione in Biblioteca nella quale
     * la copia verrà inserita,
     * @param idCopia (String) codice identificativo della nuova copia da
     * aggiungere.
     *
     * @return oggetto Copia corrispondente alla Copia creata, null se fallisce
     * la creazione e l'inserimento.
     */
    public Copia aggiungiCopia(String isbn, String isil, String idPosizione, String idCopia) {

        Posizione posizione = posizioneDAO.doRetriveById(idPosizione, isil);
        Libro libro = libroDAO.doRetriveById(isbn);

        Copia copia = new Copia(idCopia, "Non prenotato", "Disponibile", posizione, libro);

        if (copiaDAO.doRetriveById(idCopia, isbn, isil) == null) { //controlla se la copia è già presente nel database
            //Se non è presente
            if (copiaDAO.doInsert(copia) == -1) { //Inserisce la copia nel db
                return null; //return null se fallisce l'inserimento della copia
            }
        }
        return copia;
    }

    /**
     * Metodo che permette l'eliminazione di una Copia di un Libro in una
     * specifica Posizione della Biblioteca.
     *
     * @param isbn (String) codice ISBN del Libro del quale si desidera creare
     * una nuova copia,
     * @param isil (String) codice ISIL della Biblioteca che desidera creare la
     * nuova copia,
     * @param idPosizione (String) id della Posizione in Biblioteca nella quale
     * la copia verrà inserita,
     * @param idCopia (String) codice identificativo della nuova copia da
     * aggiungere.
     *
     * @return true se l'operazione è andata a buon fine, false altrimenti.
     */
    public boolean eliminaCopia(String isbn, String isil, String idPosizione, String idCopia) {

        Posizione posizione = posizioneDAO.doRetriveById(idPosizione, isil);
        Libro libro = libroDAO.doRetriveById(isbn);

        Copia copia;

        if ((copia = copiaDAO.doRetriveById(idCopia, isbn, isil)) != null) { //controlla se la copia è già presente nel database
            //Se è presente e non è stata prenotata
            if (copia.getStatus().equals(Copia.STATUS_PRENOTATO) || copia.getStatus().equals(Copia.STATUS_ELIMINATO)) {
                return false;
            }

            copia.setStatus(Copia.STATUS_ELIMINATO);

            if (copiaDAO.doUpdate(copia) == -1) { //elimina la copia dal db
                return false; //return false se fallisce l'eliminazione
            }
        }
        return true;
    }

    /**
     * Metodo che permette di elimiare un libro da una biblioteca
     *
     * @param isbn
     * @param isil
     * @return
     */
    public boolean eliminaLibro(String isbn, String isil) {
        Biblioteca biblio = bibliotecaDAO.doRetriveById(isil);
        if (biblio == null) {
            return false;
        }

        List<Posizione> posizioni = posizioneDAO.doRetriveAllByIsil(isil);
        if (posizioni == null || posizioni.isEmpty()) {
            return false;
        }
        biblio.setPosizioni(posizioni);

        for (Posizione p : biblio.getPosizioni()) {
            for (Copia c : p.getCopie()) {
                if (c.getLibro().getIsbn().equals(isbn)) {
                    c.setStatus(Copia.STATUS_ELIMINATO);
                    if (copiaDAO.doUpdate(c) == -1) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    /**
     * Metodo che permette lo spostamento di una Copia da una Posizione ad
     * un'altra Posizione, modificando il riferimento alla Posizione attuale.
     *
     * @param isbn (String) codice ISBN del Libro del quale si desidera creare
     * una nuova copia,
     * @param isil (String) codice ISIL della Biblioteca che desidera creare la
     * nuova copia,
     * @param idCopia (String) codice identificativo della nuova copia da
     * aggiungere,
     * @param vecchiaPosizione (Posizione) oggetto Posizione contenente la
     * posizione attuale della Copia,
     * @param nuovaPosizione (Posizione) oggetto Posizione nella quale si
     * desidera spostare la Copia.
     *
     * @return true se l'operazione è andata a buon fine, false se il parametro
     * vecchiaPosizione non corrisponde alla posizione attuale della Copia,
     * oppure se lo spostamento fallisce.
     */
    public boolean spostaCopie(String isbn, String isil, String idCopia, Posizione vecchiaPosizione, Posizione nuovaPosizione) {

        Copia copia = copiaDAO.doRetriveById(idCopia, isbn, isil);

        if (copia.getPosizione().getEtichetta().equals(vecchiaPosizione.getEtichetta())) {   //controllo se la copia ricevuta ha la stessa etichetta della vecchia posizione ricevuta
            if (copiaDAO.doUpdatePosizione(copia, nuovaPosizione.getEtichetta()) == -1) { //aggiorna la posizione della copia con la nuova posizione
                return false; //return false se la modifica fallisce
            }
        } else { //la vecchiaPosizione ricevuta non corrisponde alla posizione contenuta nella copia
            return false; //return false in quanto la modifica non può essere effettuata
        }

        return true;
    }

    /**
     * Metodo che permette di visualizare uno specifico libro
     *
     * @param isbn
     * @return
     */
    public Libro visualizzaLibro(String isbn) {
        return libroDAO.doRetriveById(isbn);
    }

    /**
     * Metodo che consente l'aggiunta di una nuova Posizione all'interno di una
     * Biblioteca
     *
     * @param etichetta (String) Codice identificativo della nuova Posizione,
     * @param isil (String) Codice ISIL della Biblioteca che desidera aggiungere
     * la nuova Posizione.
     *
     * @return oggetto Posizione corrispondente alla Posizione aggiunta, null
     * altrimenti.
     */
    public Posizione aggiungiPosizione(String etichetta, String isil) {

        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil); //prendo un oggetto biblioteca dalla DAO
        List<Copia> copie = new ArrayList<>();

        Posizione posizione = new Posizione(etichetta);
        posizione.setBiblioteca(biblioteca);

        if (posizioneDAO.doRetriveById(etichetta, isil) == null) { //controlla se la posizione è già presente nel database
            //Se non è presente
            if (posizioneDAO.doInsert(posizione) == -1) { //inserisce la posizione nel db
                return null; //nel caso non riesca ad inserire il metodo restituisce null
            }
        }

        return posizione; //restituisce la posizione inserita
    }

    /**
     * Metodo che restituisce una Collection di Posizione contenente tutte le
     * Posizioni in una Biblioteca che contengono un determinato Libro.
     *
     * @param isbn (String) Codice ISBN del Libro di cui si desidera cercare le
     * posizioni,
     * @param isil (String) Codice ISIL della Biblioteca che contiene tali
     * posizioni.
     *
     * @return la Collection di Posizione contenente tutte le posizioni che
     * corrispondono ai parametri ricevuti.
     */
    public Collection<Posizione> visualizzaPosizioniLibro(String isbn, String isil) {

        List<Posizione> posizioni = posizioneDAO.doRetriveByLibroAndBiblioteca(isbn, isil);
        return posizioni;
    }

}
