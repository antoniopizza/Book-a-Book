/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Admin;
import core.entities.Biblioteca;
import core.entities.Indirizzo;

/**
 *
 * @author Mery
 */
public class BibliotecaDAOStub extends BibliotecaDAO{
    
    @Override
    public Biblioteca doRetriveById(Object... id){
        String isil = (String) id[0];
        String nome = "nomeBib";
        String status = "Accettato";
        return new Biblioteca(isil,nome,status);
        
    }
    
}
