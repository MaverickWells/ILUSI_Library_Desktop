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

/**
 *
 * @author Well's-Comp
 */
public class PemesananCtrl {
    private DAO dao;
    private Vector columnsUsulan;
    private Vector columnsBahanPustaka;

    public PemesananCtrl() {
        dao = new DAO();
        
        columnsUsulan = new Vector();
        columnsBahanPustaka = new Vector();
        
        columnsUsulan.add("No.");
        columnsUsulan.add("ISBN");
        columnsUsulan.add("Judul Buku");
        columnsUsulan.add("Pengarang");
        columnsUsulan.add("Penerbit");
        
        columnsBahanPustaka.add("No. Katalog");
        columnsBahanPustaka.add("ISBN");
        columnsBahanPustaka.add("Judul");
        columnsBahanPustaka.add("Pengarang");
        columnsBahanPustaka.add("Penerbit");
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
    
    public void deleteUsulanCekTersedia(int id_detil)
    {
        dao.makeConnection();
        dao.deleteRekapitulasiUsulan(id_detil);
        dao.closeConnection();
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
}
