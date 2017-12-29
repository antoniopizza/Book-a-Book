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
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
import core.entities.Persona;
import java.util.Calendar;

/**
 *
 * @author manuel
 */
public class ManagerAccount {
    
    public boolean modificaPassword(String email,String newPsw){
        
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.doRetriveById(email);
        account.setPassword(newPsw);
        if(accountDAO.doUpdate(account) == 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
    
    public Bibliotecario modificaDatiPersonali
        (String vecchiaMail,String email,String provincia,String cap,String via,String numeroCivico,String citta){
         BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
         Bibliotecario bibliotecario = bibliotecarioDAO.doRetriveByEmail(vecchiaMail);
         
         bibliotecario.getBiblioteca().setIndirizzo(new Indirizzo(via, citta, numeroCivico, provincia, cap));
         bibliotecario.getAccount().setEmail(email);
         
         
         bibliotecarioDAO.doUpdate(bibliotecario);
         
         return bibliotecario;
    }

        
    public Persona modificaDatiPersonali
        (String vecchiaMail,String email, String nome,String cognome,String codiceFiscale,Calendar dataNascita,String provincia,String cap,String via,String numeroCivico,String citta,String recapitoTelefonico,String sesso){
            PersonaDAO personaDAO = new PersonaDAO();
            Persona persona = personaDAO.doRetriveByEmail(vecchiaMail);
            
            persona.setCognome(cognome);
            persona.setNome(nome);
            persona.setNumDocumento(codiceFiscale);
            persona.setIndirizzo(new Indirizzo(via, citta, numeroCivico, provincia, cap));
            persona.getAccount().setEmail(email);
            
            personaDAO.doUpdate(persona);
            return persona;
        }
        
        
    public Admin modificaDatiPersonali(String email,String nome,String cognome){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Biblioteca modificaDatiBiblioteca(String nome,String via,String citta,String numeroCivico){
        
        throw new UnsupportedOperationException("Not implemented yet");
        
    }

    
    public boolean recuperaPassword(String email){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    
    boolean richiestaRimozioneAccount(String isil){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    boolean rimozioneAccountUtente(String email){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
