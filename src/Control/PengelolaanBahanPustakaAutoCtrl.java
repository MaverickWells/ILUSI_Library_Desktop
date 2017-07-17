/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;
import Model.BahanPustaka;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Well's-Comp
 */
public class PengelolaanBahanPustakaAutoCtrl {
    private DAO dao;
    private Vector columnsUsulan;
    private Vector columnsBahanPustaka;

    public PengelolaanBahanPustakaAutoCtrl() {
        dao = new DAO();
        columnsUsulan = new Vector();
        columnsBahanPustaka = new Vector();
        
        columnsUsulan.add("No.");
        columnsUsulan.add("ISBN");
        columnsUsulan.add("Judul Buku");
        columnsUsulan.add("Pengarang");
        columnsUsulan.add("Penerbit");
        columnsUsulan.add("Jumlah");
        
        columnsBahanPustaka.add("No. Katalog");
        columnsBahanPustaka.add("ISBN");
        columnsBahanPustaka.add("Judul");
        columnsBahanPustaka.add("Pengarang");
        columnsBahanPustaka.add("Penerbit");
    }
    
    public String getMaxKodeKatalog()
    {
        dao.makeConnection();
        String kode = dao.getMaxKodeKatalog();
        dao.closeConnection();
        
        return kode;
    }
    
    public TableModel getTableDataUsulan()
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getTableDataUsulanVerified(), columnsUsulan);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public TableModel getTableDataBahanPustaka()
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getTableDataBahanPustakaPemesanan(), columnsBahanPustaka);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public TableModel getBahanPustakaSearch(String cari)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getBahanPustakaSearch("judul_buku", cari), columnsBahanPustaka);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public TableModel getUsulanSearch(String cari)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getUsulanSearch(cari), columnsUsulan);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public Vector getRowDataUsulan(int id_detil)
    {
        dao.makeConnection();
        Vector data = dao.getRowDataUsulan(id_detil);
        dao.closeConnection();
        
        return data;
    }
    
    public Vector getRowDataBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        Vector data = dao.getRowDataBahanPustaka(no_katalog);
        dao.closeConnection();
        
        return data;
    }
    
    public void insertBahanPustaka(BahanPustaka B)
    {
        dao.makeConnection();
        dao.insertBahanPustaka(B);
        dao.closeConnection();
    }
    
    public void deleteBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        dao.deleteBahanPustaka(no_katalog);
        dao.closeConnection();
    }
    
    public void deleteUsulan(int id_detil)
    {
        dao.makeConnection();
        dao.deleteUsulanMasukPustaka(id_detil);
        dao.closeConnection();
    }
}
