/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.LoaiDocGia;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author NGOC TUYEN
 */
public class LoaiDGDAL {
    private KetNoiCSDL kn = new KetNoiCSDL();
    
    public ArrayList<LoaiDocGia> getAllLoaiDG(){
        ArrayList<LoaiDocGia> dsldg = new ArrayList<>();
        String query = "select * from LOAIDOCGIA";
        
        try(Connection conn = kn.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
            
            while(rs.next()){
                LoaiDocGia loai = new LoaiDocGia();
                loai.setMaLoaiDG(rs.getString("maLoaiDG"));
                loai.setTenLoaiDG(rs.getString("tenLoaiDG"));
                loai.setGioiHanMuon(rs.getInt("gioiHanMuon"));
                loai.setMoTa(rs.getString("moTa"));
                loai.setTrangThai(rs.getInt("trangThai"));
                
                dsldg.add(loai);
            }    
        }catch(SQLException ex){
            System.out.println("Lỗi truy vấn dữ liệu: "+ex.getMessage());
            ex.printStackTrace();
        }
        return dsldg;
    }
    
    public int ThemLoaiDG(LoaiDocGia loai){
        String query = "Insert into LOAIDOCGIA(maLoaiDG,tenLoaiDG,gioiHanMuon,moTa,trangThai) values (?,?,?,?,1)";
        
        try(Connection conn = kn.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1,loai.getMaLoaiDG());
            pstmt.setString(2,loai.getTenLoaiDG());
            pstmt.setInt(3,loai.getGioiHanMuon());
            pstmt.setString(4,loai.getMaLoaiDG());
            
            return pstmt.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Lỗi thêm loại độc giả: "+ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }
    
    
}
