/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mirko
 */
public class Posizione {
    
    protected String etichetta;
    protected Biblioteca biblioteca;
    
    //realizza la relazione uno a molti con copia
    protected List<Copia> copie;

    public Posizione() {
        this.copie = new ArrayList<>();
    }

    public Posizione(String etichetta) {
        this.etichetta = etichetta;
        this.copie = new ArrayList<>();
    }

    public Posizione(String etichetta, Biblioteca biblioteca, List<Copia> copie) {
        this.etichetta = etichetta;
        this.biblioteca = biblioteca;
        this.copie = copie;
    }

    public Posizione(String a5, Biblioteca bib) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Copia> getCopie() {
        return copie;
    }

//    public void setCopie(List<Copia> copie) {
//        this.copie = copie;
//    }
    
    public void addCopia(Copia c){
        if(!copie.contains(c)){
            copie.add(c);
            c.setPosizione(this);
        }        
    }
    
    public void removeCopia(Copia c){
        if(copie.contains(c)){
            copie.remove(c);
            c.setPosizione(null);
        }
    }

    public String getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(String etichetta) {
        this.etichetta = etichetta;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public String toString() {
        return "Posizione{" + "etichetta=" + etichetta + ", biblioteca=" + biblioteca + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.etichetta);
        hash = 53 * hash + Objects.hashCode(this.biblioteca);
        return hash;
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
        final Posizione other = (Posizione) obj;
        if (!Objects.equals(this.etichetta, other.etichetta)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca, other.biblioteca)) {
            return false;
        }
        return true;
    }

    

    
}
