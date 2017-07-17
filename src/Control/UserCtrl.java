/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Model.User;
import DAO.DAO;
import java.util.*;
import javax.swing.table.*;

/**
 *
 * @author Well's-Comp
 */
public class UserCtrl {
    private DAO dao = new DAO();
    private Vector columns;

    public UserCtrl() {
        columns = new Vector();
        
        columns.add("ID User");
        columns.add("Username");
        columns.add("Nama Role");
    }
    
    public TableModel showDataUser(){
        dao.makeConnection();
        DefaultTableModel data = new DefaultTableModel(dao.getTableDataUser(), columns);
        dao.closeConnection();
        
        return data;
    }
    
    public ArrayList<String> cmbBoxRoleData()
    {
        dao.makeConnection();
        ArrayList<String> data = dao.getDataCmbBoxRole();
        dao.closeConnection();
        
        return data;
    }
    
    public void insertUser(User u)
    {
        dao.makeConnection();
        dao.insertUser(u);
        dao.closeConnection();
    }
    
    public String getIdRole(String name)
    {
        dao.makeConnection();
        String data = dao.getIdRole(name);
        dao.closeConnection();
        
        return data;
    }
    
    public String[] getRowDataUser(int id){
        dao.makeConnection();
        String[] data = dao.getRowDataUser(id);
        dao.closeConnection();
        
        return data;
    }
    
    public void editDataUser(User U){
        dao.makeConnection();
        dao.updateUser(U);
        dao.closeConnection();
    }
    
    public void deleteDataUser(int id_user){
        dao.makeConnection();
        dao.deleteUser(id_user);
        dao.closeConnection();
    }
    
    public int getNewIDUser()
    {
        dao.makeConnection();
        int id = dao.getNewIDUser();
        dao.closeConnection();
        
        return id;
    }
    
    public boolean isUniqueUsername(String username)
    {
        dao.makeConnection();
        boolean status = dao.isUniqueUsername(username);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean updatePassword(String username, String password)
    {
        dao.makeConnection();
        boolean status = dao.updatePassword(username, password);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean updatePasswordPemustaka(String username, String password)
    {
        dao.makeConnection();
        boolean status = dao.updatePasswordPemustaka(username, password);
        dao.closeConnection();
        
        return status;
    }
    
    public String getPassword(String username)
    {
        dao.makeConnection();
        String pass = dao.getPassword(username);
        dao.closeConnection();
        
        return pass;
    }
    
    public String getPasswordPemustaka(String username)
    {
        dao.makeConnection();
        String pass = dao.getPasswordPemustaka(username);
        dao.closeConnection();
        
        return pass;
    }
    
    public boolean isSuperAdmin(String username)
    {
        dao.makeConnection();
        boolean status = dao.isSuperAdmin(username);
        dao.closeConnection();
        
        return status;
    }
    
    public TableModel cariDataUser(String user){
        dao.makeConnection();
        DefaultTableModel data = new DefaultTableModel(dao.getTableDataCariUser(user), columns);
        dao.closeConnection();
        
        return data;
    }
    
    public boolean checkKaprodi(String kaprodi)
    {
        dao.makeConnection();
        boolean status = dao.checkKaprodi(kaprodi);
        dao.closeConnection();
        
        return status;
    }
}
