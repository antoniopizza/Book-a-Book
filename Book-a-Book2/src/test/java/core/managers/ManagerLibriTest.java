/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.BibliotecaDAOStub;
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
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import core.utils.Criterio;
import core.utils.CriterioPerAutore;

/**
 *
 * @author kliffom
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerLibriTest {
    
    Biblioteca biblioteca;
    Posizione posizione;
    Posizione posizioneNew;
    PosizioneDAO posizioneDAO;
    List<Autore> autori;
    Libro libro;
    LibroDAO libroDAO;
    Copia copia;
    
    public ManagerLibriTest() {
        
        biblioteca = new BibliotecaDAOStub().doRetriveById("IT-321");
        List<Copia> copie = new ArrayList<Copia>();
        
        posizione = new Posizione("Scaffale C");
        posizione.setBiblioteca(biblioteca);
        posizioneDAO = new PosizioneDAO();
        
        autori = new ArrayList<>();
        Autore a = new Autore("Dan Brown");
        autori.add(a);
        libroDAO = new LibroDAO();
        libro = libroDAO.doRetriveById("9788804681960");
        copia = new Copia("C-006", "Non prenotato", Copia.DISPONIBILE_SI, posizione, libro);
        posizione.addCopia(copia);
        
        posizioneNew = new Posizione("Ripiano Basso A");
        posizioneNew.setBiblioteca(biblioteca);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cercaLibro method, of class ManagerLibri.
     */

    @Test
    public void test7CercaLibro() {
        System.out.println("cercaLibro");
        Criterio c = new CriterioPerAutore("Dan Brown");        
        ManagerLibri instance = new ManagerLibri();       
        Collection<Libro> result = instance.cercaLibro(c);
        assertFalse(result.isEmpty());
        assertTrue(result.size() == 1);
        assertTrue(result.contains(libro));
    }

    /**
     * Test of modificaDisponibilita method, of class ManagerLibri.
     */

    @Test
    public void test8ModificaDisponibilita() {
        System.out.println("modificaDisponibilita");        
        boolean flag = false;
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        boolean expResult = true;
        boolean result = instance.modificaDisponibilita(libro.getIsbn(),biblioteca.getIsil(), flag);
        assertEquals(expResult, result);
    }

    /**
     * Test of aggiuntaLibro method, of class ManagerLibri.
     */

    @Test
    public void test6AggiuntaLibro() {
        System.out.println("aggiuntaLibro");
        String isbn = "9780553103540"; //9788804681960
        String titolo = "A Game of Thrones";
        String editore = "Spectra";
        String pathFoto = "https://covers.openlibrary.org/b/id/368052-M.jpg";
        Calendar dataPubblicazione = new GregorianCalendar(2002,4,28);
        String descrizione = "The first book of the songs of ice and fire";
        
        ManagerLibri instance = new ManagerLibri();
        Libro expResult = new Libro(isbn, titolo, editore, dataPubblicazione, descrizione, pathFoto);
        
        Autore autore = new Autore("George R.R. Martin");
        autore.setId(4);
        expResult.addAutore(autore);
        
        Libro result = instance.aggiuntaLibro(isbn, titolo, editore, dataPubblicazione, descrizione, pathFoto, expResult.getAutori());
        assertEquals(expResult, result);        
    }

    /**
     * Test of aggiungiCopia method, of class ManagerLibri.
     */
    @Test
    public void test2AggiungiCopia() {
        System.out.println("aggiungiCopia");
        String isbn = copia.getLibro().getIsbn();
        String isil = copia.getPosizione().getBiblioteca().getIsil();
        String idPosizione = copia.getPosizione().getEtichetta();
        String idCopia = copia.getId();
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        Copia expResult = copia;
        Copia result = instance.aggiungiCopia(isbn, isil, idPosizione, idCopia);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminaCopia method, of class ManagerLibri.
     */
    @Test
    public void test5EliminaCopia() {
        System.out.println("eliminaCopia");
        String isbn = copia.getLibro().getIsbn();
        String isil = copia.getPosizione().getBiblioteca().getIsil();
        String idPosizione = copia.getPosizione().getEtichetta();
        String idCopia = copia.getId();
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        boolean expResult = true;
        boolean result = instance.eliminaCopia(isbn, isil, idPosizione, idCopia);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminaLibro method, of class ManagerLibri.
     */

    @Test
    public void test9EliminaLibro() {
        System.out.println("eliminaLibro");       
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        boolean expResult = true;
        boolean result = instance.eliminaLibro(libro.getIsbn(), biblioteca.getIsil());
        assertEquals(expResult, result);       
    }

    /**
     * Test of spostaCopie method, of class ManagerLibri.
     */
    @Test
    public void test4SpostaCopie() {
        System.out.println("spostaCopie");
        String isbn = copia.getLibro().getIsbn();
        String isil = copia.getPosizione().getBiblioteca().getIsil();
        String idCopia = copia.getId();
        Posizione vecchiaPosizione = copia.getPosizione();
        Posizione nuovaPosizione = posizioneNew;
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        boolean expResult = true;
        boolean result = instance.spostaCopie(isbn, isil, idCopia, vecchiaPosizione, nuovaPosizione);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of visualizzaLibro method, of class ManagerLibri.
     */
/*
    @Test
    public void testVisualizzaLibro() {
        System.out.println("visualizzaLibro");
        String isbn = "";
        ManagerLibri instance = new ManagerLibri();
        Libro expResult = null;
        Libro result = instance.visualizzaLibro(isbn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of aggiungiPosizione method, of class ManagerLibri.
     */
    @Test
    public void test1AggiungiPosizione() {
        System.out.println("aggiungiPosizione");
        String etichetta = posizione.getEtichetta();
        String isil = posizione.getBiblioteca().getIsil();
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        Posizione expResult = posizione;
        Posizione result = instance.aggiungiPosizione(etichetta, isil);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of visualizzaPosizioniLibro method, of class ManagerLibri.
     */
    @Test
    public void test3VisualizzaPosizioniLibro() {
        System.out.println("visualizzaPosizioniLibro");
        String isbn = libro.getIsbn();
        String isil = biblioteca.getIsil();
        ManagerLibri instance = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        Collection<Posizione> result = instance.visualizzaPosizioniLibro(isbn, isil);
        
        //assertEquals("Le liste non sono uguali", expResult, result);
        assertEquals("Le dimensioni delle liste non sono uguali", 2, result.size());
    }
    
    @Test
    public void test6xAggiuntaLibroBiblioteca(){
        Libro b = libroDAO.doRetriveById("9780553103540");
        ManagerLibri manager = new ManagerLibri(new BibliotecaDAOStub(), posizioneDAO, libroDAO);
        List<Posizione> posizioni = new ArrayList<>();
        Posizione p = new Posizione("AR1");
        Copia c = new Copia("GOT1", Copia.STATUS_NON_PRENOTATO,Copia.DISPONIBILE_SI);
        p.addCopia(c);
        posizioni.add(p);
        boolean result = manager.aggiungiLibroBiblioteca(biblioteca.getIsil(), b, posizioni);
        assertEquals(true,result);
    }
    
}
