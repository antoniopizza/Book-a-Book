/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

import java.util.List;

/**
 *
 * @author mirko
 */
public class Autore {
    
    protected int id;
    protected String nome;
    protected List<Libro> libri;

    public Autore() {
        
    }

    public Autore(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Autore{" + "id=" + id + ", nome=" + nome + ", libri=" + libri + '}';
    }
    
}
