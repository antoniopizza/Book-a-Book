/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.AccountDAO;
import core.DAO.BibliotecarioDAO;
import core.DAO.PersonaDAO;
import core.entities.Account;
import core.entities.Bibliotecario;
import core.entities.Persona;
import core.entities.Utente;
import core.utils.Criterio;
import java.util.Collection;

/**
 *
 * @author manuel
 */
public class ManagerUtenti {
    
    public Collection<Utente> visualizzaRegistrati(Criterio cr,String val){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public boolean eliminaRegistrati(String email){
       
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.doRetriveById(email);
        
        if(account.getTipo().equals("Persona")){
            PersonaDAO personaDAO = new PersonaDAO();
            Persona persona = personaDAO.doRetriveByEmail(email);
            persona.getAccount().setEmail(null);
            
            if(personaDAO.doUpdate(persona) == -1 && accountDAO.doDelete(email) == -1){
                return false;
            }
        } else if(account.getTipo().equals("Bibliotecario")){
            BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
            Bibliotecario bibliotecario = bibliotecarioDAO.doRetriveByEmail(email);
            
            if(bibliotecarioDAO.doDelete(bibliotecario) == -1 && accountDAO.doDelete(email) == -1){
                return false;
            }
        }
        
        return true;
    }
    
}
