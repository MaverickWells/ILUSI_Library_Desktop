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
import Model.Prodi;

/**
 *
 * @author Well's-Comp
 */
public class ProdiCtrl {
    private DAO dao; 
    private Vector columns;
    
    public ProdiCtrl(){
        dao = new DAO();
        columns = new Vector();
        
        columns.add("Kode Prodi");
        columns.add("Strata Prodi");
        columns.add("Nama Prodi");
    }
    
    public TableModel showProdi()
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(dao.getTableDataProdi(), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public void insertProdi(Prodi p)
    {
        dao.makeConnection();
        dao.InsertProdi(p);
        dao.closeConnection();
    }
    
    public void updateProdi(Prodi P)
    {
        dao.makeConnection();
        dao.updateProdi(P);
        dao.closeConnection();
    }
    
    public void deleteProdi(int kode_prodi)
    {
        dao.makeConnection();
        dao.deleteProdi(kode_prodi);
        dao.closeConnection();
    }
    
    public String[] getRowDataProdi(int kode_prodi)
    {
        dao.makeConnection();
        String[] text = dao.getRowDataProdi(kode_prodi);
        dao.closeConnection();
        
        return text;
    }
    
    public boolean isUniqueKodeProdi(String kode)
    {
        dao.makeConnection();
        boolean status = dao.isUniqueKodeProdi(kode);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean isUniqueNamaProdi(String nama)
    {
        dao.makeConnection();
        boolean status = dao.isUniqueNamaProdi(nama);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean isProdiUsed(String kode)
    {
        dao.makeConnection();
        boolean status = dao.checkProdiUsed(kode);
        dao.closeConnection();
        
        return status;
    }
    
    public TableModel showCariProdi(String mode, String cari)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(dao.getTableDataCariProdi(mode, cari), columns);
        dao.closeConnection();
        
        return tableModel;
    }
}
