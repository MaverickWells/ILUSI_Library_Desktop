/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Model.*;
import com.sun.org.apache.xalan.internal.lib.ExsltStrings;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;


/**
 *
 * @author Well's-Comp
 */
public class DAO {
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    private static Connection con;
    public static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String url = "jdbc:sqlserver://localhost;"
            + "DatabaseName=ILUSI_7320; integratedSecurity=true;";
    
    String SQL_USER = "SELECT * FROM TBL_User";
    String SQL_PRODI = "SELECT * FROM TBL_Prodi";
    String SQL_KatPem = "SELECT * FROM TBL_Katagori_Pemustaka";
    String SQL_PEMUSTAKA = "SELECT * FROM TBL_Pemustaka";
    String SQL_ROLE = "SELECT * FROM TBL_Role";
    String SQL_USULAN_BAHAN_PUSTAKA = "SELECT * FROM TBL_Usulan_Bahan_Pustaka";
    String SQL_DETIL_USULAN_BAHAN_PUSTAKA = "SELECT * FROM TBL_Detil_Usulan_Bahan_Pustaka";
    String SQL_BAHAN_PUSTAKA = "SELECT * FROM TBL_BAHAN_PUSTAKA";
    String SQL_TRANSAKSI = "SELECT * FROM TBL_TRANSAKSI";
    String SQL_DETIL_TRANSAKSI = "SELECT * FROM TBL_DETIL_TRANSAKSI";
    
    public DAO(){}
    
