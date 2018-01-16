/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import static core.DAO.BibliotecaDAOTest.biblioteca;
import static core.DAO.BibliotecaDAOTest.con;
import static core.DAO.BibliotecaDAOTest.indirizzo;
import static core.DAO.BibliotecaDAOTest.indirizzoDAO;
import core.entities.Account;
import core.entities.Biblioteca;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Telefono;
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
public class TelefonoDAOTest {
    public static Connection con;
    public static Biblioteca biblio;
    public static Indirizzo indirizzo,indirizzo2;
    public static Persona pers;
    public static Account acc;
    public static Telefono telefono;
    public static BibliotecaDAO biblioDAO;
    public static IndirizzoDAO indDAO;
    public static PersonaDAO persDAO;
    public static AccountDAO accDAO;
    
    
    
    public TelefonoDAOTest() {                            
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException{
        indDAO = new IndirizzoDAO();
        persDAO = new PersonaDAO();
        accDAO = new AccountDAO();
        indirizzo2 = new Indirizzo("auuuuu", "refewi", "34567", "NA", "0987");
        acc = new Account("ciaone@tiscali.com", "Ciaone26", "edrtfyguhiu", "Utente");
        pers = new Persona("1234567", indirizzo2, "Stefano", "Rossi", acc);
        indDAO.doInsert(indirizzo2);
        accDAO.doInsert(acc);
        pers.setId(persDAO.doInsert(pers));
        
        telefono = new Telefono("+39", "3467914567", pers, null);
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException{
        PreparedStatement prst2 = con.prepareStatement("delete from Telefono where prefisso = '"+telefono.getPrefisso() + "' AND numero = '"+ telefono.getNumero() + "';");
        prst2.execute();
        persDAO.doDelete(pers);
        accDAO.doDelete(acc.getEmail());
        indDAO.doDelete(indirizzo2);
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
     * Test of doRetriveById method, of class TelefonoDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        String pref = telefono.getPrefisso();
        String numero = telefono.getNumero();
        TelefonoDAO instance = new TelefonoDAO();
        Telefono expResult = telefono;
        Telefono result = instance.doRetriveById(pref,numero);
        assertEquals(expResult, result);
    }

    /**
     * Test of doRetriveAll method, of class TelefonoDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        TelefonoDAO instance = new TelefonoDAO();
        List<Telefono> expResult = new ArrayList<>();
        expResult.add(telefono);
        List<Telefono> result = instance.doRetriveAll();
        assertEquals(expResult, result);
    }

    /**
     * Test of doInsert method, of class TelefonoDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        TelefonoDAO instance = new TelefonoDAO();
        int expResult = 0;
        int result = instance.doInsert(telefono);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class TelefonoDAO.
     */
    @Test
    public void testDoUpdate_Telefono_Telefono() {
        System.out.println("doUpdate");
        TelefonoDAO instance = new TelefonoDAO();
        int expResult = 0;
        int result = instance.doUpdate(telefono, telefono);
        assertEquals(expResult, result);
    }
}
