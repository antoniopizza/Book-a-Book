/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.Entities;

/**
 *
 * @author mirko
 */
public class Admin extends Utente{

    public Admin() {
        
    }

    public Admin(String nome, String cognome) {
        super(nome, cognome);
    }

    public Admin(String nome, String cognome, Account account) {
        super(nome, cognome, account);
    }
        
}
