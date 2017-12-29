/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Account;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
import core.entities.Persona;
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
public class PersonaDAO extends AbstractDAO<Persona>{
    private final String doRetriveByEmailQuery = "SELECT * FROM Persona WHERE email = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Persona WHERE id = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Persona";
    private final String doInsertQuery = "INSERT INTO Persona(nome,cognome,email,num_documento,via,civico,citta)" 
                                            + "VALUES(?,?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Account SET nome = ?, cognome = ?,email = ?, num_documento = ?,via = ?, civico = ?,citta = ? WHERE id = ?";
    
    /**
     * 
     * @param id[0] si aspetta un codice identificativo numerico per una persona
     * @return una persona in base al codice ic.
     */

    @Override
    public Persona doRetriveById(Object... id) {
        int idPersona = (int) id[0];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1,idPersona);
           

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Persona persona = null;
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AccountDAO accountDAO = new AccountDAO();
                if (rs.next()) {
                    Account account = accountDAO.doRetriveById(rs.getString("email"));
                    Indirizzo indirizzo = indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico"));
                    persona = new Persona(rs.getString("num_documento"), indirizzo, idPersona, rs.getString("nome"), rs.getString("cognome"), account);
                    
                }
                rs.close();
                return persona;

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

    public Persona doRetriveByEmail(String email) {
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByEmailQuery);
            prst.setString(1,email);
           

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Persona persona = null;
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AccountDAO accountDAO = new AccountDAO();
                if (rs.next()) {
                    Account account = accountDAO.doRetriveById(rs.getString("email"));
                    Indirizzo indirizzo = indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico"));
                    persona = new Persona(rs.getString("num_documento"), indirizzo, rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), account);
                    
                }
                rs.close();
                return persona;

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
    public List<Persona> doRetriveAll() {
        List<Persona> persone = new ArrayList<Persona>();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            
           

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Persona persona = null;
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                AccountDAO accountDAO = new AccountDAO();
                if (rs.next()) {
                    Account account = accountDAO.doRetriveById(rs.getString("email"));
                    Indirizzo indirizzo = indirizzoDAO.doRetriveById(rs.getString("via"),rs.getString("citta"),rs.getString("civico"));
                    persona = new Persona(rs.getString("num_documento"), indirizzo, rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), account);
                    persone.add(persona);
                }
                rs.close();
                return persone;

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
    public int doInsert(Persona persona) {
        try{
            
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            prst.setString(1, persona.getNome());
            prst.setString(2,persona.getCognome());
            prst.setString(3,persona.getAccount().getEmail());
            prst.setString(4,persona.getNumDocumento());
            prst.setString(5,persona.getIndirizzo().getVia());
            prst.setString(6,persona.getIndirizzo().getCivico());
            prst.setString(7, persona.getIndirizzo().getCitta());
           
            
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
    public int doUpdate(Persona persona) {
         try{
            
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1, persona.getNome());
            prst.setString(2,persona.getCognome());
            prst.setString(3,persona.getAccount().getEmail());
            prst.setString(4,persona.getNumDocumento());
            prst.setString(5,persona.getIndirizzo().getVia());
            prst.setString(6,persona.getIndirizzo().getCivico());
            prst.setString(7, persona.getIndirizzo().getCitta());
           
            prst.setInt(8, persona.getId());
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
