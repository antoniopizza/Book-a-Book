/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Telefono;
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
public class TelefonoDAO extends AbstractDAO<Telefono>{
    private final String doDeleteQuery = "DELETE FROM Telefono WHERE prefisso = ? AND numero = ? ";
    private final String doRetriveByIdQuery = "SELECT * FROM Telefono WHERE prefisso = ? AND numero = ?";
    private final String doRetriveByPersonaQuery = "SELECT * FROM Telefono WHERE id_persona = ?";
    private final String doRetriveByBibliotecaQuery = "SELECT * FROM Telefono WHERE isil = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Telefono";
    private final String doInsertQuery = "INSERT INTO Telefono(prefisso,numero,id_persona,isil)" 
                                            + "VALUES(?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Telefono SET prefisso = ? , numero = ? WHERE prefisso = ? AND numero = ?";
    /**
     * 
     * @param id[0] si aspetta il prefisso di un numero telefonico, id[1] si aspetta un numero di telefono
     * @return un numero di telefono in base ai parametri inseriti.
     */

    @Override
    public Telefono doRetriveById(Object... id) {
        String prefisso = (String) id[0];
        String numero = (String) id[1];
        Telefono telefono = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, prefisso);
            prst.setString(2, numero);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                if (rs.next()) {
                    PersonaDAO personaDAO = new PersonaDAO();
                    BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
                    telefono = new Telefono(prefisso, numero, personaDAO.doRetriveByEmail(rs.getString("id_persona")), bibliotecaDAO.doRetriveById(rs.getString("isil")));
                }
                rs.close();
                return telefono;

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
    public List<Telefono> doRetriveAll() {
    List<Telefono> telefoni = new ArrayList<Telefono>();
        Telefono telefono = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                while(rs.next()) {
                    PersonaDAO personaDAO = new PersonaDAO();
                    BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
                    telefono = new Telefono(rs.getString("prefisso"), rs.getString("numero"), personaDAO.doRetriveByEmail(rs.getString("id_persona")), bibliotecaDAO.doRetriveById(rs.getString("isil")));
                    telefoni.add(telefono);
                }
                rs.close();
                return telefoni;

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
    public int doInsert(Telefono telefono) {
        
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            if(telefono.getBiblioteca() == null ) {
            prst.setString(1, telefono.getPrefisso());
            prst.setString(2,telefono.getNumero());
            prst.setInt(3,telefono.getPersona().getId());
            prst.setString(4, null);
            } else {            
            prst.setString(1, telefono.getPrefisso());
            prst.setString(2,telefono.getNumero());
            prst.setString(3,null);
            prst.setString(4,telefono.getBiblioteca().getIsil());
            }
            
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

    public int doUpdate(Telefono telefono,Telefono nuovoTelefono) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
         
            prst.setString(1, nuovoTelefono.getPrefisso());
            prst.setString(2,nuovoTelefono.getNumero());
            
            prst.setString(3,telefono.getPrefisso());
            prst.setString(4, telefono.getNumero());
                
            
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
    
    public Telefono doRetriveByPersona(Persona persona) {
        int id_persona = persona.getId();
        Telefono telefono = null;
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByPersonaQuery);
            prst.setInt(1, id_persona);
            

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                if (rs.next()) {
                    PersonaDAO personaDAO = new PersonaDAO();
                    telefono = new Telefono(rs.getString("prefisso"),rs.getString("numero"), persona, null);
                }
                rs.close();
                return telefono;

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
    
    public Telefono doRetriveByBiblioteca(Biblioteca biblioteca) {
        String isil = biblioteca.getIsil();
        Telefono telefono = null;
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByBibliotecaQuery);
            prst.setString(1, isil);
            

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                if (rs.next()) {
                    BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
                    telefono = new Telefono(rs.getString("prefisso"),rs.getString("numero"), null,biblioteca);
                }
                rs.close();
                return telefono;

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
    public int doUpdate(Telefono entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int doDelete(Telefono telefono) {
      try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doDeleteQuery);
            prst.setString(1, telefono.getPrefisso());
            prst.setString(2,telefono.getNumero());

            try {
                prst.execute();
                return 0;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return -1;
            } finally {
                prst.close();
                con.commit();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
        
    
    
    }
    
}
    
    


    
