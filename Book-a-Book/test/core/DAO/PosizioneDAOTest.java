/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
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
 * @author kliffom
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PosizioneDAOTest {
    
    private static Connection con;
    private static Posizione posizione;
    
    public PosizioneDAOTest() {
        Biblioteca biblioteca = new BibliotecaDAOStub().doRetriveById("12345");
        
        List<Copia> copie = new ArrayList<Copia>();
        Copia copia = new Copia("abc", "prenotabile", "si");
        copie.add(copia);
        
        posizione = new Posizione("et1", biblioteca, copie);
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException{
        /*
        PreparedStatement prst1 = con.prepareStatement("DELETE FROM Posizione WHERE etichetta = " + posizione.getEtichetta() +
                " AND isbn = " + posizione.getLibro().getIsbn() + " AND isil = " + posizione.getBiblioteca().getIsil());
        */
        String queryDelete = "DELETE FROM Posizione WHERE etichetta = ? AND isil = ?";
        
        PreparedStatement prst1 = con.prepareStatement(queryDelete);
        prst1.setString(1, posizione.getEtichetta());
        prst1.setString(2, posizione.getBiblioteca().getIsil());

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
     * Test of doRetriveById method, of class PosizioneDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        PosizioneDAO instance = new PosizioneDAO();
        instance.setBibliotecaDAO(new BibliotecaDAOStub());
        instance.setCopiaDAO(new CopiaDAOStub(new LibroDAO(), instance));
        Posizione expResult = posizione;
        Posizione result = instance.doRetriveById(posizione.getEtichetta(), posizione.getBiblioteca().getIsil());
        assertEquals(expResult, result); 
    }

    /**
     * Test of doRetriveAll method, of class PosizioneDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        PosizioneDAO instance = new PosizioneDAO();
        instance.setBibliotecaDAO(new BibliotecaDAOStub());
        instance.setCopiaDAO(new CopiaDAOStub(new LibroDAO(), instance));
        List<Posizione> expResult = new ArrayList<>();
        expResult.add(posizione);
        List<Posizione> result = instance.doRetriveAll();
        assertEquals("Le liste non sono uguali", expResult, result);
        assertEquals("Le dimensioni delle liste non sono uguali", expResult.size(), result.size());
    }

    /**
     * Test of doRetriveAllByIsilIsbn method, of class PosizioneDAO.
     */
    @Test
    public void testDoRetriveAllByIsil() {
        System.out.println("doRetriveAllByIsilIsbn");
        String isil = posizione.getBiblioteca().getIsil();
        PosizioneDAO instance = new PosizioneDAO();     
        instance.setBibliotecaDAO(new BibliotecaDAOStub());
         instance.setCopiaDAO(new CopiaDAOStub(new LibroDAO(), instance));
        List<Posizione> expResult = new ArrayList<>();
        expResult.add(posizione);
        List<Posizione> result = instance.doRetriveAllByIsil(isil);
        assertEquals(expResult, result);
        assertEquals("Le liste non sono uguali", expResult, result);
        assertEquals("Le dimensioni delle liste non sono uguali", expResult.size(), result.size());
    }

    /**
     * Test of doInsert method, of class PosizioneDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        PosizioneDAO instance = new PosizioneDAO();
        instance.setBibliotecaDAO(new BibliotecaDAOStub());
        instance.setCopiaDAO(new CopiaDAOStub(new LibroDAO(), instance)); 
        int expResult = 0;
        int result = instance.doInsert(posizione);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class PosizioneDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        PosizioneDAO instance = new PosizioneDAO();
        instance.setBibliotecaDAO(new BibliotecaDAOStub());
        instance.setCopiaDAO(new CopiaDAOStub(new LibroDAO(), instance));
        int expResult = 0;
        int result = instance.doUpdate(posizione);
        assertEquals(expResult, result); //Check if doUpdate is done
        
        Posizione retrivedPosizione = instance.doRetriveById(posizione.getEtichetta(), posizione.getBiblioteca().getIsil());
        assertEquals(posizione, retrivedPosizione);
    }
    
}
