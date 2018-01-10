/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.AccountDAO;
import core.DAO.BibliotecaDAO;
import core.DAO.BibliotecarioDAO;
import core.DAO.IndirizzoDAO;
import core.DAO.PersonaDAO;
import core.DAO.TelefonoDAO;
import core.entities.Account;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Telefono;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerRegistrazione {
    
    public Persona registra
        (String nome,String cognome,String email,String numeroDocumento,String via,String citta,String numeroCivico,String password,String pathFoto, String provincia,String CAP,String numeroTelefono){
        PersonaDAO personaDAO = new PersonaDAO();
        AccountDAO accountDAO = new AccountDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        TelefonoDAO telefonoDAO = new TelefonoDAO();
        
        
        Account account = new Account(email, password, pathFoto, "Persona");
        Indirizzo indirizzo = new Indirizzo(via, citta, numeroCivico, provincia, CAP);
        Persona persona = new Persona(numeroDocumento, indirizzo, nome, cognome, account);
        
        Telefono telefono = new Telefono("+39", numeroTelefono, persona, null);
        
        indirizzoDAO.doInsert(indirizzo);
        accountDAO.doInsert(account);
        telefono.getPersona().setId(personaDAO.doInsert(persona));
        telefonoDAO.doInsert(telefono);
        
        return persona;
            
    }
    
        
    public Biblioteca registra(String isil,String nomeBiblioteca,String nomeBibliotecario,String via,String citta,String numeroCivico,String provincia, String CAP,String email,String password,String pathFoto,String tipo,String cognome,String numeroTelefono){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        AccountDAO accountDAO = new AccountDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        TelefonoDAO telefonoDAO = new TelefonoDAO();
                
        Indirizzo indirizzo = new Indirizzo(via, citta, numeroCivico, provincia, CAP);
        Account account = new Account(email, password, pathFoto, "Bibliotecario");
        Biblioteca biblioteca = new Biblioteca(isil, nomeBiblioteca, "In Sospeso", indirizzo, null);
        Bibliotecario bibliotecario = new Bibliotecario("In Sospeso","Responsabile",biblioteca,nomeBibliotecario,cognome,account);
        Telefono telefono = new Telefono("+39", numeroTelefono, null, biblioteca);
        //System.out.println(""+ account.toString());
        indirizzoDAO.doInsert(indirizzo);
        
        accountDAO.doInsert(account);
        bibliotecaDAO.doInsert(biblioteca);
        bibliotecarioDAO.doInsert(bibliotecario);
        telefonoDAO.doInsert(telefono);
        
        return biblioteca;
    }
    
    
    public Bibliotecario registraDipendente(String isil,String nome,String cognome,String email,String password,String path_foto){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        AccountDAO accountDAO = new AccountDAO();
        
        Account account = new Account(email, password,path_foto ,"Bibliotecario");
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil);
        Bibliotecario bibliotecario = new Bibliotecario("Accettato", "Dipendente", biblioteca,nome, cognome, account);
        
        
        accountDAO.doInsert(account);
        bibliotecarioDAO.doInsert(bibliotecario);
        
        return bibliotecario;
    }

    public List<Biblioteca> visualizzaRichieste(){
         BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
         List<Biblioteca> listBibliotecaTotale = bibliotecaDAO.doRetriveAll();
         ArrayList<Biblioteca> listAttesa = new ArrayList<Biblioteca>();
         for(Biblioteca b: listBibliotecaTotale) {
             if(b.getStatus().equalsIgnoreCase("In Sospeso")) {
                 listAttesa.add(b);
             }
         }
         return listAttesa;
         
         
    }
    
    
    public boolean modificaStatoBiblioteca(String idBiblioteca,String change){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(idBiblioteca);
        
        biblioteca.setStatus(change);
        if(bibliotecaDAO.doInsert(biblioteca)>=0) {
            return true;
        } else {
            return false;
        }
 }
    
}
