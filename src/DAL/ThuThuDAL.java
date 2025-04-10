/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.ThuThu;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Nghia0605
 */
public class ThuThuDAL {
//     maThuThu VARCHAR(10) PRIMARY KEY,
//    tenThuThu NVARCHAR(255),
//    taiKhoan NVARCHAR(50),
//    matKhau NVARCHAR(50),
//    soDienThoai NVARCHAR(15),
//    trangThai INT

    KetNoiCSDL kn = new KetNoiCSDL();
    ArrayList<ThuThu> dsTT = new ArrayList<>();
    public ArrayList<ThuThu> selectAll(){
        String query = "SELECT * FROM THUTHU WHERE trangThai = 1";
        try (Connection conn = kn.getConnection();
             PreparedStatement prs = conn.prepareStatement(query);
             ResultSet rs = prs.executeQuery()) {
            
            while(rs.next()){
                String maThuThu = rs.getString("maThuThu");
                String ten = rs.getString("tenThuThu");
                String tk = rs.getString("taikhoan");
                String mk = rs.getString("matkhau");
                String sodienthoai = rs.getString("soDienThoai");
                String trangThai = rs.getString("trangThai");                
                
                dsTT.add(new ThuThu(maThuThu, ten, tk, mk, sodienthoai));
            }
            
        } catch (Exception e) {
            System.err.println("Loi lay du lieu thu thu" + e.getMessage());
            e.printStackTrace();
        }
        return dsTT;
    }
}
