/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.DAO.*;
import core.entities.Biblioteca;
import core.entities.Indirizzo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kliffom
 */
public class BibliotecaDAOStub extends BibliotecaDAO {

    
    private List<Biblioteca> bilbioteche = new ArrayList<>();

    public BibliotecaDAOStub() {
        PosizioneDAO posizioneDAO = new PosizioneDAO();
        String isil = "IT-321";
        String nome = "Biblioteca Svevo";
        String status = "Accettata";
        Indirizzo ind = new Indirizzo("via Roma", "Nocera Inferiore","21","SA", "84014");
        Biblioteca b1 = new Biblioteca(isil, nome, status,ind,null);
        
        bilbioteche.add(b1);
        Biblioteca b2 = new Biblioteca("IT-123","Biblioteca Comunale di Atripalda","Attiva",new Indirizzo("Manfredi","Atripalda","6","AV",""),null);        
        
        bilbioteche.add(b2);
    }
    
    
    
    
    @Override
    public Biblioteca doRetriveById(Object... id) {
        String isil = (String) id[0];
        Biblioteca toReturn = null;
        
        for(Biblioteca b : bilbioteche){
            if(b.getIsil().equals(isil)){
                toReturn =  b;
                break;
            }
        }
        
        return toReturn;
    }

    @Override
    public int doInsert(Biblioteca biblioteca) {
        bilbioteche.add(biblioteca);
        return 0;
    }

    @Override
    public List<Biblioteca> doRetriveAll() {
        return bilbioteche;
    }
    
    
    
}
