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

    private final String doInsertQuery = "INSERT INTO Prenotazione (data_creazione,data_scadenza,data_consegna,id_persona,isil,status,id_copia,isbn) VALUES (?,?,?,?,?,?,?,?)";
    private final String doRetriveAllQuery = "SELECT * FROM Prenotazione";
    private final String doRetriveByIdQuery = "SELECT * FROM Prenotazione WHERE id = ? ORDER BY data_consegna DESC ";
    private final String doUpdateQuery = "UPDATE Prenotazione SET data_creazione = ?, data_scadenza = ?, data_consegna = ?, id_persona = ?, isil = ?, status = ?, id_copia = ?, isbn = ? WHERE id = ?";

    /**
     *
     * @param id[0] si aspetta un codice identificativo numerico per la
     * prenotazione.
     * @return una prenotazione in base al codice id
     */
    
    PersonaDAO persDAO;
    BibliotecaDAO bibDAO;
    CopiaDAO copiaDAO;

    public PersonaDAO getPersDAO() {
        return persDAO;
    }

    public void setPersDAO(PersonaDAO persDAO) {
        this.persDAO = persDAO;
    }

    public BibliotecaDAO getBibDAO() {
        return bibDAO;
    }

    public void setBibDAO(BibliotecaDAO bibDAO) {
        this.bibDAO = bibDAO;
    }

    public CopiaDAO getCopiaDAO() {
        return copiaDAO;
    }

    public void setCopiaDAO(CopiaDAO copiaDAO) {
        this.copiaDAO = copiaDAO;
    }
    
    
    
    
    @Override
    public Prenotazione doRetriveById(Object... id) {
        int idPrenotazione = (int) id[0];

        Prenotazione prenotazione = null;

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setInt(1, idPrenotazione);
            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                if (rs.next()) {
                    Calendar dataCreazione = new GregorianCalendar();
                    dataCreazione.setTimeInMillis(rs.getDate("data_creazione").getTime());
                    Calendar dataScadenza = new GregorianCalendar();
                    dataScadenza.setTimeInMillis(rs.getDate("data_scadenza").getTime());
                    if(rs.getDate("data_consegna")!=null){
                    Calendar dataConsegna = new GregorianCalendar();
                    dataConsegna.setTimeInMillis(rs.getDate("data_consegna").getTime());
                    prenotazione = new Prenotazione(dataCreazione, dataScadenza, dataConsegna, rs.getString("status"));
                    }
                    else{
                        prenotazione = new Prenotazione(dataCreazione, dataScadenza, null, rs.getString("status"));
                    
                    }
                    prenotazione.setPersona(persDAO.doRetriveById(rs.getInt("id_persona")));
                    prenotazione.setBiblioteca(bibDAO.doRetriveById(rs.getString("isil")));
                    prenotazione.setCopia(copiaDAO.doRetriveById(rs.getString("id_copia"), rs.getString("isbn")));
                    System.out.println(prenotazione.getId() + " " + prenotazione.getDataCreazione());
                }

                rs.close();
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
                return prenotazione;

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
    public List<Prenotazione> doRetriveAll() {
        Prenotazione prenotazione;
        ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();
        try {
            Connection con = null;
            con = (Connection) DriverManagerConnectionPool.getConnection();
            PreparedStatement stt = con.prepareStatement(doRetriveAllQuery);
            try {
                ResultSet rs = stt.executeQuery();

                while (rs.next()) {
                    Calendar dataCreazione = new GregorianCalendar();
                    dataCreazione.setTimeInMillis(rs.getDate("data_creazione").getTime());
                    Calendar dataScadenza = new GregorianCalendar();
                    dataScadenza.setTimeInMillis(rs.getDate("data_scadenza").getTime());
                    if(rs.getDate("data_consegna")!=null){
                    Calendar dataConsegna = new GregorianCalendar();
                    dataConsegna.setTimeInMillis(rs.getDate("data_consegna").getTime());
                    prenotazione = new Prenotazione(dataCreazione, dataScadenza, dataConsegna, rs.getString("status"));
                    }
                    else{
                        prenotazione = new Prenotazione(dataCreazione, dataScadenza, null, rs.getString("status"));
                    
                    }
                    prenotazione.setPersona(persDAO.doRetriveById(rs.getInt("id_persona")));
                    prenotazione.setBiblioteca(bibDAO.doRetriveById(rs.getString("isil")));
                    prenotazione.setCopia(copiaDAO.doRetriveById(rs.getString("id_copia"), rs.getString("isbn"),rs.getString("isil")));
                    listaPrenotazioni.add(prenotazione);
                }

                con.commit();
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
                return listaPrenotazioni;

            } catch (SQLException e) {
                con.rollback();
                return null;
            } finally {
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int doInsert(Prenotazione prenotazione) {

        try {
            Connection con = null;
            PreparedStatement stt = null;
            con = (Connection) DriverManagerConnectionPool.getConnection();
            stt = con.prepareStatement(doInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            stt.setDate(1, new Date(prenotazione.getDataCreazione().getTimeInMillis()));
            stt.setDate(2, new Date(prenotazione.getDataScadenza().getTimeInMillis()));
            stt.setDate(3, null);
            stt.setInt(4, prenotazione.getPersona().getId());
            stt.setString(5, prenotazione.getBiblioteca().getIsil());
            stt.setString(6, prenotazione.getStatus());
            stt.setString(7, prenotazione.getCopia().getId());
            stt.setString(8, prenotazione.getCopia().getLibro().getIsbn());

            try {
                stt.execute();
                ResultSet rs = stt.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                stt.close();
                con.commit();
                DriverManagerConnectionPool.releaseConnection(con);
                return id;

            } catch (SQLException e) {
                con.rollback();
            } finally {
                stt.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PrenotazioneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    @Override
    public int doUpdate(Prenotazione prenotazione) {

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);

            try {

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
                return 0;
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

}
