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
//Questo metodo permette di visualizzare tutte le prenotazioni con la ata di creazione che gli viene passata.
public class prenotazioniSuDataDiCreazione implements Criterio {

    private GregorianCalendar data;

    public prenotazioniSuDataDiCreazione(GregorianCalendar data) {
        this.data = data;
    }

    @Override
    public boolean isValid(Object ob) {
        Prenotazione pren = (Prenotazione) ob;
        if (pren.getDataCreazione().compareTo(data) == 0) {
            return true;
        }
        return false;
    }

}
