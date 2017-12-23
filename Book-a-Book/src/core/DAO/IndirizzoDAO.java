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
    private final String doRetriveByIdQuery = "SELECT * FROM Indirizzo WHERE via = ?,citta = ?,civico = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Indirizzo";
    private final String doInsertQuery = "INSERT INTO Indirizzo(via,citta,civico,provincia,cap)" 
                                            + "VALUES(?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Indirizzo SET via = ?, citta = ?, civico = ?, provincia = ?, cap = ? WHERE via = ?,citta = ?,civico = ?";
    /**
     * 
     * @param id[0] si aspetta una string che corrisponde alla via, id[1] si aspetta una stringa che corrisponde alla città, id[2] si aspetta una stringa che corrisponde al civico di ua strada.
     * @return un indirizzo in base alle stringhe che gli vengono passate. 
     */
    
    @Override
    public Indirizzo doRetriveById(Object... id) {
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
                Indirizzo indirizzo = null;
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
        
        Connection con;
        try {
            con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            
        try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Indirizzo indirizzo = null;
                
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
                return 0;
            } catch(SQLException e){
                return -1;
            }
            
            
        } catch(SQLException e){
            return -1;
        }
    }

    @Override
    public int doUpdate(Indirizzo indirizzo) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1, indirizzo.getVia());
            prst.setString(2,indirizzo.getCitta());
            prst.setString(3,indirizzo.getCivico());
            prst.setString(4,indirizzo.getProvincia());
            prst.setString(5,indirizzo.getCap());
           
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
