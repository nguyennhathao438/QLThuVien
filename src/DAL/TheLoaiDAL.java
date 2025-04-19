/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.TheLoai;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author tung7
 */
public class TheLoaiDAL {
    KetNoiCSDL kn = new KetNoiCSDL();
    public ArrayList<TheLoai> layDSTheLoai()
    {
        ArrayList<TheLoai> dsTL = new ArrayList<>();
        String query = "select * from TheLoai";
        try(Connection conn = kn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query))
        {
            while(rs.next())
            {
                TheLoai tl = new TheLoai();
                tl.setMaTheLoai(rs.getString("maTheLoai"));
                tl.setTheLoai(rs.getString("theLoai"));
                dsTL.add(tl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTL;
    }
    
//    public int themTheLoai(TheLoai tl)
//    {
//        int kq = -1;
//    }
}
