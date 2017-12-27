/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.Objects;

/**
 *
 * @author mirko
 */
public class Posizione {
    
    protected String etichetta;
    protected int numCopie;
    protected int numCopieTotali;
    protected Biblioteca biblioteca;
    protected Libro libro;

    public Posizione() {
        
    }

    public Posizione(String etichetta, int numCopie, int numCopieTotali) {
        this.etichetta = etichetta;
        this.numCopie = numCopie;
        this.numCopieTotali = numCopieTotali;
    }

    public Posizione(String etichetta, int numCopie, int numCopieTotali, Biblioteca biblioteca, Libro libro) {
        this.etichetta = etichetta;
        this.numCopie = numCopie;
        this.numCopieTotali = numCopieTotali;
        this.biblioteca = biblioteca;
        this.libro = libro;
    }

    public String getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(String etichetta) {
        this.etichetta = etichetta;
    }

    public int getNumCopie() {
        return numCopie;
    }

    public void setNumCopie(int numCopie) {
        this.numCopie = numCopie;
    }

    public int getNumCopieTotali() {
        return numCopieTotali;
    }

    public void setNumCopieTotali(int numCopieTotali) {
        this.numCopieTotali = numCopieTotali;
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
        return "Posizione{" + "etichetta=" + etichetta + ", numCopie=" + numCopie + ", numCopieTotali=" + numCopieTotali + ", biblioteca=" + biblioteca + ", libro=" + libro + '}';
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
        if (this.numCopie != other.numCopie) {
            return false;
        }
        if (this.numCopieTotali != other.numCopieTotali) {
            return false;
        }
        if (!Objects.equals(this.etichetta, other.etichetta)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca.getIsil(), other.biblioteca.getIsil())) {
            return false;
        }
        if (!Objects.equals(this.libro.getIsbn(), other.libro.getIsbn())) {
            return false;
        }
        return true;
    }
    
    
}
