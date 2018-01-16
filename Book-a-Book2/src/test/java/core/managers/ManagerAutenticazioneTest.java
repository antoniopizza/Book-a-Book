/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Account;
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Utente;
import javax.servlet.http.HttpSession;
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
public class ManagerAutenticazioneTest {
    
    private String admMail;
    private String admPsw;
    private String bibMail;
    private String bibPsw;
    private String persMail;
    private String persPsw;
    Admin admin;
    Bibliotecario bibliotecario;
    Persona persona;
    
    public ManagerAutenticazioneTest() {
        
        admMail = "luca@admin.it";
        admPsw = "admin";
        
        admin = new Admin("Luca", "Pangaro", new Account(admMail, admPsw, "path", Utente.TIPO_ADMIN));
        
        bibMail = "anita@bibliotecario.it";
        bibPsw = "bibliotecario";
        Biblioteca biblio = new Biblioteca("IT-123", "Biblioteca Comunale di Atripalda", "Attiva");
        bibliotecario = new Bibliotecario("Attiva", "Responsabile", biblio, "Anita", "Bianchi", new Account(bibMail, bibPsw, "path", Utente.TIPO_BIBLIOTECARIO));
        
        
        persMail = "anna@persona.it";
        persPsw = "persona";
        persona = new Persona("AS5021", new Indirizzo("via Antinori", "Fisciano", "54", "SA", "84084"), "Anna", "Califano", new Account(persMail, persPsw, "path", Utente.TIPO_PERSONA));
        
        
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
     * Test of login method, of class ManagerAutenticazione, for Admin.
     */
    @Test
    public void test1Login() {
        System.out.println("login");
        
        ManagerAutenticazione instance = new ManagerAutenticazione();
        Utente expResult = admin;
        Utente result = instance.login(admMail, admPsw);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of login method, of class ManagerAutenticazione, for Bibliotecario.
     */
    @Test
    public void test2Login() {
        System.out.println("login");
        
        ManagerAutenticazione instance = new ManagerAutenticazione();
        Utente expResult = bibliotecario;
        Utente result = instance.login(bibMail, bibPsw);
        assertEquals(expResult, result);
        
    }
    
    
    /**
     * Test of login method, of class ManagerAutenticazione, for Persona.
     */
    @Test
    public void test3Login() {
        System.out.println("login");
        
        ManagerAutenticazione instance = new ManagerAutenticazione();
        Utente expResult = persona;
        Utente result = instance.login(persMail, persPsw);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of logout method, of class ManagerAutenticazione.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        HttpSession session = null;
        ManagerAutenticazione instance = new ManagerAutenticazione();
        boolean expResult = false;
        boolean result = instance.logout(session);
        assertEquals(expResult, result);
        
    }
    
}
