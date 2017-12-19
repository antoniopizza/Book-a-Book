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
public class Persona extends Utente{

    protected String numDocumento;
    protected Indirizzo indirizzo;

    public Persona() {
        
    }

    public Persona(String numDocumento, String nome, String cognome) {
        super(nome, cognome);
        this.numDocumento = numDocumento;
    }

    public Persona(String numDocumento, Indirizzo indirizzo, String nome, String cognome, Account account) {
        super(nome, cognome, account);
        this.numDocumento = numDocumento;
        this.indirizzo = indirizzo;
    }

    public Persona(String numDocumento, Indirizzo indirizzo, int id, String nome, String cognome, Account account) {
        super(id, nome, cognome, account);
        this.numDocumento = numDocumento;
        this.indirizzo = indirizzo;
    }
    
    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }
        
}
