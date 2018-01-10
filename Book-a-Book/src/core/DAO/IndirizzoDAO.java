/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Indirizzo;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mirko
 */
public class IndirizzoDAO extends AbstractDAO<Indirizzo>{
    private final String doDeleteQuery = "DELETE FROM Indirizzo WHERE via = ? AND citta = ? AND civico = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Indirizzo WHERE via = ? AND citta = ? AND civico = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Indirizzo";
    private final String doInsertQuery = "INSERT INTO Indirizzo(via,citta,civico,provincia,cap)" 
                                            + "VALUES(?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Indirizzo SET via = ?, citta = ?, civico = ?, provincia = ?, cap = ? WHERE via = ? AND citta = ? AND civico = ?";
    /**
     * 
     * @param id[0] si aspetta una string che corrisponde alla via, id[1] si aspetta una stringa che corrisponde alla città, id[2] si aspetta una stringa che corrisponde al civico di ua strada.
     * @return un indirizzo in base alle stringhe che gli vengono passate. 
     */
    
    @Override
    public Indirizzo doRetriveById(Object... id) {
        Indirizzo indirizzo = null;
        String  via = (String) id[0];
        String citta = (String) id[1];
        String civico = (String) id[2];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, via);
            prst.setString(2, citta);
            prst.setString(3, civico);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                if (rs.next()) {
                    indirizzo = new Indirizzo(rs.getString("via"), rs.getString("citta"), rs.getString("civico"),rs.getString("provincia"),rs.getString("cap"));
                }
                rs.close();
                return indirizzo;

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
    public List<Indirizzo> doRetriveAll() {
        List<Indirizzo> indirizzi = new ArrayList<Indirizzo>();
        Indirizzo indirizzo = null;
        Connection con;
        try {
            con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            
        try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                
                while(rs.next()) {
                    indirizzo = new Indirizzo(rs.getString("via"), rs.getString("citta"), rs.getString("civico"), rs.getString("provincia"), rs.getString("cap"));
                    indirizzi.add(indirizzo);
                }
                rs.close();
                return indirizzi;

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
    public int doInsert(Indirizzo indirizzo) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            prst.setString(1, indirizzo.getVia());
            prst.setString(2,indirizzo.getCitta());
            prst.setString(3,indirizzo.getCivico());
            prst.setString(4,indirizzo.getProvincia());
            prst.setString(5,indirizzo.getCap());
            
            
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
    public int doUpdate(Indirizzo indirizzo) {
    
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    public int doUpdateIndirizzo(Indirizzo vecchioIndirizzo, Indirizzo nuovoIndirizzo) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1,nuovoIndirizzo.getVia());
            prst.setString(2,nuovoIndirizzo.getCitta());
            prst.setString(3,nuovoIndirizzo.getCivico());
            prst.setString(4,nuovoIndirizzo.getProvincia());
            prst.setString(5,nuovoIndirizzo.getCap());
           
            prst.setString(6,vecchioIndirizzo.getVia());
            prst.setString(7,vecchioIndirizzo.getCitta());
            prst.setString(8,vecchioIndirizzo.getCivico());
            try{
                prst.execute();
                con.commit();
                return 0;
            } catch(SQLException e){
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
            
        } catch(SQLException e){
            return -1;
        }
        
    }
    
    public int doDelete(Indirizzo indirizzo) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doDeleteQuery);
            prst.setString(1, indirizzo.getVia());
            prst.setString(2, indirizzo.getCitta());
            prst.setString(3, indirizzo.getCivico());

            try {
                prst.execute();
                return 0;
            } catch (SQLException e) {
                con.rollback();
            } finally {
                prst.close();
                con.commit();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }
    }
