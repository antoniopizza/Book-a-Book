/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Copia;
import java.util.List;

/**
 *
 * @author Mery
 */
public class CopiaDAO extends AbstractDAO<Copia>{

    @Override
    public Copia doRetriveById(Object... id) {
        String idCopia = (String) id[0];
        String isbn = (String) id[1];
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Copia> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Copia entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Copia entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
