/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Utente;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manuel
 */
public class ManagerAutenticazione {
    
    public Utente login(String email,String password) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public boolean logout(HttpSession session){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
