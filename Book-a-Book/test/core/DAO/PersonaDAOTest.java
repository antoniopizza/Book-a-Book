/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import static core.DAO.AccountDAOTest.account;
import static core.DAO.AccountDAOTest.con;
import static core.DAO.IndirizzoDAOTest.con;
import static core.DAO.IndirizzoDAOTest.indirizzo;
import core.entities.Account;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class PersonaDAOTest {
    
    public static Connection con;
    public static Persona persona;
    public static IndirizzoDAO indirizzoDAO;
    public static AccountDAO accountDAO;
    public static Indirizzo indirizzo;
    public static Account account;
    public static int id;
    
    public PersonaDAOTest() {
       // indirizzoDAO = new IndirizzoDAO();
       // indirizzo = new Indirizzo("Via vincenzo vitale", "Atripalda", "117", "AV", "83042");
       // indirizzoDAO.doInsert(indirizzo);
       // account = new Account("stefasolda@gmail.com", "ciaone", "isfhwfn", "Utente");
       // accountDAO = new AccountDAO();
       // accountDAO.doInsert(account);
        persona = new Persona("123456", new Indirizzo("Via vincenzo vitale", "Atripalda", "117", "AV", "83042"), "Stefano", "Soldà", new Account("stefasolda@gmail.com", "ciaone", "ciaone", "Utente"));
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        //System.out.println("id dentro tear down=" + id);
        PreparedStatement prst2 = con.prepareStatement("delete from Persona where id = '"+ id + "';");
       // PreparedStatement prst4 = con.prepareStatement("delete from Account where email = '"+ account.getEmail() + "';");
       // PreparedStatement prst3 = con.prepareStatement("delete from Indirizzo where via = '"+ indirizzo.getVia() + "' AND citta = '" + indirizzo.getCitta() + "' AND civico = '" + indirizzo.getCivico() +"';");
        prst2.execute();
        //prst3.execute();
        //prst4.execute();
        con.commit();
        prst2.close(); 
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cleared"); 
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doRetriveById method, of class PersonaDAO.
     */
    @Test
    public void testDoRetriveById() {
        //System.out.println("doRetriveById");
        //System.out.println("ID persona nel test  = "+ id);
        PersonaDAO instance = new PersonaDAO();
        Persona expResult = persona;
        Persona result = instance.doRetriveById(id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of doRetriveByEmail method, of class PersonaDAO.
     */
    @Test
    public void testDoRetriveByEmail() {
        System.out.println("doRetriveByEmail");
        String email = "stefasolda@gmail.com";
        PersonaDAO instance = new PersonaDAO();
        Persona expResult = persona;
        Persona result = instance.doRetriveByEmail(email);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of doRetriveAll method, of class PersonaDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        PersonaDAO instance = new PersonaDAO();
        List<Persona> expResult = new ArrayList<>();
        expResult.add(persona);
        List<Persona> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        assertEquals("Le dimensioni delle liste non sono uguali",expResult.size(), result.size());
    }

    /**
     * Test of doInsert method, of class PersonaDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        PersonaDAO instance = new PersonaDAO();
        int expResult = -1;
        int result = instance.doInsert(persona);
        //System.out.println("Risultato doinsert:" + result);
        persona.setId(result);
        id = result;
       // System.out.println("id= " + id);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of doUpdate method, of class PersonaDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        PersonaDAO instance = new PersonaDAO();
        int expResult = 0;
        int result = instance.doUpdate(persona);
        assertEquals(expResult, result);
    
    }
    
}
