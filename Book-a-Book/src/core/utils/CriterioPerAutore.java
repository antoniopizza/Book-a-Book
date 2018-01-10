/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import core.entities.Autore;
import core.entities.Libro;

/**
 *
 * @author manuel
 */
public class CriterioPerAutore implements Criterio{

    private String autore;

    public CriterioPerAutore(String autore) {
        this.autore = autore;
    }
    
    
    
    @Override
    public boolean isValid(Object ob) {
        Libro book = (Libro) ob;
        for(Autore a : book.getAutori()){
            if(a.getNome().equals(autore)){
                return true;
            }
        }
        
        return false;
    }
    
}
