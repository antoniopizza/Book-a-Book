/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manuel
 */
public class CopiaDAOTest {
    
    private static Copia copia;
    private static LibroDAO libroDAO;
    private static PosizioneDAO posizioneDAO;
    private static Connection con;
    
    public CopiaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        copia = new Copia("abc123","NonPrenotata", "disponibile");
        libroDAO = new LibroDAO();
        posizioneDAO = new PosizioneDAO();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
         String queryDelete = "DELETE FROM Copia WHERE id = ? AND isil = ? AND isbn = ? ";
        
        PreparedStatement prst1 = con.prepareStatement(queryDelete);
        prst1.setString(1, copia.getId());
        prst1.setString(2, copia.getPosizione().getEtichetta());
        

        prst1.execute();
        con.commit();
        prst1.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cleared.");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doRetriveById method, of class CopiaDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");        
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO);
        Copia expResult = copia;
        copia.setLibro(libroDAO.doRetriveById("978-8804630739"));
        copia.setPosizione(posizioneDAO.doRetriveById("AO1","SPBBT01"));
        Copia result = instance.doRetriveById(copia.getId());
        assertEquals(expResult, result);        
    }

    /**
     * Test of doRetriveAll method, of class CopiaDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        CopiaDAO instance = null;
        List<Copia> expResult = null;
        List<Copia> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doInsert method, of class CopiaDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        copia.setLibro(libroDAO.doRetriveById("978-8804630739"));
        copia.setPosizione(posizioneDAO.doRetriveById("AO1","SPBBT01"));
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO);
        int expResult = -1;
        int result = instance.doInsert(copia);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of doUpdate method, of class CopiaDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        Copia entity = null;
        CopiaDAO instance = null;
        int expResult = 0;
        int result = instance.doUpdate(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRetriveByPosizione method, of class CopiaDAO.
     */
    @Test
    public void testDoRetriveByPosizione() {
        System.out.println("doRetriveByPosizione");
        Posizione posizione = null;
        CopiaDAO instance = null;
        List<Copia> expResult = null;
        List<Copia> result = instance.doRetriveByPosizione(posizione);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
