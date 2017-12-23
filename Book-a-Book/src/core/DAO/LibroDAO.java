/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Libro;
import core.utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirko
 */
public class LibroDAO extends AbstractDAO<Libro>{
    
    
    private final String doRetriveByIdQuery = "SELECT * FROM Libro WHERE isbn = ?";
    
    /**
     * Metodo che dato una stringa corrispondente all'isbn 
     * restituisce un oggetto libro completo
     * @param id[0] si aspetta un isbn
     * @return un libro in base all'id
     */
    
    @Override
    public Libro doRetriveById(Object... id) {
        String isbn = (String) id[0];
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, isbn);
            
            try{
                ResultSet rs = prst.executeQuery();                               
                con.commit();               
                GregorianCalendar dataPubblicazione = new GregorianCalendar();
                dataPubblicazione.setTimeInMillis(rs.getDate("data_pubblicazione").getTime());
                Libro book = new Libro(rs.getString("isbn"),rs.getString("titolo"),rs.getString("editore"),dataPubblicazione,rs.getString("descrizione"),rs.getBoolean("disponibilita"));
                book.setAutori(new AutoreDAO().doRetriveByLibro(isbn));
                return book;
                
            } catch(SQLException e){
                con.rollback();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Libro> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
