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
public class Pemustaka {
    private int NoAnggota;
    private String Username;
    private String NamaAnggota;
    private String IdIdentitas;
    private String StatusPekerjaan;
    private String InstansiAsal;
    private String AlamatPemustaka;
    private String NoTelepon;
    private int kode_prodi;
    private int kode_katagori;

    public Pemustaka(int NoAnggota, String Username,String NamaAnggota, 
            String IdIdentitas, String StatusPekerjaan, String InstansiAsal, 
            String AlamatPemustaka, String NoTelepon, int kode_prodi, 
            int kode_katagori) 
    {
        this.NoAnggota = NoAnggota;
        this.Username = Username;
        this.NamaAnggota = NamaAnggota;
        this.IdIdentitas = IdIdentitas;
        this.StatusPekerjaan = StatusPekerjaan;
        this.InstansiAsal = InstansiAsal;
        this.AlamatPemustaka = AlamatPemustaka;
        this.NoTelepon = NoTelepon;
        this.kode_prodi = kode_prodi;
        this.kode_katagori = kode_katagori;
    }

    public int getNoAnggota() {
        return NoAnggota;
    }

    public String getNamaAnggota() {
        return NamaAnggota;
    }

    public String getIdIdentitas() {
        return IdIdentitas;
    }

    public String getStatusPekerjaan() {
        return StatusPekerjaan;
    }

    public String getInstansiAsal() {
        return InstansiAsal;
    }

    public String getAlamatPemustaka() {
        return AlamatPemustaka;
    }

    public String getNoTelepon() {
        return NoTelepon;
    }

    public int getKode_prodi() {
        return kode_prodi;
    }

    public int getKode_katagori() {
        return kode_katagori;
    }

    public String getUsername() {
        return Username;
    }
    
    
}
