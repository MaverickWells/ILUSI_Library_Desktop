/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.PengelolaanBahanPustakaCtrl;
import Model.BahanPustaka;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import sun.util.calendar.CalendarSystem;

/**
 *
 * @author Well's-Comp
 */
public class PengelolaanBahanPustakaPopUI extends javax.swing.JFrame {
    private static PengelolaanBahanPustakaUI pengBhnPusUI;
    private static PengelolaanBahanPustakaAutoUI pengBhnPusAutoUI;
    private static PengelolaanBahanPustakaCtrl C;
    private static BahanPustaka bahanPustaka;
    private static Vector data;
    private static String mode;
    /**
     * Creates new form PengelolaanBahanPustakaPopUI
     */
    public PengelolaanBahanPustakaPopUI(PengelolaanBahanPustakaUI _pengBhnPusUI,
            String _mode, Vector _data) {
        initComponents();
        
        this.C = new PengelolaanBahanPustakaCtrl();
        
        this.pengBhnPusUI = _pengBhnPusUI;
        this.pengBhnPusAutoUI = null;
        this.mode = _mode;
        this.data = _data;
        
        clear();
        setForm();
    }
    
    public PengelolaanBahanPustakaPopUI(PengelolaanBahanPustakaAutoUI _pengBhnPusAutoUI,
            String _mode, Vector _data) {
        initComponents();
        
        this.C = new PengelolaanBahanPustakaCtrl();
        
        this.pengBhnPusUI = null;
        this.pengBhnPusAutoUI = _pengBhnPusAutoUI;
        this.mode = _mode;
        this.data = _data;
        
        clear();
        setForm();
    }

    //////////////////////////////METHOD-METHOD/////////////////////////////////
    private void clear()
    {
        txtISBN.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtJudul.setText("");
        cmbProdi.setSelectedIndex(-1);
    }
    
    private void setFormEdit()
    {   
        txtISBN.setText(data.get(1).toString());
        txtJudul.setText(data.get(2).toString());
        txtPengarang.setText(data.get(3).toString());
        txtPenerbit.setText(data.get(4).toString());
        
        for(int i=0; i < cmbProdi.getItemCount(); i++)
            if(cmbProdi.getItemAt(i).toString().equalsIgnoreCase(data.get(5).toString()))
                cmbProdi.setSelectedIndex(i);
        
        cmbProdi.setEnabled(false);
        
        btnClear.setVisible(false);
    }
    
    private void setForm()
    {
        setCmbBoxProdi();
        
        if(mode.equalsIgnoreCase("edit"))
        {
            this.setTitle("UBAH DATA");
            
            setFormEdit();            
        }
        else 
        {
            this.setTitle("TAMBAH DATA");
        }
    }
    
    private void setCmbBoxProdi()
    {
        cmbProdi.removeAllItems();
        
        ArrayList<String> temp = C.getCmbBoxDataProdi();
        
        for(int i=0; i < temp.size(); i++)
            cmbProdi.addItem(temp.get(i).toString());
        
        cmbProdi.setSelectedIndex(-1);
    }
    
    private void afterSet()
    {
        if(pengBhnPusUI != null)
        {
            pengBhnPusUI.setInstance();
            pengBhnPusUI.setTableData();
        }
        else
        {
            pengBhnPusAutoUI.setInstance();
            pengBhnPusAutoUI.setTableDataBhnPustaka();
        }
        
        this.dispose();
    }
    
    private boolean checkEmpty()
    {
        if(txtISBN.getText().trim().equalsIgnoreCase("") ||
           txtJudul.getText().trim().equalsIgnoreCase("") ||
           txtPenerbit.getText().trim().equalsIgnoreCase("") ||
           txtPengarang.getText().trim().equalsIgnoreCase("") ||
           cmbProdi.getSelectedIndex() == -1)
            return true;
        
        else return false;
    }
    
