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
public class Account {
    
    protected String email;
    protected String password;
    protected String pathFoto;
    protected String tipo;

    public Account() {
        
    }

    public Account(String email, String password, String pathFoto, String tipo) {
        this.email = email;
        this.password = password;
        this.pathFoto = pathFoto;
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Account{" + "email=" + email + ", password=" + password + ", pathFoto=" + pathFoto + ", tipo=" + tipo + '}';
    }
    
}
