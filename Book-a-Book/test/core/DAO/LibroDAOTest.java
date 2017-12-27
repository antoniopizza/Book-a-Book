/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Autore;
import core.entities.Libro;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
public class LibroDAOTest {
    
    private static Connection con;
    private static Libro book;
    
    public LibroDAOTest() {
        List<Autore> autori = new ArrayList<>();
        Autore a = new Autore("Alexandre Dumas");
        a.setId(1);
        autori.add(a);
        book = new Libro("978-8807901157", "Il conte di Montecristo", "Feltrinelli",new GregorianCalendar(2014,5,14),"il conte di montecristo", true, autori, "https://images-na.ssl-images-amazon.com/images/I/41Tgk3M47HL._SX319_BO1,204,203,200_.jpg");
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("truncate table Libro_Autore");
        PreparedStatement prst2 = con.prepareStatement("delete from Libro where isbn = '"+ book.getIsbn()+"'");
        prst.execute();       
        prst2.execute();
        con.commit();
        prst.close();
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
     * Test of doInsert method, of class LibroDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");        
        LibroDAO instance = new LibroDAO();
        int expResult = 0;
        int result = instance.doInsert(book);
        assertEquals(expResult, result);       
    }
    
    /**
     * Test of doRetriveById method, of class LibroDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");        
        LibroDAO instance = new LibroDAO();
        Libro expResult = book;
        Libro result = instance.doRetriveById(book.getIsbn());
        assertEquals(expResult, result);        
    }

    /**
     * Test of doRetriveAll method, of class LibroDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        LibroDAO instance = new LibroDAO();
        List<Libro> expResult = new ArrayList<>();
        expResult.add(book);
        List<Libro> result = instance.doRetriveAll();
        assertEquals("Le liste non sono uguali",expResult, result);
        assertEquals("Le dimensioni delle liste non sono uguali",expResult.size(), result.size());
    }

   

    /**
     * Test of doUpdate method, of class LibroDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        book.setDescrizione("Il conte ammazza tutti");
        LibroDAO instance = new LibroDAO();
        int expResult = 0;
        int result = instance.doUpdate(book);
        assertEquals(expResult, result);
        
        Libro retrivedLibro = instance.doRetriveById(book.getIsbn());
        assertEquals(book,retrivedLibro);
        
    }

    
    
}
