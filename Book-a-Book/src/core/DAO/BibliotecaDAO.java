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
    
    private final String doRetriveByIdQuery = "SELECT * FROM Biblioteca WHERE isil = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Biblioteca";
    private final String doInsertQuery = "INSERT INTO Biblioteca(isil,nome,status,via,citta,civico,id_admin)" 
                                            + "VALUES(?,?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Biblioteca SET  nome = ?, status = ?, via = ?, citta = ?, civico = ? WHERE isil = ?";
    /**
     * 
     * @param id[0] si aspetta un codice isil per identificare una biblioteca
     * @return una biblioteca in base al codice isil
     */

    @Override
    public Biblioteca doRetriveById(Object... id) {
        String isil = (String) id[0];
          try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, isil);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Biblioteca biblioteca = null;
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AdminDAO adminDAO = new AdminDAO();
                Indirizzo indirizzoB = null;
                if (rs.next()) {
                    biblioteca = new Biblioteca(rs.getString("isil"), rs.getString("nome"), rs.getString("status"),indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico")),adminDAO.doRetriveById(rs.getString("id_admin")));
                    
                }
                rs.close();
                return biblioteca;

            } catch (SQLException e) {
                con.rollback();
                return null;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Biblioteca> doRetriveAll() {
        List<Biblioteca> biblioteche = new ArrayList<Biblioteca>();
        
        Connection con;
        try {
            con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            
        try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Biblioteca biblioteca = null;
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AdminDAO adminDAO = new AdminDAO();
                Indirizzo indirizzoB = null;
                while(rs.next()) {
                    biblioteca = new Biblioteca(rs.getString("isil"), rs.getString("nome"), rs.getString("status"),indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico")),adminDAO.doRetriveById(rs.getString("id_admin")));
                    biblioteche.add(biblioteca);
                }
                rs.close();
                return biblioteche;

            } catch (SQLException e) {
                con.rollback();
                return null;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
           
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
            prst.setInt(7, biblioteca.getAdmin().getId());
            
            
           
            try{
                prst.execute();
                return 0;
            } catch(SQLException e){
                return -1;
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
                return 0;
            } catch(SQLException e){
                return -1;
            }
            
            
        } catch(SQLException e){
            return -1;
        }
    }
    
}
