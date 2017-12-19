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
public class Bibliotecario extends Utente{
    
    protected String status;
    protected String tipo;
    protected Biblioteca biblioteca;

    public Bibliotecario() {
        
    }

    public Bibliotecario(String status, String tipo, String nome, String cognome) {
        super(nome, cognome);
        this.status = status;
        this.tipo = tipo;
    }

    public Bibliotecario(String status, String tipo, Biblioteca biblioteca, String nome, String cognome, Account account) {
        super(nome, cognome, account);
        this.status = status;
        this.tipo = tipo;
        this.biblioteca = biblioteca;
    }

    public Bibliotecario(String status, String tipo, Biblioteca biblioteca, int id, String nome, String cognome, Account account) {
        super(id, nome, cognome, account);
        this.status = status;
        this.tipo = tipo;
        this.biblioteca = biblioteca;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

}
