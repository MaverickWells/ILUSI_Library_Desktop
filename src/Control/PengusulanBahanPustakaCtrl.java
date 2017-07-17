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
public class PengusulanBahanPustakaCtrl {
    private DAO dao;

    public PengusulanBahanPustakaCtrl() {
        dao = new DAO();
    }
    
    public int getNewIDUsulan()
    {
        dao.makeConnection();
        int newID = dao.getNewIDUsulan();
        dao.closeConnection();
        
        return newID;
    }
    
    public void reservedUsulan(int id, int no_anggota, String tanggal)
    {
        dao.makeConnection();
        dao.insertNewUsulan(id, no_anggota, tanggal);
        dao.closeConnection();
    }
    
    public void insertNewDetilUsulan(int idUsulan, String noISBN, String judul,
            String pengarang, String penerbit, int kode_prodi)
    {
        dao.makeConnection();
        dao.insertNewDetilUsulan(idUsulan, noISBN, judul, pengarang, penerbit, kode_prodi);
        dao.closeConnection();
    }
    
    public int getIDProdi(String nama_prodi)
    {
        dao.makeConnection();
        int id = Integer.parseInt(dao.getKodeProdi(nama_prodi));
        dao.closeConnection();
        
        return id;
    }
    
    public boolean cekNoISBN(String no_isbn)
    {
        dao.makeConnection();
        boolean status = dao.cekNoISBN(no_isbn);
        dao.closeConnection();
        
        return status;
    }
    
    public boolean statusPemustaka(int no_anggota)
    {
        dao.makeConnection();
        boolean status = dao.statusPemustaka(no_anggota);
        dao.closeConnection();
        
        return status;
    }
}
