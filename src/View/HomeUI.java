/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.HomeCtrl;
import Control.PemustakaCtrl;
import Control.UserCtrl;
import java.awt.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Well's-Comp
 */
public class HomeUI extends javax.swing.JFrame {
    private static String username;
    private static String role;
    private UserCtrl userCtrl;
    private PemustakaCtrl pemCtrl;
    private HomeCtrl homeCtrl;
    private static ChangePasswordUI instance;
    private static ChangePasswordPemUI instancePem;
    
    

    /**
     * Creates new form homeUI
     */
    public HomeUI(String _username, String _role) {
        initComponents();
        
        this.userCtrl = new UserCtrl();
        this.pemCtrl = new PemustakaCtrl();
        this.homeCtrl = new HomeCtrl();
        
        this.instance = null;
        this.instancePem = null;
        this.username = _username;
        this.role = _role;
        
        String temp = "", tamp, S = "astaga";
        int cek = 0;
        
        for(int i = 0; i < S.length(); i++)
        {
            if(S.charAt(i) == ' ')
            {
                tamp = new StringBuilder(S.substring(cek, i)).reverse().toString();
                temp = temp + tamp + " ";
                cek = i + 1;
            }
            
            else if(i == (S.length()-1))
            {
                tamp = new StringBuilder(S.substring(cek)).reverse().toString();
                temp = temp + tamp;
            }
        }
        
        
        setForm();
        setMenu();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    /////////////METHOD-METHOD/////////////
    private void setForm() {
        labelUsername.setText(username);
        labelRole.setText(role);
        
        this.setTitle("HALAMAN UTAMA");
    }
    
    private void setMenu()
    {
        
        if(this.role.contains("Kaprodi"))
        {
            this.role = this.role.substring(0, 7);
        }
        
        switch(this.role)
        {
            case "BPDP" :
            {
                btnLayanan.setVisible(false);
                setMenuPengelolaanOnOff(true, true, true, true, false);
                btnPengusulan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                break;
            }
            case "BTP" :
            {
                btnLayanan.setVisible(false);
                setMenuPengelolaanOnOff(false, false, false, false, true);
                btnPengusulan.setVisible(false);
                btnVerifikasi.setVisible(false);
                break;
            }
            case "BLP" :
            {
                btnPengelolaan.setVisible(false);
                btnPengusulan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                break;
            }
            case "Kaprodi":
            {
                btnPengelolaan.setVisible(false);
                btnPengusulan.setVisible(false);
                btnRekapitulasi.setVisible(false);
                btnLayanan.setVisible(false);
                break;
            }
            case "Kepala UPT" :
            {
                btnPengelolaan.setVisible(false);
                btnPengusulan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                btnLayanan.setVisible(false);
                break;
            }
            case "Dosen UJDP" :
            {
                btnPengelolaan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                btnLayanan.setVisible(false);
                break;
            }
            case "Mahasiswa UJDP" :
            {
                btnPengelolaan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                btnLayanan.setVisible(false);
                break;
            }
            case "Dosen Luar" :
            {
                btnPengelolaan.setVisible(false);
                btnPengusulan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                btnLayanan.setVisible(false);
                break;
            }
            case "Mahasiswa Luar" :
            {
                btnPengelolaan.setVisible(false);
                btnPengusulan.setVisible(false);
                btnVerifikasi.setVisible(false);
                btnRekapitulasi.setVisible(false);
                btnLayanan.setVisible(false);
                break;
            }
            default :
            {
                setMenuOnOff(false, false, false, false);
                btnLayanan.setVisible(false);
                break;
            }
        }
    }
    
    private void setMenuOnOff(boolean pengelolaan, boolean pengusulan,
            boolean verifikasi, boolean rekapitulasi)
    {
        btnPengelolaan.setVisible(false);
        btnPengusulan.setVisible(false);
        btnVerifikasi.setVisible(false);
        btnRekapitulasi.setVisible(false);
    }
    
    private void setMenuPengelolaanOnOff(boolean user, boolean katagori,
            boolean prodi, boolean pemustaka, boolean bahanPustaka)
    {
        btnPengKatPem.setVisible(katagori);
        btnPengPemustaka.setVisible(pemustaka);
        btnPengProdi.setVisible(prodi);
        btnPengUser.setVisible(user);
        btnPengBahanPustakaManual.setVisible(bahanPustaka);
        btnPengBahanPustakaAuto.setVisible(bahanPustaka);
    }
    
    public void setInstance()
    {
        if(this.instance != null)
            this.instance = null;
        
        if(this.instancePem != null)
            this.instancePem = null;
    }
    ///////////////////////////////////////
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelRole = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        btnFile = new javax.swing.JMenu();
        btnChangePassword = new javax.swing.JMenuItem();
        btnLogout = new javax.swing.JMenuItem();
        btnExit = new javax.swing.JMenuItem();
        btnPengelolaan = new javax.swing.JMenu();
        btnPengUser = new javax.swing.JMenuItem();
        btnPengKatPem = new javax.swing.JMenuItem();
        btnPengProdi = new javax.swing.JMenuItem();
        btnPengPemustaka = new javax.swing.JMenuItem();
        btnPengBahanPustakaManual = new javax.swing.JMenuItem();
        btnPengBahanPustakaAuto = new javax.swing.JMenuItem();
        btnPengusulan = new javax.swing.JMenu();
        btnPengusulanBahanPustaka = new javax.swing.JMenuItem();
        btnVerifikasi = new javax.swing.JMenu();
        btnVerifikasiUsulan = new javax.swing.JMenuItem();
        btnRekapitulasi = new javax.swing.JMenu();
        btnRekapUsulan = new javax.swing.JMenuItem();
        btnPesanBahanPustaka = new javax.swing.JMenuItem();
        btnLayanan = new javax.swing.JMenu();
        btnPeminjaman = new javax.swing.JMenuItem();
        btnPengembalian = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SELAMAT DATANG");

        labelUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsername.setText("jLabel2");
        labelUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("ROLE :");

        labelRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRole.setText("jLabel4");
        labelRole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/a-book-a-week-image.jpg"))); // NOI18N

        btnFile.setText("File");
        btnFile.setPreferredSize(new java.awt.Dimension(73, 19));

        btnChangePassword.setText("Ganti Password");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });
        btnFile.add(btnChangePassword);

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        btnFile.add(btnLogout);

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        btnFile.add(btnExit);

        menuBar.add(btnFile);

        btnPengelolaan.setText("Pengelolaan");

        btnPengUser.setText("Data Pengguna / User");
        btnPengUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengUserActionPerformed(evt);
            }
        });
        btnPengelolaan.add(btnPengUser);

        btnPengKatPem.setText("Data Katagori Pemustaka");
        btnPengKatPem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengKatPemActionPerformed(evt);
            }
        });
        btnPengelolaan.add(btnPengKatPem);

        btnPengProdi.setText("Data Program Studi");
        btnPengProdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengProdiActionPerformed(evt);
            }
        });
        btnPengelolaan.add(btnPengProdi);

        btnPengPemustaka.setText("Data Pemustaka");
        btnPengPemustaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengPemustakaActionPerformed(evt);
            }
        });
        btnPengelolaan.add(btnPengPemustaka);

        btnPengBahanPustakaManual.setText("Data Bahan Pustaka (Manual)");
        btnPengBahanPustakaManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengBahanPustakaManualActionPerformed(evt);
            }
        });
        btnPengelolaan.add(btnPengBahanPustakaManual);

        btnPengBahanPustakaAuto.setText("Data Bahan Pustaka (Terverifikasi)");
        btnPengBahanPustakaAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengBahanPustakaAutoActionPerformed(evt);
            }
        });
        btnPengelolaan.add(btnPengBahanPustakaAuto);

        menuBar.add(btnPengelolaan);

        btnPengusulan.setText("Pengusulan");
        btnPengusulan.setPreferredSize(new java.awt.Dimension(73, 19));

        btnPengusulanBahanPustaka.setText("Pengusulan Bahan Pustaka");
        btnPengusulanBahanPustaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengusulanBahanPustakaActionPerformed(evt);
            }
        });
        btnPengusulan.add(btnPengusulanBahanPustaka);

        menuBar.add(btnPengusulan);

        btnVerifikasi.setText("Verifikasi");
        btnVerifikasi.setPreferredSize(new java.awt.Dimension(73, 19));

        btnVerifikasiUsulan.setText("Usulan Bahan Pustaka");
        btnVerifikasiUsulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifikasiUsulanActionPerformed(evt);
            }
        });
        btnVerifikasi.add(btnVerifikasiUsulan);

        menuBar.add(btnVerifikasi);

        btnRekapitulasi.setText("Rekapitulasi");
        btnRekapitulasi.setPreferredSize(new java.awt.Dimension(73, 19));

        btnRekapUsulan.setText("Usulan Bahan Pustaka");
        btnRekapUsulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekapUsulanActionPerformed(evt);
            }
        });
        btnRekapitulasi.add(btnRekapUsulan);

        btnPesanBahanPustaka.setText("Pesan Bahan Pustaka");
        btnPesanBahanPustaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesanBahanPustakaActionPerformed(evt);
            }
        });
        btnRekapitulasi.add(btnPesanBahanPustaka);

        menuBar.add(btnRekapitulasi);

        btnLayanan.setText("Layanan");
        btnLayanan.setPreferredSize(new java.awt.Dimension(73, 19));

        btnPeminjaman.setText("Peminjaman");
        btnPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeminjamanActionPerformed(evt);
            }
        });
        btnLayanan.add(btnPeminjaman);

        btnPengembalian.setText("Pengembalian");
        btnPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengembalianActionPerformed(evt);
            }
        });
        btnLayanan.add(btnPengembalian);

        menuBar.add(btnLayanan);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelRole, labelUsername});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelUsername))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelRole))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        if(this.instance != null)
            this.instance.dispose();
        
        if(this.instancePem != null)
            this.instancePem.dispose();
        
        new LoginUI().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPengUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengUserActionPerformed
        // TODO add your handling code here:
        new UserUI(this.username).setVisible(true);
    }//GEN-LAST:event_btnPengUserActionPerformed

    private void btnPengKatPemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengKatPemActionPerformed
        // TODO add your handling code here:
        new KatagoriPemustakaUI().setVisible(true);
    }//GEN-LAST:event_btnPengKatPemActionPerformed

    private void btnPengProdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengProdiActionPerformed
        // TODO add your handling code here:
        new ProdiUI().setVisible(true);
    }//GEN-LAST:event_btnPengProdiActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        if(this.instance != null)
            this.instance.dispose();
        
        if(this.instancePem != null)
            this.instancePem.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnPengPemustakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengPemustakaActionPerformed
        // TODO add your handling code here:
        new PemustakaUI().setVisible(true);
    }//GEN-LAST:event_btnPengPemustakaActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        // TODO add your handling code here:
        if(instance == null && instancePem == null)
        {
            if(this.role.equalsIgnoreCase("BLP") || this.role.equalsIgnoreCase("BTP") ||
               this.role.equalsIgnoreCase("BPDP") || this.role.equalsIgnoreCase("Kaproid") ||
               this.role.equalsIgnoreCase("Kepala UPT"))
                (instance = new ChangePasswordUI(
                        username, userCtrl.getPassword(username), this)).setVisible(true);
            else
                (instancePem = new ChangePasswordPemUI(
                    username, userCtrl.getPasswordPemustaka(username), this)).setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Jendela Penggantian Password"
                    + " Telah Aktif");
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnPengusulanBahanPustakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengusulanBahanPustakaActionPerformed
        // TODO add your handling code here:
        ArrayList data = pemCtrl.getRowDataPemUsulan(username);
        
        new PengusulanBahanPustakaUI(data.get(1).toString(), data.get(0).toString(), 
                data.get(3).toString(), data.get(2).toString()).setVisible(true);
    }//GEN-LAST:event_btnPengusulanBahanPustakaActionPerformed

    private void btnVerifikasiUsulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifikasiUsulanActionPerformed
        // TODO add your handling code here:
        System.out.println(labelRole.getText().
                substring(8));
        new VerifikasiBahanPustakaUI(homeCtrl.kodeProdi(labelRole.getText().
                substring(8))).setVisible(true);
    }//GEN-LAST:event_btnVerifikasiUsulanActionPerformed

    private void btnRekapUsulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekapUsulanActionPerformed
        // TODO add your handling code here:
        new RekapitulasiBahanPustakaUI().setVisible(true);
    }//GEN-LAST:event_btnRekapUsulanActionPerformed

    private void btnPengBahanPustakaManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengBahanPustakaManualActionPerformed
        // TODO add your handling code here:
        new PengelolaanBahanPustakaUI().setVisible(true);
    }//GEN-LAST:event_btnPengBahanPustakaManualActionPerformed

    private void btnPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeminjamanActionPerformed
        // TODO add your handling code here:
        new PengelolaanPeminjamanUI().setVisible(true);
    }//GEN-LAST:event_btnPeminjamanActionPerformed

    private void btnPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengembalianActionPerformed
        // TODO add your handling code here:
        new PengelolaanPengembalianUI().setVisible(true);
    }//GEN-LAST:event_btnPengembalianActionPerformed

    private void btnPengBahanPustakaAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPengBahanPustakaAutoActionPerformed
        // TODO add your handling code here:
        new PengelolaanBahanPustakaAutoUI().setVisible(true);
    }//GEN-LAST:event_btnPengBahanPustakaAutoActionPerformed

    private void btnPesanBahanPustakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesanBahanPustakaActionPerformed
        // TODO add your handling code here:
        new PemesananBahanPustaka().setVisible(true);
    }//GEN-LAST:event_btnPesanBahanPustakaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUI(username, role).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnChangePassword;
    private javax.swing.JMenuItem btnExit;
    private javax.swing.JMenu btnFile;
    private javax.swing.JMenu btnLayanan;
    private javax.swing.JMenuItem btnLogout;
    private javax.swing.JMenuItem btnPeminjaman;
    private javax.swing.JMenuItem btnPengBahanPustakaAuto;
    private javax.swing.JMenuItem btnPengBahanPustakaManual;
    private javax.swing.JMenuItem btnPengKatPem;
    private javax.swing.JMenuItem btnPengPemustaka;
    private javax.swing.JMenuItem btnPengProdi;
    private javax.swing.JMenuItem btnPengUser;
    private javax.swing.JMenu btnPengelolaan;
    private javax.swing.JMenuItem btnPengembalian;
    private javax.swing.JMenu btnPengusulan;
    private javax.swing.JMenuItem btnPengusulanBahanPustaka;
    private javax.swing.JMenuItem btnPesanBahanPustaka;
    private javax.swing.JMenuItem btnRekapUsulan;
    private javax.swing.JMenu btnRekapitulasi;
    private javax.swing.JMenu btnVerifikasi;
    private javax.swing.JMenuItem btnVerifikasiUsulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}