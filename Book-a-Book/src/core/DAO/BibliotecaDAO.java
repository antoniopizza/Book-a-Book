/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Indirizzo;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirko
 */
public class BibliotecaDAO extends AbstractDAO<Biblioteca>{
    private final String doRetriveIsilByCopiaQuery = "SELECT DISTINCT isil FROM Copia, Libro WHERE Copia.isbn = Libro.isbn AND Copia.isbn = ?";
    private final String doDeleteQuery = "DELETE FROM Biblioteca WHERE isil = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Biblioteca WHERE isil = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Biblioteca";
    private final String doInsertQuery = "INSERT INTO Biblioteca(isil,nome,status,via,citta,civico)" 
                                            + "VALUES(?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Biblioteca SET  nome = ?, status = ?, via = ?, citta = ?, civico = ? WHERE isil = ?";
    /**
     * 
     * @param id[0] si aspetta un codice isil per identificare una biblioteca
     * @return una biblioteca in base al codice isil
     */

    @Override
    public Biblioteca doRetriveById(Object... id) {
        String isil = (String) id[0];
         Biblioteca biblioteca = null;
          try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, isil);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
               
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AdminDAO adminDAO = new AdminDAO();
                Indirizzo indirizzoB = null;
                if (rs.next()) {
                    if((rs.getString("id_admin") == null)) {
                    biblioteca = new Biblioteca(rs.getString("isil"), rs.getString("nome"), rs.getString("status"),indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico")),null);
                    } else {
                    biblioteca = new Biblioteca(rs.getString("isil"), rs.getString("nome"), rs.getString("status"),indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico")),adminDAO.doRetriveById(rs.getInt("id_admin"))); 
                    }
                }
                rs.close();
                return biblioteca;

            } catch (SQLException e) {
                con.rollback();
                
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
          return biblioteca;
    }

    @Override
    public List<Biblioteca> doRetriveAll() {
        List<Biblioteca> biblioteche = new ArrayList<Biblioteca>();
        //System.err.println("Sono dentro il doretrieveall");
        Connection con;
        try {
            con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            //System.err.println("Sono dentro il primo try");
        try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AdminDAO adminDAO = new AdminDAO();
                
                while(rs.next()) {
                    //System.err.println("Voglio le biblio mlmlmlmlml");
                    Biblioteca biblioteca = null;
                    Indirizzo indirizzo = indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico"));
                    //System.out.println(""+indirizzo);
                    if((rs.getString("id_admin") == null)) {
                    biblioteca = new Biblioteca(rs.getString("isil"), rs.getString("nome"), rs.getString("status"),indirizzo,null);
                    //System.out.println(""+biblioteca);
                    } else {
                        biblioteca = new Biblioteca(rs.getString("isil"), rs.getString("nome"), rs.getString("status"),indirizzo,adminDAO.doRetriveById(rs.getString("id_admin")));
                    }
                    biblioteche.add(biblioteca);
                }
                rs.close();
                return biblioteche;

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
         return biblioteche;  
    }

    @Override
    public int doInsert(Biblioteca biblioteca) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            prst.setString(1, biblioteca.getIsil());
            prst.setString(2,biblioteca.getNome());
            prst.setString(3,biblioteca.getStatus());
            prst.setString(4,biblioteca.getIndirizzo().getVia());
            prst.setString(5,biblioteca.getIndirizzo().getCitta());
            prst.setString(6, biblioteca.getIndirizzo().getCivico());
            
           // System.out.println(""+biblioteca.toString());
            
            
           
            try{
                prst.execute();
                con.commit();
                return 0;
            } catch(SQLException e){
                con.rollback();
                e.printStackTrace();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
            
        } catch(SQLException e){
            return -1;
        }
        
        
    }

    @Override
    public int doUpdate(Biblioteca biblioteca) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1,biblioteca.getNome());
            prst.setString(2,biblioteca.getStatus());
            prst.setString(3,biblioteca.getIndirizzo().getVia());
            prst.setString(4,biblioteca.getIndirizzo().getCitta());
            prst.setString(5, biblioteca.getIndirizzo().getCivico());
            
            prst.setString(6, biblioteca.getIsil());
            try{
                prst.execute();
                con.commit();
                return 0;
            } catch(SQLException e){
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
            
        } catch(SQLException e){
            return -1;
        }
        return 0;
    }
    
    
   public int doDelete(String isil) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doDeleteQuery);
            prst.setString(1, isil);
            
            try{
                prst.execute();
                con.commit();
                return 0;
            } catch(SQLException e){
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
            
        } catch(SQLException e){
            return -1;
        }
        return 0;
   }
   
   public List<Biblioteca> doRetriveIsilByCopia(String isbn) {
        Biblioteca biblioteca;
        BibliotecaDAO bibliotecaDAO = null;
        List<Biblioteca> listaBiblioteche = new ArrayList<Biblioteca>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveIsilByCopiaQuery);
            prst.setString(1, isbn);
            
            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                while(rs.next()){
                    String isil = rs.getString("isil");
                    biblioteca = bibliotecaDAO.doRetriveById(isil);
                    listaBiblioteche.add(biblioteca);
                }
                
                return listaBiblioteche;
                
            } catch(SQLException e){
                con.rollback();
                return null;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
