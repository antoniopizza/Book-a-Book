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
//Questo metodo permette di visualizzare tutte le prenotazioni con la data di consegna che gli viene passata.
public class PrenotazioniSuDataDiConsegna implements Criterio {

    private GregorianCalendar data;

    public PrenotazioniSuDataDiConsegna(GregorianCalendar data) {
        this.data = data;
    }

    public PrenotazioniSuDataDiConsegna() {
        }

    @Override
    public boolean isValid(Object ob) {
        Prenotazione pren = (Prenotazione) ob;
        if (pren.getDataScadenza().compareTo(data) == 0) {
            return true;
        }
        return false;
    }
    
}
