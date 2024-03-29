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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author stefanosolda
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BibliotecarioDAOTest {
    public static Connection con;
    public static Bibliotecario bibliotecario;
    public static Biblioteca biblioteca;
    public static int id;
    public static BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
    
    public BibliotecarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        biblioteca = new Biblioteca("ITNA02", "Biblioteca di Marigliano", "Accettata", new Indirizzo("Via vincenzo vitale", "Atripalda", "117", "AV", "83042"), null);
        bibliotecaDAO.doInsert(biblioteca);
        bibliotecario = new Bibliotecario("Accettato", "Dipendente", biblioteca, "Paolo", "Bilotto", new Account("stefasolda@gmail.com", "ciaone", "ciaone", "Utente"));
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from Bibliotecario where id = '"+ id + "';");
        //bibliotecaDAO.doDelete("ITNA02");
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
        //System.out.println(""+id);
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
        String email = "stefasolda@gmail.com";
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
        int expResult = -1;
        int result = instance.doInsert(bibliotecario);
        //System.out.println("Result:"+result);
        bibliotecario.setId(result);
        id = result;
        assertNotEquals(expResult, result);
        
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
        
    }
    
       /**
     * Test of doDelete method, of class BibliotecarioDAO.
     */
    @Test
    public void testDoDelete() {
        System.out.println("doDelete");
        String isil = bibliotecario.getBiblioteca().getIsil();
        BibliotecarioDAO instance = new BibliotecarioDAO();
        int expResult = 0;
        int result = instance.doDelete(isil);
        assertEquals(expResult, result);
    }
    
}

