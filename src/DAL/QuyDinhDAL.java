
package DAL;

import BLL.DSQuyDinh;
import MODEL.QuyDinh;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuyDinhDAL {
    KetNoiCSDL kn=new KetNoiCSDL();
    public DSQuyDinh layQuyDinh() throws SQLException{
        DSQuyDinh dsqd=new DSQuyDinh();
        String query ="SELECT * FROM QuyDinh";
        try (Connection conn = kn.getConnection();
            Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)){
            
        while(rs.next()){ 
            int maqd=rs.getInt("maQuyDinh");
            String noidung = rs.getString("noiDung");
            double soTien =rs.getDouble("soTien");
            int trangThai =rs.getInt("trangThai");
            dsqd.themQuyDinh(new QuyDinh(maqd,noidung,soTien,trangThai));
        }
               
        } catch (SQLServerException ex) {
            System.out.println("Lay du lieu that bai");
        }     
        return dsqd;
    }
    public void themQuyDinh(QuyDinh qd){ 
        String query = "INSERT INTO QuyDinh(noiDung,soTien,trangThai) VALUES(?,?,1) ";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt =conn.prepareStatement(query);
            ){ 
            stmt.setString(1,qd.getNoiDung());
            stmt.setDouble(2, qd.getSoTien());
            stmt.executeUpdate();
        }catch (SQLServerException ex) {
            System.out.println("Them quy dinh that bai");
        } catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public void xoaQuyDinh(int maqd){ 
        String query ="UPDATE QuyDinh SET trangThai=0 WHERE maQuyDinh=?";
        try(Connection conn=kn.getConnection();
                PreparedStatement stmt= conn.prepareStatement(query)){ 
            stmt.setInt(1,maqd);
            stmt.executeUpdate();          
        } catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void suaQuyDinh(QuyDinh qd){ 
        String query= "UPDATE QuyDinh SET noiDung=? , soTien=? WHERE maQuyDinh=?";
        try(Connection conn=kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){  
            stmt.setString(1, qd.getNoiDung());
            stmt.setDouble(2, qd.getSoTien());
            stmt.setInt(3, qd.getMaQuyDinh());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public QuyDinh getQuyDinh(int maqd){ 
        String query="SELECT * FROM QuyDinh WHERE maQuyDinh=?";
        QuyDinh qd=new QuyDinh();
        try(Connection conn=kn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){ 
            stmt.setInt(1, maqd);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){ 
                qd.setNoiDung(rs.getString("noiDung"));
                qd.setSoTien(rs.getDouble("soTien"));
                qd.setMaQuyDinh(maqd);
            }
        } catch (SQLException ex) {
            System.out.println("Khong the lay quy dinh ");
        }
        return qd;
    }
}
