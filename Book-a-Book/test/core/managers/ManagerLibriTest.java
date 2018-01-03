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
import core.utils.Criterio;
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
        
        biblioteca = new BibliotecaDAOStub().doRetriveById("12345");
        List<Copia> copie = new ArrayList<Copia>();
        
        posizione = new Posizione("Scaffale C");
        posizione.setBiblioteca(biblioteca);
        posizioneDAO = new PosizioneDAO();
        
        autori = new ArrayList<>();
        Autore a = new Autore("Dan Brown");
        autori.add(a);
        libroDAO = new LibroDAO();
        libro = libroDAO.doRetriveById("9788804681960");
        copia = new Copia("C-005", "Non prenotato", "Disponibile", posizione, libro);
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
/*
    @Test
    public void testCercaLibro() {
        System.out.println("cercaLibro");
        Criterio c = null;
        String val = "";
        ManagerLibri instance = new ManagerLibri();
        Collection<Libro> expResult = null;
        Collection<Libro> result = instance.cercaLibro(c, val);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of modificaDisponibilita method, of class ManagerLibri.
     */
/*
    @Test
    public void testModificaDisponibilita() {
        System.out.println("modificaDisponibilita");
        String isbn = "";
        String idBiblioteca = "";
        boolean flag = false;
        ManagerLibri instance = new ManagerLibri();
        boolean expResult = false;
        boolean result = instance.modificaDisponibilita(isbn, idBiblioteca, flag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of aggiuntaLibro method, of class ManagerLibri.
     */
/*
    @Test
    public void testAggiuntaLibro() {
        System.out.println("aggiuntaLibro");
        String isil = "";
        String isbn = "";
        String titolo = "";
        String editore = "";
        Calendar dataPubblicazione = null;
        String descrizione = "";
        List<Autore> autori = null;
        List<Posizione> posizioni = null;
        ManagerLibri instance = new ManagerLibri();
        Libro expResult = null;
        Libro result = instance.aggiuntaLibro(isil, isbn, titolo, editore, dataPubblicazione, descrizione, autori, posizioni);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
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
/*
    @Test
    public void testEliminaLibro() {
        System.out.println("eliminaLibro");
        String isbn = "";
        String isil = "";
        ManagerLibri instance = new ManagerLibri();
        boolean expResult = false;
        boolean result = instance.eliminaLibro(isbn, isil);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
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
        assertEquals("Le dimensioni delle liste non sono uguali", 1, result.size());
    }
    
}
