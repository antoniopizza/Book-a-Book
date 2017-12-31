/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import java.util.GregorianCalendar;

/**
 *
 * @author Mery
 */
public class CopiaDAOStub extends CopiaDAO{
    
    public Copia doRetriveById(Object... id){
       
       String id_copia = (String) id[0];
       String status = "Prenotato";
       String disponibilita = "Disponibile";
       Biblioteca bib = new Biblioteca();
       bib.setIsil("1234567890");
       Libro libro = new Libro("567890","titolo","editore",new GregorianCalendar(2017,12,01),"qwerty","path");
       Posizione pos = new Posizione("A5",bib);
       
       
        return new Copia(id_copia,status,disponibilita,pos,libro);
    }
}
