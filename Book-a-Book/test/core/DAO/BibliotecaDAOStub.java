/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Biblioteca;

/**
 *
 * @author kliffom
 */
public class BibliotecaDAOStub extends BibliotecaDAO {

    @Override
    public Biblioteca doRetriveById(Object... id) {
        String isil = (String) id[0];
        String nome = "Biblioteca 12345";
        String status = "Accettata";
        return new Biblioteca(isil, nome, status);
    }
    
    
}
