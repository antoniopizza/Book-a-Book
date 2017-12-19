/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Bibliotecario;
import core.entities.Persona;
import java.util.Calendar;

/**
 *
 * @author manuel
 */
public class ManagerAccount {
    public boolean modificaPassword(String email,String oldPsw,String newPsw){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    
    public Bibliotecario modificaDatiPersonali
        (String email,String provincia,String cap,String via,String numeroCivico,String citta){
         
            throw new UnsupportedOperationException("Not implemented yet");
    }

        
    public Persona modificaDatiPersonali
        (String email, String nome,String cognome,String codiceFiscale,Calendar dataNascita,String provincia,String cap,String via,String numeroCivico,String citta,String recapitoTelefonico,String sesso){
            
             throw new UnsupportedOperationException("Not implemented yet");
            
        }
        
        
    public Admin modificaDatiPersonali(String email,String nome,String cognome){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Biblioteca modificaDatiBiblioteca(String nome,String via,String citta,String numeroCivico){
        
        throw new UnsupportedOperationException("Not implemented yet");
        
    }

    
    public boolean recuperaPassword(String email){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    
    boolean richiestaRimozioneAccount(String isil){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    boolean rimozioneAccountUtente(String email){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
