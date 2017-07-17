/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import Model.KatagoriPemustaka;

/**
 *
 * @author Well's-Comp
 */
public class KatagoriPemustakaCtrl {
    private DAO myDao;
    private Vector columns;
    
    public KatagoriPemustakaCtrl(){
        myDao = new DAO();
        columns = new Vector();
        
        columns.add("Kode Katagori");
        columns.add("Desk. Katagori");
        columns.add("Lama Keanggotaan");
        columns.add("Lama Peminjaman (hari)");
    }
    
    public TableModel showKatPem()
    {
        myDao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(myDao.getTableDataKatPem(), columns);
        myDao.closeConnection();
        
        return tableModel;
    }
    
    public void insertKatPem(KatagoriPemustaka k)
    {
        myDao.makeConnection();
        myDao.InsertKatPem(k);
        myDao.closeConnection();
    }
    
    public void updateKatPem(KatagoriPemustaka K)
    {
        myDao.makeConnection();
        myDao.updateKatPem(K);
        myDao.closeConnection();
    }
    
    public void deleteKatPem(int kode_katagori)
    {
        myDao.makeConnection();
        myDao.deleteKatPem(kode_katagori);
        myDao.closeConnection();
    }
    
    public String[] getRowDataKatPem(int kode_katagori)
    {
        myDao.makeConnection();
        String[] text = myDao.getRowDataKatPem(kode_katagori);
        myDao.closeConnection();
        
        return text;
    }
    
    public boolean isUniqueKodeKatPem(String kode)
    {
        myDao.makeConnection();
        boolean status = myDao.isUniqueKodeKat(kode);
        myDao.closeConnection();
        
        return status;
    }
    
    public boolean isKatPemUsed(String kode)
    {
        myDao.makeConnection();
        boolean status = myDao.checkKatPemUsed(kode);
        myDao.closeConnection();
        
        return status;
    }
    
    public TableModel showCariKatPem(String mode, String cari)
    {
        myDao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(myDao.getTableDataCariKatPem(mode, cari), columns);
        myDao.closeConnection();
        
        return tableModel;
    }
}