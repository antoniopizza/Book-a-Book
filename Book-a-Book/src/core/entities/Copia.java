/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.Objects;

/**
 *
 * @author kliffom
 */
public class Copia {
    
    public static final String DISPONIBILE_SI = "Disponibile";
    public static final String DISPONIBILE_NO = "Non disponibile";
    public static final String STATUS_PRENOTATO = "Prenotato";
    public static final String STATUS_NON_PRENOTATO = "Non Prenotato";
    public static final String STATUS_ELIMINATO = "Eliminato";
    
    protected String id;
    protected String status;
    protected String disponibilita;
    
    //realizza la relazione molti-a-uno con posizione
    protected Posizione posizione;
    
    //realizza la relazione molti-a-uno con libro
    protected Libro libro;

    public Copia() {
    }

    public Copia(String id, String status, String disponibilita) {
        this.id = id;
        this.status = status;
        this.disponibilita = disponibilita;
    }

    public Copia(String id, String status, String disponibilita, Posizione posizione, Libro libro) {
        this.id = id;
        this.status = status;
        this.disponibilita = disponibilita;
        this.posizione = posizione;
        this.libro = libro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(String disponibilita) {
        this.disponibilita = disponibilita;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione newPosizione) {
        if(this.posizione != newPosizione){
            Posizione old = this.posizione;
            this.posizione = newPosizione;
            
            if(newPosizione != null){
              posizione.addCopia(this);
            }
            
            if(old != null){
                old.removeCopia(this);
            }
        }       
        
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Copia{" + "id=" + id + ", status=" + status + ", disponibilita=" + disponibilita + ", posizione=" + posizione + ", libro=" + libro + '}';
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
        final Copia other = (Copia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.disponibilita, other.disponibilita)) {
            return false;
        }
        if (!Objects.equals(this.posizione, other.posizione)) {
            return false;
        }
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        return true;
    }
    
    
}
