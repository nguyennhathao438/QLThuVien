package DAL;

import MODEL.CTPhieuMuon;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author NGOC TUYEN
 */ 
public class CTPhieuMuonDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    
    public ArrayList<CTPhieuMuon> getAllCTPMuon(String ma){
        ArrayList<CTPhieuMuon> dsCTPM = new ArrayList<>();
        String query = "SELECT * FROM CTPHIEUMUON WHERE maPhieuMuon = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setString(1, ma);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setMaPMuon(rs.getString("maPhieuMuon"));
                ctpm.setMaSach(rs.getString("maSach"));
                ctpm.setSoLuong(rs.getInt("soLuongChuaTra"));
                ctpm.setTrangThai(rs.getInt("trangThai"));
                dsCTPM.add(ctpm);
            }            
        } catch (Exception e) {
            System.err.println("Lỗi lấy dữ liệu chi tiết phiếu mượn" + e.getMessage());
        }
        return dsCTPM;
    }
    
    public boolean themCTPM(ArrayList<CTPhieuMuon> t){
        String query = "INSERT INTO CTPHIEUMUON(maPhieuMuon,maSach,soLuongChuaTra,trangThai) VALUES(?,?,?,1)";
        for(int i = 0; i < t.size(); i++){
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query)){
                
                prs.setString(1, t.get(i).getMaPMuon());
                prs.setString(2, t.get(i).getMaSach());
                prs.setInt(3, t.get(i).getSoLuong());
                
                if (prs.executeUpdate() <= 0) {
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Lỗi thêm dữ liệu chi tiết phiếu mượn" + e.getMessage());
                return false;
            }
        }
        return true;
    }
    
    
    
    public boolean xoaCTPM(String ma){
        String query = "UPDATE CTPHIEUMUON SET trangThai = 0 WHERE maPhieuMuon = ?";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setString(1, ma);
            
            return prs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Lỗi xóa dữ liệu chi tiết phiếu mượn" + e.getMessage());
            return false;
        }        
    }
    
}
