/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.managers;

import core.entities.Autore;
import core.entities.Copia;
import core.entities.Libro;
import core.entities.Posizione;
import core.utils.Criterio;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author manuel
 */
public class ManagerLibri {
    
    //da fare
    public Collection<Libro> cercaLibro(Criterio c,String val){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //da fare
    public boolean modificaDisponibilita(String isbn,String idBiblioteca,boolean flag){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    //da fare
    public Libro aggiuntaLibro
        (String isil,String isbn,String titolo,String editore,Calendar dataPubblicazione,String descrizione,List<Autore> autori,List<Posizione> posizioni){
            throw new UnsupportedOperationException("Not implemented yet");
    }
        
    public Copia aggiungiCopia(String isbn, String isil, String idPosizione, String idCopia){
        throw new UnsupportedOperationException("Not implemented yet");
    } 
    
    public boolean eliminaCopia(String isbn, String isil, String idPosizione,String idCopia){
        throw new UnsupportedOperationException("Not implemented yet");
    }        
    
    //da fare
    public boolean eliminaLibro(String isbn,String isil){
        throw new UnsupportedOperationException("Not implemented yet");
    }

    
    public boolean spostaCopie(String isbn,String isil,String idCopia,Posizione vecchiaPosizione,Posizione nuovaPosizione){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    //da fare
    public Libro visualizzaLibro(String isbn){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Posizione aggiungiPosizione(String etichetta, String isil){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Collection<Posizione> visualizzaPosizioniLibro(String isbn, String isil){
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
