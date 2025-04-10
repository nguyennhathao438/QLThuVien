
package DAL;

import BLL.DSQuyDinh;
import MODEL.QuyDinh;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuyDinhDAL {
    static KetNoiCSDL kn=new KetNoiCSDL();
    public ArrayList<QuyDinh> layDSQuyDinh(){
        
        String query ="SELECT * FROM QuyDinh";
        try (Connection conn = kn.getConnection();
            Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)){
            ArrayList<QuyDinh> dsqd = new ArrayList();
        while(rs.next()){ 
            String maqd=rs.getString("maQuyDinh");
            String noidung = rs.getString("noiDung");
            double soTien =rs.getDouble("soTien");
            int trangThai =rs.getInt("trangThai");
            dsqd.add(new QuyDinh(maqd,noidung,soTien,trangThai));
        }
            return dsqd;   
        } catch (SQLServerException ex) {
            System.out.println("Lay du lieu that bai");
        } catch (SQLException ex) {     
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int themQuyDinh(QuyDinh qd){ 
        int ketqua =-1;
        String query = "INSERT INTO QuyDinh(maQuyDinh,noiDung,soTien,trangThai) VALUES(?,?,?,1) ";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt =conn.prepareStatement(query);
            ){ 
            stmt.setString(1,qd.getMaQuyDinh());
            stmt.setString(2,qd.getNoiDung());
            stmt.setDouble(3, qd.getSoTien());
            ketqua = stmt.executeUpdate();
        }catch (SQLServerException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return ketqua ;
    }
    public int xoaQuyDinh(String maqd){ 
        int ketqua =-1;
        String query ="UPDATE QuyDinh SET trangThai=0 WHERE maQuyDinh=?";
        try(Connection conn=kn.getConnection();
                PreparedStatement stmt= conn.prepareStatement(query)){ 
            stmt.setString(1,maqd);
            ketqua = stmt.executeUpdate();          
        } catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }
    public int suaQuyDinh(QuyDinh qd){ 
        int ketqua =-1;
        String query= "UPDATE QuyDinh SET noiDung=? , soTien=? WHERE maQuyDinh=?";
        try(Connection conn=kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){  
            stmt.setString(1, qd.getNoiDung());
            stmt.setDouble(2, qd.getSoTien());
            stmt.setString(3, qd.getMaQuyDinh());
            ketqua = stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua ;
    }
    
    public QuyDinh layQuyDinh(String maqd){ 
        String query="SELECT * FROM QuyDinh WHERE maQuyDinh=?";
        QuyDinh qd=new QuyDinh();
        try(Connection conn=kn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){ 
            stmt.setString(1, maqd);
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
