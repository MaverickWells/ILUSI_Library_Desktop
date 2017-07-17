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
public class KatagoriPemustaka {
    private String kode_kat;
    private String desc_kat;
    private String masa_anggota;
    private String lama_pinjam;

    public KatagoriPemustaka(String kode_kat, String desc_kat, String masa_anggota, String lama_pinjam) {
        this.kode_kat = kode_kat;
        this.desc_kat = desc_kat;
        this.masa_anggota = masa_anggota;
        this.lama_pinjam = lama_pinjam;
    }

    public String getKode_kat() {
        return kode_kat;
    }

    public String getDesc_kat() {
        return desc_kat;
    }

    public String getMasa_anggota() {
        return masa_anggota;
    }

    public String getLama_pinjam() {
        return lama_pinjam;
    }
}
