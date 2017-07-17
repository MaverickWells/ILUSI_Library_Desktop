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
public class Prodi {
    private String kode;
    private String strata;
    private String nama;

    public Prodi(String kode, String strata, String nama) {
        this.kode = kode;
        this.strata = strata;
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public String getStrata() {
        return strata;
    }

    public String getNama() {
        return nama;
    }   
}