    ////////////////////////////////MANDATORY///////////////////////////////////
    public void makeConnection()
    {
        System.out.println("Opening Database....");
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            System.out.println("Success!\n");
        }
        catch(Exception e){
            System.out.println("Error Opening Database");
            System.out.println(e);
        }
    }
    
    public void closeConnection(){
        System.out.println("Closing Database...");
        try{
            con.close();
            System.out.println("Success!\n");
        }
        catch(Exception e){
            System.out.println("Error Closing Database");
            System.out.println(e);
        }
    }
    ////////////////////////////////MANDATORY///////////////////////////////////
    
    ////////////////////////////////LOGIN///////////////////////////////////////
    public boolean checkUsername(String username)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username))
                    status = true;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean checkUsernamePemustaka(String username)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username))
                    status = true;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean checkLoginPengurus(String username, String password)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username) &&
                   rs.getString("password").equalsIgnoreCase(password))
                    status = true;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean checkLoginPemustaka(String username, String password)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username) &&
                   rs.getString("password").equalsIgnoreCase(password))
                    status = true;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public String getKatPem(String username)
    {
        String katagori = "";
        
        try
        {
            Statement statement=con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT TBL_Pemustaka.username, "
                    + " TBL_Katagori_Pemustaka.deskripsi_katagori"
                    + " FROM TBL_Pemustaka"
                    + " JOIN TBL_Katagori_Pemustaka on TBL_Pemustaka.Kode_Katagori ="
                    + " TBL_Katagori_Pemustaka.Kode_Katagori");
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("username").equalsIgnoreCase(username))
                        katagori = rs.getString("deskripsi_katagori");
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return katagori;
    }
    ////////////////////////////////LOGIN///////////////////////////////////////
    
    ////////////////////////////////USER////////////////////////////////////////
    public Vector getTableDataUser(){
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("select * from TBL_User u "
                   + "join TBL_Role r on u.id_role = r.id_role");
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString("status_user").equalsIgnoreCase("1"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("id_user"));                
                        row.add(rs.getString("username"));
                        row.add(rs.getString("nama_role"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public ArrayList<String> getDataCmbBoxRole()
    {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select nama_role from TBL_Role";
        System.out.println("Proses Fetching Data Berjalan");
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data.add(rs.getString("nama_role"));
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public void insertUser(User U)
    {
        try{
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_User"
                + "(id_user, username, password, id_role) VALUES('" 
                + U.getId_user()+"','" + U.getUsername()+ "','" + U.getPassword() + "'," 
                + U.getId_role() + ")");

           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }
        
    }
    
    public String getIdRole(String name)
    {
        String data = "";
        String sql = "select id_role from TBL_Role where nama_role = '" + name + "'";
        System.out.println("Proses Fetching Data Berjalan");
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data = rs.getString("id_role");
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public String getRole(String username)
    {
        String data = "";
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select TBL_Role.nama_role from TBL_User"
                + " join TBL_Role on TBL_User.id_role = TBL_Role.id_role"
                + " where TBL_User.username = '" + username + "'");
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data = rs.getString("nama_role");
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public String getPassword(String username)
    {
        String data = "";
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select password from TBL_User"
                + " where username = '" + username + "'");
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data = rs.getString("password");
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public String getPasswordPemustaka(String username)
    {
        String data = "";
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select password from TBL_Pemustaka"
                + " where username = '" + username + "'");
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data = rs.getString("password");
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public boolean updatePassword(String username, String password)
    {
        try{
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("username").equalsIgnoreCase(username))
                    {
                        rs.updateString("password", password);
                        
                        rs.updateRow();
                    }
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
            
            return false;
        }
        
        return true;
    }
    
    public boolean updatePasswordPemustaka(String username, String password)
    {
        try{
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("username").equalsIgnoreCase(username))
                    {
                        rs.updateString("password", password);
                        
                        rs.updateRow();
                    }
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
            
            return false;
        }
        
        return true;
    }
    
    public String[] getRowDataUser(int id_user)
    {
        String[] data = new String[4];
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER + " u join TBL_Role r"
                    + " on r.id_role = u.id_role");
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getInt("id_user") == id_user)
                    {
                        data[0] = String.valueOf(id_user);
                        data[1] = rs.getString("username");
                        data[2] = rs.getString("password");
                        data[3] = rs.getString("nama_role");
                    }
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return data;
    }
    
    public void updateUser(User U)
    {
        System.out.println("Updating lahan...");
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER);
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_user") == Integer.parseInt(U.getId_user()))
                    {
                        rs.updateString("username", U.getUsername());
                        rs.updateString("password", U.getPassword());

                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error updating a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public void deleteUser(int id_user)
    {
        System.out.println("Deleting lahan...");
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_user") == id_user)
                    {
                        rs.updateString("status_user", "0");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public int getNewIDUser()
    {
        int temp = 1;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USER);
            if(rs != null)
            {               
                rs.last();
                temp = rs.getInt(1) + 1;
            }
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public boolean isUniqueUsername(String username)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username))
                    status = false;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean isSuperAdmin(String username)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username))
                    if(rs.getString("deletable").equalsIgnoreCase("0"))
                        status = true;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public Vector getTableDataCariUser(String user)
    {
        Vector data = new Vector();
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("SELECT * FROM TBL_User u "
                   + "JOIN TBL_Role r ON u.id_role = r.id_role");
           
           if(rs != null)
           {
               Vector row;
               while(rs.next())
               {
                   if(rs.getString("username").toLowerCase().contains(user) &&
                           rs.getString("status_user").equalsIgnoreCase("1"))
                   {
                       row = new Vector(3);
                       for(int i = 1 ; i <= 3; i++)
                       {
                            row.add(rs.getString("id_user"));                
                            row.add(rs.getString("username"));
                            row.add(rs.getString("nama_role"));
                       }

                       data.add(row);
                   }
               }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public boolean checkKaprodi(String kaprodi)
    {
        boolean status = true;
        
        try
        {
            String kodeRole = getIdRole(kaprodi);
            
            Statement statement=con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_USER);
            
            while(rs.next())
            {
                if(rs.getString("id_role").equalsIgnoreCase(kodeRole) &&
                   rs.getString("status_user").equalsIgnoreCase("1"))
                        status = false;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    //////////////////////////////USER//////////////////////////////////////////
    
    /////////////////////////KATAGORI PEMUSTAKA/////////////////////////////////
    public void InsertKatPem(KatagoriPemustaka K)
    {
        try{
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_Katagori_Pemustaka"
                + "(kode_katagori, deskripsi_katagori, masa_keanggotaan, lama_peminjaman)"
                + "VALUES(" + Integer.parseInt(K.getKode_kat()) + ",'" 
                + K.getDesc_kat()+ "','" + K.getMasa_anggota()+ "','"
                + K.getLama_pinjam() + "')");
           
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }
        
    }
    
    public String[] getRowDataKatPem(int kode_katagori)
    {
        String[] text = new String[4];
        
        System.out.println("Getting data from table prodi...");        
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_KatPem);
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getInt("kode_katagori") == kode_katagori)
                    {
                        text[0] = rs.getString("kode_katagori");
                        text[1] = rs.getString("deskripsi_katagori");
                        text[2] = rs.getString("masa_keanggotaan");
                        text[3] = rs.getString("lama_peminjaman");
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a prodi...\n");
            System.out.println(Ex);
        }
        
        return text;
    }
    
    public void updateKatPem(KatagoriPemustaka K)
    {
        System.out.println("Updating prodi...");
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_KatPem);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("kode_katagori") == Integer.parseInt(K.getKode_kat()))
                    {
                        rs.updateString("deskripsi_katagori", K.getDesc_kat());
                        rs.updateString("masa_keanggotaan", K.getMasa_anggota());
                        rs.updateString("lama_peminjaman", K.getLama_pinjam());

                        rs.updateRow();
                    }
                }
            }
            
            System.out.println("Updated prodi\n");
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error updating a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public void deleteKatPem(int kode_katagori)
    {
        System.out.println("Deleting lahan...");
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_KatPem);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("kode_katagori") == kode_katagori)
                    {
                        rs.updateString("status", "0");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            
            System.out.println("Deleted prodi\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a prodi...\n");
            System.out.println(Ex);
        }
    }
    
    public Vector getTableDataKatPem()
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_KatPem);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
               Vector row;
               while(rs.next())
               {
                   if(rs.getString("status").equalsIgnoreCase("1"))
                   {
                        row = new Vector(columnCount);
                        for(int i = 1 ; i <= columnCount; i++)
                        {
                            row.add(rs.getString(i));
                        }

                        data.add(row);
                   }
               }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public Vector getTableDataCariKatPem(String mode, String cari)
    {
        Vector data = new Vector();
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_KatPem);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
               Vector row;
               while(rs.next())
               {
                   if(rs.getString(mode).toLowerCase().contains(cari) &&
                           rs.getString("status").equalsIgnoreCase("1"))
                   {
                       row = new Vector(columnCount);
                       for(int i = 1 ; i <= columnCount; i++)
                       {
                           row.add(rs.getString(i));
                       }

                       data.add(row);
                   }
               }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public boolean isUniqueKodeKat(String kode)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_KatPem);
            
            while(rs.next())
            {
                if(rs.getString(1).equalsIgnoreCase(kode))
                    status = false;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public ArrayList<String> getDataCmbBoxKatPem()
    {
        ArrayList<String> data = new ArrayList<>();
        System.out.println("Proses Fetching Data Berjalan");
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_KatPem);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("status").equalsIgnoreCase("1"))
                        data.add(rs.getString(2));
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public String getKodeKatPem(String name)
    {
        String data = "";
        System.out.println("Proses Fetching Data Berjalan");
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_KatPem +
                    " where deskripsi_katagori = '" + name + "'");
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data = rs.getString(1);
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public boolean checkKatPemUsed(String kode)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            while(rs.next())
            {
                if(rs.getString("kode_katagori").equalsIgnoreCase(kode) &&
                   rs.getString("terminated").equalsIgnoreCase("0"))
                    status = true;
            }
            
            rs.close();
            
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    //////////////////////////KATAGORI PEMUSTAKA////////////////////////////////
    
    //////////////////////////////////PRODI/////////////////////////////////////
    public void InsertProdi(Prodi P)
    {
        try{
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_Prodi(kode_prodi, strata_prodi, nama_prodi)"
                + " VALUES(" + Integer.parseInt(P.getKode()) + ",'" 
                + P.getStrata()+ "','" + P.getNama() + "')");
           
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }
        
    }
    
    public boolean isUniqueKodeProdi(String kode)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PRODI);
            
            while(rs.next())
            {
                if(rs.getString("kode_prodi").equalsIgnoreCase(kode))
                    status = false;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean isUniqueNamaProdi(String nama)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PRODI);
            
            while(rs.next())
            {
                if(rs.getString("nama_prodi").equalsIgnoreCase(nama))
                    status = false;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public String[] getRowDataProdi(int kode_prodi)
    {
        String[] text = new String[3];     
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PRODI);
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getInt("kode_prodi") == kode_prodi)
                    {
                        text[0] = rs.getString("kode_prodi");
                        text[1] = rs.getString("strata_prodi");
                        text[2] = rs.getString("nama_prodi");       
                    }
                }     
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a prodi...\n");
            System.out.println(Ex);
        }
        
        return text;
    }
    
    public void updateProdi(Prodi P)
    {
        System.out.println("Updating prodi...");
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PRODI);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("kode_prodi") == Integer.parseInt(P.getKode()))
                    {
                        rs.updateInt("kode_prodi", Integer.parseInt(P.getKode()));
                        rs.updateString("strata_prodi", P.getStrata());
                        rs.updateString("nama_prodi", P.getNama());

                        rs.updateRow();
                    }
                }
            }
            
            System.out.println("Updated prodi\n");
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error updating a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public void deleteProdi(int kode_prodi)
    {
        System.out.println("Deleting lahan...");
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PRODI);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("kode_prodi") == kode_prodi)
                    {
                        rs.updateString("status", "0");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            
            System.out.println("Deleted prodi\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a prodi...\n");
            System.out.println(Ex);
        }
    }
    
    public Vector getTableDataProdi()
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_PRODI);
          
           if(rs != null)
           {
               Vector row;
               while(rs.next())
               {
                   if(rs.getString("status").equalsIgnoreCase("1"))
                   {
                        row = new Vector(3);

                        for(int i = 1 ; i <= 3; i++)
                        {
                            row.add(rs.getString(i));
                        }

                        data.add(row);
                   }
               }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public Vector getTableDataCariProdi(String mode, String cari)
    {
        Vector data = new Vector();
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_PRODI);
           
           if(rs != null)
           {
               Vector row;
               while(rs.next())
               {
                   if(rs.getString(mode).toLowerCase().contains(cari) &&
                           rs.getString("status").equalsIgnoreCase("1"))
                   {
                       row = new Vector(3);
                       for(int i = 1 ; i <= 3; i++)
                       {
                           row.add(rs.getString(i));
                       }

                       data.add(row);
                   }
               }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public ArrayList<String> getDataCmbBoxProdi()
    {
        ArrayList<String> data = new ArrayList<>();
        System.out.println("Proses Fetching Data Berjalan");
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_PRODI);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("status").equalsIgnoreCase("1"))
                        data.add(rs.getString(3));
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public String getKodeProdi(String name)
    {
        String data = "";
        System.out.println("Proses Fetching Data Berjalan");
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_PRODI +
                    " where nama_prodi = '" + name + "'");
            
            if(rs != null)
            {
                while(rs.next())
                {
                    data = rs.getString(1);
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception e){
            System.out.println("Error Reading From database. . .");
            System.out.println(e);
        }
        
        return data;
    }
    
    public boolean checkProdiUsed(String kode)
    {
        boolean status = false;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            while(rs.next())
            {
                if(rs.getString("kode_prodi").equalsIgnoreCase(kode) &&
                   rs.getString("terminated").equalsIgnoreCase("0"))
                    status = true;
            }
            
            rs.close();
            
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    //////////////////////////////////PRODI/////////////////////////////////////
    
    ///////////////////////////////PEMUSTAKA////////////////////////////////////
    public Vector getTableDataPemustaka(){
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("select * from TBL_Pemustaka Pem"
                   + " join TBL_Prodi Pro on Pem.kode_prodi = Pro.kode_prodi"
                   + " join TBL_Katagori_Pemustaka KP on Pem.kode_katagori = KP.kode_katagori");
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString("terminated").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("no_anggota"));                
                        row.add(rs.getString("username"));
                        row.add(rs.getString("nama_pemustaka"));
                        row.add(rs.getString("no_identitas"));
                        row.add(rs.getString("status_pekerjaan"));
                        row.add(rs.getString("instansi_asal"));
                        row.add(rs.getString("alamat_pemustaka"));
                        row.add(rs.getString("no_telp"));
                        row.add(rs.getString("nama_prodi"));
                        row.add(rs.getString("deskripsi_katagori"));
                        row.add(rs.getString("status_keanggotaan"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public boolean insertPemustaka(Pemustaka P)
    {
        try{
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_Pemustaka(no_anggota, nama_pemustaka,"
                + " no_identitas, status_pekerjaan, instansi_asal, alamat_pemustaka,"
                + " no_telp, kode_prodi, kode_katagori, username) VALUES("
                + P.getNoAnggota() + ",'" + P.getNamaAnggota() + "', '" + P.getIdIdentitas() + "', '"
                + P.getStatusPekerjaan() + "', '" + P.getInstansiAsal() + "', '"
                + P.getAlamatPemustaka() + "', '" + P.getNoTelepon() + "',"
                + P.getKode_prodi() + ", " + P.getKode_katagori() + ",'"
                + P.getUsername() + "')");
           
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println(EX);
            
            return false;
        }
        
        return true;
    }
    
    public boolean updatePemustaka(Pemustaka P)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == P.getNoAnggota())
                    {
                        rs.updateString("username", P.getUsername());
                        rs.updateString("nama_pemustaka", P.getNamaAnggota());
                        rs.updateString("no_identitas", P.getIdIdentitas());
                        rs.updateString("status_pekerjaan", P.getStatusPekerjaan());
                        rs.updateString("instansi_asal", P.getInstansiAsal());
                        rs.updateString("alamat_pemustaka", P.getAlamatPemustaka());
                        rs.updateString("no_telp", P.getNoTelepon());
                        rs.updateInt("kode_prodi", P.getKode_prodi());
                        rs.updateInt("kode_katagori", P.getKode_katagori());

                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
            
            return false;
        }
        
        return true;
    }
    
    public void deletePemustaka(int no_anggota)
    {
        System.out.println("Deleting lahan...");
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota)
                    {
                        rs.updateString("terminated", "1");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public Vector getTableDataCariPemustaka(String mode, String cari)
    {
        Vector data = new Vector();
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("select * from TBL_Pemustaka Pem"
                   + " join TBL_Prodi Pro on Pem.kode_prodi = Pro.kode_prodi"
                   + " join TBL_Katagori_Pemustaka KP on Pem.kode_katagori = KP.kode_katagori");
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
               Vector row;
               while(rs.next())
               {
                   if(rs.getString(mode).toLowerCase().contains(cari) && 
                           rs.getString("terminated").equalsIgnoreCase("0"))
                   {
                       row = new Vector(columnCount);
                       
                       row.add(rs.getString("no_anggota"));                
                        row.add(rs.getString("username"));
                        row.add(rs.getString("nama_pemustaka"));
                        row.add(rs.getString("no_identitas"));
                        row.add(rs.getString("status_pekerjaan"));
                        row.add(rs.getString("instansi_asal"));
                        row.add(rs.getString("alamat_pemustaka"));
                        row.add(rs.getString("no_telp"));
                        row.add(rs.getString("nama_prodi"));
                        row.add(rs.getString("deskripsi_katagori"));
                        row.add(rs.getString("status_keanggotaan"));

                       data.add(row);
                   }
               }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public ArrayList getRowDataPemustaka(int no_anggota)
    {
        ArrayList<String> temp = new ArrayList<>();
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("select * from TBL_Pemustaka Pem"
                   + " join TBL_Prodi Pro on Pem.kode_prodi = Pro.kode_prodi"
                   + " join TBL_Katagori_Pemustaka KP on Pem.kode_katagori = KP.kode_katagori");
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota &&
                       rs.getString("terminated").equalsIgnoreCase("0"))
                    {
                        temp.add(rs.getString("no_anggota"));                
                        temp.add(rs.getString("username"));
                        temp.add(rs.getString("nama_pemustaka"));
                        temp.add(rs.getString("no_identitas"));
                        temp.add(rs.getString("status_pekerjaan"));
                        temp.add(rs.getString("instansi_asal"));
                        temp.add(rs.getString("alamat_pemustaka"));
                        temp.add(rs.getString("no_telp"));
                        temp.add(rs.getString("nama_prodi"));
                        temp.add(rs.getString("deskripsi_katagori"));
                    }
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
 
        return temp;
    }
    
    public String getNewIDAnggota()
    {
        String temp = "1";
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            if(rs != null)
            {               
                rs.last();
                temp = String.valueOf(rs.getInt(1) + 1);
            }
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public void setStatusAktif(int no_anggota)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota)
                    {
                        rs.updateString("status_keanggotaan", "Aktif");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public void setDeaktivasi(int no_anggota)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota)
                    {
                        rs.updateString("status_keanggotaan", "Tidak Aktif");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public ArrayList getRowDataPemUsulan(String username)
    {
        ArrayList<String> temp = new ArrayList<>();
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("select * from TBL_Pemustaka Pem"
                   + " join TBL_Prodi Pro on Pem.kode_prodi = Pro.kode_prodi"
                   + " join TBL_Katagori_Pemustaka KP on Pem.kode_katagori = KP.kode_katagori");
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getString("username").equalsIgnoreCase(username) &&
                       rs.getString("terminated").equalsIgnoreCase("0"))
                    {
                        temp.add(rs.getString("no_anggota"));                
                        temp.add(rs.getString("nama_pemustaka"));
                        temp.add(rs.getString("nama_prodi"));
                        temp.add(rs.getString("deskripsi_katagori"));
                    }
                }
            }
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
 
        return temp;
    }
    
    public String cekPemustaka(int no_anggota)
    {
        String status = "Tidak Terdaftar";
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota)
                        status = rs.getString("status_keanggotaan");
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean statusPemustaka(int no_anggota)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota &&
                       rs.getString("status_keanggotaan").equalsIgnoreCase("Tidak Aktif"))
                        status = false;
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public boolean statusPemustaka(String username)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("username").equalsIgnoreCase(username) &&
                       rs.getString("status_keanggotaan").equalsIgnoreCase("Tidak Aktif"))
                        status = false;
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    
    public int getLamaPinjamPemustaka(int no_anggota)
    {
        int lama = 0;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT P.NO_ANGGOTA, K.LAMA_PEMINJAMAN "
                    + "FROM TBL_PEMUSTAKA P "
                    + "JOIN TBL_KATAGORI_PEMUSTAKA K ON P.KODE_KATAGORI = K.KODE_KATAGORI");
            
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota)
                        lama = rs.getInt("lama_peminjaman");
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return lama;
    }   
    
    public boolean isUniqueUsernamePem(String username)
    {
        boolean status = true;
        
        try
        {
            Statement statement=con.createStatement();
            ResultSet rs = statement.executeQuery(SQL_PEMUSTAKA);
            
            while(rs.next())
            {
                if(rs.getString("username").equalsIgnoreCase(username))
                    status = false;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
        
        return status;
    }
    ///////////////////////////////PEMUSTAKA////////////////////////////////////
    
    //////////////////////////PENGUSULAN BAHAN PUSTAKA//////////////////////////
    public int getNewIDUsulan()
    {
        int temp = 1;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_USULAN_BAHAN_PUSTAKA);
            
            if(rs != null)
            {               
                rs.last();
                temp = rs.getInt("id_request") + 1;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public int getNewIDDetilUsulan()
    {
        int temp = 1;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
            
            if(rs != null)
            {               
                rs.last();
                temp = rs.getInt("id_detil_usulan") + 1;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public void insertNewUsulan(int id, int no_anggota, String tanggal)
    {
        try {
            java.util.Date dt = df.parse(tanggal);
            java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        
        
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_USULAN_BAHAN_PUSTAKA("
                   + "id_request, no_anggota, tanggal) VALUES("
                   + id + ", " + no_anggota + ", " + "CONVERT(DATE, "
                   + "'" + sqlDate + "'))");
           
           statement.close();
        } 
        catch(Exception EX)
        {
            System.out.println(EX);
        }
        
    }
    
    public void insertNewDetilUsulan(int idUsulan, String noISBN, String judul,
            String pengarang, String penerbit, int kode_prodi)
    {
        try {
            Statement statement = con.createStatement();
           
            statement.executeUpdate("INSERT INTO TBL_DETIL_USULAN_BAHAN_PUSTAKA("
                   + "id_detil_usulan, id_request, no_isbn_buku, judul_buku,"
                   + "pengarang_buku, penerbit_buku, kode_prodi) VALUES(" 
                   + this.getNewIDDetilUsulan() + "," + idUsulan + ",'" + noISBN
                   + "','" + judul + "','" + pengarang + "','" + penerbit + "'," 
                   + kode_prodi + ")");
           
            statement.close();
        } 
        catch(Exception EX)
        {
            System.out.println(EX);
        }
        
    }
    
    public int getKodeBulan(String bulan)
    {
        int temp = 1;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM TBL_Bulan");
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getString("nama_bulan").equalsIgnoreCase(bulan))
                        temp = rs.getInt("kode_bulan");
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public Vector getTableDataUsulan(int kode_prodi, String bulan){
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("SELECT * FROM TBL_Detil_Usulan_Bahan_Pustaka" +
                " INNER JOIN TBL_Usulan_Bahan_Pustaka " +    
                " ON TBL_Detil_Usulan_Bahan_Pustaka.id_request = TBL_Usulan_Bahan_Pustaka.id_request" +
                " INNER JOIN TBL_Bulan ON month(TBL_Usulan_Bahan_Pustaka.tanggal) = TBL_Bulan.kode_bulan" +
                " WHERE month(TBL_Usulan_Bahan_Pustaka.tanggal) = " + getKodeBulan(bulan));
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString("rekomendasi").equalsIgnoreCase("belum diverifikasi") &&
                       rs.getString("deleted").equalsIgnoreCase("0") &&
                       (rs.getInt("jumlah") == 0) && 
                       (rs.getInt("kode_prodi") == kode_prodi))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("id_detil_usulan"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));
                        row.add(rs.getString("jumlah"));
                        row.add(rs.getString("rekomendasi"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void verifyUsulan(int id_detil, int jumlah, String rekomendasi)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_detil_usulan") == id_detil)
                    {
                        if(rekomendasi.equalsIgnoreCase("Tidak Disetujui"))
                            rs.updateString("deleted", "1");
                        
                        rs.updateString("rekomendasi", rekomendasi);
                        rs.updateInt("jumlah", jumlah);
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public Vector getTableDataRekapitulasi(){
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString("rekomendasi").equalsIgnoreCase("belum diverifikasi") &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("id_detil_usulan"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void deleteRekapitulasiUsulan(int id_detil)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_detil_usulan") == id_detil)
                    {
                        rs.updateString("deleted", "1");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public Vector getUsulanSearch(String cari)
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString("judul_buku").contains(cari) &&
                       rs.getString("rekomendasi").equalsIgnoreCase("disetujui") &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("id_detil_usulan"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public Vector getRowDataUsulan(int id_detil)
    {
        Vector data = null;
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                
                while(rs.next())
                {
                    if(rs.getInt("id_detil_usulan") == id_detil &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        data = new Vector();
                        
                        data.add(rs.getString("no_isbn_buku"));
                        data.add(rs.getString("judul_buku"));
                        data.add(rs.getString("pengarang_buku"));
                        data.add(rs.getString("penerbit_buku"));
                        data.add(rs.getString("jumlah"));
                        data.add(rs.getString("kode_prodi"));
                        
                        break;
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void deleteUsulanMasukPustaka(int id_detil)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_detil_usulan") == id_detil)
                    {
                        rs.updateString("rekomendasi", "Sudah Masuk");
                        rs.updateString("deleted", "1");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public Vector getBulan()
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("SELECT * FROM TBL_Bulan");
           
           if(rs != null)
           {
               while(rs.next())
                data.add(rs.getString("nama_bulan"));
           }
           
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    //////////////////////////PENGUSULAN BAHAN PUSTAKA//////////////////////////
    
    /////////////////////////PENGELOLAAN BAHAN PUSTAKA//////////////////////////
    public Vector getTableDataBahanPustaka()
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA + " JOIN TBL_PRODI"
                   + " ON TBL_BAHAN_PUSTAKA.KODE_PRODI = TBL_PRODI.KODE_PRODI");
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                
                while(rs.next())
                {
                    if(rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("no_katalog"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void deleteBahanPustaka(String no_katalog)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
            
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("no_katalog").equalsIgnoreCase(no_katalog))
                    {
                        rs.updateString("deleted", "1");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
            System.out.println("Deleted lahan\n");
        }
        catch(Exception Ex)
        {
            System.out.println("Error deleting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public void insertBahanPustaka(BahanPustaka B)
    {
        try {
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_BAHAN_PUSTAKA("
                   + "no_katalog, no_isbn_buku, judul_buku, pengarang_buku, "
                   + "penerbit_buku, kode_prodi) VALUES('"
                   + B.getNoKatalog() + "', '" + B.getNoISBN() + "', '" + B.getJudul()
                   + "', '" + B.getPengarang() + "', '" + B.getPenerbit() + "', "
                   + B.getKode_prodi() + ")");
           
           statement.close();
        } 
        catch(Exception EX)
        {
            System.out.println(EX);
        }
    }
    
    public String getMaxKodeKatalog()
    {
        String no_katalog = "000001";
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
            
            if(rs != null)
            {               
                int temp = 0;
                while(rs.next())
                {
                    if(temp < Integer.parseInt(rs.getString(
                            "no_katalog").substring(9)))
                    {
                        temp = Integer.parseInt(
                            rs.getString("no_katalog").substring(9));
                    }
                }
                
                no_katalog = String.valueOf(temp + 1);
                
                if(no_katalog.length() < 6)
                    no_katalog = String.format("%06d", Integer.parseInt(no_katalog));
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return no_katalog;
    }
    
    public Vector getRowDataBahanPustaka(String no_katalog)
    {
        Vector data = null;
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA + " JOIN TBL_PRODI"
                   + " ON TBL_BAHAN_PUSTAKA.KODE_PRODI = TBL_PRODI.KODE_PRODI");
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                
                while(rs.next())
                {
                    if(rs.getString("no_katalog").equalsIgnoreCase(no_katalog) &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        data = new Vector();

                        data.add(rs.getString("no_katalog"));                
                        data.add(rs.getString("no_isbn_buku"));
                        data.add(rs.getString("judul_buku"));
                        data.add(rs.getString("pengarang_buku"));
                        data.add(rs.getString("penerbit_buku"));
                        data.add(rs.getString("nama_prodi"));
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void updateBahanPustaka(BahanPustaka B)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString("no_katalog").equalsIgnoreCase(B.getNoKatalog()))
                    {
                        rs.updateString("no_isbn_buku", B.getNoISBN());
                        rs.updateString("judul_buku", B.getJudul());
                        rs.updateString("pengarang_buku", B.getPengarang());
                        rs.updateString("penerbit_buku", B.getPenerbit());
                        rs.updateInt("kode_prodi", B.getKode_prodi());

                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
    }  
    
    public Vector getBahanPustakaSearch(String mode, String cari)
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString(mode).contains(cari) &&
                       rs.getString("deleted").equalsIgnoreCase("0") &&
                       rs.getString("status").equalsIgnoreCase("ada"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("no_katalog"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public boolean cekNoISBN(String no_isbn)
    {
        boolean temp = true;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getString("no_isbn_buku").equalsIgnoreCase(no_isbn))
                    {
                        temp = false;   
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    /////////////////////////PENGELOLAAN BAHAN PUSTAKA//////////////////////////
    
    //////////////////////////PENGELOLAAN PEMINJAMAN////////////////////////////
    public int getNewIDTransaksi()
    {
        int temp = 1;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_TRANSAKSI);
            
            if(rs != null)
            {               
                rs.last();
                temp = rs.getInt("id_transaksi") + 1;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public int getNewIDDetilTransaksi()
    {
        int temp = 1;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_DETIL_TRANSAKSI);
            
            if(rs != null)
            {               
                rs.last();
                temp = rs.getInt("id_detil_transaksi") + 1;
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public void insertNewTransaksi(int id, int no_anggota, String tgl_pinjam,
            String tgl_kembali)
    {
        try {
            java.util.Date tgl_pinjam_date = df.parse(tgl_pinjam);
            java.sql.Date tgl_pinjam_sql = new java.sql.Date(tgl_pinjam_date.getTime());
            
            java.util.Date tgl_kembali_date = df.parse(tgl_kembali);
            java.sql.Date tgl_kembali_sql = new java.sql.Date(tgl_kembali_date.getTime());
        
           Statement statement = con.createStatement();
           
           statement.executeUpdate("INSERT INTO TBL_TRANSAKSI(id_transaksi, "
                   + "no_anggota, tanggal_pinjam, tanggal_kembali) VALUES(" 
                   + id + ", " + no_anggota + ", " + "CONVERT(DATE, '"
                   + tgl_pinjam_sql + "'), CONVERT(DATE, '" + tgl_kembali_sql  
                   + "'))");
           
           statement.close();
        } 
        catch(Exception EX)
        {
            System.out.println(EX);
        }
    }
    
    public void insertNewDetilTransaksi(int idTransaksi, String noKatalog)
    {
        try {
            Statement statement = con.createStatement();
           
            statement.executeUpdate("INSERT INTO TBL_DETIL_TRANSAKSI("
                   + "id_detil_transaksi, id_transaksi, no_katalog) VALUES(" 
                   + this.getNewIDDetilTransaksi()+ "," + idTransaksi + ",'"
                   + noKatalog + "')");
           
            statement.close();
        } 
        catch(Exception EX)
        {
            System.out.println(EX);
        }
        
    }
    
    public boolean PinjamBahanPustaka(String no_katalog)
    {
        boolean temp = false;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getString("no_katalog").equalsIgnoreCase(no_katalog))
                    {
                        rs.updateString("status", "Dipinjam");
                        rs.updateRow();
                        
                        temp = true;   
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    
    public void KembaliBahanPustaka(String no_katalog)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    if(rs.getString("no_katalog").equalsIgnoreCase(no_katalog))
                    {
                        rs.updateString("status", "Ada");
                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
    }
    
    public int getJumlahPinjaman(int no_anggota)
    {
        int temp = 0;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(
                    "SELECT count(TBL_Detil_Transaksi.id_detil_transaksi) AS Jumlah " +
                    "FROM TBL_Detil_Transaksi " +
                    "INNER JOIN TBL_Transaksi ON TBL_Detil_Transaksi.id_transaksi = TBL_Transaksi.id_transaksi " +
                    "WHERE TBL_Detil_Transaksi.deleted = 0 AND TBL_Transaksi.no_anggota = " + no_anggota);
            
            if(rs != null)
            {               
                while(rs.next())
                {
                    temp = rs.getInt("Jumlah");
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println("Error selecting a lahan...\n");
            System.out.println(Ex);
        }
        
        return temp;
    }
    //////////////////////////PENGELOLAAN PEMINJAMAN////////////////////////////
    
    /////////////////////////PENGELOLAAN PENGEMBALIAN///////////////////////////
    public Vector getDataPeminjaman(int no_anggota)
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("SELECT * FROM TBL_DETIL_TRANSAKSI D"
                   + " JOIN TBL_BAHAN_PUSTAKA P ON D.NO_KATALOG = P.NO_KATALOG"
                   + " JOIN TBL_TRANSAKSI T ON T.ID_TRANSAKSI = D.ID_TRANSAKSI");
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("id_detil_transaksi"));                
                        row.add(rs.getString("no_katalog"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void pengembalianBahanPustaka(int id, String tglKembaliAktual)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM TBL_DETIL_TRANSAKSI D"
                    + " JOIN TBL_TRANSAKSI T ON T.ID_TRANSAKSI = D.ID_TRANSAKSI");
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_detil_transaksi") == id )
                    {
                        java.util.Date tglAktual = df.parse(tglKembaliAktual);
                        java.sql.Date tglAktualSQL = new java.sql.Date(tglAktual.getTime());
                        
                        rs.updateString("deleted", "1");
                        rs.updateDate("tgl_kembali_aktual", tglAktualSQL);

                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
    }
    
    public void pengembalianBahanPustakaDenda(int id, String tglKembaliAktual,
            int denda)
    {
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM TBL_DETIL_TRANSAKSI D"
                    + " JOIN TBL_TRANSAKSI T ON T.ID_TRANSAKSI = D.ID_TRANSAKSI");
            if(rs != null)
            {
                while(rs.next())
                {
                    if(rs.getInt("id_detil_transaksi") == id )
                    {
                        java.util.Date tglAktual = df.parse(tglKembaliAktual);
                        java.sql.Date tglAktualSQL = new java.sql.Date(tglAktual.getTime());
                        
                        rs.updateString("deleted", "1");
                        rs.updateDate("tgl_kembali_aktual", tglAktualSQL);
                        rs.updateInt("denda", denda);

                        rs.updateRow();
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
    }
    
    public String getTanggalKembali(int no_anggota, String no_katalog)
    {
        String data = null;
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery("SELECT * FROM TBL_DETIL_TRANSAKSI D"
                   + " JOIN TBL_TRANSAKSI T ON T.ID_TRANSAKSI = D.ID_TRANSAKSI");
           
           if(rs != null)
           {
                while(rs.next())
                {
                    if(rs.getInt("no_anggota") == no_anggota &&
                       rs.getString("no_katalog").equalsIgnoreCase(no_katalog) &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        data = rs.getString("tanggal_kembali");
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public void setPrinted(ArrayList<String> no_detil)
    {
        int counter = 0;
        
        try
        {
            Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(SQL_DETIL_TRANSAKSI);
            if(rs != null)
            {
                while(rs.next())
                {
                    for(int i = 0; i < no_detil.size(); i++)
                    {
                        if(rs.getString("id_detil_transaksi").equalsIgnoreCase(no_detil.get(i).toString()))
                        {
                            rs.updateString("printed", "1");                           

                            rs.updateRow();
                        }
                    }
                }
            }
            
            rs.close();
            statement.close();
        }
        catch(Exception Ex)
        {
            System.out.println(Ex);
        }
    }
    /////////////////////////PENGELOLAAN PENGEMBALIAN///////////////////////////
    
    //////////////////////////PENGELOLAAN PEMESANAN/////////////////////////////
    public Vector getTableDataUsulanVerified(){
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_DETIL_USULAN_BAHAN_PUSTAKA);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                while(rs.next())
                {
                    if(rs.getString("rekomendasi").equalsIgnoreCase("disetujui") &&
                       rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("id_detil_usulan"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));
                        row.add(rs.getString("jumlah"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    
    public Vector getTableDataBahanPustakaPemesanan()
    {
        Vector data = new Vector();
        System.out.println("Proses fetching data berjalan. . .");
        
        try{
           Statement statement = con.createStatement();
           ResultSet rs = statement.executeQuery(SQL_BAHAN_PUSTAKA);
           ResultSetMetaData meta = rs.getMetaData();
           
           int columnCount = meta.getColumnCount();
           
           if(rs != null)
           {
                Vector row;
                
                while(rs.next())
                {
                    if(rs.getString("deleted").equalsIgnoreCase("0"))
                    {
                        row = new Vector(columnCount);

                        row.add(rs.getString("no_katalog"));                
                        row.add(rs.getString("no_isbn_buku"));
                        row.add(rs.getString("judul_buku"));
                        row.add(rs.getString("pengarang_buku"));
                        row.add(rs.getString("penerbit_buku"));

                        data.add(row);
                    }
                }
           }
           rs.close();
           statement.close();
        }
        catch(Exception EX)
        {
            System.out.println("Error Reading From database. . .");
            System.out.println(EX);
        }

        return data;
    }
    //////////////////////////PENGELOLAAN PEMESANAN/////////////////////////////
}