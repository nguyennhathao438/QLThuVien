/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import MODEL.TKTongChiTheoNgay;
import MODEL.TKTongChiTheoQuy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Nghia0605
 */
public class TKTongChiDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    
    public ArrayList<TKTongChiTheoNgay> TKTongChiTheoNgay(Timestamp fromDate, Timestamp toDate){
        ArrayList<TKTongChiTheoNgay> result = new ArrayList();
        String query = "SELECT CONVERT(DATE,PN.thoiGian) AS ngay, "
                + "SUM(PN.tongTien) AS tongchi "
                + "FROM PHIEUNHAP PN "
                + "WHERE PN.thoiGian BETWEEN ? AND ? "
                + "GROUP BY CONVERT(DATE,PN.thoiGian) "
                + "ORDER BY ngay";
        try (Connection conn = kn.getConnection();
              PreparedStatement prs = conn.prepareStatement(query) ){
            
            prs.setTimestamp(1, fromDate);
            prs.setTimestamp(2, toDate);
            
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                Date date = rs.getObject("ngay", Date.class);
                double tongchi = rs.getDouble("tongchi");
                result.add(new TKTongChiTheoNgay(date, tongchi));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("khong the thong ke tong chi theo ngay");
        }
        return result;
    }
    
    public ArrayList<TKTongChiTheoQuy> TKTongChiTheoQuy(int nam){
        ArrayList<TKTongChiTheoQuy> result = new ArrayList<>();
        double[] tien = new double[5];
        String query = "SELECT s.tenSach,"
                + " SUM(CASE WHEN MONTH(pn.thoiGian) BETWEEN 1 AND 3 THEN ct.soLuong * ct.donGia ELSE 0 END) AS Q1,"
                + " SUM(CASE WHEN MONTH(pn.thoiGian) BETWEEN 4 AND 6 THEN ct.soLuong * ct.donGia ELSE 0 END) AS Q2,"
                + " SUM(CASE WHEN MONTH(pn.thoiGian) BETWEEN 7 AND 9 THEN ct.soLuong * ct.donGia ELSE 0 END) AS Q3,"
                + " SUM(CASE WHEN MONTH(pn.thoiGian) BETWEEN 10 AND 12 THEN ct.soLuong * ct.donGia ELSE 0 END) AS Q4,"
                + " SUM(ct.soLuong * ct.donGia) AS tong"
                + " FROM CTPHIEUNHAP ct"
                + " JOIN PHIEUNHAP pn ON ct.maPNhap = pn.maPNhap"
                + " JOIN SACH s ON s.maSach = ct.maSach"
                + " WHERE YEAR(pn.thoiGian) = ?"
                + " GROUP BY s.tenSach" ;
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query);){
            
            prs.setInt(1, nam);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                String tenSach = rs.getString("tenSach");
                double Q1 = rs.getDouble("Q1");
                double Q2 = rs.getDouble("Q2");
                double Q3 = rs.getDouble("Q3");
                double Q4 = rs.getDouble("Q4");
                double tong  = rs.getDouble("tong");
                
                result.add(new TKTongChiTheoQuy(tenSach, Q1, Q2, Q3, Q4, tong));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Khong the lay thong ke theo Quys");
        }
        return result; 
    }
    
}
