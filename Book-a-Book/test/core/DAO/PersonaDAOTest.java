/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

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
    
    
    public PersonaDAOTest() {
        persona = new Persona("123456", new Indirizzo("Via vincenzo vitale", "Atripalda", "117", "Avellino", "83042"), 0, "Stefano", "Soldà", new Account("stefasolda@gmail.com", "ciaone", "isfhwfn", "Utente"));
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from Persona where id = '"+ 0 + ";");
        prst2.execute();
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
        System.out.println("doRetriveById");
        int id = persona.getId();
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
        int expResult = 0;
        int result = instance.doInsert(persona);
        assertEquals(expResult, result);
        
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
