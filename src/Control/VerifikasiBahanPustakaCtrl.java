/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.DAO;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Well's-Comp
 */
public class VerifikasiBahanPustakaCtrl {
    private DAO dao;
    private Vector columns;

    public VerifikasiBahanPustakaCtrl() {
        dao = new DAO();
        columns = new Vector();
        
        columns.add("No.");
        columns.add("No. ISBN");
        columns.add("Judul Buku");
        columns.add("Pengarang Buku");
        columns.add("Penerbit Buku");
        columns.add("Jumlah");
        columns.add("Rekomendasi");
    }
    
    public TableModel showDataUsulan(int kode_prodi, String bulan)
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getTableDataUsulan(kode_prodi, bulan), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public void verifyUsulan(int id_detil, int jumlah, String rekomendasi)
    {
        dao.makeConnection();
        dao.verifyUsulan(id_detil, jumlah, rekomendasi);
        dao.closeConnection();
    }
    
    public Vector getBulan()
    {
        dao.makeConnection();
        Vector data = dao.getBulan();
        dao.makeConnection();
        
        return data;
    }
}