    private boolean checkInputData()
    {
        Pattern ptrnAngka = Pattern.compile("[0-9]+");
        Pattern ptrnHuruf = Pattern.compile("[A-Za-z]+");
        Pattern ptrnAlphaNum = Pattern.compile("[0-9A-Za-z]+");
        Pattern ptrnISBN = Pattern.compile("[0-9]{3}-[0-9]-[0-9]{2}-[0-9]{6}-[0-9]");
        
        String noISBN = txtISBN.getText().toString().trim();
        String judul = txtJudul.getText().toString().trim();
        String pengarang = txtPengarang.getText().toString().trim();
        String penerbit = txtPenerbit.getText().toString().trim();
        int prodi = cmbProdi.getSelectedIndex();
        
        try
        {
            if(checkEmpty())
                throw new Exception("Lengkapi Data Yang Masih Kosong");
            else if(noISBN.length() > 17)
                throw new Exception("Panjang Karakter No. ISBN Hanya 17");
            else if(! ptrnISBN.matcher(noISBN).matches())
                throw new Exception("Format ISBN XXX-X-XX-XXXXXX-X (X adalah angka)");
            else if(! ptrnAlphaNum.matcher(judul).matches())
                throw new Exception("Judul Buku Hanya Boleh AlphaNumerik");
            else if(judul.length() > 50)
                throw new Exception("Judul Buku Hanya Boleh 50 Huruf");
            else if(! ptrnHuruf.matcher(pengarang).matches())
                throw new Exception("Nama Pengarang Hanya Boleh Huruf");
            else if(pengarang.length() > 50)
                throw new Exception("Nama Pengarang Hanya Boleh 50 Huruf");
            else if(! ptrnHuruf.matcher(penerbit).matches())
                throw new Exception("Nama Penerbit Hanya Boleh Huruf");
            else if(penerbit.length() > 50)
                throw new Exception("Nama Penerbit Hanya Boleh 50 Huruf");
            else if(prodi == -1)
                throw new Exception("Silakan Pilih Prodi Terlebih Dahulu");
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "PESAN", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            return false;
        }
        
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////
    
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
        txtISBN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPengarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPenerbit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbProdi = new javax.swing.JComboBox();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("PENGELOLAAN BAHAN PUSTAKA");

        jLabel2.setText("No. ISBN");

        jLabel3.setText("JUDUL BUKU");

        jLabel4.setText("PENGARANG BUKU");

        jLabel5.setText("PENERBIT BUKU");

        jLabel7.setText("PRODI PENGUSUL");

        cmbProdi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel7});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbProdi, txtISBN, txtJudul, txtPenerbit, txtPengarang});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnClear, btnSimpan});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(checkInputData())
        {
            int kode_prodi = C.getKodeProdi(cmbProdi.getSelectedItem().toString());
            
            if(this.mode.equalsIgnoreCase("add"))
            {
                DateFormat df = new SimpleDateFormat("yyyy");

                String no_katalog = kode_prodi + "." + df.format(
                        Calendar.getInstance().getTime()) + "-" + C.getMaxKodeKatalog();

                C.insertBahanPustaka(new BahanPustaka(no_katalog, 
                        txtISBN.getText().trim(), txtJudul.getText().trim(), 
                        txtPengarang.getText().trim(), txtPenerbit.getText().trim(), kode_prodi));

                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Ditambahkan");
            }
            else
            {
                C.updateBahanPustaka(new BahanPustaka(data.get(0).toString(), 
                        txtISBN.getText().trim(), txtJudul.getText().trim(),
                        txtPengarang.getText().trim(), txtPenerbit.getText().trim(), kode_prodi));
                
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Diubah");
            }
            afterSet();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSimpanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnSimpanActionPerformed(null);
    }//GEN-LAST:event_btnSimpanKeyPressed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        if(pengBhnPusUI != null) pengBhnPusUI.setInstance();
        else pengBhnPusAutoUI.setInstance();
        
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
        if(pengBhnPusUI != null) pengBhnPusUI.setInstance();
        else pengBhnPusAutoUI.setInstance();
    }//GEN-LAST:event_formWindowClosed

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
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PengelolaanBahanPustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PengelolaanBahanPustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PengelolaanBahanPustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PengelolaanBahanPustakaPopUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PengelolaanBahanPustakaPopUI(pengBhnPusUI, mode, data).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cmbProdi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    // End of variables declaration//GEN-END:variables
}