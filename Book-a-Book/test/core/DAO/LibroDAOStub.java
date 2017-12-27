/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Libro;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author kliffom
 */
public class LibroDAOStub extends LibroDAO{

    @Override
    public Libro doRetriveById(Object... id) {
        String isbn = (String) id[0];
        String titolo = "Libro";
        String editore = "Editore";
        Calendar dataPubblicazione = new GregorianCalendar(2017, 11, 27);
        String descrizione = "Descrizione libro";
        
        return new Libro(isbn, titolo, editore, dataPubblicazione, descrizione, true);
    }
    
    
    
}
