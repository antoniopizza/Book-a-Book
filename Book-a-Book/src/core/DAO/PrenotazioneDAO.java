/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Prenotazione;
import core.utils.DriverManagerConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirko
 */
public class PrenotazioneDAO extends AbstractDAO<Prenotazione> {

    private final String doInsertQuery = "INSERT INTO prenotazione (data_creazione,data_scadenza,data_consegna,id_persona,isbn,isil,status) VALUES (?,?,?,?,?,?,?,?)";
    private final String doRetriveAllQuery = "SELECT * FROM prenotazione"
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
        Prenotazione prenotazione;
            ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();
        try{
            Connection con = null;
            con = (Connection) DriverManagerConnectionPool.getConnection();
            PreparedStatement stt = con.prepareStatement(doRetriveAllQuery);
            try{
                ResultSet rs = stt.executeQuery();
                
                if(rs.next()){
                     Calendar dataCreazione = new GregorianCalendar();
                     dataCreazione.setTimeInMillis(rs.getDate("data_creazione").getTime());
                     Calendar dataScadenza = new GregorianCalendar();
                     dataScadenza.setTimeInMillis(rs.getDate("data_scadenza").getTime());
                     Calendar dataConsegna = new GregorianCalendar();
                     dataConsegna.setTimeInMillis(rs.getDate("data_consegna").getTime());
                     prenotazione = new Prenotazione(dataCreazione, dataScadenza, dataConsegna, rs.getString("status"));
                     prenotazione.setPersona(new PersonaDAO().doRetriveById(rs.getInt("id_persona")));
                     prenotazione.setBiblioteca(new BibliotecaDAO().doRetriveById(rs.getString("isil")));
                     prenotazione.setLibro(new LibroDAO().doRetriveById(rs.getString("isbn")));
                     listaPrenotazioni.add(prenotazione);
                }
                
                con.commit();
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
                
            }
            catch(SQLException e){
                con.rollback();
            }
            
            finally{
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
        
        catch(SQLException e){
            e.printStackTrace();
        }
        return listaPrenotazioni;
    }

    @Override
    public int doInsert(Prenotazione prenotazione) {
        try {
            Connection con = null;
            con = (Connection) DriverManagerConnectionPool.getConnection();
            PreparedStatement stt = null;
            stt = con.prepareStatement(doInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
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
            
            finally{
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
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
