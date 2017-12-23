/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Autore;
import core.entities.Libro;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.Date;
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
public class LibroDAO extends AbstractDAO<Libro> {

    private final String doRetriveByIdQuery = "SELECT * FROM Libro WHERE isbn = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Libro";
    private final String doInsertQuery = "INSERT INTO Libro(isbn,titolo,editore,data_pubblicazione,descrizione,disponibilita,path_foto)"
            + "VALUES (?,?,?,?,?,?,?)";
    private final String doInsertAutoreQuery = "INSERT INTO Libro_Autore VALUES (?,?)";

    /**
     * Metodo che dato una stringa corrispondente all'isbn restituisce un
     * oggetto libro completo
     *
     * @param id[0] si aspetta un isbn
     * @return un libro in base all'id oppure null se c'è qualche errore oppure
     * il libro non viene trovato
     */
    @Override
    public Libro doRetriveById(Object... id) {
        String isbn = (String) id[0];

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, isbn);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Libro book = null;
                if (rs.next()) {
                    GregorianCalendar dataPubblicazione = new GregorianCalendar();
                    dataPubblicazione.setTimeInMillis(rs.getDate("data_pubblicazione").getTime());
                    book = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getString("editore"), dataPubblicazione, rs.getString("descrizione"), rs.getBoolean("disponibilita"), rs.getString("path_foto"));
                    book.setAutori(new AutoreDAO().doRetriveByLibro(isbn));
                }
                rs.close();
                return book;

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

    /**
     *
     * @return
     */
    @Override
    public List<Libro> doRetriveAll() {
        List<Libro> libri = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();

                while (rs.next()) {
                    GregorianCalendar dataPubblicazione = new GregorianCalendar();
                    dataPubblicazione.setTimeInMillis(rs.getDate("data_pubblicazione").getTime());
                    Libro book = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getString("editore"), dataPubblicazione, rs.getString("descrizione"), rs.getBoolean("disponibilita"), rs.getString("path_foto"));
                    book.setAutori(new AutoreDAO().doRetriveByLibro(book.getIsbn()));
                    libri.add(book);
                }

                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return libri;
    }

    /**
     *
     * @param libro da inserire. N.B. Gli autori sono già stati inseriti.
     * @return
     */
    @Override
    public int doInsert(Libro libro) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, libro.getIsbn());
                prst.setString(2, libro.getTitolo());
                prst.setString(3, libro.getEditore());
                prst.setDate(4, new Date(libro.getDataPubblicazione().getTimeInMillis()));
                prst.setString(5, libro.getDescrizione());
                prst.setBoolean(6, libro.isDisponibilta());
                prst.setString(7, libro.getPathFoto());

                prst.execute();

                PreparedStatement prst2 = con.prepareStatement(doInsertAutoreQuery);

                for (Autore a : libro.getAutori()) {
                    prst2.setInt(1, a.getId());
                    prst2.setString(2, libro.getIsbn());
                    prst2.execute();
                }

                prst.close();
                prst.close();
                return 0;
                
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }

    }

    @Override
    public int doUpdate(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
