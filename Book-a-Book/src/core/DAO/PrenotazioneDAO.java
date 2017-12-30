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

    private final String doInsertQuery = "INSERT INTO Prenotazione (data_creazione,data_scadenza,data_consegna,id_persona,isil,status,id_copia,isbn) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String doRetriveAllQuery = "SELECT * FROM Prenotazione";    
    private final String doRetriveByIdQuery = "SELECT * FROM Prenotazione WHERE id = ? ORDER BY data_consegna DESC ";
    private final String doUpdateQuery = "UPDATE Prenotazione SET data_creazione = ?, data_scadenza = ?, data_consegna = ?, id_persona = ?, isil = ?, status = ?, id_copia = ?, isbn = ? WHERE id = ?";
    
    
    CopiaDAO copiaDAO;
    BibliotecaDAO bibDAO;
    PersonaDAO persDAO;

    public CopiaDAO getCopiaDAO() {
        return copiaDAO;
    }

    public BibliotecaDAO getBibDAO() {
        return bibDAO;
    }

    public PersonaDAO getPersDAO() {
        return persDAO;
    }

    public void setCopiaDAO(CopiaDAO copiaDAO) {
        this.copiaDAO = copiaDAO;
    }
    
    public void setBibDAO(BibliotecaDAO bibDAO) {
        this.bibDAO = bibDAO;
    }

    public void setPersDAO(PersonaDAO persDAO) {
        this.persDAO = persDAO;
    }
    
    /**
     *
     * @param id[0] si aspetta un codice identificativo numerico per la
     * prenotazione.
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
                    prenotazione.setPersona(persDAO.doRetriveById(rs.getInt("id_persona")));
                    prenotazione.setBiblioteca(bibDAO.doRetriveById(rs.getString("isil")));
                    prenotazione.setCopia(copiaDAO.doRetriveById(rs.getString("id_copia"),rs.getString("isbn")));
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
                     prenotazione.setCopia(new CopiaDAO().doRetriveById(rs.getString("id_copia"),rs.getString("isbn")));
                     listaPrenotazioni.add(prenotazione);
                }
                
                con.commit();
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
                
            }
            catch(SQLException e){
                con.rollback();
                e.printStackTrace();
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
            stt.setString(5, prenotazione.getBiblioteca().getIsil());
            stt.setString(6, prenotazione.getStatus());
            stt.setString(7, prenotazione.getCopia().getId());
            stt.setString(8, prenotazione.getCopia().getLibro().getIsbn());
            
            try {
                stt.executeQuery();
                //ResultSet rs = stt.getGeneratedKeys();
                //int id = rs.getInt(1);
                stt.close();
                con.commit();
                DriverManagerConnectionPool.releaseConnection(con);
                return 0;
                
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
        return 0;
  }

    @Override
    public int doUpdate(Prenotazione prenotazione) {
        
        try{            
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            
            try{
            
            prst.setDate(1, new Date(prenotazione.getDataCreazione().getTimeInMillis()));
            prst.setDate(2, new Date(prenotazione.getDataScadenza().getTimeInMillis()));
            prst.setDate(3, null);
            prst.setInt(4, prenotazione.getPersona().getId());
            prst.setString(5, prenotazione.getBiblioteca().getIsil());
            prst.setString(6, prenotazione.getStatus());
            prst.setString(7, prenotazione.getCopia().getId());
            prst.setString(8, prenotazione.getCopia().getLibro().getIsbn());
            prst.setInt(9, prenotazione.getId());

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
            return -2;
        }
        
        return 0;
    }

    
}
