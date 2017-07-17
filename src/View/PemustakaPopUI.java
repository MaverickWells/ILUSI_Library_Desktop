/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.PemustakaCtrl;
import Model.Pemustaka;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 *
 * @author Well's-Comp
 */
public class PemustakaPopUI extends javax.swing.JFrame {
    private PemustakaCtrl C;
    private static String mode;
    private static PemustakaUI PemUI;
    private static ArrayList data;

    /**
     * Creates new form PemustakaPopUI
     * @param _mode
     * @param _PemUI
     * @param _row
     * @param _data
     */
    public PemustakaPopUI(String _mode, PemustakaUI _PemUI, ArrayList _data) {
        initComponents();
        
        C = new PemustakaCtrl();
        
        mode = _mode;
        PemUI = _PemUI;
        data = _data;
        
        clear();
        
        setCmbBoxKatPem();
        setCmbBoxProdi();
        
        setForm();
    }

    /////////////////////////METHOD-METHOD//////////////////////////////
    private void clear()
    {
        txtAlamatPemustaka.setText("");
        txtInstansiAsal.setText("");
        txtNamaPemustaka.setText("");
        txtNoIdentitas.setText("");
        txtNoTelepon.setText("");
        txtStatusPekerjaan.setText("");
        cmbKatagori.setSelectedIndex(-1);
        cmbProdi.setSelectedIndex(-1);
    }
    
    private void setFormEdit()
    {   
        txtNoAnggota.setText(data.get(0).toString());
        txtUsername.setText(data.get(1).toString());
        txtNamaPemustaka.setText(data.get(2).toString());
        txtNoIdentitas.setText(data.get(3).toString());
        txtStatusPekerjaan.setText(data.get(4).toString());
        txtInstansiAsal.setText(data.get(5).toString());
        txtAlamatPemustaka.setText(data.get(6).toString());
        txtNoTelepon.setText(data.get(7).toString());
        
        for(int i=0; i < cmbProdi.getItemCount(); i++)
            if(cmbProdi.getItemAt(i).toString().equalsIgnoreCase(data.get(8).toString()))
                cmbProdi.setSelectedIndex(i);
        
        for(int i=0; i < cmbKatagori.getItemCount(); i++)
            if(cmbKatagori.getItemAt(i).toString().equalsIgnoreCase(data.get(9).toString()))
                cmbKatagori.setSelectedIndex(i);
        
        btnClear.setVisible(false);
    }
    
    private void setForm()
    {
        txtNoAnggota.setEditable(false);
        
        if(mode.equalsIgnoreCase("edit"))
        {
            this.setTitle("UBAH DATA");
            
            txtUsername.setEditable(false);
            
            setFormEdit();            
        }
        else 
        {
            this.setTitle("TAMBAH DATA");
            
            txtNoAnggota.setText(C.getNewIDAnggota());
        }
    }
    
    private boolean checkEmpty()
    {
        if(txtAlamatPemustaka.getText().trim().equalsIgnoreCase("") ||
           txtInstansiAsal.getText().trim().equalsIgnoreCase("") ||
           txtNamaPemustaka.getText().trim().equalsIgnoreCase("") ||
           txtUsername.getText().trim().equalsIgnoreCase("") ||
           txtNoIdentitas.getText().trim().equalsIgnoreCase("") ||
           txtNoTelepon.getText().trim().equalsIgnoreCase("") ||
           txtStatusPekerjaan.getText().trim().equalsIgnoreCase("") ||
           cmbKatagori.getSelectedIndex() == -1 ||
           cmbProdi.getSelectedIndex() == -1)
            return true;
        
        else return false;
    }
    
