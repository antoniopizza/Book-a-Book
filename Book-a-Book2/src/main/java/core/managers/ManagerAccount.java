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
import core.DAO.CopiaDAO;
import core.DAO.IndirizzoDAO;
import core.DAO.LibroDAO;
import core.DAO.PersonaDAO;
import core.DAO.PosizioneDAO;
import core.DAO.PrenotazioneDAO;
import core.DAO.TelefonoDAO;
import core.entities.Account;
import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Copia;
import core.entities.Indirizzo;
import core.entities.Persona;
import core.entities.Posizione;
import core.entities.Prenotazione;
import core.entities.Telefono;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerAccount {

    public boolean modificaPassword(String email, String newPsw) {

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.doRetriveById(email);
        account.setPassword(newPsw);
        if (accountDAO.doUpdate(account) == 0) {
            return true;
        } else {
            return false;
        }

    }

    public Bibliotecario modificaDatiPersonali(String vecchiaMail, String email, String path_foto) {
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        Bibliotecario bibliotecario = bibliotecarioDAO.doRetriveByEmail(vecchiaMail);
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account(email, bibliotecario.getAccount().getPassword(), path_foto, "Bibliotecario");

        accountDAO.doUpdateEmail(vecchiaMail, email);
        accountDAO.doUpdate(account);

        bibliotecario.getAccount().setEmail(email);
        bibliotecario.getAccount().setPathFoto(path_foto);
        bibliotecarioDAO.doUpdate(bibliotecario);

        return bibliotecario;
    }

    public Persona modificaDatiPersonali(String vecchiaMail, String email, String nome, String cognome, String numDocumento, String provincia, String cap, String via, String numeroCivico, String citta, String recapitoTelefonico, String path_foto) {
        PersonaDAO personaDAO = new PersonaDAO();
        Persona persona = personaDAO.doRetriveByEmail(vecchiaMail);
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        Indirizzo indirizzo = null;
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account(email, persona.getAccount().getPassword(), path_foto, "Persona");

        
        
        if ((indirizzo = indirizzoDAO.doRetriveById(via, citta, numeroCivico)) == null) {
            
            indirizzoDAO.doUpdateIndirizzo(persona.getIndirizzo(),new Indirizzo(via, citta, numeroCivico, provincia, cap));
           
        }

        accountDAO.doUpdateEmail(vecchiaMail, email);
        accountDAO.doUpdate(account);

        TelefonoDAO telefonoDAO = new TelefonoDAO();
        Telefono telefono = telefonoDAO.doRetriveByPersona(persona);
        Telefono telefonoNuovo = telefonoDAO.doRetriveByPersona(persona);
        telefonoNuovo.setNumero(recapitoTelefonico);
        telefonoDAO.doUpdate(telefono, telefonoNuovo);

        persona.setCognome(cognome);
        persona.setNome(nome);
        persona.setNumDocumento(numDocumento);
        persona.setIndirizzo(new Indirizzo(via, citta, numeroCivico, provincia, cap));
        persona.getAccount().setEmail(email);
        persona.getAccount().setPathFoto(path_foto);

        personaDAO.doUpdate(persona);

        return persona;
    }

    public Admin modificaDatiPersonali(String vecchiaMail, String email) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.doRetriveByEmail(vecchiaMail);
        AccountDAO accountDAO = new AccountDAO();

        
        admin.getAccount().setEmail(email);
        accountDAO.doInsert(admin.getAccount());
        accountDAO.doUpdateEmail(admin.getAccount().getEmail(), email);
        adminDAO.doUpdate(admin);
        accountDAO.doDelete(vecchiaMail);
        
        
        //System.out.println("" + admin.toString());
        return admin;
    }

    public Biblioteca modificaDatiBiblioteca(String isil, String nome, String via, String citta, String numeroCivico, String numeroTelefono, String provincia, String cap) {
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil);
        IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        Indirizzo indirizzo = null;

        if ((indirizzo = indirizzoDAO.doRetriveById(via, citta, numeroCivico)) == null) {
            indirizzoDAO.doUpdateIndirizzo(biblioteca.getIndirizzo(), new Indirizzo(via, citta, numeroCivico, provincia, cap));
        }

        TelefonoDAO telefonoDAO = new TelefonoDAO();
        Telefono telefono = telefonoDAO.doRetriveByBiblioteca(biblioteca);
        Telefono telefonoNuovo = telefonoDAO.doRetriveByBiblioteca(biblioteca);
        telefonoNuovo.setNumero(numeroTelefono);
        telefonoDAO.doUpdate(telefono, telefonoNuovo);

        biblioteca.setNome(nome);
        biblioteca.getIndirizzo().setCitta(citta);
        biblioteca.getIndirizzo().setCivico(numeroCivico);
        biblioteca.getIndirizzo().setVia(via);

        bibliotecaDAO.doUpdate(biblioteca);
        return biblioteca;
    }

    public boolean recuperaPassword(String email, String nuovaPassword) {
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.doRetriveById(email);

        account.setPassword(nuovaPassword);

        if (accountDAO.doUpdate(account) == 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean richiestaRimozioneAccount(String isil) {

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Biblioteca biblioteca = bibliotecaDAO.doRetriveById(isil);
        AccountDAO accountDAO = new AccountDAO();
        List<Bibliotecario> bibliotecari = bibliotecarioDAO.doRetriveAll();
        PosizioneDAO posizioneDAO = new PosizioneDAO();
        posizioneDAO.setBibliotecaDAO(bibliotecaDAO);
        CopiaDAO copiaDAO = posizioneDAO.getCopiaDAO();
        List<Prenotazione> prenotazioni = prenotazioneDAO.doRetriveAll();
        if(prenotazioni != null) {
        for(Prenotazione p: prenotazioni) {
            if(p.getBiblioteca().getIsil().equals(isil)) {
                prenotazioneDAO.doDelete(p);
            }
        }
        }
        
        List<Posizione> posizioni = posizioneDAO.doRetriveAllByIsil(isil);
        List<Copia> copie = null;
        
        for(Posizione p: posizioni) {
            p.setBiblioteca(biblioteca);
         copie = copiaDAO.doRetriveByPosizione(p);
         for(Copia c: copie) {
             p.addCopia(c);
             copiaDAO.doDelete(c);
         }
         posizioneDAO.doDelete(p);
        }
        

        TelefonoDAO telefonoDAO = new TelefonoDAO();
        Telefono telefono = telefonoDAO.doRetriveByBiblioteca(bibliotecaDAO.doRetriveById(isil));
        telefonoDAO.doDelete(telefono);
        if (bibliotecarioDAO.doDelete(isil) == 0 && bibliotecaDAO.doDelete(isil) == 0) {
            for (Bibliotecario b : bibliotecari) {
                if (b.getBiblioteca().getIsil().equals(isil)) {
                    accountDAO.doDelete(b.getAccount().getEmail());
                }
            }

            //IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
            //Indirizzo indirizzo = indirizzoDAO.doRetriveById(biblioteca.getIndirizzo().getVia(), biblioteca.getIndirizzo().getCitta(), biblioteca.getIndirizzo().getCivico());
            //indirizzoDAO.doDelete(indirizzo);

            return true;
        } else {
            return false;
        }

    }

    public boolean rimozioneAccountUtente(String email) {
        TelefonoDAO telefonoDAO = new TelefonoDAO();
        PersonaDAO personaDAO = new PersonaDAO();
        AccountDAO accountDAO = new AccountDAO();
        Persona persona = personaDAO.doRetriveByEmail(email);
        Telefono telefono = telefonoDAO.doRetriveByPersona(persona);
        telefonoDAO.doDelete(telefono);
        persona.setAccount(null);
        personaDAO.doUpdate(persona);

        //IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
        //Indirizzo indirizzo = indirizzoDAO.doRetriveById(persona.getIndirizzo().getVia(), persona.getIndirizzo().getCitta(), persona.getIndirizzo().getCivico());
        //indirizzoDAO.doDelete(indirizzo);

        if (accountDAO.doDelete(email) == 0) {
            return true;
        } else {
            return false;
        }
    }

}
