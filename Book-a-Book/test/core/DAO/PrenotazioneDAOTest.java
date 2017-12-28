/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Libro;
import core.entities.Persona;
import core.entities.Prenotazione;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Mery
 */
public class PrenotazioneDAOTest {
    
    private static Connection con;
    private static Prenotazione prenotazione;
            
    public PrenotazioneDAOTest() {
        Libro book = new Libro();
        Biblioteca bib = new Biblioteca();
        Persona person = new Persona();
        prenotazione = new Prenotazione(new GregorianCalendar(2017,20,12),new GregorianCalendar(2018,28,3),new GregorianCalendar(2017,28,12),person,"Da ritirare",bib,book);
        
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("DELETE FROM prenotazione WHERE id = '"+ prenotazione.getId()+"'");
        prst.execute();
        con.commit();
        prst.close();
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
     * Test of doRetriveById method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        Prenotazione expResult = prenotazione;
        Prenotazione result = instance.doRetriveById(prenotazione.getId());
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRetriveAll method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        List<Prenotazione> expResult = new ArrayList<>();
        expResult.add(prenotazione);
        List<Prenotazione> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());
        fail("The test case is a prototype.");
    }

    /**
     * Test of doInsert method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        int expResult = prenotazione.getId();
        int result = instance.doInsert(prenotazione);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of doUpdate method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        int expResult = prenotazione.getId();
        int result = instance.doUpdate(prenotazione);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
