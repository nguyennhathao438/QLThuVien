package DAL;

import MODEL.TKDocGia;
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

    public double[] tkHoatDongThang(int thang, int nam) {
        double data[] = new double[6];
        String qpm = "SELECT COUNT(maPMuon) as col FROM PHIEUMUON WHERE (MONTH(ngayMuon) =? OR ? IS NULL) AND YEAR(ngayMuon) = ?";
        String qpt = "SELECT COUNT(maPTra) as col FROM PHIEUTRA WHERE (MONTH(ngayThucTra) =? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        String qpn = "SELECT COUNT(maPNhap) as col FROM PHIEUNHAP WHERE (MONTH(thoiGian) =? OR ? IS NULL) AND YEAR(thoiGian) = ?";
        String qtn = "SELECT SUM(tongTien) as col FROM PHIEUNHAP WHERE  (MONTH(thoiGian) =? OR ? IS NULL) AND YEAR(thoiGian) = ?";
        String qphth = "SELECT COUNT(maPhuThu) as col FROM PHIEUTRA WHERE (MONTH(ngayThucTra) =? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        String qtphth = "SELECT SUM(tienPhat) as col FROM PHUTHU \n"
                + "JOIN PHIEUTRA ON PHIEUTRA.maPhuThu= PHUTHU.maPhuThu\n"
                + "WHERE  (MONTH(ngayThucTra) =? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        try (Connection conn = kn.getConnection()) {
            data[0] = exeCuteTKThang(conn, qpm, thang, nam);
            data[1] = exeCuteTKThang(conn, qpt, thang, nam);
            data[2] = exeCuteTKThang(conn, qpn, thang, nam);
            data[3] = exeCuteTKThang(conn, qtn, thang, nam);
            data[4] = exeCuteTKThang(conn, qphth, thang, nam);
            data[5] = exeCuteTKThang(conn, qtphth, thang, nam);
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

    }

    private double exeCuteTKThang(Connection conn, String query, int thang, int nam) {
        double result = 0;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (thang == 0) {
                stmt.setNull(1, Types.INTEGER);
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(1, thang);
                stmt.setInt(2, thang);
            }
            stmt.setInt(3, nam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("col");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public double[] tkHoatDongQuy(int quy, int nam) {
        double data[] = new double[6];
        String qpm = "SELECT COUNT(maPMuon) as col FROM PHIEUMUON WHERE (DATEPART(QUARTER, ngayMuon) = ? OR ? IS NULL) AND YEAR(ngayMuon) = ?";
        String qpt = "SELECT COUNT(maPTra) as col FROM PHIEUTRA WHERE (DATEPART(QUARTER, ngayThucTra) = ? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        String qpn = "SELECT COUNT(maPNhap) as col FROM PHIEUNHAP WHERE (DATEPART(QUARTER, thoiGian) = ? OR ? IS NULL) AND YEAR(thoiGian) = ?";
        String qtn = "SELECT SUM(tongTien) as col FROM PHIEUNHAP WHERE (DATEPART(QUARTER, thoiGian) = ? OR ? IS NULL) AND YEAR(thoiGian) = ?";
        String qphth = "SELECT COUNT(maPhuThu) as col FROM PHIEUTRA WHERE (DATEPART(QUARTER, ngayThucTra) = ? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        String qtphth = "SELECT SUM(tienPhat) as col FROM PHUTHU "
                + "JOIN PHIEUTRA ON PHIEUTRA.maPhuThu = PHUTHU.maPhuThu "
                + "WHERE (DATEPART(QUARTER, ngayThucTra) = ? OR ? IS NULL) AND YEAR(ngayThucTra) = ?";
        try (Connection conn = kn.getConnection()) {
            data[0] = exeCuteTKThang(conn, qpm, quy, nam);
            data[1] = exeCuteTKThang(conn, qpt, quy, nam);
            data[2] = exeCuteTKThang(conn, qpn, quy, nam);
            data[3] = exeCuteTKThang(conn, qtn, quy, nam);
            data[4] = exeCuteTKThang(conn, qphth, quy, nam);
            data[5] = exeCuteTKThang(conn, qtphth, quy, nam);
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;

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

    private double exeCuteTKNgay(Connection conn, String sql, Date fromdate, Date todate) {
        double result = 0;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(todate.getTime()));
            ps.setDate(2, new java.sql.Date(fromdate.getTime()));
            System.out.println(new java.sql.Date(fromdate.getTime()));
            System.out.println(new java.sql.Date(todate.getTime()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("col");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<TKDocGia> getTKDocGia() {
        ArrayList<TKDocGia> dstk = new ArrayList();
        String query = "SELECT \n"
                + "    DG.tenDocGia, \n"
                + "    PM.soPhieuMuon, \n"
                + "    CT.slSachMuon, \n"
                + "	ST.slSachTra,\n"
                + "    PT.soQuyDinhPhat\n"
                + "FROM DOCGIA DG\n"
                + "LEFT JOIN (\n"
                + "    SELECT maDocGia, COUNT(DISTINCT maPMuon) as soPhieuMuon\n"
                + "    FROM PHIEUMUON\n"
                + "    GROUP BY maDocGia\n"
                + ") PM ON DG.maDocGia = PM.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTPM.soLuong) as slSachMuon\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN CTPHIEUMUON CTPM ON PM.maPMuon = CTPM.maPhieuMuon\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") CT ON DG.maDocGia = CT.maDocGia\n"
                + "LEFT JOIN (\n"
                + "	SELECT PM.maDocGia,SUM(CTT.soLuong) as slSachTra\n"
                + "	FROM PHIEUMUON PM\n"
                + "	JOIN PHIEUTRA PT ON PM.maPMuon=PT.maPMuon\n"
                + "	JOIN CTPHIEUTRA CTT ON PT.maPTra = CTT.maPhieuTra\n"
                + "	GROUP BY maDocGia \n"
                + ") ST ON DG.maDocGIA =ST.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, COUNT(CTP.maQuyDinh) as soQuyDinhPhat\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN PHUTHU PH ON PT.maPhuThu = PH.maPhuThu\n"
                + "    JOIN CTPHAT CTP ON PH.maPhuThu = CTP.maPhuThu\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") PT ON DG.maDocGia = PT.maDocGia;";
        try (Connection conn = kn.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            TKDocGia tkdg;
            while (rs.next()) {
                tkdg = new TKDocGia();
                tkdg.setTenDocGia(rs.getString("tenDocGia"));
                tkdg.setSoPhieuMuon(rs.getInt("soPhieuMuon"));
                tkdg.setSoSachMuon(rs.getInt("slSachMuon"));
                tkdg.setSoSachTra(rs.getInt("slSachTra"));
                tkdg.setSoLanSaiQD(rs.getInt("soQuyDinhPhat"));
                dstk.add(tkdg);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }

    public ArrayList<TKDocGia> getTKDocGiaThang(int thang, int nam) {
        ArrayList<TKDocGia> dstk = new ArrayList();
        String query = "SELECT \n"
                + "    DG.tenDocGia, \n"
                + "    PM.soPhieuMuon, \n"
                + "    CT.slSachMuon, \n"
                + "    ST.slSachTra, \n"
                + "    PT.soQuyDinhPhat\n"
                + "FROM DOCGIA DG\n"
                + "LEFT JOIN (\n"
                + "    SELECT maDocGia, COUNT(DISTINCT maPMuon) AS soPhieuMuon\n"
                + "    FROM PHIEUMUON\n"
                + "    WHERE MONTH(ngayMuon) = ? AND YEAR(ngayMuon) = ?\n"
                + "    GROUP BY maDocGia\n"
                + ") PM ON DG.maDocGia = PM.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTPM.soLuong) AS slSachMuon\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN CTPHIEUMUON CTPM ON PM.maPMuon = CTPM.maPhieuMuon\n"
                + "    WHERE MONTH(PM.ngayMuon) = ? AND YEAR(PM.ngayMuon) = ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") CT ON DG.maDocGia = CT.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTT.soLuong) AS slSachTra\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN CTPHIEUTRA CTT ON PT.maPTra = CTT.maPhieuTra\n"
                + "    WHERE MONTH(PT.ngayThucTra) = ? AND YEAR(PT.ngayThucTra) = ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") ST ON DG.maDocGia = ST.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, COUNT(CTP.maQuyDinh) AS soQuyDinhPhat\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN PHUTHU PH ON PT.maPhuThu = PH.maPhuThu\n"
                + "    JOIN CTPHAT CTP ON PH.maPhuThu = CTP.maPhuThu\n"
                + "    WHERE MONTH(PT.ngayThucTra) = ? AND YEAR(PT.ngayThucTra) = ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") PT ON DG.maDocGia = PT.maDocGia;";
        try (Connection conn = kn.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            
            for (int i = 1; i <= 8; i++) {
                if (i % 2 == 0) {
                    stmt.setInt(i, nam);
                } else {
                    stmt.setInt(i, thang);
                }
            }
            TKDocGia tkdg;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tkdg = new TKDocGia();
                tkdg.setTenDocGia(rs.getString("tenDocGia"));
                tkdg.setSoPhieuMuon(rs.getInt("soPhieuMuon"));
                tkdg.setSoSachMuon(rs.getInt("slSachMuon"));
                tkdg.setSoSachTra(rs.getInt("slSachTra"));
                tkdg.setSoLanSaiQD(rs.getInt("soQuyDinhPhat"));
                dstk.add(tkdg);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }

    public ArrayList<TKDocGia> getTKDocGiaQuy(int quy, int nam) {
        ArrayList<TKDocGia> dstk = new ArrayList();
        String query = "SELECT \n"
                + "    DG.tenDocGia, \n"
                + "    PM.soPhieuMuon, \n"
                + "    CT.slSachMuon, \n"
                + "    ST.slSachTra, \n"
                + "    PT.soQuyDinhPhat\n"
                + "FROM DOCGIA DG\n"
                + "LEFT JOIN (\n"
                + "    SELECT maDocGia, COUNT(DISTINCT maPMuon) AS soPhieuMuon\n"
                + "    FROM PHIEUMUON\n"
                + "    WHERE DATEPART(QUARTER,ngayMuon) = ? AND YEAR(ngayMuon) = ?\n"
                + "    GROUP BY maDocGia\n"
                + ") PM ON DG.maDocGia = PM.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTPM.soLuong) AS slSachMuon\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN CTPHIEUMUON CTPM ON PM.maPMuon = CTPM.maPhieuMuon\n"
                + "    WHERE DATEPART(QUARTER,PM.ngayMuon) = ? AND YEAR(PM.ngayMuon) = ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") CT ON DG.maDocGia = CT.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTT.soLuong) AS slSachTra\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN CTPHIEUTRA CTT ON PT.maPTra = CTT.maPhieuTra\n"
                + "    WHERE DATEPART(QUARTER,PT.ngayThucTra) = ? AND YEAR(PT.ngayThucTra) = ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") ST ON DG.maDocGia = ST.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, COUNT(CTP.maQuyDinh) AS soQuyDinhPhat\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN PHUTHU PH ON PT.maPhuThu = PH.maPhuThu\n"
                + "    JOIN CTPHAT CTP ON PH.maPhuThu = CTP.maPhuThu\n"
                + "    WHERE DATEPART(QUARTER,PT.ngayThucTra) = ? AND YEAR(PT.ngayThucTra) = ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") PT ON DG.maDocGia = PT.maDocGia;";
        try (Connection conn = kn.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            
            for (int i = 1; i <= 8; i++) {
                if (i % 2 == 0) {
                    stmt.setInt(i, nam);
                } else {
                    stmt.setInt(i, quy);
                }
            }
            TKDocGia tkdg;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tkdg = new TKDocGia();
                tkdg.setTenDocGia(rs.getString("tenDocGia"));
                tkdg.setSoPhieuMuon(rs.getInt("soPhieuMuon"));
                tkdg.setSoSachMuon(rs.getInt("slSachMuon"));
                tkdg.setSoSachTra(rs.getInt("slSachTra"));
                tkdg.setSoLanSaiQD(rs.getInt("soQuyDinhPhat"));
                dstk.add(tkdg);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }

    public ArrayList<TKDocGia> getTKDocGiaJNgay(Date fromdate, Date todate) {
        ArrayList<TKDocGia> dstk = new ArrayList();
        String query = "SELECT \n"
                + "    DG.tenDocGia, \n"
                + "    PM.soPhieuMuon, \n"
                + "    CT.slSachMuon, \n"
                + "    ST.slSachTra, \n"
                + "    PT.soQuyDinhPhat\n"
                + "FROM DOCGIA DG\n"
                + "LEFT JOIN (\n"
                + "    SELECT maDocGia, COUNT(DISTINCT maPMuon) AS soPhieuMuon\n"
                + "    FROM PHIEUMUON\n"
                + "    WHERE ngayMuon BETWEEN ? AND ?\n"
                + "    GROUP BY maDocGia\n"
                + ") PM ON DG.maDocGia = PM.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTPM.soLuong) AS slSachMuon\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN CTPHIEUMUON CTPM ON PM.maPMuon = CTPM.maPhieuMuon\n"
                + "    WHERE PM.ngayMuon BETWEEN ? AND ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") CT ON DG.maDocGia = CT.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, SUM(CTT.soLuong) AS slSachTra\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN CTPHIEUTRA CTT ON PT.maPTra = CTT.maPhieuTra\n"
                + "    WHERE PT.ngayThucTra BETWEEN ? AND ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") ST ON DG.maDocGia = ST.maDocGia\n"
                + "LEFT JOIN (\n"
                + "    SELECT PM.maDocGia, COUNT(CTP.maQuyDinh) AS soQuyDinhPhat\n"
                + "    FROM PHIEUMUON PM\n"
                + "    JOIN PHIEUTRA PT ON PM.maPMuon = PT.maPMuon\n"
                + "    JOIN PHUTHU PH ON PT.maPhuThu = PH.maPhuThu\n"
                + "    JOIN CTPHAT CTP ON PH.maPhuThu = CTP.maPhuThu\n"
                + "    WHERE PT.ngayThucTra BETWEEN ? AND ?\n"
                + "    GROUP BY PM.maDocGia\n"
                + ") PT ON DG.maDocGia = PT.maDocGia;";
        try (Connection conn = kn.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            
            for (int i = 1; i <= 8; i++) {
                if (i % 2 == 0) {
                    stmt.setDate(i, new java.sql.Date(todate.getTime()));
                } else {
                    stmt.setDate(i, new java.sql.Date(fromdate.getTime()));
                }
            }
            TKDocGia tkdg;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tkdg = new TKDocGia();
                tkdg.setTenDocGia(rs.getString("tenDocGia"));
                tkdg.setSoPhieuMuon(rs.getInt("soPhieuMuon"));
                tkdg.setSoSachMuon(rs.getInt("slSachMuon"));
                tkdg.setSoSachTra(rs.getInt("slSachTra"));
                tkdg.setSoLanSaiQD(rs.getInt("soQuyDinhPhat"));
                dstk.add(tkdg);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }
}
