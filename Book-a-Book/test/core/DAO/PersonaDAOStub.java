/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Account;
import core.entities.Indirizzo;
import core.entities.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mery
 */
public class PersonaDAOStub extends PersonaDAO{
    
    @Override
    public Persona doRetriveById(Object... id){
        String doc = (String) id[0];
        String numdoc = "numdoc";
        String nome= "nome";
        String cognome ="cognome";
        Account account = new Account();
        account.setEmail(null);
        account.setPassword("pass");
        account.setPathFoto("foto");
        account.setTipo("Persona");
        Indirizzo ind = new Indirizzo();
        ind.setCitta("citta");
        ind.setCivico("432");
        ind.setVia("Via");
        ind.setCap("543");
        ind.setProvincia("NA");
        
        return new Persona(numdoc,ind,nome,cognome,account);
    }
    
     @Override
    public List<Persona> doRetriveAll(){
        List<Persona> lista = new ArrayList<>();
        String numdoc = "numdoc";
        String nome= "nome";
        String cognome ="cognome";
        Account account = new Account();
        account.setEmail(null);
        account.setPassword("pass");
        account.setPathFoto("foto");
        account.setTipo("Persona");
        Indirizzo ind = new Indirizzo();
        ind.setCitta("citta");
        ind.setCivico("432");
        ind.setVia("Via");
        ind.setCap("543");
        ind.setProvincia("NA");
        Persona pers = new Persona(numdoc,ind,nome,cognome,account);
        lista.add(pers);
        return lista;
    }
    
}
