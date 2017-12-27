/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Posizione;
import core.entities.Biblioteca;
import core.entities.Libro;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mirko
 */
public class PosizioneDAO extends AbstractDAO<Posizione> {
    
    private final String doRetriveByIdQuery = "SELECT * FROM Posizione WHERE etichetta = ? AND isbn = ? AND isil = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Posizione";
    private final String doRetriveAllIsilIsbnQuery = "SELECT * FROM Posizione WHERE isbn = ? AND isil = ?";
    private final String doInsertQuery = "INSERT INTO Posizione(etichetta, num_copie, num_copie_totali, isil, isbn) VALUES (?, ?, ?, ?, ?)";
    private final String doUpdateQuery = "UPDATE Posizione SET num_copie = ?, num_copie_totali = ? WHERE etichetta = ? AND isbn = ? AND isil = ?";

    
    LibroDAO libroDAO;
    BibliotecaDAO bibliotecaDAO;

    public LibroDAO getLibroDAO() {
        return libroDAO;
    }

    public void setLibroDAO(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public BibliotecaDAO getBibliotecaDAO() {
        return bibliotecaDAO;
    }

    public void setBibliotecaDAO(BibliotecaDAO bibliotecaDAO) {
        this.bibliotecaDAO = bibliotecaDAO;
    }
    
    
    /**
     * Metodo che restituisce un oggetto Posizione in base ad un id(String) passato,
     * corrispondente all'etichetta di tale posizione.
     * 
     * @param id[0] si aspetta un codice che identifica la posizione di un
     * libro, 
     * @param id[1] si aspetta il codice identificativo di una biblioteca, 
     * @param id[2] si apsetta il codice di un libro.
     * @return la posizione di un libro in base ai parametri passati.
     */
    @Override
    public Posizione doRetriveById(Object... id) {
        String etichetta = (String) id[0];
        String isil = (String) id[1];
        String isbn = (String) id[2];

        try {
            Connection con = DriverManagerConnectionPool.getConnection();

            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, etichetta); 
            prst.setString(2, isbn);
            prst.setString(3, isil);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                Posizione posizione = null;

                if (rs.next()) {
                    
                    Biblioteca biblioteca = bibliotecaDAO.doRetriveById(rs.getString("isil"));

                    
                    Libro libro = libroDAO.doRetriveById(rs.getString("isbn"));

                    posizione = new Posizione(rs.getString("etichetta"), rs.getInt("num_copie"), rs.getInt("num_copie_totali"), biblioteca, libro);
                }
                rs.close();
                return posizione;
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

    
     /**
     * Metodo che restituisce una List di Posizione, 
     * contenente tutte le posizioni presenti.
     * 
     * @return la List di Posizione
     */
    @Override
    public List<Posizione> doRetriveAll() {

        List<Posizione> posizioneList = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();

            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                while (rs.next()) {
                    
                    
                    Biblioteca biblioteca = bibliotecaDAO.doRetriveById(rs.getString("isil"));

                    
                    Libro libro = libroDAO.doRetriveById(rs.getString("isbn"));
                    
                    Posizione posizione = new Posizione(rs.getString("etichetta"), rs.getInt("num_copie"), rs.getInt("num_copie_totali"), biblioteca, libro);
                    
                  
                    posizioneList.add(posizione);
                }
                rs.close();
                return posizioneList;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posizioneList;
    }
    
     /**
     * Metodo che restituisce un oggetto Posizione in base ad un id(String) passato,
     * corrispondente all'etichetta di tale posizione.
     * 
     * @param isil si aspetta un codice (String) che identifica la Biblioteca
     * contenente la posizione desiderata, 
     * @param isbn si aspetta il codice (String) che identifica il Libro
     * contenuto nella posizione desiderata.
     * @return la List di Posizione in base ai parametri passati.
     */
    public List<Posizione> doRetriveAllByIsilIsbn(String isil, String isbn){

        List<Posizione> posizioneList = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            
            PreparedStatement prst = con.prepareStatement(doRetriveAllIsilIsbnQuery);
            prst.setString(1, isbn);
            prst.setString(2, isil);
            
            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                while(rs.next()) {
                    
                    Biblioteca biblioteca = bibliotecaDAO.doRetriveById(rs.getString("isil"));

                    
                    Libro libro = libroDAO.doRetriveById(rs.getString("isbn"));

                    Posizione posizione = new Posizione(rs.getString("etichetta"), rs.getInt("num_copie"), rs.getInt("num_copie_totali"), biblioteca, libro);

                    posizioneList.add(posizione);
                }
                rs.close();
                return posizioneList;
            }
            catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return posizioneList;
    }

     /**
     * Metodo per l'inserimento di una Posizione all'interno del database.
     * 
     * @param posizione si aspetta un oggetto Posizione contenente tutti i parametri
     * da inserire all'interno del database.
     * @return 0 se l'inserimento è andato a buon fine, -1 altrimenti
     */
    @Override
    public int doInsert(Posizione posizione) {

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            
            prst.setString(1, posizione.getEtichetta());
            prst.setInt(2, posizione.getNumCopie());
            prst.setInt(3, posizione.getNumCopieTotali());
            prst.setString(4, posizione.getBiblioteca().getIsil());
            prst.setString(5, posizione.getLibro().getIsbn());
            
            System.out.println(prst.toString());
            System.out.println("ISIL: " + posizione.getBiblioteca().getIsil() + ", ISBN: " + posizione.getLibro().getIsbn());
            
            try {
                prst.executeUpdate();
                con.commit();
                return 0;
            }
            catch(SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
            finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return -1;
    }

      /**
     * Metodo per l'aggiornamento di una Posizione all'interno del database.
     * 
     * @param posizione si aspetta un oggetto Posizione contenente tutti i parametri
     * da aggiornare all'interno del database.
     * @return 0 se l'aggiornamento è andato a buon fine, -1 altrimenti
     */
    @Override
    public int doUpdate(Posizione posizione) {
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);

            prst.setInt(1, posizione.getNumCopie());
            prst.setInt(2, posizione.getNumCopieTotali());
            prst.setString(3, posizione.getEtichetta());
            prst.setString(4, posizione.getBiblioteca().getIsil());
            prst.setString(5, posizione.getLibro().getIsbn());
            
            try {
                prst.executeUpdate();
                con.commit();
                return 0;
            }
            catch(SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
            finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return -1;
    }

}
