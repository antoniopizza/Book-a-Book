/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Utente;
import core.entities.Account;
import core.DAO.AccountDAO;
import core.DAO.AdminDAO;
import core.DAO.BibliotecarioDAO;
import core.DAO.PersonaDAO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manuel
 */
public class ManagerAutenticazione {
    
    
    private AccountDAO accountDAO;
    private AdminDAO adminDAO;
    private BibliotecarioDAO bibliotecarioDAO;
    private PersonaDAO personaDAO;
    
    public ManagerAutenticazione() {
        this.accountDAO = new AccountDAO();
        this.adminDAO = new AdminDAO();
        this.bibliotecarioDAO = new BibliotecarioDAO();
        this.personaDAO = new PersonaDAO();
    }
    
    public ManagerAutenticazione(AccountDAO accountDAO, BibliotecarioDAO bibliotecarioDAO, AdminDAO adminDAO, PersonaDAO personaDAO) {
        this.accountDAO = accountDAO;
        this.adminDAO = adminDAO;
        this.bibliotecarioDAO = bibliotecarioDAO;
        this.personaDAO = personaDAO;
    }
    
    /**
     * Metodo che esegue il login di un utente all'interno del sistema.
     * @param email (String) indirizzo email dell'utente che vuole loggarsi,
     * @param password (String) password corrispondente all'email dell'utente che vuole loggarsi.
     * @return oggetto Utente corrispondente a chi si è loggato, null altrimenti.
     */
    public Utente login(String email,String password) {
        
        Account account = accountDAO.doRetriveById(email);
        
        if(account!=null) { //l'account è esistente
            
            if(password.equals(account.getPassword())) { //la password corrisponde
                
                Utente utente;
                switch(account.getTipo()) { //in base al tipo di utente, restituisce un tipo di Utente.
                    case Utente.TIPO_ADMIN:
                        utente = adminDAO.doRetriveByEmail(email);
                        break;
                        
                    case Utente.TIPO_BIBLIOTECARIO:
                        utente = bibliotecarioDAO.doRetriveByEmail(email);                        
                        break;
                    
                    case Utente.TIPO_PERSONA:
                        utente = personaDAO.doRetriveByEmail(email);
                        break;
                        
                    default:
                        utente = null;
                        break;
                }
                return utente;
            }
        }
        
        return null;
    }
    
    
    /**
     * Metodo che esegue un logout dell'utente collegato dal sistema.
     * @param session (HttpSession) la sessione nella quale l'utente è loggato
     * @return true se il logout è avvenuto con successo, false altrimenti
     */
    public boolean logout(HttpSession session){
        try {
            session.invalidate();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
