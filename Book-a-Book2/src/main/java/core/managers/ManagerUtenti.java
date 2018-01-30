/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.DAO.AccountDAO;
import core.DAO.BibliotecaDAO;
import core.DAO.BibliotecarioDAO;
import core.DAO.PersonaDAO;
import core.entities.Account;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Persona;
import core.entities.Utente;
import core.utils.Criterio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerUtenti {

    public Collection<Utente> visualizzaRegistrati(Criterio cr) {
        PersonaDAO personaDAO = new PersonaDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();

        Collection<Utente> listaUtenti = new ArrayList<Utente>();
        List<Utente> lista = new ArrayList<>();
        List<Persona> listaPersone = personaDAO.doRetriveAll();
        List<Bibliotecario> listaBibliotecari = bibliotecarioDAO.doRetriveAll();

        lista.addAll(listaPersone);
        lista.addAll(listaBibliotecari);

        for (int i = 0; i <= lista.size(); i++) {
            if (cr.isValid(lista.get(i))) {
                listaUtenti.add(lista.get(i));
            }
        }

        return listaUtenti;
    }

    public boolean eliminaRegistrati(String email) {

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.doRetriveById(email);

        if (account.getTipo().equals("Persona")) {
            PersonaDAO personaDAO = new PersonaDAO();
            Persona persona = personaDAO.doRetriveByEmail(email);
            persona.getAccount().setEmail(null);

            if (personaDAO.doUpdate(persona) == -1 && accountDAO.doDelete(email) == -1) {
                return false;
            }
        } else if (account.getTipo().equals("Bibliotecario")) {
            BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
            Bibliotecario bibliotecario = bibliotecarioDAO.doRetriveByEmail(email);

            if (bibliotecarioDAO.doDeleteBibliotecario(bibliotecario) == -1 && accountDAO.doDelete(email) == -1) {
                return false;
            }
        }

        return true;
    }

    public Collection<Bibliotecario> visualizzaDipendenti(Criterio cr, String isil) {
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        Collection<Bibliotecario> listaDipendenti = new ArrayList<Bibliotecario>();
        List<Bibliotecario> lista = new ArrayList<Bibliotecario>();
        lista = bibliotecarioDAO.doRetriveDipendentiByIsil(isil);

        for (int i = 0; i <= lista.size(); i++) {
            if (cr.isValid(lista.get(i))) {
                lista.add(lista.get(i));
            }
        }

        return listaDipendenti;
    }

    
    public Collection<Biblioteca> visualizzaBiblioteche(){
        
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
        return bibliotecaDAO.doRetriveAll();        
    }
}
