/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Copia;
import core.entities.Posizione;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kliffom
 */
public class CopiaDAO extends AbstractDAO<Copia> {

    private final String doInsertQuery = "INSERT INTO Copia(id,status,disponibilita,isbn,id_posizione,isil) VALUES(?,?,?,?,?,?)";
    private final String doRetriveByIdQuery = "SELECT * FROM Copia WHERE id = ? AND isbn = ? AND isil = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Copia";
    private final String doRetriveByPosizioneQuery = "SELECT * FROM Copia WHERE id_posizione = ? AND isil = ?";
    private final String doDeleteQuery = "DELETE FROM Copia WHERE id = ? AND isbn = ? AND isil = ?";
    private final String doUpdatePosizioneQuery = "UPDATE Copia SET id_posizione = ? WHERE id = ? AND isbn = ? AND isil = ?";
    private final String doUpdate = "UPDATE Copia SET disponibilita = ? , status = ?  WHERE id = ? AND isbn = ? AND isil = ?";
    
    private LibroDAO libroDao;
    private PosizioneDAO posizioneDAO;

    public CopiaDAO(LibroDAO libroDao, PosizioneDAO posizioneDAO) {
        this.libroDao = libroDao;
        this.posizioneDAO = posizioneDAO;
    }
    
    /**
     * Metodo che date delle stringhe identificantia i parametri di una copia, 
     * restituisce l'oggetto copia corrispodente a tali parametri
     *
     * @param id[0] si aspetta un idCopia(String),
     * id[1] si aspetta un isbn(String),
     * id[2] si aspetta un isil(String).
     * 
     * @return la copia corrispondente ai parametri, altrimenti null.
     */
    @Override
    public Copia doRetriveById(Object... id) {
        String idCopia = (String) id[0];
        String isbn  = (String) id[1];
        String isil  = (String) id[2];

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, idCopia);
            prst.setString(2, isbn);
            prst.setString(3, isil);

            try (ResultSet rs = prst.executeQuery()) {
                con.commit();
                if (rs.next()) {
                    Copia c = new Copia();
                    c.setId(rs.getString("id"));
                    c.setDisponibilita(rs.getString("disponibilita"));
                    c.setStatus(rs.getString("status"));
                    c.setLibro(libroDao.doRetriveById(rs.getString("isbn")));
                    c.setPosizione(posizioneDAO.doRetriveById(rs.getString("id_posizione"), rs.getString("isil")));
                    return c;

                }

                
            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    /**
     * Metodo restituisce una lista contenente tutte le copie presenti nel database
     * 
     * @return la List di Copia completa se presenti nel db, altrimenti null.
     */
    @Override
    public List<Copia> doRetriveAll() {
        List<Copia> copie = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try (ResultSet rs = prst.executeQuery()) {
                while (rs.next()) {
                    con.commit();
                    Copia c = new Copia();
                    c.setId(rs.getString("id"));
                    c.setDisponibilita(rs.getString("disponibilita"));
                    c.setStatus(rs.getString("status"));
                    c.setLibro(libroDao.doRetriveById(rs.getString("isbn")));
                    c.setPosizione(posizioneDAO.doRetriveById(rs.getString("id_posizione"), rs.getString("isil")));
                    copie.add(c);
                }

                

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return copie;
    }

    /**
     * Metodo che dato un oggetto Copia, 
     * inserisce i dati di tale oggetto all'interno del database.
     *
     * @param entity (Copia) oggetto da inserire nel db.
     * 
     * @return 0 se le operazioni sono andate a buon fine, -1 altrimenti.
     */
    @Override
    public int doInsert(Copia entity) {
        
        //ordine di inserimento status disponibilita isbn id_posizione isil
        
        try{
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            prst.setString(1, entity.getId());
            prst.setString(2, entity.getStatus());
            prst.setString(3,entity.getDisponibilita());
            prst.setString(4, entity.getLibro().getIsbn());
            prst.setString(5, entity.getPosizione().getEtichetta());
            prst.setString(6, entity.getPosizione().getBiblioteca().getIsil());
            
            try{
                prst.execute();
                con.commit();
                return 0;
                
            } catch(SQLException ex){
                con.rollback();
                ex.printStackTrace();
            } finally{
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
            
        }
        
        return -1;
    }

    /**
     * Metodo che aggiorna status e disponibilita di una copia
     * @param copia
     * @return 0 se tutto ok, -1 altrimenti
     */
    @Override
    public int doUpdate(Copia copia) {
         try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdate);
            prst.setString(1,copia.getDisponibilita());
            prst.setString(2,copia.getStatus());            
            prst.setString(3, copia.getId());
            prst.setString(4, copia.getLibro().getIsbn());
            prst.setString(5, copia.getPosizione().getBiblioteca().getIsil());
            
            try {
                prst.execute();
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
     * Metodo restituisce una lista contenente tutte le copie presenti nel database in base ad una posizione.
     * 
     * @param posizione (Posizione) oggetto contenente la posizione della quale si desidera ricevere le copie.
     * 
     * @return la List di Copia completa in base ad una Posizione se presente nel db, altrimenti null.
     */
    public List<Copia> doRetriveByPosizione(Posizione posizione) {
        List<Copia> copie = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByPosizioneQuery);
            prst.setString(1, posizione.getEtichetta());
            prst.setString(2, posizione.getBiblioteca().getIsil());
            
            try (ResultSet rs = prst.executeQuery()) {
                while (rs.next()) {
                    con.commit();
                    Copia c = new Copia();
                    c.setId(rs.getString("id"));
                    c.setDisponibilita(rs.getString("disponibilita"));
                    c.setStatus(rs.getString("status"));
                    c.setLibro(libroDao.doRetriveById(rs.getString("isbn")));
                    c.setPosizione(posizione);
                    copie.add(c);
                }

                

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return copie;
    }
    
    /**
     * Metodo che elimina una tupla dal db corrispondente all'oggetto Copia ricevuto.
     * 
     * @param entity (Copia) oggetto che si desidera eliminare dal db.
     * 
     * @return 0 se l'operazione è andata a buon fine, -1 altrimenti.
     */
    public int doDelete(Copia entity) {
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doDeleteQuery);
            prst.setString(1, entity.getId());
            prst.setString(2, entity.getLibro().getIsbn());
            prst.setString(3, entity.getPosizione().getBiblioteca().getIsil());
            
            try{
                prst.execute();
                con.commit();
                return 0;
                
            } catch(SQLException ex){
                con.rollback();
                ex.printStackTrace();
            } finally{
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    /**
     * Metodo che modifica il riferimento (id) ad una Posizione di una Copia
     * con un altro riferimento (id) ad una Posizione uguale a quello passato.
     * 
     * @param copia (Copia) oggetto che si desidera eliminare dal db,
     * @param idNuovaPosizione (String) stringa contenente l'id dove si desidera spostare la copia passata.
     * 
     * @return 0 se l'operazione è andata a buon fine, -1 altrimenti.
     */
    public int doUpdatePosizione(Copia copia, String idNuovaPosizione) {
        //private final String doUpdatePosizioneQuery = "UPDATE Copia SET id_posizione = ? WHERE id = ? AND isbn = ? AND isil = ?";
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdatePosizioneQuery);
            prst.setString(1, idNuovaPosizione);
            prst.setString(2, copia.getId());
            prst.setString(3, copia.getLibro().getIsbn());
            prst.setString(4, copia.getPosizione().getBiblioteca().getIsil());
            
            try {
                prst.execute();
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
