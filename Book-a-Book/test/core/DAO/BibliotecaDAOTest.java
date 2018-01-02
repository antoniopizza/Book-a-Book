/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import static core.DAO.AccountDAOTest.account;
import static core.DAO.AccountDAOTest.con;
import core.entities.Admin;
import core.entities.Biblioteca;
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
public class BibliotecaDAOTest {
    
    public static Connection con;
    public static Biblioteca biblioteca;
    
    public BibliotecaDAOTest() {
        biblioteca = new Biblioteca("ITNA02", "Biblioteca di Marigliano", "Accettata", new Indirizzo("Via appia", "Pratola Serra", "19", "AV", "83030"), null);
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
       con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from Biblioteca where isil = '"+ biblioteca.getIsil() + "';");
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
     * Test of doRetriveById method, of class BibliotecaDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        String id = biblioteca.getIsil();
        BibliotecaDAO instance = new BibliotecaDAO();
        Biblioteca expResult = biblioteca;
        Biblioteca result = instance.doRetriveById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of doRetriveAll method, of class BibliotecaDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        BibliotecaDAO instance = new BibliotecaDAO();
        List<Biblioteca> expResult = new ArrayList<>();
        expResult.add(biblioteca);
        List<Biblioteca> result = instance.doRetriveAll();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of doInsert method, of class BibliotecaDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        BibliotecaDAO instance = new BibliotecaDAO();
        int expResult = 0;
        int result = instance.doInsert(biblioteca);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class BibliotecaDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        BibliotecaDAO instance = new BibliotecaDAO();
        int expResult = 0;
        int result = instance.doUpdate(biblioteca);
        assertEquals(expResult, result);
    }
   
   // /**
    // * Test of doDelete method, of class BibliotecaDAO.
    // */
    //@Test
    //public void testDoDelete() {
      //  System.out.println("doDelete");
        //String isil = biblioteca.getIsil();
        //BibliotecaDAO instance = new BibliotecaDAO();
       // int expResult = 0;
       // int result = instance.doDelete(isil);
       // assertEquals(expResult, result);
   // }
    
}
