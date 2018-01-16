/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import java.sql.Connection;
import java.sql.SQLException;
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
public class DriverManagerConnectionPoolTest {
    
    public DriverManagerConnectionPoolTest() {
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
     * Test of getConnection method, of class DriverManagerConnectionPool.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        String dbUrl = "jdbc:mysql://localhost:3306/bookabookDB";
        Connection result = DriverManagerConnectionPool.getConnection();
        assertEquals(dbUrl, result.getMetaData().getURL());
        
    }

    /**
     * Test of releaseConnection method, of class DriverManagerConnectionPool.
     */
    @Test
    public void testReleaseConnection() throws SQLException {
        System.out.println("releaseConnection");
        Connection connection = DriverManagerConnectionPool.getConnection();
        DriverManagerConnectionPool.releaseConnection(connection);       
        //assertTrue(DriverManagerConnectionPool.);
        
    }
    
}
