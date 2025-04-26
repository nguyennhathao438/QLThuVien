/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.TheLoai;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author tung7
 */
public class TheLoaiDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    public ArrayList<TheLoai> layDSTheLoai()
    {
        ArrayList<TheLoai> dsTL = new ArrayList<>();
        String query = "select * from TheLoai";
        try(Connection conn = kn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query))
        {
             TheLoai tl;
            while(rs.next())
            {
                tl = new TheLoai();
                tl.setMaTheLoai(rs.getString("maTheLoai"));
                tl.setTheLoai(rs.getString("theLoai"));
                tl.setTrangThai(rs.getInt("trangThai"));
                dsTL.add(tl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTL;
    }
    
    public int themTheLoai(TheLoai tl)
    {
        int kq = -1;
        String query = "INSERT INTO TheLoai (maTheLoai,theLoai,trangThai) VALUES(?,?,1)";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, tl.getMaTheLoai());
            stmt.setString(2, tl.getTheLoai());
            kq = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public int suaTheLoai(TheLoai tl)
    {
        int kq = -1;
        String query = "UPDATE TheLoai SET theLoai=? WHERE maTheLoai=?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, tl.getTheLoai());
            stmt.setString(2, tl.getMaTheLoai());
            kq = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
            return kq;
    }
    
    public int xoaTheLoai(String maTL)
    {
        int kq = -1;
        String query = "UPDATE TheLoai SET trangThai=0 WHERE maTheLoai=?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, maTL);
            kq = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public TheLoai layTheLoai(String maTL)
    {
        TheLoai tl = new TheLoai();
        String query = "SELECT * FROM TheLoai WHERE maTheloai=?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, maTL);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                tl.setMaTheLoai(rs.getString("maTheLoai"));
                tl.setTheLoai(rs.getString("theLoai"));
                tl.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tl;
    }
}
