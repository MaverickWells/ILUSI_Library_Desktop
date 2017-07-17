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
public class RekapitulasiUsulanBahanPustakaCtrl {
    private DAO dao;
    private Vector columns;

    public RekapitulasiUsulanBahanPustakaCtrl() {
        dao = new DAO();
        columns = new Vector();
        
        columns.add("No.");
        columns.add("No. ISBN");
        columns.add("Judul Buku");
        columns.add("Pengarang Buku");
        columns.add("Penerbit Buku");
    }
    
    public TableModel showDataRekapitulasiUsulan()
    {
        dao.makeConnection();
        DefaultTableModel tableModel = new DefaultTableModel(
                dao.getTableDataRekapitulasi(), columns);
        dao.closeConnection();
        
        return tableModel;
    }
    
    public void deleteRekapitulasiUsulan(int id_detil)
    {
        dao.makeConnection();
        dao.deleteRekapitulasiUsulan(id_detil);
        dao.closeConnection();
    }
}
