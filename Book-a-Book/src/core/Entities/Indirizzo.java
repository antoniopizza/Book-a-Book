/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.Entities;

/**
 *
 * @author mirko
 */
public class Indirizzo {
    
    protected String via;
    protected String citta;
    protected String civico;
    protected String provincia;
    protected String cap;

    public Indirizzo() {
        
    }

    public Indirizzo(String via, String citta, String civico, String provincia, String cap) {
        this.via = via;
        this.citta = citta;
        this.civico = civico;
        this.provincia = provincia;
        this.cap = cap;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "Indirizzo{" + "via=" + via + ", citta=" + citta + ", civico=" + civico + ", provincia=" + provincia + ", cap=" + cap + '}';
    }
        
}
