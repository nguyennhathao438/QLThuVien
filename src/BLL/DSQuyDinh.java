/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.QuyDinhDAL;
import MODEL.QuyDinh;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DSQuyDinh {
    QuyDinhDAL qddal=new QuyDinhDAL();
    ArrayList<QuyDinh> dsqd=new ArrayList();
    public ArrayList<QuyDinh> layAllQuyDinh(){ 
        dsqd = QuyDinhDAL.layDSQuyDinh();
        return dsqd;
    }
    public void showMess(String s){ 
        JOptionPane.showMessageDialog(null, s);
    }
    
    public void themQD(QuyDinh qd){ 
        dsqd= layAllQuyDinh();
      if(qd.getMaQuyDinh().isEmpty()){ 
            showMess("Mã quy định không được để trống ");
            return ;
        }
        for(QuyDinh a:dsqd){ 
            if(a.getMaQuyDinh().equals(qd.getMaQuyDinh())){ 
                showMess("Mã quy định đã tồn tại ");
                return;
            }
        }
        if(qd.getNoiDung().isEmpty()){ 
            showMess("Nội dung không được để trống ");
            return ;
        }
        if(qd.getSoTien()<0){ 
            showMess("Số tiền phải lớn hơn 0");
            return ; 
        }
        if(QuyDinhDAL.themQuyDinh(qd)> 0){ 
            showMess("Thêm quy định thành công ");
            return ;
        }
        showMess("Thêm quy định thất bại ");
    }
    public void xoaQD(String maqd){ 
        if(QuyDinhDAL.xoaQuyDinh(maqd)>0){ 
            showMess("Xoá quy định thành công ");
            return ;
        }
        showMess("Xoá qưy định thất bại ");
    }
    public void suaQD(QuyDinh qd){ 
        dsqd= layAllQuyDinh();
      if(qd.getMaQuyDinh().isEmpty()){ 
            showMess("Mã quy định không được để trống ");
            return ;
        }
      boolean kt=false;
        for(QuyDinh a:dsqd){ 
            if(a.getMaQuyDinh().equals(qd.getMaQuyDinh())){ 
                kt=true;
                break;
            }
        }
        if(!kt){ 
            showMess("Mã quy định không tồn tại ");
            return ;
        }
        if(qd.getNoiDung().isEmpty()){ 
            showMess("Nội dung không được để trống ");
            return ;
        }
        if(qd.getSoTien()<0){ 
            showMess("Số tiền phải lớn hơn 0");
            return ; 
        }
        if(QuyDinhDAL.suaQuyDinh(qd)> 0){ 
            showMess("Sửa quy định thành công ");
            return ;
        }
        showMess("Sửa quy định thất bại ");
    }
    public QuyDinh getQuyDinh(String maqd){ 
        return QuyDinhDAL.layQuyDinh(maqd);
    }
}
