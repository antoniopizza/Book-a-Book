/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.utils;

import core.entities.Prenotazione;

/**
 *
 * @author Mery
 */
public class PrenotazioniPerCodice implements Criterio{

    private int id;

    public PrenotazioniPerCodice(int id) {
        this.id = id;
    }

    public PrenotazioniPerCodice() {
        }

    @Override
    public boolean isValid(Object ob) {
        Prenotazione pren = (Prenotazione) ob;
        if ((pren.getId()) == id) {
            return true;
        }
        return false;
    }
    
    
}
