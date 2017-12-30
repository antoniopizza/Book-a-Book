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
public class Biblioteca {
    
    protected String isil;
    protected String nome;
    protected String status;
    protected Indirizzo indirizzo;
    protected Admin admin;
    protected List<Posizione> posizioni;

    public Biblioteca() {
        
    }

    public Biblioteca(String isil, String nome, String status) {
        this.isil = isil;
        this.nome = nome;
        this.status = status;
    }

    public Biblioteca(String isil, String nome, String status, Indirizzo indirizzo, Admin admin) {
        this.isil = isil;
        this.nome = nome;
        this.status = status;
        this.indirizzo = indirizzo;
        this.admin = admin;
    }

    public Biblioteca(String isil, String nome, String status, Indirizzo indirizzo, Admin admin, List<Posizione> posizioni) {
        this.isil = isil;
        this.nome = nome;
        this.status = status;
        this.indirizzo = indirizzo;
        this.admin = admin;
        this.posizioni = posizioni;
    }
    
    

    public String getIsil() {
        return isil;
    }

    public void setIsil(String isil) {
        this.isil = isil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Posizione> getPosizioni() {
        return posizioni;
    }

    public void setPosizioni(List<Posizione> posizioni) {
        this.posizioni = posizioni;
    }

    @Override
    public String toString() {
        return "Biblioteca{" + "isil=" + isil + ", nome=" + nome + ", status=" + status + ", indirizzo=" + indirizzo + ", admin=" + admin + ", posizioni=" + posizioni + '}';
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
        final Biblioteca other = (Biblioteca) obj;
        if (!Objects.equals(this.isil, other.isil)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.indirizzo, other.indirizzo)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        if (!Objects.equals(this.posizioni, other.posizioni)) {
            return false;
        }
        return true;
    }

    
    
}
