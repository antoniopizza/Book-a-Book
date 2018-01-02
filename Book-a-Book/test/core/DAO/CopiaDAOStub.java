/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Copia;
import core.entities.Posizione;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kliffom
 */
public class CopiaDAOStub extends CopiaDAO{

    public CopiaDAOStub(LibroDAO libroDao, PosizioneDAO posizioneDAO) {
        super(libroDao, posizioneDAO);
    }

    @Override
    public List<Copia> doRetriveByPosizione(Posizione posizione) {
        List<Copia> copie = new ArrayList<Copia>();
        Copia copia = new Copia("abc", "prenotabile", "si");
        copie.add(copia);
        
        return copie;
    }
    
    
}
