/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.PhieuMuon;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhieuMuonDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    
    public ArrayList<PhieuMuon> getAllPhieuMuon(){ 
        ArrayList<PhieuMuon> dspm = new ArrayList();
        String query = "SELECT * FROM PHIEUMUON";
        try(Connection conn = kn.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(query);){
            
            while(rs.next()){ 
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieuMuon(rs.getString("maPMuon"));
                pm.setNgayMuon(rs.getObject("ngayMuon", LocalDate.class));
                pm.setNgayTra(rs.getObject("ngayTra", LocalDate.class));
                pm.setMaDocGia(rs.getString("maDocGia"));
                pm.setMaThuThu(rs.getString("maThuThu"));
                pm.setTrangThai(rs.getInt("trangThai"));
                dspm.add(pm);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(PhieuMuonDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dspm;
    }
    
    public boolean themPM(PhieuMuon pm){
        String query = "INSERT INTO PHIEUMUON(maPMuon,ngayMuon,ngayTra,maDocGia,maThuThu,trangThai) VALUES (?,?,?,?,?,1)";
        try (Connection conn = kn.getConnection();
            PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setString(1, pm.getMaPhieuMuon());
            prs.setObject(2, pm.getNgayMuon());
            prs.setObject(3, pm.getNgayTra());
            prs.setString(4, pm.getMaDocGia());
            prs.setString(5, pm.getMaThuThu());
            
            return prs.executeUpdate() > 0;
        }catch (SQLException e) {
            System.err.println("Lỗi khi thêm phiếu mượn: " + e.getMessage());        
            return false;
        }
        
    }
    
    public boolean suaPM(PhieuMuon pm){
        String query = "UPDATE PHIEUMUON SET ngayMuon = ?, ngayTra = ?, maDocGia = ?, maThuThu = ? WHERE maPMuon = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setObject(1, pm.getNgayMuon());
            prs.setObject(2, pm.getNgayTra());
            prs.setString(3, pm.getMaDocGia());
            prs.setString(4, pm.getMaThuThu());
            prs.setString(5, pm.getMaPhieuMuon());
            return prs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phiếu mượn: " + e.getMessage());        
            return false;
        }
        
    }
    
    public boolean xoaPM(String maPM){
        String query = "UPDATE PHIEUMUON SET trangThai = 0 WHERE maPMuon = ?";
        try (Connection conn = kn.getConnection();
            PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, maPM);
            
            return prs.executeUpdate()> 0;
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa phiếu mượn" + e.getMessage());
            return false;
        }
    }
    
    
}
