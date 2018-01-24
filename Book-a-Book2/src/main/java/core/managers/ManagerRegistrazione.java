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
import core.DAO.IndirizzoDAO;
import core.DAO.PersonaDAO;
import core.DAO.TelefonoDAO;
import core.entities.Account;
import core.entities.Admin;
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
    
    public int checkEmail(String email) {
        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.doRetriveAll();
        for(Account a:accounts) {
            if(a.getEmail().equals(email)) {
                return 0;
            } else {
                return 1;
            }
        }
        return 1;
    }
    
    public Persona registra
       (String nome,String cognome,String email,String numeroDocumento,String via,String citta,String numeroCivico,String password,String pathFoto, String provincia,String CAP,String numeroTelefono){
        PersonaDAO personaDAO = new PersonaDAO();
        AccountDAO accountDAO = new AccountDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        TelefonoDAO telefonoDAO = new TelefonoDAO();
        
        
        Account account = new Account(email, password, pathFoto, "Persona");
        Indirizzo indirizzo = new Indirizzo(via, citta, numeroCivico, provincia, CAP);
        Persona persona = new Persona(numeroDocumento, indirizzo, nome, cognome, account);
        persona.setIndirizzo(indirizzo);
        
        Telefono telefono = new Telefono("+39", numeroTelefono, null, null);
        
        indirizzoDAO.doInsert(indirizzo);
        accountDAO.doInsert(account);
        persona.setId(personaDAO.doInsert(persona));
        telefono.setPersona(persona);        
        telefonoDAO.doInsert(telefono);
        
        return persona;
            
    }
    
        
    public Biblioteca registra(String isil,String nomeBiblioteca,String via,String citta,String numeroCivico,String provincia, String CAP,String numeroTelefono){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        TelefonoDAO telefonoDAO = new TelefonoDAO();
                
        Indirizzo indirizzo = new Indirizzo(via, citta, numeroCivico, provincia, CAP);
        Biblioteca biblioteca = new Biblioteca(isil, nomeBiblioteca, "In Sospeso", indirizzo, null);
        Telefono telefono = new Telefono("+39", numeroTelefono, null, biblioteca);
        //System.out.println(""+ account.toString());
        indirizzoDAO.doInsert(indirizzo);        
        bibliotecaDAO.doInsert(biblioteca);
        telefonoDAO.doInsert(telefono);
        
        return biblioteca;
    }
    
    
    public Bibliotecario registraDipendente(String isil,String nome,String cognome,String email,String password,String path_foto,String tipo){
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        AccountDAO accountDAO = new AccountDAO();
        
        Account account = new Account(email, password,path_foto ,"Bibliotecario");
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil);
        Bibliotecario bibliotecario = new Bibliotecario(biblioteca.getStatus(), tipo, biblioteca,nome, cognome, account);
        
        
        accountDAO.doInsert(account);
        
          bibliotecario.setId(bibliotecarioDAO.doInsert(bibliotecario));
        
        
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
    
      public List<Biblioteca> visualizzaRichiesteRimozione(){
         BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
         List<Biblioteca> listBibliotecaTotale = bibliotecaDAO.doRetriveAll();
         ArrayList<Biblioteca> listAttesa = new ArrayList<Biblioteca>();
         for(Biblioteca b: listBibliotecaTotale) {
             if(b.getStatus().equalsIgnoreCase("Rimuovere")) {
                 listAttesa.add(b);
             }
         }
         return listAttesa;
         
         
    }
    
    
    public boolean modificaStatoBiblioteca(String idBiblioteca,String change,int id_admin){
        AdminDAO adminDAO = new AdminDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        List<Bibliotecario> bibliotecari = bibliotecarioDAO.doRetriveAll();
       
        Admin admin = adminDAO.doRetriveById(id_admin);
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(idBiblioteca);
        
        for(Bibliotecario b: bibliotecari){
            if((b.getBiblioteca().getIsil()).equals(biblioteca.getIsil())){
                
               
                b.setStatus(change);
                b.getBiblioteca().setStatus(change);
                b.getBiblioteca().setAdmin(admin);
                
                
                bibliotecarioDAO.doUpdate(b);
            }
        }
        
         
        biblioteca.setAdmin(admin);
        biblioteca.setStatus(change);
        if(bibliotecaDAO.doUpdate(biblioteca)>=0) {
            
             
            return true;
        } else {
            return false;
        }
 }
    
    public Telefono recuperaTelefonoPersona(Persona persona) {
        TelefonoDAO telefonoDAO = new TelefonoDAO();
        return telefonoDAO.doRetriveByPersona(persona);
    }
    
    public Telefono recuperaTelefonoBiblioteca(Biblioteca biblioteca) {
        TelefonoDAO telefonoDAO = new TelefonoDAO();
        return telefonoDAO.doRetriveByBiblioteca(biblioteca);
    }
    
    
}
