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
public class Telefono {
    
    protected String prefisso;
    protected String numero;
    protected Persona persona;
    protected Biblioteca biblioteca;

    public Telefono() {
        
    }

    public Telefono(String prefisso, String numero) {
        this.prefisso = prefisso;
        this.numero = numero;
    }

    public Telefono(String prefisso, String numero, Persona persona, Biblioteca biblioteca) {
        this.prefisso = prefisso;
        this.numero = numero;
        this.persona = persona;
        this.biblioteca = biblioteca;
    }

    public String getPrefisso() {
        return prefisso;
    }

    public void setPrefisso(String prefisso) {
        this.prefisso = prefisso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    @Override
    public String toString() {
        return "Telefono{" + "prefisso=" + prefisso + ", numero=" + numero + ", persona=" + persona + ", biblioteca=" + biblioteca + '}';
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
        final Telefono other = (Telefono) obj;
        if (!Objects.equals(this.prefisso, other.prefisso)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        
        return true;
    }
    
    
    
}
