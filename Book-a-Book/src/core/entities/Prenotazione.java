/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.Calendar;

/**
 *
 * @author mirko
 */
public class Prenotazione {
    
    protected int id;
    protected Calendar dataCreazione;
    protected Calendar dataScadenza;
    protected Calendar dataConsegna;
    protected Persona persona;
    protected Biblioteca biblioteca;
    protected Libro libro;

    public Prenotazione() {
        
    }

    public Prenotazione(Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna) {
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
    }

    public Prenotazione(Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna, Persona persona, Biblioteca biblioteca, Libro libro) {
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
        this.persona = persona;
        this.biblioteca = biblioteca;
        this.libro = libro;
    }

    public Prenotazione(int id, Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna, Persona persona, Biblioteca biblioteca, Libro libro) {
        this.id = id;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
        this.persona = persona;
        this.biblioteca = biblioteca;
        this.libro = libro;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Calendar dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Calendar getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Calendar dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Calendar getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Calendar dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Prenotazione{" + "id=" + id + ", dataCreazione=" + dataCreazione + ", dataScadenza=" + dataScadenza + ", dataConsegna=" + dataConsegna + ", persona=" + persona + ", biblioteca=" + biblioteca + ", libro=" + libro + '}';
    }
        
}
