/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.DocGia;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author NGOC TUYEN
 */
public class DocGiaDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    
    public ArrayList<DocGia> getAllDocGia(){
        ArrayList<DocGia> dsdg = new ArrayList<>();
        String query = "select * from DOCGIA";
        
        try(Connection conn = kn.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
            
            while(rs.next()){
                DocGia dg = new DocGia();
                dg.setMaDocGia(rs.getString("maDocGia"));
                dg.setTenDocGia(rs.getString("tenDocGia"));
                dg.setSoDienThoai(rs.getString("soDienThoai"));
                dg.setDiaChi(rs.getString("diaChi"));
                dg.setMaLoaiDG(rs.getString("maLoaiDG"));
                dg.setTrangThai(rs.getInt("trangThai"));
                
                dsdg.add(dg);
            }
            
        }catch(SQLException ex){
            System.out.println("Lỗi truy vấn dữ liệu"+ex.getMessage());
            ex.printStackTrace();    
        }
        return dsdg;
    }
    
    public int ThemDG(DocGia dg){
        String query = "Insert into DOCGIA(maDocGia, tenDocGia, soDienThoai, diaChi, maLoaiDG, trangThai) values (?,?,?,?,?,1)";
        
        try(Connection conn = kn.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1,dg.getMaDocGia());
            pstmt.setString(2, dg.getTenDocGia());
            pstmt.setString(3, dg.getSoDienThoai());
            pstmt.setString(4, dg.getDiaChi());
            pstmt.setString(5, dg.getMaLoaiDG());
            
            return pstmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Lỗi thêm độc giả"+ex.getMessage());
            ex.printStackTrace();
            return -1;
        }   
    }
    
    public int SuaDG(DocGia dg){
        String query = "Update DOCGIA set tenDocGia = ?, soDienThoai = ?, diaChi = ?, maLoaiDG = ? where  maDocGia = ?";
        try(Connection conn = kn.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, dg.getTenDocGia());
            pstmt.setString(2, dg.getSoDienThoai());
            pstmt.setString(3, dg.getDiaChi());
            pstmt.setString(4, dg.getMaLoaiDG());
            pstmt.setString(5,dg.getMaDocGia());
            
            return pstmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Lỗi sửa độc giả"+ex.getMessage());
            ex.printStackTrace();
            return -1;
        }   
    }
    
    public int XoaDG(String maDG){
        String query = "update DOCGIA set trangThai = 0 where maLoaiDG = ?";
        
        try(Connection conn = kn.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, maDG);
            
            return pstmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Lỗi xóa độc giả" +ex.getMessage());
            ex.printStackTrace();
            return -1;    
        }
    }
    
   
}
