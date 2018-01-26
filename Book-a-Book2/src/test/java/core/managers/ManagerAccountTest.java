/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.AccountDAO;
import core.DAO.AdminDAO;
import core.DAO.BibliotecaDAO;
import core.DAO.BibliotecarioDAO;
import core.DAO.IndirizzoDAO;
import core.DAO.PersonaDAO;
import core.DAO.TelefonoDAO;
import core.entities.Account;
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Telefono;
import static core.managers.ManagerRegistrazioneTest.biblioteca;
import static core.managers.ManagerRegistrazioneTest.bibliotecario;
import static core.managers.ManagerRegistrazioneTest.dipendente;
import static core.managers.ManagerRegistrazioneTest.persona;
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
public class ManagerAccountTest {
    public static Account account,accountDip,accountBiblio,accountBiblioMod,accountAdmin;
    public static AccountDAO accountDAO;
    public static BibliotecaDAO bibliotecaDAO;
    public static Biblioteca biblioteca,bibliotecaMod;
    public static BibliotecarioDAO bibliotecarioDAO;
    public static Bibliotecario bibliotecario,dipendente,bibliotecarioMod;
    public static TelefonoDAO telefonoDAO;
    public static Telefono telefonoBiblio;
    public static Telefono telefonoPers;
    public static PersonaDAO personaDAO;
    public static Persona persona,personaMod;
    public static IndirizzoDAO indirizzoDAO;
    public static Indirizzo indirizzoPers, indirizzoBiblio;
    public static AdminDAO adminDAO;
    public static Admin admin;
    
   
    public ManagerAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    telefonoDAO = new TelefonoDAO();
        indirizzoDAO =  new IndirizzoDAO();
        indirizzoPers = new Indirizzo("appia", "Atripalda", "117", "NA", "83030");
        indirizzoBiblio = new Indirizzo("via", "citta", "civico", "PR", "83042");
        accountDAO = new AccountDAO();
        accountAdmin = new Account("admin@admin.it", "Ciaone34", "ehirbfeirfn", "Admin");
        account = new Account("chicco@chicchettino.com", "Ciaone96", "34567", "23456765");
        accountDip = new Account("chicchettino@chicchettino.com", "Ciaone96", "34567", "23456765");
        accountBiblio = new Account("chicchino@chicchettino.com", "Ciaone96", "34567", "23456765");
        accountBiblioMod = new Account("auauauaua@gmail.com", "Ciaone96", "34567", "23456765");
        personaDAO = new PersonaDAO();
        admin = new Admin("admin", "Admin96", accountAdmin,1);
        persona = new Persona("123456", indirizzoPers, "Stefano", "rossi", account);
        personaMod = new Persona("123456", indirizzoPers, "CiaoneBello", "rossi", account);
        bibliotecaDAO = new BibliotecaDAO();
        biblioteca = new Biblioteca("ITAN34", "Cicciono", "In Sospeso", indirizzoBiblio, null);
        bibliotecaMod = new Biblioteca("ITAN34", "Ciccionina", "In Sospeso", indirizzoBiblio, null);
        bibliotecarioDAO = new BibliotecarioDAO();
        dipendente = new Bibliotecario("Accettato", "Dipendente", biblioteca, "Mario", "Rossi",accountDip);
        telefonoBiblio = new Telefono("+39", "3463202474", null, biblioteca);
        telefonoPers = new Telefono("+39","3456789091" , persona, null);     
        bibliotecario = new Bibliotecario("In Sospeso", "Responsabile", bibliotecaMod, "Stefano", "Malato", accountBiblio);
        bibliotecarioMod = new Bibliotecario("In Sospeso", "Responsabile", bibliotecaMod, "Stefano", "Malato", accountBiblioMod);
        indirizzoDAO.doInsert(indirizzoPers);
        indirizzoDAO.doInsert(indirizzoBiblio);
        accountDAO.doInsert(account);
        accountDAO.doInsert(accountDip);
        accountDAO.doInsert(accountBiblio);
        persona.setId(personaDAO.doInsert(persona));    
        bibliotecaDAO.doInsert(biblioteca);
        bibliotecarioDAO.doInsert(bibliotecario);
        bibliotecarioDAO.doInsert(dipendente);
        telefonoDAO.doInsert(telefonoPers);
        telefonoDAO.doInsert(telefonoBiblio);  
        
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
     * Test of modificaPassword method, of class ManagerAccount.
     */
    @Test
    public void test5ModificaPassword() {
        System.out.println("modificaPassword");
        String email = persona.getAccount().getEmail();
        String newPsw = "auuuuuuu95";
        ManagerAccount instance = new ManagerAccount();
        boolean expResult = true;
        boolean result = instance.modificaPassword(email, newPsw);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificaDatiPersonali method, of class ManagerAccount.
     */
    @Test
    public void test4ModificaDatiPersonaliBibliotecario() {
        System.out.println("modificaDatiPersonali");
        String vecchiaMail = bibliotecario.getAccount().getEmail();
        String email = bibliotecario.getAccount().getEmail();
        String path_foto = bibliotecario.getAccount().getPathFoto();
        ManagerAccount instance = new ManagerAccount();
        Bibliotecario expResult = bibliotecarioMod;
        Bibliotecario result = instance.modificaDatiPersonali(vecchiaMail, email,path_foto);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificaDatiPersonali method, of class ManagerAccount.
     */
    @Test
    public void test2ModificaDatiPersonaliPersona() {
        System.out.println("modificaDatiPersonali");
        String vecchiaMail = persona.getAccount().getEmail();
        String email = persona.getAccount().getEmail();
        String nome = "CiaoneBello";
        String cognome = persona.getCognome();
        String numDocumento = persona.getNumDocumento();
        String provincia = persona.getIndirizzo().getProvincia();
        String cap = persona.getIndirizzo().getCap();
        String via = persona.getIndirizzo().getVia();
        String numeroCivico = persona.getIndirizzo().getCivico();
        String citta = persona.getIndirizzo().getCitta();
        String recapitoTelefonico = telefonoPers.getNumero();
        String path_foto = persona.getAccount().getPathFoto();
        telefonoPers.setPersona(personaMod);
        ManagerAccount instance = new ManagerAccount();
        Persona expResult = personaMod;
        Persona result = instance.modificaDatiPersonali(vecchiaMail, email, nome, cognome, numDocumento, provincia, cap, via, numeroCivico, citta, recapitoTelefonico, path_foto);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of modificaDatiPersonali method, of class ManagerAccount.
     */
    @Test
    public void test3ModificaDatiPersonaliAdmin() {
        System.out.println("modificaDatiPersonali");
        //System.out.println(""+admin.toString());
        String vecchiaMail = admin.getAccount().getEmail();
        //System.out.println(""+admin.getAccount().toString());
        String email = admin.getAccount().getEmail();
        ManagerAccount instance = new ManagerAccount();
        Admin expResult = admin;
        Admin result = instance.modificaDatiPersonali(vecchiaMail, email);
        //System.out.println(""+admin);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of modificaDatiBiblioteca method, of class ManagerAccount.
     */
    @Test
    public void test1ModificaDatiBiblioteca() {
        System.out.println("modificaDatiBiblioteca");
        String isil = biblioteca.getIsil();
        String nome = "Ciccionina";
        String via = biblioteca.getIndirizzo().getVia();
        String citta = biblioteca.getIndirizzo().getCitta();
        String numeroCivico = biblioteca.getIndirizzo().getCivico();
        String numeroTelefono = telefonoBiblio.getNumero();
        String provincia = biblioteca.getIndirizzo().getProvincia();
        String cap = biblioteca.getIndirizzo().getCap();
        telefonoBiblio.setBiblioteca(bibliotecaMod);
        ManagerAccount instance = new ManagerAccount();
        Biblioteca expResult = bibliotecaMod;
        bibliotecario.setBiblioteca(bibliotecaMod);
        bibliotecarioMod.setBiblioteca(bibliotecaMod);
        Biblioteca result = instance.modificaDatiBiblioteca(isil, nome, via, citta, numeroCivico, numeroTelefono, provincia, cap); 
        assertEquals(expResult, result);
    }

    /**
     * Test of recuperaPassword method, of class ManagerAccount.
     */
    @Test
    public void test6RecuperaPassword() {
        System.out.println("recuperaPassword");
        String email = personaMod.getAccount().getEmail();
        String nuovaPassword = "Coclote95";
        ManagerAccount instance = new ManagerAccount();
        boolean expResult = true;
        boolean result = instance.recuperaPassword(email, nuovaPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of richiestaRimozioneAccount method, of class ManagerAccount.
     */
    @Test
    public void test7RichiestaRimozioneAccount() {
        System.out.println("richiestaRimozioneAccount");
        String isil = bibliotecaMod.getIsil();
        ManagerAccount instance = new ManagerAccount();
        boolean expResult = true;
        boolean result = instance.richiestaRimozioneAccount(isil);
        assertEquals(expResult, result);
    }

    /**
     * Test of rimozioneAccountUtente method, of class ManagerAccount.
     */
    @Test
    public void test8RimozioneAccountUtente() {
        System.out.println("rimozioneAccountUtente");
        String email = personaMod.getAccount().getEmail();
        ManagerAccount instance = new ManagerAccount();
        boolean expResult = true;
        boolean result = instance.rimozioneAccountUtente(email);
        assertEquals(expResult, result);
    }
    
}
