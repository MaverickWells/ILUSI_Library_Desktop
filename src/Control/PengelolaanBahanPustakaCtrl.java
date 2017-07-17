/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;
import Model.BahanPustaka;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Well's-Comp
 */
public class PengelolaanBahanPustakaCtrl {
    private DAO dao;
    private Vector columns;


    public PengelolaanBahanPustakaCtrl() {
        dao = new DAO();
        columns = new Vector();
        
        columns.add("No. Katalog");
        columns.add("No. ISBN");
        columns.add("Judul Buku");
        columns.add("Pengarang Buku");
        columns.add("Penerbit Buku");        

    }
    
    public TableModel getBahanPustakaSearch(String cari)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getBahanPustakaSearch("judul_buku", cari), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public TableModel showDataBahanPustaka()
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getTableDataBahanPustaka(), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public void deleteBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        dao.deleteBahanPustaka(no_katalog);
        dao.closeConnection();
    }
    
    public ArrayList getCmbBoxDataProdi()
    {
        dao.makeConnection();
        ArrayList data = dao.getDataCmbBoxProdi();
        dao.closeConnection();
        
        return data;
    }
    
    public void insertBahanPustaka(BahanPustaka B)
    {
        dao.makeConnection();
        dao.insertBahanPustaka(B);
        dao.closeConnection();
    }
    
    public int getKodeProdi(String nama_prodi)
    {
        dao.makeConnection();
        int kode = Integer.parseInt(dao.getKodeProdi(nama_prodi));
        dao.closeConnection();
        
        return kode;
    }
    
    public String getMaxKodeKatalog()
    {
        dao.makeConnection();
        String kode = dao.getMaxKodeKatalog();
        dao.closeConnection();
        
        return kode;
    }
    
    public Vector getRowDataBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        Vector data = dao.getRowDataBahanPustaka(no_katalog);
        dao.closeConnection();
        
        return data;
    }
    
    public void updateBahanPustaka(BahanPustaka B)
    {
        dao.makeConnection();
        dao.updateBahanPustaka(B);
        dao.closeConnection();
    }
}
