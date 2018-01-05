/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import core.entities.Utente;

/**
 *
 * @author mirko
 */
public class RegistratiPerTipo implements Criterio{

    private String tipo;

    public RegistratiPerTipo() {
        
    }

    public RegistratiPerTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Utente user = (Utente) ob;
        
        if(user.getAccount().getTipo().equals(tipo)){
            return true;
        }
        
        return false;
    }
    
}
