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
public class PeminjamanCtrl {
    private DAO dao;
    private Vector columns;

    public PeminjamanCtrl() {
        dao = new DAO();
        columns = new Vector();
        
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
    
    public int getLamaPinjamPemustaka(int no_anggota)
    {
        dao.makeConnection();
        int lama = dao.getLamaPinjamPemustaka(no_anggota);
        dao.closeConnection();
        
        return lama;
    }
    
    public TableModel showBahanPustakaHasilSearch(String mode, String cari)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getBahanPustakaSearch(mode, cari), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public Vector columnNames()
    {
        return columns;
    }
    
    public int getNewIDTransaksi()
    {
        dao.makeConnection();
        int id = dao.getNewIDTransaksi();
        dao.closeConnection();
        
        return id;
    }
    
    public void insertNewTransaksi(int id, int no_anggota, String tgl_pinjam,
            String tgl_kembali)
    {
        dao.makeConnection();
        dao.insertNewTransaksi(id, no_anggota, tgl_pinjam, tgl_kembali);
        dao.closeConnection();
    }
    
    public void insertNewDetilTransaksi(int idTransaksi, String noKatalog)
    {
        dao.makeConnection();
        dao.insertNewDetilTransaksi(idTransaksi, noKatalog);
        dao.closeConnection();
    }
    
    public boolean PinjamBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        boolean status = dao.PinjamBahanPustaka(no_katalog);
        dao.closeConnection();
        
        return status;
    }
    
    public void KembaliBahanPustaka(String no_katalog)
    {
        dao.makeConnection();
        dao.KembaliBahanPustaka(no_katalog);
        dao.closeConnection();
    }
    
    public int getJumlahPinjaman(int no_anggota)
    {
        dao.makeConnection();
        int jumlah = dao.getJumlahPinjaman(no_anggota);
        dao.closeConnection();
        
        return jumlah;
    }
}
