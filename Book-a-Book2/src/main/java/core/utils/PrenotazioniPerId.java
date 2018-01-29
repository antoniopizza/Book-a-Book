/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import core.entities.Prenotazione;
import java.util.GregorianCalendar;

/**
 *
 * @author Mery
 */
//Questo metodo permette di visualizzare la prenotazione con l'Id che gli viene passato.
public class PrenotazioniPerId implements Criterio{

    private int id;

    public PrenotazioniPerId(int id) {
        this.id = id;
    }

    public PrenotazioniPerId() {
        }

    @Override
    public boolean isValid(Object ob) {
        Prenotazione pren = (Prenotazione) ob;
        if ((pren.getPersona().getId()) == id) {
            return true;
        }
        return false;
    }
    
}
