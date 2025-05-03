
package DAL;

import MODEL.TKThuThu;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
public class ThongKeDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    public double[] tkHoatDongThang(int thang , int nam){ 
        double data[]=new double[6];
        String qpm= "SELECT COUNT(maPMuon) as col FROM PHIEUMUON WHERE (MONTH(ngayMuon) =? OR ? IS NULL) AND YEAR(ngayMuon) = ?";
        String qpt="SELECT COUNT(maPTra) as col FROM PHIEUTRA WHERE (MONTH(ngayThucTra) =? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        String qpn="SELECT COUNT(maPNhap) as col FROM PHIEUNHAP WHERE (MONTH(thoiGian) =? OR ? IS NULL) AND YEAR(thoiGian) = ?";
        String qtn="SELECT SUM(tongTien) as col FROM PHIEUNHAP WHERE  (MONTH(thoiGian) =? OR ? IS NULL) AND YEAR(thoiGian) = ?";
        String qphth="SELECT COUNT(maPhuThu) as col FROM PHIEUTRA WHERE (MONTH(ngayThucTra) =? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        String qtphth="SELECT SUM(tienPhat) as col FROM PHUTHU \n" +
"JOIN PHIEUTRA ON PHIEUTRA.maPhuThu= PHUTHU.maPhuThu\n" +
"WHERE  (MONTH(ngayThucTra) =? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        try(Connection conn = kn.getConnection()){ 
            data[0] = exeCuteTKThang(conn,qpm,thang,nam);
            data[1] = exeCuteTKThang(conn,qpt,thang,nam);
            data[2] = exeCuteTKThang(conn,qpn,thang,nam);
            data[3] = exeCuteTKThang(conn,qtn,thang,nam);
            data[4] = exeCuteTKThang(conn,qphth,thang,nam);
            data[5] = exeCuteTKThang(conn,qtphth,thang,nam);
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data ;
        
    }
    private double exeCuteTKThang(Connection conn,String query, int thang,int nam){ 
        double result = 0 ;
        try(PreparedStatement stmt = conn.prepareStatement(query)){ 
            if(thang == 0){ 
                stmt.setNull(1,Types.INTEGER );
                stmt.setNull(2,Types.INTEGER );
            }else{ 
                stmt.setInt(1,thang);
                stmt.setInt(2,thang);
            }
            stmt.setInt(3,nam);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){ 
                result = rs.getDouble("col");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public double[] tkHoatDongQuy(int quy , int nam){ 
        double data[]=new double[6];
        String qpm = "SELECT COUNT(maPMuon) as col FROM PHIEUMUON WHERE (DATEPART(QUARTER, ngayMuon) = ? OR ? IS NULL) AND YEAR(ngayMuon) = ?";
    String qpt = "SELECT COUNT(maPTra) as col FROM PHIEUTRA WHERE (DATEPART(QUARTER, ngayThucTra) = ? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
    String qpn = "SELECT COUNT(maPNhap) as col FROM PHIEUNHAP WHERE (DATEPART(QUARTER, thoiGian) = ? OR ? IS NULL) AND YEAR(thoiGian) = ?";
    String qtn = "SELECT SUM(tongTien) as col FROM PHIEUNHAP WHERE (DATEPART(QUARTER, thoiGian) = ? OR ? IS NULL) AND YEAR(thoiGian) = ?";
    String qphth = "SELECT COUNT(maPhuThu) as col FROM PHIEUTRA WHERE (DATEPART(QUARTER, ngayThucTra) = ? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
    String qtphth = "SELECT SUM(tienPhat) as col FROM PHUTHU " +
                    "JOIN PHIEUTRA ON PHIEUTRA.maPhuThu = PHUTHU.maPhuThu " +
                    "WHERE (DATEPART(QUARTER, ngayThucTra) = ? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        try(Connection conn = kn.getConnection()){ 
            data[0] = exeCuteTKThang(conn,qpm,quy,nam);
            data[1] = exeCuteTKThang(conn,qpt,quy,nam);
            data[2] = exeCuteTKThang(conn,qpn,quy,nam);
            data[3] = exeCuteTKThang(conn,qtn,quy,nam);
            data[4] = exeCuteTKThang(conn,qphth,quy,nam);
            data[5] = exeCuteTKThang(conn,qtphth,quy,nam);
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data ;
        
    }
    public double[] tkHoatDongNgay(Date ngayBatDau, Date ngayKetThuc) {
        double data[] = new double[6];

        String qpm = "SELECT COUNT(maPMuon) as col FROM PHIEUMUON WHERE ngayMuon BETWEEN ? AND ?";
        String qpt = "SELECT COUNT(maPTra) as col FROM PHIEUTRA WHERE ngayThucTra BETWEEN ? AND ?";
        String qpn = "SELECT COUNT(maPNhap) as col FROM PHIEUNHAP WHERE thoiGian BETWEEN ? AND ?";
        String qtn = "SELECT SUM(tongTien) as col FROM PHIEUNHAP WHERE thoiGian BETWEEN ? AND ?";
        String qphth = "SELECT COUNT(maPhuThu) as col FROM PHIEUTRA WHERE ngayThucTra BETWEEN ? AND ?";
        String qtphth = "SELECT SUM(tienPhat) as col FROM PHUTHU "
                + "JOIN PHIEUTRA ON PHIEUTRA.maPhuThu = PHUTHU.maPhuThu "
                + "WHERE ngayThucTra BETWEEN ? AND ?";

        try (Connection conn = kn.getConnection()) {
            data[0] = exeCuteTKNgay(conn, qpm, ngayBatDau, ngayKetThuc);
            data[1] = exeCuteTKNgay(conn, qpt, ngayBatDau, ngayKetThuc);
            data[2] = exeCuteTKNgay(conn, qpn, ngayBatDau, ngayKetThuc);
            data[3] = exeCuteTKNgay(conn, qtn, ngayBatDau, ngayKetThuc);
            data[4] = exeCuteTKNgay(conn, qphth, ngayBatDau, ngayKetThuc);
            data[5] = exeCuteTKNgay(conn, qtphth, ngayBatDau, ngayKetThuc);
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
}   
    private double exeCuteTKNgay(Connection conn, String sql, Date fromdate, Date todate){
    double result = 0;
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        
                ps.setDate(1, new java.sql.Date(todate.getTime()));
        ps.setDate(2,new java.sql.Date(fromdate.getTime()));
                System.out.println(new java.sql.Date(fromdate.getTime()));
              System.out.println(new java.sql.Date(todate.getTime()));
       
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getDouble("col");
        }
    }   catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    return result;
}
    public ArrayList<TKThuThu> getTKThuThu(int nam){
        ArrayList<TKThuThu> result = new ArrayList<>();
        String query = "SELECT tt.maThuThu, tt.tenThuThu,"
                + "(SELECT COUNT(*) FROM PHIEUMUON pm WHERE pm.maThuThu = tt.maThuThu AND YEAR(pm.ngayMuon) = ?) AS soPhieuMuon, " 
                + "(SELECT COUNT(*) FROM PHIEUTRA pt WHERE pt.maThuThu = tt.maThuThu AND YEAR(pt.ngayThucTra)= ?) AS soPhieuTra, "
                + "(SELECT COUNT(*) FROM PHIEUNHAP pn WHERE pn.maThuThu = tt.maThuThu AND YEAR(pn.thoiGian) = ?) AS soPhieuNhap "
                + "FROM THUTHU tt";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)){
            
            prs.setInt(1, nam);
            prs.setInt(2, nam);
            prs.setInt(3, nam);
            
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                String ma = rs.getString("maThuThu");
                String ten = rs.getString("tenThuThu");
                int muon = rs.getInt("soPhieuMuon");
                int tra = rs.getInt("soPhieuTra");
                int nhap = rs.getInt("soPhieuNhap");
                if (muon == 0 && tra == 0 && nhap == 0) continue;
                result.add(new TKThuThu(ma, ten, muon, tra, nhap));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
