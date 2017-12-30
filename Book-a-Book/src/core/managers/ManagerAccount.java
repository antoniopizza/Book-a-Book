/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.AccountDAO;
import core.DAO.AdminDAO;
import core.DAO.BibliotecaDAO;
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
        
        
    public Admin modificaDatiPersonali(String vecchiaMail,String email,String nome,String cognome){
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.doRetriveByEmail(vecchiaMail);
        
        admin.setNome(nome);
        admin.setCognome(cognome);
        admin.getAccount().setEmail(email);
        
        adminDAO.doUpdate(admin);
        return admin;
    }

    public Biblioteca modificaDatiBiblioteca(String isil,String nome,String via,String citta,String numeroCivico){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil);
        
        biblioteca.setNome(nome);
        biblioteca.getIndirizzo().setCitta(citta);
        biblioteca.getIndirizzo().setCivico(numeroCivico);
        biblioteca.getIndirizzo().setVia(via);
        
        bibliotecaDAO.doUpdate(biblioteca);
        return biblioteca;
    }

    
    public boolean recuperaPassword(String email,String nuovaPassword){
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.doRetriveById(email);
        
        account.setPassword(nuovaPassword);
        
        if(accountDAO.doUpdate(account) == 0) {
            return true;
        } else {
            return false;
        }
        

    }

    
    boolean richiestaRimozioneAccount(String isil){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        
        if(bibliotecaDAO.doDelete(isil) == 0 && bibliotecarioDAO.doDelete(isil) == 0) {
            return true;
        } else 
            return false;
        
        
    }
    
    
    
    boolean rimozioneAccountUtente(String email){
        AccountDAO accountDAO = new AccountDAO();
        if(accountDAO.doDelete(email) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
