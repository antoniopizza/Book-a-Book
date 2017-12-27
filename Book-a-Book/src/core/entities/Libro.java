/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mirko
 */
public class Libro {
    
    protected String isbn;
    protected String titolo;
    protected String editore;
    protected Calendar dataPubblicazione;
    protected String descrizione;
    protected boolean disponibilta;
    protected List<Autore> autori;
    protected String pathFoto;

    public Libro() {
        
    }

    public Libro(String isbn, String titolo, String editore, Calendar dataPubblicazione, String descrizione, boolean disponibilta,String pathFoto) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.editore = editore;
        this.dataPubblicazione = dataPubblicazione;
        this.descrizione = descrizione;
        this.disponibilta = disponibilta;
        this.pathFoto = pathFoto;
    }

    public Libro(String isbn, String titolo, String editore, Calendar dataPubblicazione, String descrizione, boolean disponibilta, List<Autore> autori,String pathFoto) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.editore = editore;
        this.dataPubblicazione = dataPubblicazione;
        this.descrizione = descrizione;
        this.disponibilta = disponibilta;
        this.autori = autori;
        this.pathFoto = pathFoto;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public Calendar getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Calendar dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isDisponibilta() {
        return disponibilta;
    }

    public void setDisponibilta(boolean disponibilta) {
        this.disponibilta = disponibilta;
    }

    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titolo=" + titolo + ", editore=" + editore + ", dataPubblicazione=" + dataPubblicazione + ", descrizione=" + descrizione + ", disponibilta=" + disponibilta + ", autori=" + autori + ", pathFoto=" + pathFoto + '}';
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
        final Libro other = (Libro) obj;
        if (this.disponibilta != other.disponibilta) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.titolo, other.titolo)) {
            return false;
        }
        if (!Objects.equals(this.editore, other.editore)) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.pathFoto, other.pathFoto)) {
            return false;
        }
        if (!Objects.equals(this.dataPubblicazione, other.dataPubblicazione)) {
            return false;
        }
        if (!Objects.equals(this.autori, other.autori)) {
            return false;
        }
        return true;
    }

    
            
}