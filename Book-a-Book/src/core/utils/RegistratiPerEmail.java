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
public class RegistratiPerEmail implements Criterio{
    
    private String email;

    public RegistratiPerEmail() {
        
    }

    public RegistratiPerEmail(String email) {
        this.email = email;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Utente user = (Utente) ob;
        
        if(user.getAccount().getEmail().equals(email)){
            return true;
        }
        
        return false;
    }
    
}
