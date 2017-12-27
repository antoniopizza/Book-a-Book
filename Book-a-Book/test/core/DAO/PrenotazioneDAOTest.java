/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Prenotazione;
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
    
    public PrenotazioneDAOTest() {
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
     * Test of doRetriveById method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        Object[] id = null;
        PrenotazioneDAO instance = new PrenotazioneDAO();
        Prenotazione expResult = null;
        Prenotazione result = instance.doRetriveById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRetriveAll method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        List<Prenotazione> expResult = null;
        List<Prenotazione> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doInsert method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        Prenotazione prenotazione = null;
        PrenotazioneDAO instance = new PrenotazioneDAO();
        int expResult = 0;
        int result = instance.doInsert(prenotazione);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doUpdate method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        Prenotazione prenotazione = null;
        PrenotazioneDAO instance = new PrenotazioneDAO();
        int expResult = 0;
        int result = instance.doUpdate(prenotazione);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
