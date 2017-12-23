/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Prenotazione;
import core.utils.DriverManagerConnectionPool;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirko
 */
public class PrenotazioneDAO extends AbstractDAO<Prenotazione> {

    /**
     *
     * @param id[0] si aspetta un codice identificativo numerico per la
     * prenotazione.
     * @return una prenotazione in base al codice id
     */
    @Override
    public Prenotazione doRetriveById(Object... id) {
        int idPrenotazione = (int) id[0];

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prenotazione> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Prenotazione prenotazione) {
        try {
            Connection con = null;
            con = (Connection) DriverManagerConnectionPool.getConnection();
            PreparedStatement stt = null;
            stt = con.prepareStatement("INSERT INTO Prenotazione (data_creazione,data_scadenza,data_consegna,id_persona,isbn,isil,status) VALUES (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            stt.setDate(1, new Date(prenotazione.getDataCreazione().getTimeInMillis()));
            stt.setDate(2, new Date(prenotazione.getDataScadenza().getTimeInMillis()));
            stt.setDate(3, null);
            stt.setInt(4, prenotazione.getPersona().getId());
            stt.setString(5, prenotazione.getLibro().getIsbn());
            stt.setString(6, prenotazione.getBiblioteca().getIsil());
            stt.setString(7, prenotazione.getStatus());
            
            try {
                stt.executeQuery();
                ResultSet rs = stt.getGeneratedKeys();
                int id = rs.getInt(1);
                stt.close();
                con.commit();
                DriverManagerConnectionPool.releaseConnection(con);
                return id;
            } catch (SQLException e) {
                con.rollback();
            }
            finally{DriverManagerConnectionPool.releaseConnection(con);}
            
        } catch (SQLException ex) {
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Prenotazione prenotazione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
