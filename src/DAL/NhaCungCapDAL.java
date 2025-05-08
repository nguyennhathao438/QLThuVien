/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.NhaCungCap;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Nghia0605
 */
public class NhaCungCapDAL {
        KetNoiCSDL kn = new KetNoiCSDL();
        
        public ArrayList<NhaCungCap> selectAll(){
            String query = "SELECT * FROM NHACUNGCAP WHERE trangThai = 1";
            ArrayList<NhaCungCap> dsNCC =  new ArrayList<>();
            try (Connection conn = kn.getConnection();
                PreparedStatement prs = conn.prepareStatement(query);
                ResultSet rs = prs.executeQuery() ){
                
                while(rs.next()){
                    String maNCC = rs.getString("maNCC");
                    String tenNCC = rs.getString("tenNCC");
                    String phoneNCC = rs.getString("soDienThoai");
                    int trangThai = rs.getInt("trangThai");                    
                    dsNCC.add(new NhaCungCap(maNCC, tenNCC, phoneNCC,trangThai));                                       
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Khong the lay du lieu nha cung cap");
            }
            return dsNCC;
        }
        
        public boolean insert(NhaCungCap ncc){
            String query = "INSERT INTO NHACUNGCAP(maNCC,tenNCC,soDienThoai,trangThai) VALUES(?,?,?,1)";
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query)){
                prs.setString(1, ncc.getMaNCC());
                prs.setString(2, ncc.getTenNCC());
                prs.setString(3, ncc.getSoDienThoai());
//                prs.setInt(3, ncc.getTrangThai());
                return prs.executeUpdate() > 0;
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Khong the chen du lieu");
            }
            return false;
        }
        
        public boolean update(NhaCungCap ncc){
            String query = "UPDATE NHACUNGCAP SET tenNCC = ?, soDienThoai = ? WHERE maNCC = ?";
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query)){
                
                prs.setString(1, ncc.getTenNCC());
                prs.setString(2, ncc.getSoDienThoai());                
                prs.setString(3, ncc.getMaNCC());
                
                return prs.executeUpdate() > 0;
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Khong the cap nhat du lieu");
            }
            return false;
        }
        
//        public boolean delete(int id){
//            String query = "DELETE FROM NHACUNGCAP WHERE maNCC = ?";
//            try (Connection conn = kn.getConnection();
//                 PreparedStatement prs = conn.prepareStatement(query)){
//                
//                prs.setInt(1, id);
//                return prs.executeUpdate() > 0;
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Khong the xoa du lieu");
//            }
//            return false;
//        }
        public boolean delete(String id){
            String query = "UPDATE NHACUNGCAP SET trangThai = ? WHERE maNCC = ?";
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query)){
                
                prs.setInt(1, 0);
                prs.setString(2, id);
                
                return prs.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Khong the xoa!");
            }
            return false;
        }
        
        public int getAutoIncremnt(){
            String query = "SELECT IDENT_CURRENT('NHACUNGCAP') + 1 AS NextID";
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query);
                 ResultSet rs = prs.executeQuery()){                
                if(rs.next()){
                    return rs.getInt("NextID");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Khong the lay ma tiep theo");
            }
            return -1;
        }
        
        public ArrayList<String> getAllMaNCC(){
            ArrayList<String> lstMaNCC = new ArrayList<>();
            String query = "SELECT maNCC FROM NHACUNGCAP";
            try (Connection conn = kn.getConnection();
                 PreparedStatement prs = conn.prepareStatement(query);
                 ResultSet rs = prs.executeQuery()){
                while(rs.next()){
                    lstMaNCC.add(rs.getString("maNCC"));
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lstMaNCC;
        }
}
