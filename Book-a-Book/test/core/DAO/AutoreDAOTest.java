/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Autore;
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
public class AutoreDAOTest {

    private static Connection con;
    private static Autore autore;

    public AutoreDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        autore = new Autore("Alexandre Dumas");
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        try {
            PreparedStatement prst = con.prepareStatement("DELETE FROM Autore WHERE id =?");
            prst.setInt(1, autore.getId());
            prst.execute();
            con.commit();
            prst.close();
        } catch (SQLException e) {
            con.rollback();
        } finally{
            DriverManagerConnectionPool.releaseConnection(con);
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of doRetriveById method, of class AutoreDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");       
        AutoreDAO instance = new AutoreDAO();
        Autore expResult = autore;
        Autore result = instance.doRetriveById(autore.getId());
        assertEquals("L'autore selezionato non è lo stesso di quello atteso",expResult, result);       
    }

    /**
     * Test of doRetriveAll method, of class AutoreDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        AutoreDAO instance = new AutoreDAO();     
        List<Autore> result = instance.doRetriveAll();
        
        //Attualmente il db ha 2 tuple
        assertEquals("C'è qualche autore in meno o in più",2, result.size());       
    }

    /**
     * Test of doRetriveByLibro method, of class AutoreDAO.
     */
    @Test
    public void testDoRetriveByLibro() {
        System.out.println("doRetriveByLibro");
        String isbn = "978-8804492528";
        AutoreDAO instance = new AutoreDAO();        
        List<Autore> result = instance.doRetriveByLibro(isbn);
        assertEquals(1, result.size());        
    }

    /**
     * Test of doRetriveByNome method, of class AutoreDAO.
     */
    @Test
    public void testDoRetriveByNome() {
        System.out.println("doRetriveByNome");        
        AutoreDAO instance = new AutoreDAO();        
        Autore result = instance.doRetriveByNome(autore.getNome());
        assertEquals("L'autore non è lo stesso",autore, result);        
    }

    /**
     * Test of doInsert method, of class AutoreDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");        
        AutoreDAO instance = new AutoreDAO();
        int notExpected = -1;
        int result = instance.doInsert(autore);
        assertNotEquals("Inserimento fallito",notExpected, result);
        if(result != -1){
            autore.setId(result);
        }
    }

    /**
     * Test of doUpdate method, of class AutoreDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");      
        autore.setNome("Niccolò Machiavelli");
        AutoreDAO instance = new AutoreDAO();
        int notExpected = -1;
        int result = instance.doUpdate(autore);
        assertNotEquals(notExpected, result);
        
        Autore retrived = instance.doRetriveById(autore.getId());
        assertEquals(autore,retrived);
        
    }

}
