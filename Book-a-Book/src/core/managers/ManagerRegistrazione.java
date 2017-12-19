/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Persona;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerRegistrazione {
    
    public Persona registra
        (String nome,String cognome,String email,String numeroDocumento,String via,String citta,String numeroCivico,String password,String confermaPassword){
        
            throw new UnsupportedOperationException("Not implemented yet");
            
    }
    
        
    public Biblioteca registra(String isil,String nome,String via,String citta,String numeroCivico){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    
    public Bibliotecario registraDipendente(String isil,String nome,String cognome,String email,String password){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Biblioteca> visualizzaRichieste(){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    
    public boolean modificaStatoBiblioteca(String idBiblioteca, boolean flag){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
