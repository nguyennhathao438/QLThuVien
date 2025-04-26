/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import MODEL.CTPhieuNhap;
import Model.Sach;
import java.util.ArrayList;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SachDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    public ArrayList<Sach> layDSSach()
    {
        ArrayList<Sach> dsSach = new ArrayList<>();
        String query = "SELECT * FROM Sach";
        try(Connection conn = kn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query))
        {
            Sach s;
            while(rs.next())
            {
                s = new Sach();
                s.setMaSach(rs.getString("maSach"));
                s.setTenSach(rs.getString("tenSach"));
                s.setNamXuatBan(rs.getInt("namXuatBan"));
                s.setSoLuong(rs.getInt("soLuong"));
                s.setDonGia(rs.getDouble("donGia"));
                s.setTrangThai(rs.getInt("trangThai"));
                s.setMaTheLoai(rs.getString("maTheLoai"));
                s.setMaTacGia(rs.getString("maTacGia"));
                dsSach.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsSach;
    }   
    
    public int themSach(Sach s)
    {
        int kq = -1;
        String query = "INSERT INTO SACH(maSach,tenSach,namXuatBan,soLuong,donGia,maTacGia,maTheLoai,trangThai) VALUES(?,?,?,?,?,?,?,1)";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1,s.getMaSach());
            stmt.setString(2,s.getTenSach());
            stmt.setInt(3,s.getNamXuatBan());
            stmt.setInt(4,s.getSoLuong());
            stmt.setDouble(5, s.getDonGia());
            stmt.setString(6, s.getMaTacGia());
            stmt.setString(7, s.getMaTheLoai());
            kq = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public int xoaSach(String maS)
    {
        int kq = -1;
        String query = "UPDATE SACH SET trangThai=0 WHERE maSach=?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1,maS);
            kq = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public int suaSach(Sach s)
    {
        int kq = -1;
        String query = "UPDATE SACH SET tenSach=?,namXuatBan=?,soLuong=?,donGia=?,maTacGia=?,maTheLoai? WHERE maSach=?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            
            stmt.setString(1, s.getTenSach());
            stmt.setInt(2, s.getNamXuatBan());
            stmt.setInt(3, s.getSoLuong());
            stmt.setDouble(4, s.getDonGia());
            stmt.setString(5, s.getMaSach());
            stmt.setString(6, s.getMaTacGia());
            stmt.setString(7, s.getMaTheLoai());
            kq = stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    
    public Sach laySach(String maS)
    {
        String query = "SELECT * FROM Sach WHERE maSach=?";
        Sach s = new Sach();
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1,maS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                s.setMaSach(rs.getString("maSach"));
                s.setTenSach(rs.getString("tenSach"));
                s.setNamXuatBan(rs.getInt("namXuatBan"));
                s.setSoLuong(rs.getInt("soLuong"));
                s.setDonGia(rs.getDouble("donGia"));
                s.setMaTacGia(rs.getString("maTacGia"));
                s.setMaTheLoai(rs.getString("maTheLoai"));
                s.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public boolean updateSoLuongSach(ArrayList<CTPhieuNhap> ctpn){
        String query = "UPDATE SACH SET soLuong = soLuong + ? WHERE maSach = ?";
        for(int i = 0; i < ctpn.size(); i++){
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query)){
                
                prs.setInt(1, ctpn.get(i).getSoLuong());
                prs.setString(2, ctpn.get(i).getMaSach());
                
                if(prs.executeUpdate() <= 0){
                    return false;
                }
                
            } catch (Exception e) {
                System.err.println("Khong the cap nhat so luong sach");
                return false;
            }
        }
        return true;
    }
}
