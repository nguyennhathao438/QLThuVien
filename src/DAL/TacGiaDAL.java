package DAL;

import BLL.DSTacGiaBLL;
import Model.TacGia;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TacGiaDAL {
    static KetNoiCSDL kn =new KetNoiCSDL();
    public ArrayList<TacGia> layDSTacGia(){ 
        ArrayList<TacGia> dstg = new ArrayList();
        String query="SELECT * FROM TacGia";
        try(Connection conn =kn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
                ){
            TacGia tg;
            while(rs.next()){
                tg=new TacGia();
            tg.setMaTacGia(rs.getString("maTacGia"));
            tg.setTenTacGia(rs.getString("tenTacGia"));
            tg.setSoDienThoai(rs.getString("soDienThoai"));
            tg.setNamSinh(rs.getInt("namSinh"));
            tg.setTrangThai(rs.getInt("trangThai"));
            dstg.add(tg);
            }
            
        } catch (SQLServerException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dstg;
    }
    public int themTacGia(TacGia tg){ 
        int kq = -1;
        String query ="INSERT INTO TACGIA(maTacGia,tenTacGia,namSinh,soDienThoai,trangThai) VALUES(?,?,?,?,1) ";
        try(Connection conn =kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){ 
            stmt.setString(1,tg.getMaTacGia());
            stmt.setString(2, tg.getTenTacGia());
            stmt.setInt(3,tg.getNamSinh());
            stmt.setString(4,tg.getSoDienThoai());
            kq= stmt.executeUpdate();            
        } catch (SQLServerException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq ;
    }
    public int xoaTacGia(String matg){ 
        int ketqua = -1;
       String query ="UPDATE TACGIA SET trangThai=0 WHERE maTacGia = ? ";
        try(Connection conn =kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){ 
            stmt.setString(1, matg);
           ketqua= stmt.executeUpdate();            
        } catch (SQLServerException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ketqua;
    }
    public int suaTacGia(TacGia tg){ 
        int ketqua = -1;
       String query ="UPDATE TACGIA SET tenTacGia=?,namSinh=?,soDienThoai=? WHERE maTacGia = ? ";
        try(Connection conn =kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){ 
            stmt.setString(1, tg.getTenTacGia());
            stmt.setInt(2, tg.getNamSinh());
            stmt.setString(3, tg.getSoDienThoai());
            stmt.setString(4, tg.getMaTacGia());
           ketqua = stmt.executeUpdate();            
        } catch (SQLServerException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ketqua ;
    }
    public TacGia layTacGia(String matg){ 
        String query="SELECT * FROM TacGia WHERE maTacGia =? ";
        TacGia tg=new TacGia();
        try(Connection conn =kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ){
            stmt.setString(1,matg);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
            tg.setMaTacGia(rs.getString("maTacGia"));
            tg.setTenTacGia(rs.getString("tenTacGia"));
            tg.setSoDienThoai(rs.getString("soDienThoai"));
            tg.setNamSinh(rs.getInt("namSinh"));
            tg.setTrangThai(rs.getInt("trangThai"));
  
            }
            
        } catch (SQLServerException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tg;
    }
}
