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
public class BibliotecarioDAO extends AbstractDAO<Bibliotecario> {

    private final String doDeleteQuery = "DELETE FROM Bibliotecario WHERE isil = ?";
    private final String doRetrieveByEmailQuery = "SELECT * FROM Bibliotecario WHERE email = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Bibliotecario WHERE id = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Bibliotecario";
    private final String doInsertQuery = "INSERT INTO Bibliotecario(nome,cognome,status,email,isil,tipo)"
            + "VALUES(?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Bibliotecario SET nome = ?, cognome = ?, status = ?,email = ?, isil = ?, tipo = ? WHERE id = ?";
    private final String doRetriveDipendentiByIsilQuery = "SELECT * FROM Bibliotecario WHERE tipo = ? AND isil = ?";
    
    /**
     *
     * @param id[0] si aspetta un codice identificativo numerico di un
     * bibliotecario
     * @return un bibliotecario in base al codice id
     */

    @Override
    public Bibliotecario doRetriveById(Object... id) {
        int idBibliotecario = (int) id[0];
        Bibliotecario bibliotecario = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1, idBibliotecario);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                if (rs.next()) {
                    BibliotecaDAO biblioteca = new BibliotecaDAO();
                    Biblioteca biblioteca2 = biblioteca.doRetriveById(rs.getString("isil"));
                    AccountDAO account = new AccountDAO();
                    Account account2 = account.doRetriveById(rs.getString("email"));
                    bibliotecario = new Bibliotecario(rs.getString("status"), rs.getString("tipo"), biblioteca2, rs.getString("nome"), rs.getString("cognome"), account2);
                }
                rs.close();
                return bibliotecario;

            } catch (SQLException e) {
                e.printStackTrace();
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

    public Bibliotecario doRetriveByEmail(String email) {
        Bibliotecario bibliotecario = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetrieveByEmailQuery);
            prst.setString(1, email);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                if (rs.next()) {
                    BibliotecaDAO biblioteca = new BibliotecaDAO();
                    Biblioteca biblioteca2 = biblioteca.doRetriveById(rs.getString("isil"));
                    AccountDAO account = new AccountDAO();
                    Account account2 = account.doRetriveById(rs.getString("email"));

                    bibliotecario = new Bibliotecario(rs.getString("status"), rs.getString("tipo"), biblioteca2, rs.getString("nome"), rs.getString("cognome"), account2);
                }
                rs.close();
                return bibliotecario;

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
    public List<Bibliotecario> doRetriveAll() {
        List<Bibliotecario> bibliotecari = new ArrayList<Bibliotecario>();

        Connection con;
        try {
            con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Bibliotecario bibliotecario = null;
                BibliotecaDAO biblioteca = new BibliotecaDAO();
                AccountDAO account = new AccountDAO();
                while (rs.next()) {
                    Biblioteca biblioteca2 = biblioteca.doRetriveById(rs.getString("isil"));
                    Account account2 = account.doRetriveById(rs.getString("email"));

                    bibliotecario = new Bibliotecario(rs.getString("status"), rs.getString("tipo"), biblioteca2, rs.getString("nome"), rs.getString("cognome"), account2);
                    bibliotecari.add(bibliotecario);
                }
                rs.close();
                return bibliotecari;

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
    public int doInsert(Bibliotecario bibliotecario) {
        try {

            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            prst.setString(1, bibliotecario.getNome());
            prst.setString(2, bibliotecario.getCognome());
            prst.setString(3, bibliotecario.getStatus());
            prst.setString(4, bibliotecario.getAccount().getEmail());
            prst.setString(5, bibliotecario.getBiblioteca().getIsil());
            prst.setString(6, bibliotecario.getTipo());

            try {
                prst.execute();
                con.commit();
                ResultSet rs = prst.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                return id;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }

    }

    @Override
    public int doUpdate(Bibliotecario bibliotecario) {
        try {

            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1, bibliotecario.getNome());
            prst.setString(2, bibliotecario.getCognome());
            prst.setString(3, bibliotecario.getStatus());
            prst.setString(4, bibliotecario.getAccount().getEmail());
            prst.setString(5, bibliotecario.getBiblioteca().getIsil());
            prst.setString(6, bibliotecario.getTipo());

            prst.setInt(7, bibliotecario.getId());

            try {
                prst.execute();
                con.commit();
                return 0;
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }

    }

    public int doDelete(String isil) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doDeleteQuery);
            prst.setString(1, isil);

            try {
                prst.execute();
                con.commit();
                return 0;
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }

    }
    
    public List<Bibliotecario> doRetriveDipendentiByIsil(String isil){
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            AccountDAO accountDAO = new AccountDAO();
            BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
            List<Bibliotecario> bibliotecari = new ArrayList<Bibliotecario>();
            Bibliotecario bibliotecario = null;
            PreparedStatement prst = con.prepareStatement(doRetriveDipendentiByIsilQuery);
            prst.setString(1, "Dipendente");
            prst.setString(2, isil);
            
            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                while (rs.next()) {
                    bibliotecario = new Bibliotecario(rs.getString("status"), rs.getString("tipo"), rs.getString("nome"), rs.getString("cognome"));
                    bibliotecario.setAccount(accountDAO.doRetriveById(rs.getString("email")));
                    bibliotecario.setBiblioteca(bibliotecaDAO.doRetriveById(rs.getString("isil")));
                    bibliotecari.add(bibliotecario);
                }
                
                rs.close();
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
                return bibliotecari;
                
            } catch(SQLException e) {
                con.rollback();
                return null;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
