/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import core.DAO.TelefonoDAO;
import core.entities.Account;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Telefono;
import java.util.ArrayList;
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
 * @author stefanosolda
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerRegistrazioneTest {
    public static Persona persona;
    public static Biblioteca biblioteca;
    public static Bibliotecario bibliotecario,dipendente;
    public static Telefono telefonoBiblio;
    public static Telefono telefonoPers;
    
    public ManagerRegistrazioneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    persona = new Persona("123456", new Indirizzo("appia", "Atripalda", "117", "NA", "83030"), "Stefano", "rossi", new Account("chicco@chicchettino.com", "Ciaone96", "34567", "23456765"));
        biblioteca = new Biblioteca("ITAN34", "Cicciono", "In Sospeso", new Indirizzo("via", "citta", "civico", "PR", "83042"), null);
        dipendente = new Bibliotecario("Accettato", "Dipendente", biblioteca, "Mario", "Rossi", new Account("dipendente@biblio.org", "Ciaone95","bhinjuyi" , "Bibliotecario"));
        Telefono telefonoBiblio = new Telefono("+39", "3463202474", null, biblioteca);
        Telefono telefonoPers = new Telefono("+39","3456789091" , persona, null);
        bibliotecario = new Bibliotecario("In Sospeso", "Responsabile", biblioteca, "Stefano", "Malato", new Account("bibliotecario@gmail.com", "Ciaone95", "5676567", "Bibliotecario"));
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
     * Test of registra method, of class ManagerRegistrazione.
     */
    @Test
    public void test1Registra_12args() {
        System.out.println("registra");
        String nome = persona.getNome();
        String cognome = persona.getCognome();
        String email = persona.getAccount().getEmail();
        String numeroDocumento = persona.getNumDocumento();
        String via = persona.getIndirizzo().getVia();
        String citta = persona.getIndirizzo().getCitta();
        String numeroCivico = persona.getIndirizzo().getCivico();
        String password = persona.getAccount().getPassword();
        String pathFoto = persona.getAccount().getPathFoto();
        String provincia = persona.getIndirizzo().getProvincia();
        String CAP = persona.getIndirizzo().getCap();
        String numeroTelefono = "3456789091";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Persona expResult = persona;
        Persona result = instance.registra(nome, cognome, email, numeroDocumento, via, citta, numeroCivico, password, pathFoto, provincia, CAP, numeroTelefono);
        assertEquals(expResult, result);
    }

    /**
     * Test of registra method, of class ManagerRegistrazione.
     */
    @Test
    public void test2Registra_14args() {
        System.out.println("registra");
        String isil = biblioteca.getIsil();
        String nomeBiblioteca = biblioteca.getNome();
        String nomeBibliotecario = bibliotecario.getNome();
        String via = biblioteca.getIndirizzo().getVia();
        String citta = biblioteca.getIndirizzo().getCitta();
        String numeroCivico = biblioteca.getIndirizzo().getCivico();
        String provincia = biblioteca.getIndirizzo().getProvincia();
        String CAP = biblioteca.getIndirizzo().getCap();
        String email = bibliotecario.getAccount().getEmail();
        String password = bibliotecario.getAccount().getPassword();
        String pathFoto = bibliotecario.getAccount().getPathFoto();
        String tipo = bibliotecario.getAccount().getTipo();
        String cognome = bibliotecario.getCognome();
        String numeroTelefono = "3463202474";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Biblioteca expResult = biblioteca;
        Biblioteca result = instance.registra(isil, nomeBiblioteca, via, citta, numeroCivico, provincia, CAP, numeroTelefono);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of registraDipendente method, of class ManagerRegistrazione.
     */
    @Test
    public void test3RegistraDipendente() {
        System.out.println("registraDipendente");
        String isil = biblioteca.getIsil();
        String nome = "Mario";
        String cognome = "Rossi";
        String email = "dipendente@biblio.org";
        String password = "Ciaone95";
        String path_foto = "bhinjuyi";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Bibliotecario expResult = dipendente;
        Bibliotecario result = instance.registraDipendente(isil, nome, cognome, email, password, path_foto,"Dipendente");
        assertEquals(expResult, result);
    }

    /**
     * Test of visualizzaRichieste method, of class ManagerRegistrazione.
     */
    @Test
    public void test4VisualizzaRichieste() {
        System.out.println("visualizzaRichieste");
        ManagerRegistrazione instance = new ManagerRegistrazione();
        List<Biblioteca> expResult = new ArrayList<>();
        expResult.add(biblioteca);
        List<Biblioteca> result = instance.visualizzaRichieste();
        assertEquals(expResult, result);
    }

    /**
     * Test of modificaStatoBiblioteca method, of class ManagerRegistrazione.
     */
    @Test
    public void test5ModificaStatoBiblioteca() {
        System.out.println("modificaStatoBiblioteca");
        String idBiblioteca = biblioteca.getIsil();
        String change = "Accettata";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        boolean expResult = true;
        boolean result = instance.modificaStatoBiblioteca(idBiblioteca, change);
        assertEquals(expResult, result);
    }

    /**
     * Test of recuperaTelefonoPersona method, of class ManagerRegistrazione.
     */
    @Test
    public void test6RecuperaTelefonoPersona() {
        System.out.println("recuperaTelefonoPersona");
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Telefono expResult = telefonoPers;
        Telefono result = instance.recuperaTelefonoPersona(persona);
        assertEquals(expResult, result);
    }

    /**
     * Test of recuperaTelefonoBiblioteca method, of class ManagerRegistrazione.
     */
    @Test
    public void test7RecuperaTelefonoBiblioteca() {
        System.out.println("recuperaTelefonoBiblioteca");
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Telefono expResult = telefonoBiblio;
        Telefono result = instance.recuperaTelefonoBiblioteca(biblioteca);
        assertEquals(expResult, result);
        
        
    }
    
}
