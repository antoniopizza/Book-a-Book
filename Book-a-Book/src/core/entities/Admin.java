/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entities;

/**
 *
 * @author mirko
 */
public class Admin extends Utente{
    private int id;
    
    public Admin(String nome,String cognome,Account account) {
        super(nome, cognome, account);
        
    }
    
    public Admin(String nome,String cognome,Account account, int id) { 
        super(nome, cognome, account);
        this.id = id;
    }

    public Admin() {
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
        final Admin other = (Admin) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
        
}
