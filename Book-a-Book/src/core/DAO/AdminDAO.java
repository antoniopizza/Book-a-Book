/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import core.entities.Admin;
import java.util.List;

/**
 *
 * @author mirko
 */
public class AdminDAO extends AbstractDAO<Admin>{
    
    /**
     * 
     * @param id[0] si aspetta un codice identificativo numerico di un Admin.
     * @return un Admin in base al codice id. 
     */

    @Override
    public Admin doRetriveById(Object... id) {
        int idAdmin = (int) id[0];
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
