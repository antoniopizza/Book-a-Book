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
 * @author Manuel De Stefano
 */
public class AutoreDAO extends AbstractDAO<Autore> {

    private final String doRetriveByIdQuery = "SELECT * FROM Autore WHERE id = ? ";
    private final String doRetriveAllQuery = "SELECT * FROM Autore";
    private final String doRetriveByLibroQuery = "SELECT * FROM Autore a JOIN Libro_Autore la "
                                               + "ON a.id = la.id_autore WHERE la.isbn = ? ";
    private final String doRetriveByNomeQuery = "SELECT * FROM Autore WHERE nome = ? ";
    private final String doInsertQuery = "INSERT INTO Autore(nome) VALUES(?)";
    private final String doUpdateQuery = "UPDATE Autore SET nome = ? WHERE id = ?";
    
    /**
     * Seleziona un autore in base al suo id
     * @param id[0] si aspetta un codice identificativo di un autore
     * @return un autore se viene trovato, null atrimenti.
     */
    @Override
    public Autore doRetriveById(Object... id) {
        int idAutore = (int) id[0];

        try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1, idAutore);

            try (ResultSet rs = prst.executeQuery()) {
                con.commit();
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

    /**
     * Seleziona tutti gli autori presenti nel database
     * @return la lista di tutti gli autori presenti sul db 
     */
    @Override
    public List<Autore> doRetriveAll() {
        List<Autore> autori = new ArrayList<>();
        
         try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);            

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
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return autori;
    }

    /**
     * Seleziona dal Db tutti gli autori di un dato libro
     * @param isbn del libro.
     * @return la lista di autori del libro, una lista vuota altrimenti.
     */
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
                e.printStackTrace();
                
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return autori;
    }
    
    /**
     * Metodo che seleziona dal Db un autore in base al nome
     * @param nome
     * @return un oggetto Autore se viene trovato, null altrimenti.
     */
    public Autore doRetriveByNome(String nome){
         try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveByNomeQuery);
            prst.setString(1, nome);

            try (ResultSet rs = prst.executeQuery()) {
                con.commit();
                Autore a = null;
                if (rs.next()) {
                    a = new Autore();
                    a.setId(rs.getInt("id"));
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

    /**
     * Metodo che inserisce un autore nel database
     * @param autore da inserire
     * @return l'id della tupla inserita, -1 altrimenti
     */
    @Override
    public int doInsert(Autore autore) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            prst.setString(1,autore.getNome());
            
            try{
                prst.execute();
                con.commit();
                ResultSet rs = prst.getGeneratedKeys();
                rs.next();
                
                return rs.getInt(1);                                
                
            } catch(SQLException e) {
                con.rollback();
                e.printStackTrace();
                return -1;
            }
            
        } catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Metodo che aggiorna un autore nel database.
     * @param autore
     * @return 
     */
    @Override
    public int doUpdate(Autore autore) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1,autore.getNome());
            prst.setInt(2,autore.getId());
            
            try{
                prst.execute();
                con.commit(); 
                return autore.getId();                
                
            } catch(SQLException e) {
                con.rollback();
                e.printStackTrace();
                return -1;
            }
            
        } catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

}
