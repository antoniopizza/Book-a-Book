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
import core.utils.Criterio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerUtenti {

    public Collection<Account> visualizzaRegistrati(Criterio cr) {
        AccountDAO accountDAO = new AccountDAO();
        List<Account> listaUtenti = new ArrayList<>();
        Collection<Account> lista = new ArrayList<Account>();
        listaUtenti = accountDAO.doRetrivePersoneAndBibliotecari();

        for (int i = 0; i <= listaUtenti.size(); i++) {
            if (cr.isValid(listaUtenti.get(i))) {
                lista.add(listaUtenti.get(i));
            }
        }
        
        return lista;
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

            if (bibliotecarioDAO.doDelete(bibliotecario) == -1 && accountDAO.doDelete(email) == -1) {
                return false;
            }
        }

        return true;
    }

}
