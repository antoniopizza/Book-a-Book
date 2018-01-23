/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author mirko
 */
public class Prenotazione {
    
    public static final String DA_RITIRARE ="Da ritirare";
    public static final String RITIRATO ="Ritirato";
    public static final String RESTITUITO ="Restituito";
    public static final String ANNULLATA = "Annullata";
    public static final String OLTRE_SCADENZA="Oltre scadenza";
    
    protected int id;
    protected Calendar dataCreazione;
    protected Calendar dataScadenza;
    protected Calendar dataConsegna;
    protected Persona persona;
    protected String status;
    protected Biblioteca biblioteca;
    protected Copia copia;

    public Prenotazione() {
        
    }

    public Prenotazione(Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna, String status) {
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
        this.status = status;
    }

    public Prenotazione(Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna, Persona persona, String status, Biblioteca biblioteca, Copia copia) {
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
        this.persona = persona;
        this.status = status;
        this.biblioteca = biblioteca;
        this.copia = copia;
    }

    public Prenotazione(int id, Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna, String status) {
        this.id = id;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
        this.status = status;
    }
    
    public Prenotazione(int id, Calendar dataCreazione, Calendar dataScadenza, Calendar dataConsegna, Persona persona, String status, Biblioteca biblioteca, Copia copia) {
        this.id = id;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.dataConsegna = dataConsegna;
        this.persona = persona;
        this.status = status;
        this.biblioteca = biblioteca;
        this.copia = copia;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Copia getCopia() {
        return copia;
    }

    public void setCopia(Copia copia) {
        this.copia = copia;
    }

    @Override
    public String toString() {
        return "Prenotazione{" + "id=" + id + ", dataCreazione=" + dataCreazione + ", dataScadenza=" + dataScadenza + ", dataConsegna=" + dataConsegna + ", persona=" + persona + ", status=" + status + ", biblioteca=" + biblioteca + ", copia=" + copia + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prenotazione other = (Prenotazione) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.dataCreazione, other.dataCreazione)) {
            return false;
        }
        if (!Objects.equals(this.dataScadenza, other.dataScadenza)) {
            return false;
        }
        if (!Objects.equals(this.dataConsegna, other.dataConsegna)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca, other.biblioteca)) {
            return false;
        }
        if (!Objects.equals(this.copia, other.copia)) {
            return false;
        }
        return true;
    }
    
}
