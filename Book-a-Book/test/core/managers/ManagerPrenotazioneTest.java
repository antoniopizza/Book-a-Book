/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.PrenotazioneDAO;
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Copia;
import core.entities.Indirizzo;
import core.entities.Libro;
import core.entities.Persona;
import core.entities.Posizione;
import core.entities.Prenotazione;
import core.utils.Criterio;
import core.utils.prenotazioniPerId;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mirko
 */
public class ManagerPrenotazioneTest {
    
    Persona p;
    Indirizzo indPers, indBib;
    Admin admin;
    Libro libro;
    Biblioteca bib;
    Posizione pos;
    Copia copia;
    Prenotazione prenotazione;
    List<Prenotazione> listaPren;
    
    public ManagerPrenotazioneTest() {
        libro = new Libro("9788804492504","Il fu Mattia Pascal","Mondadori",new GregorianCalendar(2001,01,01),"\"Una delle poche, anzi forse la sola ch'io sapessi di certo era questa: che mi chiamavo Mattia Pascal\". Ma anche la certezza del proprio nome doveva svanire ben presto nella vita del bibliotecario Mattia Pascal. A lui il caso ha dato una clamorosa possibilità: rinascere, azzerare il proprio passato e ricominciare una nuova vita. Moglie, suocera e amici lo riconoscono nel cadavere di un suicida e lo credono morto. Ricco, grazie a una vincita al gioco, può rifarsi una nuova vita e si inventa così il ruolo di Adriano Meis. Ma la libertà appena acquisita è in realtà una ferrea prigione: non è nessuno, non esiste, non ha una realtà sociale, è un \"forestiere della vita\". Nemmeno l'amore che prova per la dolce Adriana può aiutarlo (come può sposarsi?). L'unica soluzione è morire di nuovo: uccidere Adriano e far rinascere Mattia. La sua nuova identità ora è quella del fu Mattia Pascal: un morto-vivo che non può riprendere la vita di prima (la moglie si è risposata) e a cui non resta quindi che ritornare bibliotecario in un paese dove nessuno legge e andare di tanto in tanto a far visita alla propria tomba. Il romanzo, pubblicato nel 1904, scandaglia, anche umoristicamente, la realtà piccolo-borghese ed evidenzia l'impossibilità per l'uomo di essere totalmente artefice del proprio destino.","path");
        indBib = new Indirizzo("via Roma","Nocera Inferiore","21","SA","84014");
        admin = new Admin();
        admin.setId(7);
        bib = new Biblioteca("IT-321","Biblioteca Svevo","Attiva",indBib, admin);
        pos = new Posizione("Ripiano Basso A",bib);
        copia = new Copia("C-002","Prenotato","Disponibile",pos,libro);
        indPers = new Indirizzo("via Piave", "Avellino", "51", "AV", "83100");
        p = new Persona("AS6258", indPers, 4, "Chiara", "Senatore", null);
        prenotazione = new PrenotazioneDAO().doRetriveById(1);
        listaPren = new PrenotazioneDAO().doRetriveAll();
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
     * Test of prenotareLibro method, of class ManagerPrenotazione.
     */
    @Test
    public void test1PrenotareLibro() {
        System.out.println("prenotareLibro");
        String isil = bib.getIsil();
        ManagerPrenotazione instance = new ManagerPrenotazione();
        boolean expResult = false;
        boolean result = instance.prenotareLibro(p, copia, isil);
        assertEquals(expResult, result);
    }

    /**
     * Test of visualizzaPrenotazioni method, of class ManagerPrenotazione.
     */
    @Test
    public void test4VisualizzaPrenotazioni() {
        System.out.println("visualizzaPrenotazioni");
        Criterio cp = new prenotazioniPerId();
        ManagerPrenotazione instance = new ManagerPrenotazione();
        Collection<Prenotazione> expResult = listaPren;
        Collection<Prenotazione> result = instance.visualizzaPrenotazioni(cp);
        assertEquals(expResult, result);
    }

    /**
     * Test of controlloPrenotazione method, of class ManagerPrenotazione.
     */
    @Test
    public void test3ControlloPrenotazione() {
        System.out.println("controlloPrenotazione");
        int idPrenotazione = 1;
        String email = prenotazione.getPersona().getAccount().getEmail();
        String status = prenotazione.getStatus();
        ManagerPrenotazione instance = new ManagerPrenotazione();
        boolean expResult = false;
        boolean result = instance.controlloPrenotazione(idPrenotazione, email, status);
        assertEquals(expResult, result);
    }

    /**
     * Test of visualizzaPrenotazione method, of class ManagerPrenotazione.
     */
    @Test
    public void test2VisualizzaPrenotazione() {
        System.out.println("visualizzaPrenotazione");
        int idPrenotazione = 1;
        ManagerPrenotazione instance = new ManagerPrenotazione();
        Prenotazione expResult = prenotazione;
        Prenotazione result = instance.visualizzaPrenotazione(idPrenotazione);
        assertEquals(expResult, result);
    }
    
}