    private boolean checkInputData()
    {
        Pattern ptrnAngka = Pattern.compile("[0-9]+");
        Pattern ptrnHuruf = Pattern.compile("[A-Za-z]+");
        Pattern ptrnAlphaNum = Pattern.compile("[0-9A-Za-z]+");
        
        String username = txtUsername.getText().toString().trim();
        String nama_pemustaka = txtNamaPemustaka.getText().toString().trim();
        String no_identitas = txtNoIdentitas.getText().toString().trim();
        String status_pekerjaan = txtStatusPekerjaan.getText().toString().trim();
        String instansi_asal = txtInstansiAsal.getText().toString().trim();
        String alamat_pemustaka = txtAlamatPemustaka.getText().toString().trim();
        String no_telepon = txtNoTelepon.getText().toString().trim();
        int prodi = cmbProdi.getSelectedIndex();
        int katagori = cmbKatagori.getSelectedIndex();
        
        try
        {
            if(checkEmpty())
                throw new Exception("Lengkapi Data Yang Masih Kosong");
            else if(! ptrnHuruf.matcher(nama_pemustaka).matches())
                throw new Exception("Nama Pemustaka Hanya Boleh Huruf");
            else if(username.length() > 50)
                throw new Exception("Username Hanya Boleh 50 Huruf");
            else if(! C.isUniqueUsername(username) && mode.equalsIgnoreCase("add"))
                throw new Exception("Username Sudah Ada");
            else if(nama_pemustaka.length() > 50)
                throw new Exception("Nama Pemustaka Hanya Boleh 50 Huruf");
            else if(! ptrnAngka.matcher(no_identitas).matches())
                throw new Exception("No. Identitas Hanya Boleh Angka");
            else if(no_identitas.length() > 25)
                throw new Exception("No. Identitas Hanya Boleh 25 Angka");
            else if(! ptrnHuruf.matcher(status_pekerjaan).matches())
                throw new Exception("Status Pekerjaan Hanya Boleh Huruf");
            else if(status_pekerjaan.length() > 25)
                throw new Exception("Status Pekerjaan Hanya Boleh 25 Huruf");
            else if(! ptrnHuruf.matcher(instansi_asal).matches())
                throw new Exception("Instansi Asal Hanya Boleh Huruf");
            else if(instansi_asal.length() > 25)
                throw new Exception("Instansi Asal Hanya Boleh 25 Huruf");
            else if(! ptrnAlphaNum.matcher(alamat_pemustaka).matches())
                throw new Exception("Alamat Pemustaka Hanya Boleh Angka dan Huruf");
            else if(alamat_pemustaka.length() > 75)
                throw new Exception("Alamat Pemustaka Hanya Boleh 75 Karakter");
            else if(! ptrnAngka.matcher(no_telepon).matches())
                throw new Exception("No. Telp. Hanya Boleh Angka");
            else if(no_telepon.length() > 12)
                throw new Exception("No. Telp. Hanya Boleh 12 Angka");
            else if(prodi == -1)
                throw new Exception("Silakan Pilih Prodi Terlebih Dahulu");
            else if(katagori == -1)
                throw new Exception("Silakan Pilih Katagori Terlebih Dahulu");
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "PESAN", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            return false;
        }
        
        return true;
    }
    
    private void setCmbBoxKatPem()
    {
        cmbKatagori.removeAllItems();
        
        ArrayList<String> temp = C.cmbBoxKatPemData();
        
        for(int i=0; i < temp.size(); i++)
            cmbKatagori.addItem(temp.get(i).toString());
        
        cmbKatagori.setSelectedIndex(-1);
    }
    
    private void setCmbBoxProdi()
    {
        cmbProdi.removeAllItems();
        
        ArrayList<String> temp = C.cmbBoxProdiData();
        
        for(int i=0; i < temp.size(); i++)
            cmbProdi.addItem(temp.get(i).toString());
        
        cmbProdi.setSelectedIndex(-1);
    }
    
    private void afterSet()
    {
        PemUI.setInstance();
        PemUI.setTableData();
        
        this.dispose();
    }
    ////////////////////////////////////////////////////////////////////
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNoAnggota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNamaPemustaka = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNoIdentitas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStatusPekerjaan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInstansiAsal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAlamatPemustaka = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNoTelepon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbProdi = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cmbKatagori = new javax.swing.JComboBox();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("PENGELOLAAN DATA PEMUSTAKA");

        jLabel2.setText("No. ANGGOTA");

        jLabel3.setText("NAMA PEMUSTAKA");

        txtNamaPemustaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPemustakaKeyTyped(evt);
            }
        });

        jLabel4.setText("No. IDENTITAS");

        txtNoIdentitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoIdentitasKeyTyped(evt);
            }
        });

        jLabel5.setText("STATUS PEKERJAAN");

        txtStatusPekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStatusPekerjaanKeyTyped(evt);
            }
        });

        jLabel6.setText("INSTANSI ASAL");

        txtInstansiAsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInstansiAsalKeyTyped(evt);
            }
        });

        jLabel7.setText("ALAMAT PEMUSTAKA");

        txtAlamatPemustaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatPemustakaKeyTyped(evt);
            }
        });

        jLabel8.setText("No. TELEPON");

        txtNoTelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTeleponKeyTyped(evt);
            }
        });

        jLabel9.setText("PROGRAM STUDI");

        cmbProdi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("KATAGORI PEMUSTAKA");

        cmbKatagori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSimpan.setText("SIMPAN");
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanMouseExited(evt);
            }
        });
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        btnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSimpanKeyPressed(evt);
            }
        });

        btnBatal.setText("BATAL");
        btnBatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBatalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBatalMouseExited(evt);
            }
        });
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        btnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBatalKeyPressed(evt);
            }
        });

        btnClear.setText("BERSIHKAN");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClearMouseExited(evt);
            }
        });
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        btnClear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnClearKeyPressed(evt);
            }
        });

        jLabel11.setText("USERNAME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSimpan)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBatal)
                                .addGap(35, 35, 35)
                                .addComponent(btnClear))
                            .addComponent(txtNoAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txtStatusPekerjaan)
                            .addComponent(txtNoIdentitas)
                            .addComponent(txtNamaPemustaka)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbProdi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNoTelepon)
                                .addComponent(cmbKatagori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAlamatPemustaka)
                                .addComponent(txtInstansiAsal))
                            .addComponent(txtUsername))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbKatagori, cmbProdi, txtAlamatPemustaka, txtInstansiAsal, txtNamaPemustaka, txtNoAnggota, txtNoIdentitas, txtNoTelepon, txtStatusPekerjaan, txtUsername});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnClear, btnSimpan});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaPemustaka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNoIdentitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtStatusPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtInstansiAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAlamatPemustaka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbKatagori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnClear))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(mode.equalsIgnoreCase("add"))
        {
            if(checkInputData())
            {
                if(C.insertPemustaka(new Pemustaka(Integer.parseInt(C.getNewIDAnggota()),
                        txtUsername.getText().toString().trim(),
                        txtNamaPemustaka.getText().toString().trim(), 
                        txtNoIdentitas.getText().toString().trim(),
                        txtStatusPekerjaan.getText().toString().trim(),
                        txtInstansiAsal.getText().toString().trim(), 
                        txtAlamatPemustaka.getText().toString().trim(), 
                        txtNoTelepon.getText().toString().trim(),
                        C.getKodeProdi(cmbProdi.getSelectedItem().toString()),
                        C.getKodeKatPem(cmbKatagori.getSelectedItem().toString()))))
                    JOptionPane.showMessageDialog(rootPane, "Data Pemustaka Berhasil Dimasukkan");
                else JOptionPane.showMessageDialog(rootPane, "Data Pemustaka Tidak Berhasil Dimasukkan");
                
                afterSet();
            }
        }
        else if(mode.equalsIgnoreCase("edit"))
        {
            if(checkInputData())
            {
                if(C.updatePemustaka(new Pemustaka(Integer.parseInt(data.get(0).toString()),
                        txtUsername.getText().toString().trim(),
                        txtNamaPemustaka.getText().toString().trim(), 
                        txtNoIdentitas.getText().toString().trim(),
                        txtStatusPekerjaan.getText().toString().trim(),
                        txtInstansiAsal.getText().toString().trim(), 
                        txtAlamatPemustaka.getText().toString().trim(), 
                        txtNoTelepon.getText().toString().trim(),
                        C.getKodeProdi(cmbProdi.getSelectedItem().toString()),
                        C.getKodeKatPem(cmbKatagori.getSelectedItem().toString()))))
                    JOptionPane.showMessageDialog(rootPane, "Data Pemustaka Berhasil Diubah");
                else JOptionPane.showMessageDialog(rootPane, "Data Pemustaka Tidak Berhasil Diubah");
                
                afterSet();
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSimpanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnSimpanActionPerformed(null);
    }//GEN-LAST:event_btnSimpanKeyPressed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        PemUI.setInstance();
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBatalKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnBatalActionPerformed(null);
    }//GEN-LAST:event_btnBatalKeyPressed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnClearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnClearKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnClearActionPerformed(null);
    }//GEN-LAST:event_btnClearKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        PemUI.setInstance();
    }//GEN-LAST:event_formWindowClosed

    private void txtNamaPemustakaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPemustakaKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txtNamaPemustakaKeyTyped

    private void txtNoIdentitasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoIdentitasKeyTyped
        // TODO add your handling code here:
        if(! Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txtNoIdentitasKeyTyped

    private void txtStatusPekerjaanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStatusPekerjaanKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txtStatusPekerjaanKeyTyped

    private void txtInstansiAsalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInstansiAsalKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txtInstansiAsalKeyTyped

    private void txtAlamatPemustakaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatPemustakaKeyTyped
        // TODO add your handling code here:
        if(! Character.isLetterOrDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txtAlamatPemustakaKeyTyped

    private void txtNoTeleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTeleponKeyTyped
        // TODO add your handling code here:
        if(! Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txtNoTeleponKeyTyped

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        // TODO add your handling code here:
        btnSimpan.setBackground(Color.green);
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        // TODO add your handling code here:
        btnSimpan.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        // TODO add your handling code here:
        btnBatal.setBackground(Color.red);
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        // TODO add your handling code here:
        btnBatal.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseEntered
        // TODO add your handling code here:
        btnClear.setBackground(Color.yellow);
    }//GEN-LAST:event_btnClearMouseEntered

    private void btnClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseExited
        // TODO add your handling code here:
        btnClear.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnClearMouseExited

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
            java.util.logging.Logger.getLogger(PemustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PemustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PemustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PemustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PemustakaPopUI(mode, PemUI, data).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cmbKatagori;
    private javax.swing.JComboBox cmbProdi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAlamatPemustaka;
    private javax.swing.JTextField txtInstansiAsal;
    private javax.swing.JTextField txtNamaPemustaka;
    private javax.swing.JTextField txtNoAnggota;
    private javax.swing.JTextField txtNoIdentitas;
    private javax.swing.JTextField txtNoTelepon;
    private javax.swing.JTextField txtStatusPekerjaan;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
