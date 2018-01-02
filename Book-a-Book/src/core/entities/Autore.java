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
public class Autore {
    
    protected int id;
    protected String nome;
    protected List<Libro> libri;

    public Autore() {
        this.libri = new ArrayList<>();
    }

    public Autore(String nome) {
        this.nome = nome;
        this.libri = new ArrayList<>();
    }

    public Autore(String nome, List<Libro> libri) {
        this.nome = nome;
        this.libri = libri;
    }

    public Autore(int id, String nome, List<Libro> libri) {
        this.id = id;
        this.nome = nome;
        this.libri = libri;
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

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }
    
    public void addLibro(Libro b){
        if(!libri.contains(b)){
            libri.add(b);
            b.addAutore(this);
        }
    }
    
    public void removeLibro(Libro b){
        if(libri.contains(b)){
            libri.remove(b);
            b.removeAutore(this);
        }
    }

    @Override
    public String toString() {
        return "Autore{" + "id=" + id + ", nome=" + nome + ", libri=" + libri + '}';
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
        final Autore other = (Autore) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.libri, other.libri)) {
            return false;
        }
        return true;
    }
    
    
    
}
