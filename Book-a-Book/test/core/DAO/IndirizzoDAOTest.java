/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import static core.DAO.AccountDAOTest.account;
import static core.DAO.PersonaDAOTest.con;
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
public class IndirizzoDAOTest {
    
    
    public static Connection con;
    public static Indirizzo indirizzo;
    public static Indirizzo nuovoIndirizzo;
    
    public IndirizzoDAOTest() {
        indirizzo = new Indirizzo("Via vincenzo vitale", "Atripalda", "117", "AV", "83042");
        nuovoIndirizzo = new Indirizzo("Via appia", "Pratola Serra", "19", "AV", "83030");
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from Indirizzo where via = '"+ indirizzo.getVia() + "' AND citta = '" + indirizzo.getCitta() + "' AND civico = '" + indirizzo.getCivico() +"';");
        PreparedStatement prst = con.prepareStatement("delete from Indirizzo where via = '"+ nuovoIndirizzo.getVia() + "' AND citta = '" + nuovoIndirizzo.getCitta() + "' AND civico = '" + nuovoIndirizzo.getCivico() +"';");
        prst2.execute();
        prst.execute();
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
     * Test of doRetriveById method, of class IndirizzoDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        String via = indirizzo.getVia();
        String citta = indirizzo.getCitta();
        String civico = indirizzo.getCivico();
        IndirizzoDAO instance = new IndirizzoDAO();
        Indirizzo expResult = indirizzo;
        Indirizzo result = instance.doRetriveById(via,citta,civico);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of doRetriveAll method, of class IndirizzoDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        IndirizzoDAO instance = new IndirizzoDAO();
        List<Indirizzo> expResult = new ArrayList<>();
        expResult.add(indirizzo);
        List<Indirizzo> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of doInsert method, of class IndirizzoDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        IndirizzoDAO instance = new IndirizzoDAO();
        int expResult = 0;
        int result = instance.doInsert(indirizzo);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of doUpdateIndirizzo method, of class IndirizzoDAO.
     */
    @Test
    public void testDoUpdateIndirizzo() {
        System.out.println("doUpdateIndirizzo");
        Indirizzo vecchioIndirizzo = indirizzo;
        IndirizzoDAO instance = new IndirizzoDAO();
        int expResult = 0;
        int result = instance.doUpdateIndirizzo(vecchioIndirizzo, nuovoIndirizzo);
        assertEquals(expResult, result);
        
    }
    
     /**
     * Test of doDelete method, of class AccountDAO.
     */
    @Test
   public void testDoDelete() {
       System.out.println("doDelete");
       IndirizzoDAO instance = new IndirizzoDAO();
       int expResult = 0;
       int result = instance.doDelete(indirizzo);
       assertEquals(expResult, result);
    }
    
}
