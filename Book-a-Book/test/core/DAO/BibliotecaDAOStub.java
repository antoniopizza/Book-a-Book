/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;


import core.entities.Biblioteca;
import core.entities.Indirizzo;

/**
 *
 * @author kliffom
 */
public class BibliotecaDAOStub extends BibliotecaDAO {

    @Override
    public Biblioteca doRetriveById(Object... id) {
        String isil = "IT-321";
        String nome = "Biblioteca Svevo";
        String status = "Accettata";
        Indirizzo ind = new Indirizzo("via Roma", "Nocera Inferiore","21","SA", "84014");
        return new Biblioteca(isil, nome, status,ind,null);
    }
    
    

  
}
