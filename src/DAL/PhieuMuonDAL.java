/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.PhieuMuon;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGOC TUYEN
 */
public class PhieuMuonDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    public ArrayList<PhieuMuon> getAllPhieuMuon(){ 
        ArrayList<PhieuMuon> dspm = new ArrayList();
        String query = "SELECT maPMuon,ngayMuon,ngayTra,PHIEUMUON.maDocGia,tenDocGia,PHIEUMUON.maThuThu,tenThuThu,PHIEUMUON.trangThai FROM PHIEUMUON JOIN DOCGIA ON PHIEUMUON.maDocGia= DOCGIA.maDocGia JOIN THUTHU ON THUTHU.maThuThu=PHIEUMUON.maThuThu ";
        try(Connection conn = kn.getConnection();
                Statement stmt = conn.createStatement();
                        ResultSet rs= stmt.executeQuery(query);
                ){
             while(rs.next()){ 
                 PhieuMuon pm = new PhieuMuon();
                 pm.setMaPhieuMuon(rs.getString("maPMuon"));
                 pm.setNgayMuon(rs.getDate("ngayMuon"));
                 pm.setNgayTra(rs.getDate("ngayTra"));
                 pm.setTenDocGia(rs.getString("tenDocGia"));
                 pm.setTenThuThu(rs.getString("tenThuThu"));
                 pm.setMaDocGia(rs.getString("maDocGia"));
                 pm.setMaThuThu(rs.getString("maThuThu"));
                 pm.setTrangThai(rs.getInt("trangThai"));
                 dspm.add(pm);
             }
    }   catch (SQLServerException ex) {
            Logger.getLogger(PhieuMuonDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuMuonDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dspm;
    }
}
