/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Copia;
import core.entities.Indirizzo;
import core.entities.Libro;
import core.entities.Persona;
import core.entities.Posizione;
import core.entities.Prenotazione;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * @author Mery
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrenotazioneDAOTest {
    
    private static Connection con;
    private static Prenotazione prenotazione;
    public static int i;
            
    public PrenotazioneDAOTest() {
        Calendar datalibro = new GregorianCalendar();
        datalibro.setTimeInMillis(new Date(2017,12,01).getTime());
        Libro libro = new Libro("567890","titolo","editore",datalibro,"qwerty","path");
        Indirizzo ind = new Indirizzo("via boh","citta boh","12","BO","09876");
        Biblioteca bib = new Biblioteca("1234567890","biblioteche","Accettato",ind,null);
        Posizione pos = new Posizione("A5");
        pos.setBiblioteca(bib);
        Copia copia = new Copia("1","Prenotato","Disponibile",pos,libro);
        Persona person = new Persona("numdoc",ind,1,"nome","cognome",null);
        Calendar data = new GregorianCalendar();
        data.setTimeInMillis(new Date(2017,20,12).getTime());
        Calendar data2 = new GregorianCalendar();
        data2.setTimeInMillis(new Date(2018,28,3).getTime());
        prenotazione = new Prenotazione(data,data2,null,person,"Da ritirare",bib,copia);
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("DELETE FROM Prenotazione WHERE id = '"+ i +"'");
        prst.execute();
        con.commit();
        prst.close();
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
     * Test of doInsert method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        int expResult = -1;
        int result = instance.doInsert(prenotazione);
        assertNotEquals(expResult, result);
        prenotazione.setId(result);
        i = result;
        
    }

    /**
     * Test of doRetriveById method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        //instance.setBibDAO(new BibliotecaDAOStub());
        //instance.setCopiaDAO(new CopiaDAO(new LibroDAO(), new PosizioneDAO()));
        //instance.setPersDAO(new PersonaDAOStub());
        Prenotazione expResult = prenotazione;
        Prenotazione result = instance.doRetriveById(i);
        assertEquals(expResult, result);
        
    }
/**
     * Test of doRetriveAll method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        List<Prenotazione> expResult = new ArrayList<>();
        expResult.add(prenotazione);
        List<Prenotazione> result = instance.doRetriveAll();
        assertEquals(expResult, result);
        
    }

/**
     * Test of doUpdate method, of class PrenotazioneDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate");
        PrenotazioneDAO instance = new PrenotazioneDAO();
        int expResult = -1;
        int result = instance.doUpdate(prenotazione);
        assertNotEquals(expResult, result);
        
    }
    
}
