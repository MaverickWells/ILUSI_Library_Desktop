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
public class LoginCtrl {
    private DAO dao; 

    public LoginCtrl() {
        dao = new DAO();
    }
    
    public boolean checkUsername(String username)
    {
        dao.makeConnection();
        boolean status = dao.checkUsername(username);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean checkUsernamePemustaka(String username)
    {
        dao.makeConnection();
        boolean status = dao.checkUsernamePemustaka(username);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean checkLoginPengurus(String username, String password)
    {
        dao.makeConnection();
        boolean status = dao.checkLoginPengurus(username, password);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean checkLoginPemustaka(String username, String password)
    {
        dao.makeConnection();
        boolean status = dao.checkLoginPemustaka(username, password);
        dao.closeConnection();
        
        return status;
    }
    
    public String getRole(String username)
    {
        dao.makeConnection();
        String data = dao.getRole(username);
        dao.closeConnection();
        
        return data;
    }
    
    public String getKatPem(String username)
    {
        dao.makeConnection();
        String data = dao.getKatPem(username);
        dao.closeConnection();
        
        return data;
    }
    
    public boolean statusPemustaka(String username)
    {
        dao.makeConnection();
        boolean status = dao.statusPemustaka(username);
        dao.closeConnection();
        
        return status;
    }
}
