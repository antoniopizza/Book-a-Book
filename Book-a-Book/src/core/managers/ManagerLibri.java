/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Autore;
import core.entities.Libro;
import core.entities.Posizione;
import core.utils.Criterio;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author manuel
 */
public class ManagerLibri {
    
    public Collection<Libro> cercaLibro(Criterio c,String val){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean modificaDisponibilita(String isbn,String idBiblioteca,boolean flag){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public boolean aggiuntaLibro
        (String isil,String isbn,String titolo,String editore,String disponibilita,Calendar dataPubblicazione,String descrizione,Autore autore){
            throw new UnsupportedOperationException("Not implemented yet");
    }
        
    //TODO
    public boolean modificaNumeroCopie(String isbn,String isil,int numeroCopie,String etichetta){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    //TODO
    public boolean eliminaLibro(String isbn,String isil){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //TODO
    public boolean spostamentoCopieLibro(String isbn,String isil,String numeroCopie,Posizione vecchiaPosizione,Posizione nuovaPosizione){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Libro visualizzaLibro(String isbn){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
