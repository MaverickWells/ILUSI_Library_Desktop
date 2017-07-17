/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Well's-Comp
 */
public class BahanPustaka {
    private String noKatalog;
    private String noISBN;
    private String judul;
    private String pengarang;
    private String penerbit;
    private int kode_prodi;

    public BahanPustaka(String noKatalog, String noISBN, String judul, 
            String pengarang, String penerbit, int kode_prodi) {
        this.noKatalog = noKatalog;
        this.noISBN = noISBN;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.kode_prodi = kode_prodi;
    }

    public String getNoKatalog() {
        return noKatalog;
    }

    public String getNoISBN() {
        return noISBN;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public int getKode_prodi() {
        return kode_prodi;
    }
    
    
}
