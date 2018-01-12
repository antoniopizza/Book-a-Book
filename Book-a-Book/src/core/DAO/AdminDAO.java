/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Account;
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
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
public class AdminDAO extends AbstractDAO<Admin>{
    private final String doRetrieveByEmailQuery = "SELECT * FROM Admin WHERE email = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Admin WHERE id = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Admin";
    private final String doInsertQuery = "INSERT INTO Admin(nome,cognome,email)" 
                                            + "VALUES(?,?,?);";
    private final String doUpdateQuery = "UPDATE Admin SET nome = ?, cognome = ?, email = ? WHERE id = ?";
    /**
     * 
     * @param id[0] si aspetta un codice identificativo numerico di un Admin.
     * @return un Admin in base al codice id. 
     */

    @Override
    public Admin doRetriveById(Object... id) {
        int idAdmin = (int) id[0];
        Admin admin = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1,idAdmin) ;
           

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                if (rs.next()) {
                    AccountDAO account = new AccountDAO();
                    Account account2 = account.doRetriveById(rs.getString("email"));                   
                    admin = new Admin(rs.getString("nome"), rs.getString("cognome"), account2,idAdmin);
                }
                rs.close();
                return admin;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
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
    
    public Admin doRetriveByEmail(String email) {
        Admin admin = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetrieveByEmailQuery);
            prst.setString(1,email) ;
           

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                if (rs.next()) {
                    
                    AccountDAO account = new AccountDAO();
                    Account account2 = account.doRetriveById(rs.getString("email"));                    
                    admin = new Admin(rs.getString("nome"), rs.getString("cognome"), account2,rs.getInt("id"));
                    //System.out.println(""+admin);
                }
                
                rs.close();
                return admin;
                

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
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
    public List<Admin> doRetriveAll() {
        List<Admin> admins = new ArrayList<>();
       try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            
            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Admin admin = null;
                while (rs.next()) {
                    AccountDAO account = new AccountDAO();
                    Account account2 = account.doRetriveById(rs.getString("email")); 
                    admin = new Admin(rs.getString("nome"), rs.getString("cognome"), account2);
                    admins.add(admin);
                }
                rs.close();
                return admins;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
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
    public int doInsert(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Admin admin) {
       try{
            
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1, admin.getNome());
            prst.setString(2,admin.getCognome());
            prst.setString(3,admin.getAccount().getEmail());
            
            
            prst.setInt(4, admin.getId());
           
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
}
    

