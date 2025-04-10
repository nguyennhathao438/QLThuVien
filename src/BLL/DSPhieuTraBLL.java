
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
    PhieuTraDAL ptdal=new PhieuTraDAL();
    static ArrayList<PhieuTra> dspt =new ArrayList();
    public DSPhieuTraBLL(){ 
        this.dspt = ptdal.layDSPTra();
    }
    public static ArrayList<PhieuTra> getAllPhieuTra(){ 
        return dspt;
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    public void taoPhieuPhat(String maPhieuTra,String maPhuThu,String[] dsmqd,double tienPhat){ 
        try {
            int kq = ptdal.taoPhieuPhat(maPhieuTra, maPhuThu,dsmqd, tienPhat);
            if(kq >=0){
                showMess("Tạo phiếu phạt thành công");
                int index = getIndexbyMaPT(maPhieuTra);
                dspt.get(index).setMaPhuThu(maPhuThu);
            }else{
                showMess("Tạo phiếu phạt thất bại ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DSPhieuTraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public CTPhat getChiTietPhat(String maPhieuTra){ 
        return ptdal.getCTPhat(maPhieuTra);
    }
    public int getIndexbyMaPT(String maPT){
        for(int i=0;i<dspt.size();i++){ 
            if(dspt.get(i).getMaPhieuTra().equals(maPT)){ 
                return i;
            }
        }
        return -1 ;
    }
    public void xoaPhieuTra(String maPT){ 
        if(ptdal.xoaPhieuTra(maPT)>0){ 
            int index =getIndexbyMaPT(maPT);
            dspt.get(index).setTrangThai(0);
            showMess("Xoá phiếu trả thành công ");
        }
    }
}
