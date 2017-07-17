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
public class Buku {
    private String no_katalog;
    private String noISBN;
    private String judul;
    private String pengarang;
    private String penerbit;

    public Buku(String no_katalog, String noISBN, String judul, String pengarang, String penerbit) {
        this.no_katalog = no_katalog;
        this.noISBN = noISBN;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
    }

    public String getNo_katalog() {
        return no_katalog;
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

    
}
