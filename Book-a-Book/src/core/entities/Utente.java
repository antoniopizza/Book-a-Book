/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

/**
 *
 * @author mirko
 */
public class Utente {
    
    protected int id;
    protected String nome;
    protected String cognome;
    protected Account account;

    public Utente() {
        
    }

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Utente(String nome, String cognome, Account account) {
        this.nome = nome;
        this.cognome = cognome;
        this.account = account;
    }

    public Utente(int id, String nome, String cognome, Account account) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.account = account;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
        
}
