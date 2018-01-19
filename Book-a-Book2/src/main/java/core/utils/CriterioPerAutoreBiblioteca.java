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
public class CriterioPerAutoreBiblioteca extends CriterioPerAutore {

    private Biblioteca biblioteca;

    public CriterioPerAutoreBiblioteca(String autore, Biblioteca biblioteca) {
        super(autore);
        this.biblioteca = biblioteca;
    }

    @Override
    public boolean isValid(Object ob) {
        Libro book = (Libro) ob;
        if (super.isValid(ob)) {
            PosizioneDAO posizioneDAO = new PosizioneDAO();
            posizioneDAO.setBibliotecaDAO(new BibliotecaDAO());
            return !posizioneDAO.doRetriveByLibroAndBiblioteca(book.getIsbn(), biblioteca.getIsil()).isEmpty();
        } else {
            return false;
        }
    }

}