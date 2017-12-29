/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Persona;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stefanosolda
 */
public class ManagerRegistrazioneTest {
    
    public ManagerRegistrazioneTest() {
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
     * Test of registra method, of class ManagerRegistrazione.
     */
    @Test
    public void testRegistra_11args() {
        System.out.println("registra");
        String nome = "";
        String cognome = "";
        String email = "";
        String numeroDocumento = "";
        String via = "";
        String citta = "";
        String numeroCivico = "";
        String password = "";
        String pathFoto = "";
        String provincia = "";
        String CAP = "";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Persona expResult = null;
        Persona result = instance.registra(nome, cognome, email, numeroDocumento, via, citta, numeroCivico, password, pathFoto, provincia, CAP);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registra method, of class ManagerRegistrazione.
     */
    @Test
    public void testRegistra_12args() {
        System.out.println("registra");
        String isil = "";
        String nome = "";
        String via = "";
        String citta = "";
        String numeroCivico = "";
        String provincia = "";
        String CAP = "";
        String email = "";
        String password = "";
        String pathFoto = "";
        String tipo = "";
        String cognome = "";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Biblioteca expResult = null;
        Biblioteca result = instance.registra(isil, nome, via, citta, numeroCivico, provincia, CAP, email, password, pathFoto, tipo, cognome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registraDipendente method, of class ManagerRegistrazione.
     */
    @Test
    public void testRegistraDipendente() {
        System.out.println("registraDipendente");
        String isil = "";
        String nome = "";
        String cognome = "";
        String email = "";
        String password = "";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Bibliotecario expResult = null;
        Bibliotecario result = instance.registraDipendente(isil, nome, cognome, email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visualizzaRichieste method, of class ManagerRegistrazione.
     */
    @Test
    public void testVisualizzaRichieste() {
        System.out.println("visualizzaRichieste");
        ManagerRegistrazione instance = new ManagerRegistrazione();
        List<Biblioteca> expResult = null;
        List<Biblioteca> result = instance.visualizzaRichieste();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificaStatoBiblioteca method, of class ManagerRegistrazione.
     */
    @Test
    public void testModificaStatoBiblioteca() {
        System.out.println("modificaStatoBiblioteca");
        String idBiblioteca = "";
        String change = "";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        boolean expResult = false;
        boolean result = instance.modificaStatoBiblioteca(idBiblioteca, change);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registra method, of class ManagerRegistrazione.
     */
    @Test
    public void testRegistra_13args() {
        System.out.println("registra");
        String isil = "";
        String nomeBiblioteca = "";
        String nomeBibliotecario = "";
        String via = "";
        String citta = "";
        String numeroCivico = "";
        String provincia = "";
        String CAP = "";
        String email = "";
        String password = "";
        String pathFoto = "";
        String tipo = "";
        String cognome = "";
        ManagerRegistrazione instance = new ManagerRegistrazione();
        Biblioteca expResult = null;
        Biblioteca result = instance.registra(isil, nomeBiblioteca, nomeBibliotecario, via, citta, numeroCivico, provincia, CAP, email, password, pathFoto, tipo, cognome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
