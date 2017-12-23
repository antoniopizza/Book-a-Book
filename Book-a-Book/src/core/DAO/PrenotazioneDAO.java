/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Prenotazione;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author mirko
 */
public class PrenotazioneDAO extends AbstractDAO<Prenotazione>{
    
    private final String doRetriveByIdQuery = "SELECT * FROM prenotazione WHERE id = ? ORDER BY data_consegna DESC ";
    private final String doUpdateQuery = "UPDATE prenotazione SET data_creazione = ?, data_scadenza = ?, data_consegna = ?, id_persona = ?, isbn = ?, isil = ?, status = ? WHERE id = ?";
    
    /**
     * 
     * @param id[0] si aspetta un codice identificativo numerico per la prenotazione.
     * @return una prenotazione in base al codice id
     */

    @Override
    public Prenotazione doRetriveById(Object... id) {
        int idPrenotazione = (int) id[0];
        Prenotazione prenotazione = null;

        try{
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1, idPrenotazione);
            try{
                ResultSet rs = prst.executeQuery();
                con.commit();

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
                }

                rs.close();
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
                
            }catch(SQLException e){
                con.rollback();
            }finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return prenotazione;
        
    }

    @Override
    public List<Prenotazione> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Prenotazione prenotazione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Prenotazione prenotazione) {
        
        try{            
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            
            try{
            
                prst.setDate(1, new Date(prenotazione.getDataCreazione().getTimeInMillis()));
                prst.setDate(2, new Date(prenotazione.getDataScadenza().getTimeInMillis()));
                prst.setDate(3, new Date(prenotazione.getDataConsegna().getTimeInMillis()));
                prst.setInt(4, prenotazione.getPersona().getId());
                prst.setString(5, prenotazione.getLibro().getIsbn());
                prst.setString(6, prenotazione.getBiblioteca().getIsil());
                prst.setString(7, prenotazione.getStatus());
                prst.setInt(8, prenotazione.getId());

                prst.execute();

                con.commit();
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            } catch(SQLException e) {
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        
        return prenotazione.getId();
    }
    
}
