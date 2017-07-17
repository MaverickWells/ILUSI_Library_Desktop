/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;
import java.util.*;
import javax.swing.table.*;
import Model.Pemustaka;

/**
 *
 * @author Well's-Comp
 */
public class PemustakaCtrl {
    private DAO dao; 
    private Vector columns;
    
    public PemustakaCtrl(){
        dao = new DAO();
        columns = new Vector();
        
        columns.add("No. Anggota");
        columns.add("Username");
        columns.add("Nama Pemustaka");
        columns.add("No. Identitas");
        columns.add("Status Pekerjaan");
        columns.add("Instansi Asal");
        columns.add("Alamat Pemustaka");
        columns.add("No. Telepon");
        columns.add("Prodi Asal");
        columns.add("Katagori Pemustaka");
        columns.add("Status Keanggotaan");
    }
    
    public TableModel showPemustaka()
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(dao.getTableDataPemustaka(), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public boolean insertPemustaka(Pemustaka P)
    {
        dao.makeConnection();
        boolean status = dao.insertPemustaka(P);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean updatePemustaka(Pemustaka P)
    {
        dao.makeConnection();
        boolean status = dao.updatePemustaka(P);
        dao.closeConnection();
        
        return status;
    }
    
    public void deletePemustaka(int no_anggota)
    {
        dao.makeConnection();
        dao.deletePemustaka(no_anggota);
        dao.closeConnection();
    }
    
    public ArrayList getRowDataPemustaka(int no_anggota)
    {
        dao.makeConnection();
        ArrayList<String> data = dao.getRowDataPemustaka(no_anggota);
        dao.closeConnection();
        
        return data;
    }
    
    public ArrayList getRowDataPemUsulan(String username)
    {
        dao.makeConnection();
        ArrayList<String> data = dao.getRowDataPemUsulan(username);
        dao.closeConnection();
        
        return data;
    }

    public TableModel showCariPemustaka(String mode, String cari)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getTableDataCariPemustaka(mode, cari), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public ArrayList<String> cmbBoxProdiData()
    {
        dao.makeConnection();
        ArrayList<String> data = dao.getDataCmbBoxProdi();
        dao.closeConnection();
        
        return data;
    }
    
    public ArrayList<String> cmbBoxKatPemData()
    {
        dao.makeConnection();
        ArrayList<String> data = dao.getDataCmbBoxKatPem();
        dao.closeConnection();
        
        return data;
    }
    
    public String getNewIDAnggota()
    {
        dao.makeConnection();
        String temp = dao.getNewIDAnggota();
        dao.closeConnection();
        
        return temp;
    }
    
    public int getKodeProdi(String prodi)
    {
        dao.makeConnection();
        int temp = Integer.parseInt(dao.getKodeProdi(prodi));
        dao.closeConnection();
        
        return temp;
    }
    
    public int getKodeKatPem(String katPem)
    {
        dao.makeConnection();
        int temp = Integer.parseInt(dao.getKodeKatPem(katPem));
        dao.closeConnection();
        
        return temp;
    }
    
    public void setStatusAktif(int no_anggota)
    {
        dao.makeConnection();
        dao.setStatusAktif(no_anggota);
        dao.closeConnection();
    }
    
    public void setDeaktivasi(int no_anggota)
    {
        dao.makeConnection();
        dao.setDeaktivasi(no_anggota);
        dao.closeConnection();
    }
    
    public boolean isUniqueUsername(String username)
    {
        dao.makeConnection();
        boolean status = dao.isUniqueUsernamePem(username);
        dao.closeConnection();
        
        return status;
    }
}
