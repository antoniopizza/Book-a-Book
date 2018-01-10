/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Copia;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author manuel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CopiaDAOTest {
    
    private static Copia copia;
    private static LibroDAO libroDAO;
    private static PosizioneDAO posizioneDAO;
    private static BibliotecaDAO bibliotecaDAO;
    private static Connection con;
    private static Posizione pos;
    
    public CopiaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();        
        
        libroDAO = new LibroDAO();
        posizioneDAO = new PosizioneDAO();
        posizioneDAO.setBibliotecaDAO(new BibliotecaDAOStub());
        
        bibliotecaDAO = new BibliotecaDAOStub();
        
        copia = new Copia("abc123","NonPrenotata", "disponibile");                               
        copia.setLibro(libroDAO.doRetriveById("9788804492504"));
        pos = posizioneDAO.doRetriveById("Ripiano Basso A", "IT-321");
        copia.setPosizione(pos);
        
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
         String queryDelete = "DELETE FROM Copia WHERE id = ? AND isil = ? AND isbn = ? ";
        
        PreparedStatement prst1 = con.prepareStatement(queryDelete);
        prst1.setString(1, copia.getId());
        prst1.setString(2, copia.getPosizione().getBiblioteca().getIsil());
        prst1.setString(3, copia.getLibro().getIsbn());
        

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
        
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);
        posizioneDAO.setCopiaDAO(instance);
        
        Copia expResult = copia;        
        Copia result = instance.doRetriveById(copia.getId(),copia.getLibro().getIsbn(),copia.getPosizione().getBiblioteca().getIsil());
        assertEquals(expResult, result);        
    }

    /**
     * Test of doRetriveAll method, of class CopiaDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);   
        
        //dovrebbero esserci 5 copie nel db a questo punto
        int expResult = 6;
        List<Copia> result = instance.doRetriveAll();
        assertEquals(expResult, result.size());        
    }

    /**
     * Test of doInsert method, of class CopiaDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");       
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);
        int expResult = -1;
        int result = instance.doInsert(copia);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of doUpdate method, of class CopiaDAO.
     *
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        Copia entity = null;
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO);
        posizioneDAO.setCopiaDAO(instance);
        int expResult = 0;
        int result = instance.doUpdate(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of doRetriveByPosizione method, of class CopiaDAO.
     */
    @Test
    public void testDoRetriveByPosizione() {
        System.out.println("doRetriveByPosizione");       
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);
        posizioneDAO.setCopiaDAO(instance);
        //dovrebbero esserci 3 copie corrispondenti a questa posizione
        int expResult = 3;
        List<Copia> result = instance.doRetriveByPosizione(pos);
        
        assertEquals(expResult, result.size());
        
    }
    
    
    /**
     * Test of doDelete method, of class CopiaDAO.
     */
    @Test
    public void testzDoDelete() {
        System.out.println("doDelete");
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);
        posizioneDAO.setCopiaDAO(instance);
        int expResult = 0;
        int result = instance.doDelete(copia);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of doUpdatePosizione method, of class CopiaDAO.
     */
    @Test
    public void testDoUpdatePosizione() {
        System.out.println("doUpdatePosizione");
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);
        posizioneDAO.setCopiaDAO(instance);
        int expResult = 0;
        int result = instance.doUpdatePosizione(copia, "Ripiano Basso B");
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of doRetriveIsilByCopia, of class CopiaDAO
     */
    @Test
    public void testDoRetriveIsilByCopia() {
        System.out.println("doRetriveIsilByCopia");
        CopiaDAO instance = new CopiaDAO(libroDAO, posizioneDAO, bibliotecaDAO);
        
        String isbn = "9788804492504";
        int expResult = 2;
        List<Biblioteca> result = instance.doRetriveIsilByCopia(isbn);
        
        assertEquals(expResult, result.size());
    }
    
}
