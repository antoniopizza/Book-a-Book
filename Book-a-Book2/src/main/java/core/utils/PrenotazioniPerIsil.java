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
public class PrenotazioniPerIsil implements Criterio {

    private String isil;

    public PrenotazioniPerIsil(String isil) {
        this.isil = isil;
    }

    public PrenotazioniPerIsil() {
    }

    @Override
    public boolean isValid(Object ob) {
        Prenotazione pren = (Prenotazione) ob;
        if ((pren.getBiblioteca().getIsil()).equals(isil)) {
            return true;
        }
        return false;
    }
}
