/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import MODEL.PhieuNhap;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author Nghia0605
 */
public class PhieuNhapDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    
    public ArrayList<PhieuNhap> selectAll(){
        ArrayList<PhieuNhap> dsPN = new ArrayList<>();
        String query = "SELECT * FROM PHIEUNHAP WHERE trangThai = 1";
        try (Connection conn = kn.getConnection();
            PreparedStatement prs = conn.prepareStatement(query);
            ResultSet rs = prs.executeQuery()){
            
            while(rs.next()){
                String maPN = rs.getString("maPNhap");
                LocalDateTime date = rs.getObject("thoiGian", LocalDateTime.class);
                double tongtien = rs.getDouble("tongTien");
                String maNCC = rs.getString("maNCC");
                String maTT = rs.getString("maThuThu");
                int status = rs.getInt("trangThai");
//                if(status != 0){
                    dsPN.add(new PhieuNhap(maPN, date, tongtien, maNCC, maTT, status));
//                }
            }
            
        } catch (SQLException e) {
        System.err.println("Loi khi lay du lieu phieu nhap: " + e.getMessage());                
        }
        return dsPN;
    }
    
    public boolean update(PhieuNhap pn){
        String query = "UPDATE PHIEUNHAP SET thoiGian = ?, tongTien = ?, maNCC = ?, maThuThu = ? WHERE maPNhap = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setObject(1, pn.getThoiGian());
            prs.setDouble(2, pn.getTongTien());
            prs.setString(3, pn.getMaNCC());
            prs.setString(4, pn.getMaThuThu());
            prs.setString(5, pn.getMaPhieuNhap());
            return prs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phiếu nhập: " + e.getMessage());        
            return false;
        }
        
    }
    public boolean insert(PhieuNhap pn){
        String query = "INSERT INTO PHIEUNHAP(maPNhap,thoiGian,tongTien,maNCC,maThuThu,trangThai) VALUES (?,?,?,?,?,1)";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setString(1, pn.getMaPhieuNhap());
            prs.setObject(2, pn.getThoiGian());
            prs.setDouble(3, pn.getTongTien());
            prs.setString(4, pn.getMaNCC());
            prs.setString(5, pn.getMaThuThu());
            
            return prs.executeUpdate() > 0;
        }catch (SQLException e) {
            System.err.println("Lỗi khi chèn phiếu nhập: " + e.getMessage());        
            return false;
        }
        
    }
    public boolean delete(String maPN){
        String query = "UPDATE PHIEUNHAP SET trangThai = 0 WHERE maPhieuNhap = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, maPN);
            
            return prs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Loi khi xoa phieu nhap!");
            return false;
        }
    }
}
