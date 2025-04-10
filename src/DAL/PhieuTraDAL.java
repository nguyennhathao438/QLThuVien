
package DAL;

import MODEL.CTPhat;
import MODEL.PhieuTra;
import MODEL.QuyDinh;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuTraDAL {
    static KetNoiCSDL kn=new KetNoiCSDL();
    public ArrayList<PhieuTra> layDSPTra(){ 
       String query="SELECT maPTra,ngayThucTra,maPMuon,tenThuThu,PHIEUTRA.trangThai,maPhuThu FROM PHIEUTRA,THUTHU WHERE PHIEUTRA.maThuThu=THUTHU.maThuThu";
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
            pt.setTenThuThu(rs.getString("tenThuThu"));
            pt.setTrangThai(rs.getInt("trangThai"));
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
}
