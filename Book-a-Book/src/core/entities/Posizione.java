/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author mirko
 */
public class Posizione {
    
    protected String etichetta;
    protected Biblioteca biblioteca;
    protected List<Copia> copie;

    public Posizione() {
        
    }

    public Posizione(String etichetta) {
        this.etichetta = etichetta;
    }

    public Posizione(String etichetta, Biblioteca biblioteca, List<Copia> copie) {
        this.etichetta = etichetta;
        this.biblioteca = biblioteca;
        this.copie = copie;
    }

    public List<Copia> getCopie() {
        return copie;
    }

    public void setCopie(List<Copia> copie) {
        this.copie = copie;
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
        return "Posizione{" + "etichetta=" + etichetta + ", biblioteca=" + biblioteca + ", copie=" + copie + '}';
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
        if (!Objects.equals(this.copie, other.copie)) {
            return false;
        }
        return true;
    }

    
}
