/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import MODEL.CTPhieuNhap;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Nghia0605
 */
public class CTPhieuNhapDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    
    public boolean insert(ArrayList<CTPhieuNhap> t){
        String query = "INSERT INTO CTPHIEUNHAP(maPNhap,maSach,soLuong,donGia) VALUES(?,?,?,?)";
        for(int i = 0; i < t.size(); i++){
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query)){
                
                prs.setString(1, t.get(i).getMaPNhap());
                prs.setString(2, t.get(i).getMaSach());
                prs.setInt(3, t.get(i).getSoLuong());
                prs.setDouble(4, t.get(i).getDonGia());
                
                if (prs.executeUpdate() <= 0) {
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Khong the chen chi tiet hoa don");
                return false;
            }
        }
        return true;
    }
    
    
    public ArrayList<CTPhieuNhap> selectAll(String ma){
        ArrayList<CTPhieuNhap> dsCTPN = new ArrayList<>();
        String query = "SELECT * FROM CTPHIEUNHAP WHERE maPNhap = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setString(1, ma);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPNhap(rs.getString("maPNhap"));
                ctpn.setMaSach(rs.getString("maSach"));
                ctpn.setSoLuong(rs.getInt("soLuong"));
                ctpn.setDonGia(rs.getDouble("donGia"));
                dsCTPN.add(ctpn);
            }            
        } catch (Exception e) {
            System.err.println("Loi lay du lieu chi tiet phieu nhap");
        }
        return dsCTPN;
    }
    
    public boolean delete(String ma){
        String query = "DELETE FROM CTPHIEUNHAP WHERE maPNhap = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setString(1, ma);
            
            return prs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Khong the xoa chi tiet phieu nhap");
            return false;
        }        
    }
}
