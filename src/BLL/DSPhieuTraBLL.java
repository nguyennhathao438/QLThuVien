
package BLL;

import DAL.PhieuTraDAL;
import MODEL.CTPhat;
import MODEL.PhieuTra;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DSPhieuTraBLL {
    PhieuTraDAL kn=new PhieuTraDAL();
    ArrayList<PhieuTra> dspt =new ArrayList();
    public ArrayList<PhieuTra> getAllPhieuTra(){ 
        dspt = kn.layDSPTra();
        return dspt;
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    public void taoPhieuPhat(String maPhieuTra,String maPhuThu,String[] dsmqd,double tienPhat){ 
        try {
            int kq = PhieuTraDAL.taoPhieuPhat(maPhieuTra, maPhuThu,dsmqd, tienPhat);
            if(kq >=0){
                showMess("Tạo phiếu phạt thành công");
            }else{
                showMess("Tạo phiếu phạt thất bại ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuTraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public CTPhat getChiTietPhat(String maPhieuTra){ 
        return PhieuTraDAL.getCTPhat(maPhieuTra);
    }
}
