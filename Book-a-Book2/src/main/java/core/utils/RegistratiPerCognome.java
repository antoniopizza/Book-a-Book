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
public class RegistratiPerCognome implements Criterio{

    private String cognome;

    public RegistratiPerCognome() {
        
    }

    public RegistratiPerCognome(String cognome) {
        this.cognome = cognome;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Utente user = (Utente) ob;
        
        if(user.getCognome().equals(cognome)){
            return true;
        }
        
        return false;
    }
    
}
