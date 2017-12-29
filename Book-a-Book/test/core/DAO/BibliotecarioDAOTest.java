/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import static core.DAO.AccountDAOTest.account;
import static core.DAO.AccountDAOTest.con;
import static core.DAO.BibliotecaDAOTest.biblioteca;
import core.entities.Account;
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
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

/**
 *
 * @author stefanosolda
 */
public class BibliotecarioDAOTest {
    public static Connection con;
    public static Bibliotecario bibliotecario;
    public static Biblioteca biblioteca;
    
    public BibliotecarioDAOTest() {
        biblioteca = new Biblioteca("ITNA02", "Biblioteca di Marigliano", "Accettata", new Indirizzo("Via pozzuoli", "Marigliano", "18", "NA", "83057"), new Admin());
        bibliotecario = new Bibliotecario("Accettato", "Dipendente", biblioteca, 0, "Paolo", "Bilotto", new Account("paolobilotto@gmail.com", "ciaonepaolo", "iberfnjf", "BIbliotecario"));
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from bibliotecario where id = '"+ bibliotecario.getId() + ";");
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
     * Test of doRetriveById method, of class BibliotecarioDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        int id = bibliotecario.getId();
        BibliotecarioDAO instance = new BibliotecarioDAO();
        Bibliotecario expResult = bibliotecario;
        Bibliotecario result = instance.doRetriveById(id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of doRetriveByEmail method, of class BibliotecarioDAO.
     */
    @Test
    public void testDoRetriveByEmail() {
        System.out.println("doRetriveByEmail");
        String email = "paolobilotto@gmail.com";
        BibliotecarioDAO instance = new BibliotecarioDAO();
        Bibliotecario expResult = bibliotecario;
        Bibliotecario result = instance.doRetriveByEmail(email);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of doRetriveAll method, of class BibliotecarioDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        BibliotecarioDAO instance = new BibliotecarioDAO();
        List<Bibliotecario> expResult = new ArrayList<>();
        expResult.add(bibliotecario);
        List<Bibliotecario> result = instance.doRetriveAll();
        assertEquals(expResult, result);
    }

    /**
     * Test of doInsert method, of class BibliotecarioDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        BibliotecarioDAO instance = new BibliotecarioDAO();
        int expResult = 0;
        int result = instance.doInsert(bibliotecario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doUpdate method, of class BibliotecarioDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        BibliotecarioDAO instance = new BibliotecarioDAO();
        int expResult = 0;
        int result = instance.doUpdate(bibliotecario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
