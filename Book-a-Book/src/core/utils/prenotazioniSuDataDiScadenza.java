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

//Questa classe ha il metodo che permette di trovare tutte le prenotazioni della data di scadenza passat come parametro.

public class prenotazioniSuDataDiScadenza implements Criterio {

    private GregorianCalendar data;

    public prenotazioniSuDataDiScadenza(GregorianCalendar data) {
        this.data = data;
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
