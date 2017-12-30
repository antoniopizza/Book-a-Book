/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import static core.DAO.PersonaDAOTest.con;
import core.entities.Account;
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

/**
 *
 * @author stefanosolda
 */
public class AccountDAOTest {
    public static Account account;
    public static Connection con;
    
    public AccountDAOTest() {
        account = new Account("stefasolda@gmail.com", "ciaone", "eirbfreifnwe", "Utente");
        
        
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from Account where email = '"+ account.getEmail() + ";");
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
     * Test of doRetriveById method, of class AccountDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        String id = account.getEmail();
        AccountDAO instance = new AccountDAO();
        Account expResult = account;
        Account result = instance.doRetriveById(id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of doRetriveAll method, of class AccountDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = new ArrayList<>();
        expResult.add(account);
        List<Account> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doInsert method, of class AccountDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.doInsert(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doUpdate method, of class AccountDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.doUpdate(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doUpdateEmail method, of class AccountDAO.
     */
    @Test
    public void testDoUpdateEmail() {
        System.out.println("doUpdateEmail");
        String vecchiaMail = "stefasolda@gmail.com";
        String nuovaMail = "stefano-solda@hotmail.it";
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.doUpdateEmail(vecchiaMail, nuovaMail);
        assertEquals(expResult, result);
    }
   
    /**
     * Test of doDelete method, of class AccountDAO.
     */
    @Test
    public void testDoDelete() {
        System.out.println("doDelete");
        String email = account.getEmail();
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.doDelete(email);
        assertEquals(expResult, result);
    }
    
}
