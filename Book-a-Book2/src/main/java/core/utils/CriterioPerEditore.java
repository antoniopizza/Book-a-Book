/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import core.entities.Libro;

/**
 *
 * @author kliffom
 */
public class CriterioPerEditore implements Criterio{
    
    private String editore;

    public CriterioPerEditore(String editore) {
        this.editore = editore;
    }

    @Override
    public boolean isValid(Object ob) {
        Libro book = (Libro) ob;
        
        if (book.getEditore().toLowerCase().contains(editore.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
}
