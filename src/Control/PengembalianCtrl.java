/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Well's-Comp
 */
public class PengembalianCtrl {
    private DAO dao;
    private Vector columns;

    public PengembalianCtrl() {
        dao = new DAO();
        columns = new Vector();
        
        columns.add("No.");
        columns.add("No. Katalog");
        columns.add("No. ISBN");
        columns.add("Judul Buku");
        columns.add("Pengarang Buku");
        columns.add("Penerbit Buku");
    }
    
    public String cekPemustaka(int no_anggota)
    {
        dao.makeConnection();
        String status = dao.cekPemustaka(no_anggota);
        dao.closeConnection();
        
        return status;
    }
    
    public Vector columnNames()
    {
        return columns;
    }
    
    public TableModel getDataPeminjaman(int no_anggota)
    {
        dao.makeConnection();
        DefaultTableModel data = new DefaultTableModel(
                dao.getDataPeminjaman(no_anggota), columns);
        dao.closeConnection();
        
        return data;
    }
    
    public void pengembalianBahanPustaka(int id, String tglKembaliAktual)
    {
        dao.makeConnection();
        dao.pengembalianBahanPustaka(id, tglKembaliAktual);
        dao.closeConnection();
    }
    
    public void pengembalianBahanPustakaDenda(int id, String tglKembaliAktual,
            int denda)
    {
        dao.makeConnection();
        dao.pengembalianBahanPustakaDenda(id, tglKembaliAktual, denda);
        dao.closeConnection();
    }
    
    public String getTanggalKembali(int no_anggota, String no_katalog)
    {
        dao.makeConnection();
        String tgl = dao.getTanggalKembali(no_anggota, no_katalog);
        dao.closeConnection();
        
        return tgl;
    }
    
    public void KembaliBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        dao.KembaliBahanPustaka(no_katalog);
        dao.closeConnection();
    }
    
    public void setPrinted(ArrayList<String> no_detil)
    {
        dao.makeConnection();
        dao.setPrinted(no_detil);
        dao.closeConnection();
    }
}
