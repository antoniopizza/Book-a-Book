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
    private final String doRetriveByIdQuery = "SELECT * FROM Copia WHERE id = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Copia";
    private final String doRetriveByPosizioneQuery = "SELECT * FROM Copia WHERE id_posizione = ? AND isil = ?";

    private LibroDAO libroDao;
    private PosizioneDAO posizioneDAO;

    public CopiaDAO(LibroDAO libroDao, PosizioneDAO posizioneDAO) {
        this.libroDao = libroDao;
        this.posizioneDAO = posizioneDAO;
    }

    @Override
    public Copia doRetriveById(Object... id) {
        String idCopia = (String) id[0];
        String isbn  = (String) id[1];
        String isil  = (String) id[2];

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, idCopia);

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
     * Metodo per inserire una copia nel database
     * @param entity la copia da inserire
     * @return 0 se l'inserimento va a buon fine, -1 altrimenti
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

    @Override
    public int doUpdate(Copia entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
}
