/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Autore;
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
public class AutoreDAO extends AbstractDAO<Autore> {

    private final String doRetriveByIdQuery = "SELECT * FROM Autore WHERE id = ? ";
    private final String doRetriveByLibroQuery = "SELECT * FROM Autore a JOIN Libro_Autore la "
                                               + "ON a.id = la.id_autore WHERE la.isbn = ? ";

    /**
     *
     * @param id[0] si aspetta un codice identificativo di un autore
     * @return un autore in base al codice id
     */
    @Override
    public Autore doRetriveById(Object... id) {
        int idAutore = (int) id[0];

        try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1, idAutore);

            try (ResultSet rs = prst.executeQuery()) {
                Autore a = null;
                if (rs.next()) {
                    a = new Autore();
                    a.setId(idAutore);
                    a.setNome(rs.getString("nome"));
                }
                rs.close();
                return a;
                
            } catch (SQLException e ){
                con.rollback();
                return null;
                
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
            }

        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public List<Autore> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Autore> doRetriveByLibro(String isbn) {
        List<Autore> autori = new ArrayList<>();
        
        try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveByLibroQuery);
            prst.setString(1, isbn);

            try (ResultSet rs = prst.executeQuery()) {
                con.commit();
                
                
                while (rs.next()) {                    
                    Autore a = new Autore();
                    a.setId(rs.getInt("id"));
                    a.setNome(rs.getString("nome"));
                    autori.add(a);
                }
                rs.close();
                
                
            } catch (SQLException e ){
                con.rollback();
                return null;
                
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
            }

        } catch (SQLException e) {
            return null;
        }
        
        return autori;
    }

    @Override
    public int doInsert(Autore autore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Autore autore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
