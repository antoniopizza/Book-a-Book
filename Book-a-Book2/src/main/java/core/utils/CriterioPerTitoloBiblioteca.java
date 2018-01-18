/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import core.DAO.BibliotecaDAO;
import core.DAO.PosizioneDAO;
import core.entities.Biblioteca;
import core.entities.Libro;

/**
 *
 * @author manuel
 */
public class CriterioPerTitoloBiblioteca extends CriterioPerTitolo{
    
    private Biblioteca biblio;
    
    public CriterioPerTitoloBiblioteca(String titolo,Biblioteca biblio) {        
        super(titolo);
        this.biblio = biblio;
        
    }

    @Override
    public boolean isValid(Object ob) {
        Libro book = (Libro) ob;
        if(super.isValid(ob)){
            PosizioneDAO posizioneDAO = new PosizioneDAO();
            posizioneDAO.setBibliotecaDAO(new BibliotecaDAO());
            return !posizioneDAO.doRetriveByLibroAndBiblioteca(book.getIsbn(), biblio.getIsil()).isEmpty();
        } else {
            return false;
        }
    }
       
    
    
}
