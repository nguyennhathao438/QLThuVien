
package DAL;

import MODEL.CTPhat;
import MODEL.PhieuTra;
import MODEL.QuyDinh;
import MODEL.SachTra;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuTraDAL {
     KetNoiCSDL kn=new KetNoiCSDL();
    public ArrayList<PhieuTra> layDSPTra(){ 
       String query="SELECT * FROM PHIEUTRA";
    try(Connection conn = kn.getConnection();
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
            ){ 
        ArrayList<PhieuTra> dspt=new ArrayList();
        
        while(rs.next()){ 
            PhieuTra pt =new PhieuTra();
            pt.setMaPhieuMuon(rs.getString("maPMuon"));
            pt.setMaPhieuTra(rs.getString("maPTra"));
            pt.setNgayThucTra(rs.getDate("ngayThucTra"));
            pt.setTrangThai(rs.getInt("trangThai"));
            pt.setMaThuThu(rs.getString("maThuThu"));
            pt.setMaPhuThu(rs.getString("maPhuThu"));
            dspt.add(pt);
        }
        return dspt;
        
    }   catch (SQLServerException ex) {
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}  
    public int taoPhieuPhat(String maPhieuTra,String maPhuThu,String[] dsmqd,double tienPhat) throws SQLException{ 
        int ketqua = -1;
        String queryPhuThu = "INSERT INTO PHUTHU(maPhuThu,tienPhat,trangThai) VALUES (?,?, 1)";
        String queryPhieuTra= "UPDATE PHIEUTRA SET maPhuThu = ? WHERE maPTra = ?";
        String queryCTPhat = "INSERT INTO CTPHAT (maPhuThu, maQuyDinh) VALUES (?, ?)";
        Connection conn=kn.getConnection();
        try(
                PreparedStatement pstPhuThu = conn.prepareStatement(queryPhuThu);
                PreparedStatement pstPhieuTra = conn.prepareStatement(queryPhieuTra);
                PreparedStatement pstCTPhat = conn.prepareStatement(queryCTPhat);
                
                ){ 
            conn.setAutoCommit(false);
            ResultSet rs=null;
            //PhuThu
            pstPhuThu.setString(1,maPhuThu);
            pstPhuThu.setDouble(2, tienPhat);
            pstPhuThu.executeUpdate();
            //PhieuTra
             
            pstPhieuTra.setString(1, maPhuThu);
            pstPhieuTra.setString(2, maPhieuTra);
            ketqua = pstPhieuTra.executeUpdate();
            
            //CTPhat
            for(int i=0;i<dsmqd.length;i++){ 
                if(dsmqd[i] != null && !dsmqd[i].isEmpty()){
                pstCTPhat.setString(1, maPhuThu);
                pstCTPhat.setString(2, dsmqd[i]);
                pstCTPhat.executeUpdate();
                }
            }
            conn.commit();
            
        } catch (SQLServerException ex) {
            
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.rollback();
            }
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua ;
    }
    public CTPhat getCTPhat(String maPhieuTra){ 
        CTPhat ctp= new CTPhat();
        String query="SELECT phieuTRA.maPhuThu,PHUTHU.tienPhat,QUYDINH.maQuyDinh,QUYDINH.noiDung,QUYDINH.soTien FROM PHIEUTRA JOIN PHUTHU ON PHIEUTRA.maPhuThu = PHUTHU.maPhuThu JOIN CTPHAT ON PHUTHU.maPHUTHU = CTPHAT.maPhuThu JOIN QUYDINH ON CTPHAT.maQuyDinh=QUYDINH.maQuyDinh WHERE maPTra=?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt=conn.prepareStatement(query)
                ){
            stmt.setString(1,maPhieuTra);
            ResultSet rs = stmt.executeQuery();
            ArrayList<QuyDinh> dsqd = new ArrayList();
            QuyDinh qd;
            if(rs.next()){ 
               qd = new QuyDinh();
                ctp.setMaPhuThu(rs.getString("maPhuThu"));
                ctp.setThanhTien(rs.getDouble("tienPhat"));
                qd.setMaQuyDinh(rs.getString("maQuyDinh"));
                qd.setNoiDung(rs.getString("noiDung"));
                qd.setSoTien(rs.getDouble("soTien"));
                dsqd.add(qd);
            }
            while(rs.next()){ 
                qd= new QuyDinh();
                qd.setMaQuyDinh(rs.getString("maQuyDinh"));
                qd.setNoiDung(rs.getString("noiDung"));
                qd.setSoTien(rs.getDouble("soTien"));
                dsqd.add(qd);
            }
            ctp.setDsqd(dsqd);
        } catch (SQLServerException ex) {
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctp ;
    }
    public int xoaPhieuTra(String maPhieuTra){ 
        int kq = -1 ;
        String query="UPDATE PHIEUTRA SET trangThai = 0 WHERE maPTra =?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt= conn.prepareStatement(query)){ 
            stmt.setString(1,maPhieuTra);
            kq = stmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq ;
    }
    public ArrayList<SachTra> laySachChuaTra(String mapm){ 
        ArrayList<SachTra> dsst = new ArrayList();
        String query ="SELECT maSach, soLuongChuaTra  FROM CTPHIEUMUON WHERE maPhieuMuon= ? ";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ){ 
            stmt.setString(1,mapm);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                SachTra st = new SachTra();
                st.setMaSach(rs.getString("maSach"));
                st.setSoLuongChuaTra(rs.getInt("soLuongChuaTra"));
                dsst.add(st);
            }
        } catch (SQLServerException ex) {
             Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
         }
        return dsst;
        
    }
    public int taoPhieuTra(PhieuTra pt,ArrayList<SachTra> dsdt,ArrayList<SachTra> dsct) throws SQLServerException{ 
        int kt = -1;
        String qcttra="INSERT INTO CTPHIEUTRA (maPhieuTra,maSach,soLuong) VALUES (?,?,?)";
        String qctmuon="UPDATE CTPHIEUMUON SET soLuongChuaTra =? WHERE maSach = ? AND maPhieuMuon = ? ";
        String qtra ="INSERT INTO PHIEUTRA (maPTra,ngayThucTra,maPMuon,maThuThu,trangThai) VALUES (?,?,?,?,1)";
        Connection conn = kn.getConnection();
        try( PreparedStatement pcttra = conn.prepareStatement(qcttra);
                PreparedStatement pctmuon = conn.prepareStatement(qctmuon);
                PreparedStatement ptra = conn.prepareStatement(qtra)){ 
            conn.setAutoCommit(false);
            //Sách chưa trả trong CTPHIEUMUON
            for(SachTra a:dsct){ 
                pctmuon.setInt(1,a.getSoLuongChuaTra());
                pctmuon.setString(2,a.getMaSach());
                pctmuon.setString(3,pt.getMaPhieuMuon());
                pctmuon.executeUpdate();
            }
            //Phiếu trả
            ptra.setString(1,pt.getMaPhieuTra());
            ptra.setDate(2, new java.sql.Date(pt.getNgayThucTra().getTime()) );
            ptra.setString(3,pt.getMaPhieuMuon());
            ptra.setString(4,pt.getMaThuThu());
            ptra.executeUpdate();
            //Chi tiết trả
            for(SachTra a:dsdt){ 
                pcttra.setString(1,pt.getMaPhieuTra());
                 pcttra.setString(2,a.getMaSach());
                 pcttra.setInt(3,a.getSoLuongChuaTra());
                 pcttra.executeUpdate();
            }
            conn.commit();
            kt = 1;
            
        } catch (Exception e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PhieuTraDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
             e.printStackTrace();
         } 
        return kt;
    }
}
