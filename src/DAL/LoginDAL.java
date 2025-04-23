/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoginDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    public String ktraDangNhap(String tk,String mk){ 
        String tenThuThu ="";
        String query ="SELECT tenThuThu FROM THUTHU WHERE taiKhoan=? AND matKhau= ?";
        try(Connection conn = kn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){ 
            stmt.setString(1,tk);
            stmt.setString(2,mk);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){ 
                tenThuThu = rs.getString(tenThuThu);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(LoginDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tenThuThu;
    }
}
