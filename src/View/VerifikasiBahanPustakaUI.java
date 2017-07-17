/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.VerifikasiBahanPustakaCtrl;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.Color;

/**
 *
 * @author Well's-Comp
 */
public class VerifikasiBahanPustakaUI extends javax.swing.JFrame {
    private static VerifikasiBahanPustakaCtrl C;
    private JTextField jumlah;
    private static int kodeProdi;
    /**
     * Creates new form RekapitulasiBahanPustakaUI
     */
    public VerifikasiBahanPustakaUI(int _kodeProdi) {
        initComponents();
        
        C = new VerifikasiBahanPustakaCtrl();
        jumlah = null;
        this.kodeProdi = _kodeProdi;
        
        System.out.println(kodeProdi);
        
        clear();
        setForm();
    }

    //////////////////////////////METHOD-METHOD/////////////////////////////////
    private void clear()
    {
        tableData.clearSelection();
    }
    
    private void setForm()
    {
        this.setTitle("VERIFIKASI USULAN BAHAN PUSTAKA");
        
        setBulan();
        setTableData();
        setTable();        
    }
    
    private void setBulan()
    {
        cmbBulan.setModel(new DefaultComboBoxModel<String>(C.getBulan()));
        cmbBulan.setSelectedIndex(0);
    }
    
    private void setTableData()
    {
        this.tableData.setModel(C.showDataUsulan(kodeProdi, 
                cmbBulan.getSelectedItem().toString()));
        
        setTableEditor();
    }
    
    private void setTable()
    {
        for(int i = 0; i < tableData.getColumnCount(); i++)
        {
            Class<?> column = tableData.getColumnClass(i);
            tableData.setDefaultEditor(column, null);
        }
        
        tableData.setRowHeight(25);
        
        tableData.getTableHeader().setReorderingAllowed(false);
        
        tableData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        tableData.setDragEnabled(false);
        tableData.setShowGrid(true);
        
        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void setTableEditor()
    {
        final JComboBox cmbVerifikasi = new JComboBox(new String[] 
            {"Belum Diverifikasi", "Disetujui", "Tidak Disetujui"});
        
        cmbVerifikasi.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem().toString().equalsIgnoreCase("Disetujui"))
                    cmbVerifikasi.setBackground(Color.green);
                else if(e.getItem().toString().equalsIgnoreCase("Belum Diverifikasi"))
                    cmbVerifikasi.setBackground(Color.lightGray);
                if(e.getItem().toString().equalsIgnoreCase("Tidak Disetujui"))
                    cmbVerifikasi.setBackground(Color.red);
            }
        });
        
        tableData.getColumnModel().getColumn(6).setCellEditor(
                new DefaultCellEditor(cmbVerifikasi));
        
        jumlah = new JTextField();
        jumlah.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                    if(jumlah.getText().length() == 0 || 
                            jumlah.getText().equalsIgnoreCase(""))
                        jumlah.setText("0");
            }
            
            public void keyTyped(java.awt.event.KeyEvent evt){
                if(Character.isLetter(evt.getKeyChar()) ||
                   ! Character.isDigit(evt.getKeyChar()))
                    evt.consume();
            }
        });
        
        jumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(jumlah.getText().equalsIgnoreCase("0"))
                    jumlah.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(jumlah.getText().length() == 0)
                    jumlah.setText("0");
            }
        });
        
        jumlah.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                jumlah.setText("");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(jumlah.getText().length() == 0)
                    jumlah.setText("0");
            }
            
            
        });
        
        tableData.getColumnModel().getColumn(5).setCellEditor(
                new DefaultCellEditor(jumlah));
        
        
    }
    
    private boolean checkVerifikasi()
    {
        for(int i = 0; i < tableData.getRowCount(); i++)
        {
            
            if(tableData.getValueAt(i, 6).toString().equalsIgnoreCase("Disetujui"))
            {
                if(tableData.getValueAt(i, 5).toString().equalsIgnoreCase("") || 
                        Integer.parseInt(tableData.getValueAt(i, 5).toString()) == 0)
                {
                    tableData.editCellAt(i, 5);
                    
                    jumlah.requestFocusInWindow();
                    
                    return false;
                }
            }
        }
        return true;
    }
    //////////////////////////////METHOD-METHOD/////////////////////////////////
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnSelesai = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbBulan = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("DAFTAR USULAN BAHAN PUSTAKA");

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableDataKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableData);

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

        btnSelesai.setText("SELESAI");
        btnSelesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelesaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelesaiMouseExited(evt);
            }
        });
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });
        btnSelesai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSelesaiKeyPressed(evt);
            }
        });

        jLabel2.setText("SILAKAN KLIK KOLOM JUMLAH DAN VERIFIKASI");

        jLabel3.setText("UNTUK MENGUBAH STATUS VERIFIKASI DAN JUMLAH");

        jLabel4.setText("BULAN");

        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbBulan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBulanItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelesai)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSelesai, btnSimpan});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSimpan)
                        .addComponent(btnSelesai))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(checkVerifikasi())
        {
                
            int pil = JOptionPane.showConfirmDialog(rootPane, 
                    "Simpan Hasil Verifikasi?", "Konfirmasi Penyimpanan", 
                    JOptionPane.YES_NO_OPTION);
        
            if(pil == 0)
            {
                for(int i = 0; i < tableData.getRowCount(); i++)
                    {
                        C.verifyUsulan(Integer.parseInt(tableData.getValueAt(i, 0).toString()), 
                                Integer.parseInt(tableData.getValueAt(i, 5).toString()), 
                                tableData.getValueAt(i, 6).toString());
                    }

                    setTableData();
            }
        }
        else
            JOptionPane.showMessageDialog(rootPane, 
                    "Silahkan Masukkan Jumlah Yang Masih Kosong/Nol "
                            + "\nUntuk Bahan Pustaka Disetujui");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSimpanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnSimpanActionPerformed(null);
    }//GEN-LAST:event_btnSimpanKeyPressed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void btnSelesaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSelesaiKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnSelesaiActionPerformed(null);
    }//GEN-LAST:event_btnSelesaiKeyPressed

    private void tableDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDataKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            evt.consume();
    }//GEN-LAST:event_tableDataKeyPressed

    private void cmbBulanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBulanItemStateChanged
        // TODO add your handling code here:
        setTableData();
    }//GEN-LAST:event_cmbBulanItemStateChanged

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        // TODO add your handling code here:
        btnSimpan.setBackground(Color.green);
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        // TODO add your handling code here:
        btnSimpan.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSelesaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelesaiMouseEntered
        // TODO add your handling code here:
        btnSelesai.setBackground(Color.cyan);
    }//GEN-LAST:event_btnSelesaiMouseEntered

    private void btnSelesaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelesaiMouseExited
        // TODO add your handling code here:
        btnSelesai.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnSelesaiMouseExited

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
            java.util.logging.Logger.getLogger(VerifikasiBahanPustakaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerifikasiBahanPustakaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerifikasiBahanPustakaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerifikasiBahanPustakaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerifikasiBahanPustakaUI(kodeProdi).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cmbBulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableData;
    // End of variables declaration//GEN-END:variables
}
