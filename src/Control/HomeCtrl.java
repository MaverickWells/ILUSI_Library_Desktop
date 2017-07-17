/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;

/**
 *
 * @author Well's-Comp
 */
public class HomeCtrl {
    private DAO dao;

    public HomeCtrl() {
        dao = new DAO();
    }
    
    public int kodeProdi(String nama)
    {
        dao.makeConnection();
        int kode = Integer.parseInt(dao.getKodeProdi(nama));
        dao.makeConnection();
        
        return kode;
    }
}
